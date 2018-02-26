package pe.com.rc.mobile.service.clan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.dao.ClanCommentsDAO;
import pe.com.rc.mobile.dao.ClanDAO;
import pe.com.rc.mobile.dao.GameDAO;
import pe.com.rc.mobile.dao.MemberTypeDAO;
import pe.com.rc.mobile.dao.SolicitudeDAO;
import pe.com.rc.mobile.dao.SolicitudeTypeDAO;
import pe.com.rc.mobile.dao.StateDAO;
import pe.com.rc.mobile.dao.UserDAO;
import pe.com.rc.mobile.model.ClanComments;
import pe.com.rc.mobile.model.ClanMembers;
import pe.com.rc.mobile.model.Game;
import pe.com.rc.mobile.model.MemberType;
import pe.com.rc.mobile.model.Solicitude;
import pe.com.rc.mobile.model.SolicitudeType;
import pe.com.rc.mobile.model.State;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.Clan;
import pe.com.rc.mobile.model.clan.ListClanResponse;
import pe.com.rc.mobile.model.clan.TeamSearch.AcceptMemberRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.AssignRoleRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.CandidatesRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.CandidatesResponse;
import pe.com.rc.mobile.model.clan.TeamSearch.DropMemberRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.PostularRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.RankTeamRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.RecruitRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.TeamBuildRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.TeamDeleteRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.TeamMembers;
import pe.com.rc.mobile.model.clan.TeamSearch.TeamSearchRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.TeamSearchResponse;

@Service
public class ClanServiceImpl implements ClanService {

	@Autowired
	private ClanDAO clanDAO;
	@Autowired
	private GameDAO gameDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private MemberTypeDAO memberTypeDAO;
	@Autowired
	private SolicitudeDAO solicitudeDAO;
	@Autowired
	private SolicitudeTypeDAO solicitudeTypeDAO;
	@Autowired
	private StateDAO stateDAO;
	@Autowired
	private ClanCommentsDAO clanCommentsDAO;

	private static final Logger logger = LoggerFactory.getLogger(ClanServiceImpl.class);

