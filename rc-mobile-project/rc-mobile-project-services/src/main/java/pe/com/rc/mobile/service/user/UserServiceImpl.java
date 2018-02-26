package pe.com.rc.mobile.service.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.dao.ClanDAO;
import pe.com.rc.mobile.dao.GameDAO;
import pe.com.rc.mobile.dao.MemberTypeDAO;
import pe.com.rc.mobile.dao.SolicitudeDAO;
import pe.com.rc.mobile.dao.SolicitudeTypeDAO;
import pe.com.rc.mobile.dao.StateDAO;
import pe.com.rc.mobile.dao.UserDAO;
import pe.com.rc.mobile.model.ClanMembers;
import pe.com.rc.mobile.model.Game;
import pe.com.rc.mobile.model.MemberType;
import pe.com.rc.mobile.model.Solicitude;
import pe.com.rc.mobile.model.SolicitudeType;
import pe.com.rc.mobile.model.State;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.Clan;
import pe.com.rc.mobile.model.clan.UserReqRes.AcceptClanRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.InvitationsToTeamRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.InvitationsToTeamResponse;
import pe.com.rc.mobile.service.solicitude.SolicitudeService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private StateDAO stateDAO;
	@Autowired
	private ClanDAO clanDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private SolicitudeDAO solicitudeDAO;
	@Autowired
	private MemberTypeDAO memberTypeDAO;
	@Autowired
	private SolicitudeService solicitudeService;
	@Autowired
	private GameDAO gameDAO;
	@Autowired
	private SolicitudeTypeDAO solicitudeTypeDAO;

	public void processClanRequest(AcceptClanRequest request) throws ServiceException {

		logger.info("Process Clan Request.");

		try {
			// User
			User user = userDAO.find(new User(request.getUserId()));
			// User response 5 ACEPTAR - 6 RECHAZAR
			State state = stateDAO.find(new State(request.getState()));
			// Clan
			Clan clan = clanDAO.find(new Clan(request.getClanId()));
			// Solicitud
			Solicitude solicitud = solicitudeService.findSolicitud(new Solicitude(request.getSolicitudeId()));

			// ACTUALIZAR SOLICITUD
			solicitud.setState(state);
			solicitud.setUpdateDate(new Date());
			solicitudeService.update(solicitud);

			// VALIDAR SI ACEPTA O RECHAZA SOLICITUD
			if (state.getId().equals(5L)) {
				// ACEPTA
				// INSERT CLAN MEMBER
				MemberType memberType = getMemberType(clan);
				// VALIDAR CANTIDAD DE USUARIOS Y ASIGNACION DE ROL X DEFAULT
				// SOLO PUEDEN SER 10 USUARIOS
				// 1 TL, 4 MEMBERS, 5 SUPLENTES
				if (memberType != null) {
					ClanMembers members = new ClanMembers();
					members.setMemberType(memberType);
					members.setCreateDate(new Date());
					members.setClan(clan);
					members.setUser(user);
					members.setActive(1);
					clanDAO.insertMember(members);
				}
			}
		} catch (DaoException e) {
			logger.error("DaoException in Process Clan Request.", e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception in Process Clan Request.", e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	private MemberType getMemberType(Clan clan) throws ServiceException {
		try {
			Integer cantMembers = clan.getClanMembers().size();
			MemberType memberType = null;
			if (cantMembers < 5) {
				// MIEMBRO
				memberType = memberTypeDAO.find(new MemberType(2L));
			} else if (cantMembers < 10) {
				// SUPLENTE
				memberType = memberTypeDAO.find(new MemberType(3L));
			}
			return memberType;
		} catch (DaoException e) {
			logger.error("DaoException in get MemberType.", e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception in get MemberType.", e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<InvitationsToTeamResponse> getInvitationsTeams(InvitationsToTeamRequest request)
			throws ServiceException {
		try {
			User user = userDAO.find(new User(request.getUserId()));
			Game game = gameDAO.find(new Game(request.getGameId()));
			State state = stateDAO.find(new State(1L)); // PENDIENTES DE
														// APROBACION

			// RECLUTAMIENTOS O POSTULACIONES
			SolicitudeType type = solicitudeTypeDAO.find(new SolicitudeType(request.getSolicitudeType()));

			List<Solicitude> solicitudes = solicitudeDAO.getSolicitudesByUserAndGameAndStateAndType(user, game, state,
					type);
			if (solicitudes != null) {
				return getResponse(solicitudes);
			}
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return null;
	}

	private List<InvitationsToTeamResponse> getResponse(List<Solicitude> solicitudes) {
		List<InvitationsToTeamResponse> resp = new ArrayList();
		for (Solicitude s : solicitudes) {
			InvitationsToTeamResponse e = new InvitationsToTeamResponse();
			e.setClanName(s.getClan().getName());
			e.setGameName(s.getGame().getName());
			e.setSince(getTime(s.getCreateDate()));
			e.setSolicitudeId(s.getId());
			e.setState(s.getState().getDescription());
			e.setTipoSolicitud(s.getSolicitudeType().getDescription());
			resp.add(e);
		}
		return resp;
	}

	private String getTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
}
