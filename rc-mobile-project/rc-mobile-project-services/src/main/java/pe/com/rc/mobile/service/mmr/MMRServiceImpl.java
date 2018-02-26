package pe.com.rc.mobile.service.mmr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.dao.ClanDAO;
import pe.com.rc.mobile.dao.MatchMakingDAO;
import pe.com.rc.mobile.dao.StateDAO;
import pe.com.rc.mobile.dao.UserDAO;
import pe.com.rc.mobile.model.MatchMaking;
import pe.com.rc.mobile.model.State;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.Clan;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRBuildRequest;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRCancelRequest;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRSearchRequest;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRSearchResponse;
import pe.com.rc.mobile.model.clan.MMRSearch.PendingMMRByTeam;

@Service
public class MMRServiceImpl implements MMRService {

	private static final Logger logger = LoggerFactory.getLogger(MMRServiceImpl.class);

	@Autowired
	UserDAO userDAO;
	@Autowired
	ClanDAO clanDAO;
	@Autowired
	StateDAO stateDAO;
	@Autowired
	MatchMakingDAO matchMakingDAO;

	public void buildMMR(MMRBuildRequest request) throws ServiceException {
		logger.info("Build MMR " + request.getClanAId());
		try {
			Clan clanWhoCreates = clanDAO.find(new Clan(request.getClanAId()));
			User userWhoCreates = userDAO.find(new User(request.getUserCreateId()));

			if (clanWhoCreates != null && userWhoCreates != null
					&& isAvailable(clanWhoCreates, getDateFromString(request.getTempDate()), request.getHours())) {
				matchMakingDAO.save(prepareMatchMaking(clanWhoCreates, userWhoCreates, request));
			}
		} catch (DaoException e) {
			logger.error("DaoException in Build MMR " + request.getClanAId(), e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception in Build MMR " + request.getClanAId(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private boolean isAvailable(Clan clan, Date inicioMMR, Integer hours) {
		logger.info("See if Team is avaliable for that Temp Date.");
		List<MatchMaking> activeMMRS = matchMakingDAO.getMMRsByClan(clan);
		Date finMMR = getGameTime(inicioMMR, hours);
		for (MatchMaking mmr : activeMMRS) {
			if (!(inicioMMR.before(mmr.getTempDate()) || inicioMMR.after(mmr.getTempDateEnd()))
					|| !(finMMR.before(mmr.getTempDate()) || finMMR.after(mmr.getTempDateEnd()))) {
				if (inicioMMR.getTime() == mmr.getTempDateEnd().getTime()) {
					return true;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	private MatchMaking prepareMatchMaking(Clan clanWhoCreates, User userWhoCreates, MMRBuildRequest request)
			throws ServiceException {
		logger.info("Prepare MMR for DB.");
		try {
			MatchMaking mmr = new MatchMaking();
			mmr.setTeamA(clanWhoCreates);
			mmr.setUserCreate(userWhoCreates);
			mmr.setTempDate(getDateFromString(request.getTempDate()));
			mmr.setTempDateEnd(getGameTime(getDateFromString(request.getTempDate()), request.getHours()));
			mmr.setHours(request.getHours());
			mmr.setIpServ(request.getIpServ());
			mmr.setDescription(request.getDescription());
			mmr.setPhone(request.getPhone());
			mmr.setMail(request.getMail());
			mmr.setCreateDate(new Date());
			mmr.setActive(1);
			// STATE BY DEFAULT 1L - PENDIENTE
			mmr.setState(stateDAO.find(new State(1L)));
			return mmr;
		} catch (DaoException e) {
			logger.error("DaoException in prepareMatchMaking.", e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception in prepareMatchMaking.", e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private Date getDateFromString(String strDate) {
		// It must have the next format "2015-07-16 17:07:21";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			logger.error("Error trying to parse TEMPDATE for MMRBuild.");
		}
		return null;
	}

	private Date getGameTime(Date date, Integer hours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, hours);
		return cal.getTime();
	}

	public void acceptMMR(MMRBuildRequest request) throws ServiceException {
		try {
			// GET VALUES FROM MMR
			MatchMaking mmr = matchMakingDAO.find(new MatchMaking(request.getMmrId()));
			Clan clanWhoAccepts = clanDAO.find(new Clan(request.getClanBId()));
			User userWhoAccepts = userDAO.find(new User(request.getUserAcceptId()));
			if (isAvailable(clanWhoAccepts, mmr.getTempDate(), mmr.getHours())) {
				matchMakingDAO.update(prepareAcceptMatchMaking(clanWhoAccepts, userWhoAccepts, mmr));
			}
		} catch (DaoException e) {
			logger.error("DaoException in acceptMMR " + request.getMmrId(), e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception in acceptMMR " + request.getMmrId(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private MatchMaking prepareAcceptMatchMaking(Clan clanWhoAccepts, User userWhoAccepts, MatchMaking request)
			throws ServiceException {
		try {
			MatchMaking mmr = new MatchMaking();
			mmr.setTeamB(clanWhoAccepts);
			mmr.setUserAccept(userWhoAccepts);
			mmr.setApprovedDate(new Date());
			mmr.setUpdateDate(new Date());
			// IF USER ACCEPTS STATE IS FINALIZADO
			mmr.setState(stateDAO.find(new State(2L)));
			return mmr;
		} catch (DaoException e) {
			logger.error("DaoException in prepareAcceptMatchMaking " + clanWhoAccepts + "/" + request.getId(), e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception in prepareAcceptMatchMaking " + clanWhoAccepts + "/" + request.getId(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<MMRSearchResponse> listMMR(MMRSearchRequest request) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public void cancelMMR(MMRCancelRequest request) throws ServiceException {
		try {
			// SOLO EL USUARIO Q CREO EL MMR PUEDE CANCELAR
			User user = userDAO.find(new User(request.getUserId()));
			MatchMaking mmr = matchMakingDAO.find(new MatchMaking(request.getMmrId()));
			if (mmr.getUserCreate().getId().equals(user.getId())) {
				State state = stateDAO.find(new State(4L)); // CANCELADO
				mmr.setState(state);
				mmr.setUpdateDate(new Date());
				matchMakingDAO.updateMMRState(mmr, state);
			}
		} catch (DaoException e) {
			logger.error("DaoException in cancelMMR " + request.getMmrId() + " by User " + request.getUserId(), e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception in cancelMMR " + request.getMmrId() + " by User " + request.getUserId(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<MMRSearchResponse> listPendingMMR(PendingMMRByTeam request) throws ServiceException {
		try {
			Clan clan = clanDAO.find(new Clan(request.getClanId()));
			List<MatchMaking> pendingMMRSToApprove = matchMakingDAO.getMMRsByClan(clan);

			List<MMRSearchResponse> resp = new ArrayList();
			for (MatchMaking mmr : pendingMMRSToApprove) {
				MMRSearchResponse r = new MMRSearchResponse();
				r.setClanNameWhoCreates(mmr.getTeamA().getName());
				r.setDescription(mmr.getDescription());
				r.setGame(mmr.getTeamA().getGame().getName());
				r.setIpServ(mmr.getIpServ());
				r.setMail(mmr.getMail());
				r.setMmrId(mmr.getId());
				r.setPhone(mmr.getPhone());
				r.setTempDate(getTime(mmr.getTempDate()));
				r.setUserNameWhoCreate(mmr.getUserCreate().getName());
				resp.add(r);
			}

			return resp;
		} catch (DaoException e) {
			logger.error("DaoException in listPendingMMR " + request.getClanId(), e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception in listPendingMMR " + request.getClanId(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private String getTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
}