	public List<ListClanResponse> listClanes() throws ServiceException {
		logger.info("List Clanes.");
		List<ListClanResponse> lista = new ArrayList<ListClanResponse>();
		try {
			List<Clan> clanes = clanDAO.all();
			for (Clan clan : clanes) {
				ListClanResponse c = new ListClanResponse();
				c.setName(clan.getName());
				c.setDescription(clan.getDecription());
				c.setStarsNum(clan.getStarsNumber());
				c.setGame(clan.getGame().getName());
				lista.add(c);
			}
		} catch (DaoException e) {
			logger.error("DaoException en Listar Clanes.", e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception en Listar Clanes.", e);
			throw new ServiceException("Error in " + e.getMessage());
		}
		return lista;
	}

	public TeamSearchResponse getTeam(TeamSearchRequest request) throws ServiceException {
		logger.info("Listar Detalles del Team.");
		TeamSearchResponse response = null;

		try {
			Game game = gameDAO.find(new Game(new Long(request.getGameId())));
			Clan clan = null;

			if (Character.isDigit(request.getCriteria().charAt(0))) {
				// POR ID
				clan = clanDAO.find(new Clan(new Long(request.getCriteria())));
			} else {
				// POR NAME
				clan = clanDAO.getClanByNameAndGame(request.getCriteria(), game.getId());
			}

			if (clan != null) {
				response = new TeamSearchResponse();
				response.setClanId(clan.getId());
				response.setClanName(clan.getName());
				response.setGameId(clan.getGame().getId());
				response.setGameName(clan.getGame().getName());

				List<TeamMembers> lista = new ArrayList();
				for (ClanMembers member : clan.getClanMembers()) {
					TeamMembers teamMember = new TeamMembers();
					teamMember.setName(member.getUser().getName());
					teamMember.setType(member.getMemberType().getDescription());
					teamMember.setUserId(member.getUser().getId());
					lista.add(teamMember);
				}
				response.setMembers(lista);
			}
		} catch (DaoException e) {
			logger.error("DaoException en Listar Detalles del Clan.", e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception en Listar Detalles del Clan.", e);
			throw new ServiceException("Error in " + e.getMessage());
		}

		return response;
	}

	public void buildTeam(TeamBuildRequest request) throws ServiceException {
		logger.info("Build Team.");
		try {
			Game game = gameDAO.find(new Game(request.getGameId()));
			User user = userDAO.find(new User(request.getUserId()));
			MemberType memberType = memberTypeDAO.find(new MemberType(1L));

			Clan clan = new Clan();
			clan.setName(request.getNombre());
			clan.setGame(game);
			clan.setDecription(request.getDescripcion());
			clan.setStarsNumber(0);
			clan.setCreateDate(new Date());
			clan.setActive(1);

			clanDAO.save(clan);

			ClanMembers members = new ClanMembers();
			members.setMemberType(memberType);
			members.setCreateDate(new Date());
			members.setClan(clan);
			members.setUser(user);
			members.setActive(1);
			clan.getClanMembers().add(members);

			clanDAO.insertMember(members);
		} catch (DaoException e) {
			logger.error("DaoException en Build Team.", e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception en Build Team.", e);
			throw new ServiceException("Error in " + e.getMessage());
		}
	}

	public void deleteTeam(TeamDeleteRequest request) throws ServiceException {
		logger.info("Delete Team.");
		try {
			// Validate if the user is TEAM LEADER
			Clan clan = clanDAO.find(new Clan(request.getClanId()));
			if (isUserTL(clan, request.getUserId())) {
				clanDAO.delete(clanDAO.find(new Clan(request.getClanId())));
			}
		} catch (DaoException e) {
			logger.error("DaoException en Delete Team.", e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception en Delete Team.", e);
			throw new ServiceException("Error in " + e.getMessage());
		}
	}

	private boolean isUserTL(Clan clan, Long userId) {
		logger.info("Validate if User is TL.");
		for (ClanMembers member : clan.getClanMembers()) {
			if (member.getUser().getId().equals(userId))
				return true;

		}
		return false;
	}

	public void recruitPlayer(RecruitRequest request) throws ServiceException {
		logger.info("Recruit Player. Clan " + request.getClanId() + "and User " + request.getUserId());
		try {
			Clan clan = clanDAO.find(new Clan(request.getClanId()));
			// VALIDAR ROOM EN EL TEAM
			if (clan.getClanMembers().size() == 10) {
				return;
			}
			// INSERT SOLICITUDE TABLE
			Solicitude solicitude = new Solicitude();
			solicitude.setClan(clan);
			solicitude.setUser(userDAO.find(new User(request.getUserId())));
			solicitude.setSolicitudeType(solicitudeTypeDAO.find(new SolicitudeType(1L))); // RECLUTAR
			solicitude.setState(stateDAO.find(new State(1L))); // PENDIENTE
			solicitude.setActive(1);
			solicitude.setCreateDate(new Date());
			solicitudeDAO.save(solicitude);
		} catch (DaoException e) {
			logger.error("DaoException en Recruit Player.", e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception en Recruit Player.", e);
			throw new ServiceException("Error in " + e.getMessage());
		}
	}

	public void acceptPlayer(AcceptMemberRequest request) throws ServiceException {
		logger.info("Accept Player. Clan " + request.getClanId() + " and User " + request.getUserId());
		try {
			// ACTUALIZA SOLICITUDE
			Solicitude solicitude = solicitudeDAO.find(new Solicitude(request.getSolicitudeId()));
			// VALIDAR LOS ESTADOS
			if (solicitude != null && (request.getState().equals(2L) || request.getState().equals(3L))) {
				solicitude.setState(stateDAO.find(new State(request.getState())));
				solicitude.setUpdateDate(new Date());
				solicitudeDAO.update(solicitude);
				// INSERT CLAN MEMBER
				Clan clan = clanDAO.find(new Clan(request.getClanId()));
				User user = userDAO.find(new User(request.getUserId()));
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
			logger.error("DaoException en Accept Player.", e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception en Accept Player.", e);
			throw new ServiceException("Error in " + e.getMessage());
		}
	}

	public List<CandidatesResponse> getCandidates(CandidatesRequest request) throws ServiceException {
		logger.info("Get Candidates for Team. " + request.getClanId());
		try {
			// GET CANDIDATES
			List<CandidatesResponse> candidates = new ArrayList();
			Clan clan = clanDAO.find(new Clan(request.getClanId()));
			State state = stateDAO.find(new State(1L)); // PENDIENTE
			List<Solicitude> solicitudes = solicitudeDAO.getSolicitudesByClanAndState(clan, state, 1);
			for (Solicitude solicitude : solicitudes) {
				CandidatesResponse can = new CandidatesResponse();
				can.setClanId(solicitude.getClan().getId());
				can.setUserId(solicitude.getUser().getId());
				can.setClanName(solicitude.getClan().getName());
				can.setUserName(solicitude.getUser().getName());
				candidates.add(can);
			}
			return candidates;
		} catch (DaoException e) {
			logger.error("DaoException en Get Candidates for Clan. " + request.getClanId(), e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception en Get Candidates for Clan. " + request.getClanId(), e);
			throw new ServiceException("Error in " + e.getMessage());
		}

	}

	public void dropMember(DropMemberRequest request) throws ServiceException {
		logger.info("Drop Member " + request.getMemberId() + " from Team " + request.getClanId() + " by TL "
				+ request.getLeaderId());
		try {
			Clan clan = clanDAO.find(new Clan(request.getClanId()));
			User teamLeader = getLeader(clan);
			User userToDrop = userDAO.find(new User(request.getMemberId()));

			// User that asks is leader but member to drop cant be leader
			if (request.getLeaderId().equals(teamLeader.getId()) && !request.getMemberId().equals(teamLeader.getId())) {
				clanDAO.dropMember(clan, userToDrop);
			}
		} catch (DaoException e) {
			logger.error("DaoException en Drop Member " + request.getMemberId() + " from Team " + request.getClanId()
					+ " by TL " + request.getLeaderId(), e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception en Drop Member " + request.getMemberId() + " from Team " + request.getClanId()
					+ " by TL " + request.getLeaderId(), e);
			throw new ServiceException("Error in " + e.getMessage());
		}
	}

	public void postular(PostularRequest request) throws ServiceException {
		logger.info("Postular Team " + request.getClanId() + " for user " + request.getUserId());
		try {
			Solicitude solicitude = new Solicitude();
			solicitude.setClan(clanDAO.find(new Clan(request.getClanId())));
			solicitude.setUser(userDAO.find(new User(request.getUserId())));
			solicitude.setSolicitudeType(solicitudeTypeDAO.find(new SolicitudeType(2L))); // POSTULAR
			solicitude.setState(stateDAO.find(new State(1L))); // PENDIENTE
			solicitude.setActive(1);
			solicitude.setCreateDate(new Date());
			solicitudeDAO.save(solicitude);
		} catch (DaoException e) {
			logger.error("DaoException en Postular Team " + request.getClanId() + " for user " + request.getUserId(),
					e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception en Postular Team " + request.getClanId() + " for user " + request.getUserId(), e);
			throw new ServiceException("Error in " + e.getMessage());
		}
	}

	public void rankTeam(RankTeamRequest request) throws ServiceException {
		logger.info("Rank Team " + request.getClanToRank() + " by user " + request.getUserId() + " stars "
				+ request.getNumStars());
		try {
			User user = userDAO.find(new User(request.getUserId()));
			Clan clanToRank = clanDAO.find(new Clan(request.getClanToRank()));
			// VALIDAR SI EL USUARIO YA RANKEO EL MISMO EQUIPO - INSERT O UPDATE
			ClanComments clanComments = clanCommentsDAO.findByClanAndUser(clanToRank, user);
			if (clanComments != null) {
				clanComments.setStarsNum(request.getNumStars());
				clanComments.setDescription(request.getDescription());
				clanComments.setUpdateDate(new Date());
				clanCommentsDAO.update(clanComments);
			} else {
				clanCommentsDAO
						.save(prepareClanComments(clanToRank, user, request.getDescription(), request.getNumStars()));
			}
			// ACTUALIZAR PERFIL DEL TEAM
			updateClanProfileStarsRank(clanToRank);
		} catch (DaoException e) {
			logger.error("DaoException en Rank Team " + request.getClanToRank() + " by user " + request.getUserId()
					+ " stars " + request.getNumStars(), e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception en Rank Team " + request.getClanToRank() + " by user " + request.getUserId()
					+ " stars " + request.getNumStars(), e);
			throw new ServiceException("Error in " + e.getMessage());
		}

	}

	private ClanComments prepareClanComments(Clan clan, User user, String description, Integer starsNum) {
		logger.info("Prepare Clan Comments.");
		ClanComments comment = new ClanComments();
		comment.setClan(clan);
		comment.setUser(user);
		comment.setGame(clan.getGame());
		comment.setDescription(description);
		comment.setStarsNum(starsNum);
		return comment;
	}

	private void updateClanProfileStarsRank(Clan clan) throws ServiceException {
		logger.info("Update Clan Profile Stars Rank " + clan.getId());
		try {
			List<ClanComments> comments = clanCommentsDAO.listCommentsByClan(clan);
			if (comments != null) {
				Integer totalVotes = comments.size();
				Integer countStars = 0;
				for (ClanComments cc : comments) {
					countStars += cc.getStarsNum();
				}
				if (totalVotes != 0) {
					Integer newStars = countStars / totalVotes;
					clan.setStarsNumber(newStars);
					clanDAO.update(clan);
				}
			}
		} catch (DaoException e) {
			logger.error("DaoException en Update Clan Profile Stars Rank " + clan.getId(), e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception en Update Clan Profile Stars Rank " + clan.getId(), e);
			throw new ServiceException("Error in " + e.getMessage());
		}
	}

	public void assignRole(AssignRoleRequest request) throws ServiceException {
		logger.info("Assign Role/User " + request.getNewRolId() + "/" + request.getMemberId() + " in Clan "
				+ request.getClanId() + " by TL " + request.getLeaderId());
		try {
			Clan clan = clanDAO.find(new Clan(request.getClanId()));
			User teamLeader = getLeader(clan);
			MemberType memberType = memberTypeDAO.find(new MemberType(request.getNewRolId()));

			// ONLY LEADER CAN ASSIGN ROLES
			if (request.getLeaderId().equals(teamLeader.getId())) {
				// GET MEMBER TO ASSIGN
				for (ClanMembers member : clan.getClanMembers()) {
					if (member.getUser().getId().equals(request.getMemberId())) {
						// IS LEADER LEAVING
						if (memberType.getId().equals(1L)) {
							// Old leader becomes a member
							clanDAO.updateMemberRole(2L, teamLeader.getId(), clan.getId());
						}
						// actualizar rol
						clanDAO.updateMemberRole(memberType.getId(), member.getUser().getId(), clan.getId());
						break;
					}
				}
			}
		} catch (DaoException e) {
			logger.error("DaoException en Assign Role/User " + request.getNewRolId() + "/" + request.getMemberId()
					+ " in Clan " + request.getClanId() + " by TL " + request.getLeaderId(), e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception en Assign Role/User " + request.getNewRolId() + "/" + request.getMemberId()
					+ " in Clan " + request.getClanId() + " by TL " + request.getLeaderId(), e);
			throw new ServiceException("Error in " + e.getMessage());
		}

	}

	private MemberType getMemberType(Clan clan) throws ServiceException {
		logger.info("get Available Member Type for Clan " + clan.getId());
		try {
			Integer cantMembers = clan.getClanMembers().size();
			MemberType memberType = null;
			if (cantMembers < 5) {
				// MIEMBRO
				memberType = memberTypeDAO.find(new MemberType(2L));
			} else if (cantMembers < 10) {
				// SUPLENTE
				memberType = memberTypeDAO.find(new MemberType(3L));
			} else {
				// CLAN FULL
			}
			return memberType;
		} catch (DaoException e) {
			logger.error("DaoException get Available Member Type for Clan " + clan.getId(), e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception get Available Member Type for Clan " + clan.getId(), e);
			throw new ServiceException("Error in " + e.getMessage());
		}
	}

	private User getLeader(Clan clan) {
		for (ClanMembers member : clan.getClanMembers()) {
			if (member.getMemberType().getId().equals(1L)) {
				return member.getUser();
			}
		}
		return null;
	}
}
