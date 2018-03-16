package pe.com.rc.mobile.service.clan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import pe.com.rc.mobile.model.clan.BuildClanRequest;
import pe.com.rc.mobile.model.clan.Clan;
import pe.com.rc.mobile.model.clan.ClanMembersResponse;
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
import pe.com.rc.mobile.service.mail.Mail;

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
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ServiceException("Error in " + e.getMessage());
		}
		return lista;
	}

	public TeamSearchResponse getTeam(TeamSearchRequest request) {
		Game game = gameDAO.find(new Game(new Long(request.getGameId())));
		Clan clan = null;
		TeamSearchResponse response = null;

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
				teamMember.setName(member.getUser().getMail());
				teamMember.setType(member.getMemberType().getDescription());
				teamMember.setUserId(member.getUser().getId());
				lista.add(teamMember);
			}
			response.setMembers(lista);
		}

		return response;
	}

	public void buildTeam(TeamBuildRequest request) {
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
	}

	public void deleteTeam(TeamDeleteRequest request) {
		// Validate if the user is TEAM LEADER
		System.out.println("VALIDATE");
		if (isUserTL(request)) {
			System.out.println("TRUE");
			clanDAO.delete(clanDAO.find(new Clan(request.getClanId())));
		}
	}

	private boolean isUserTL(TeamDeleteRequest request) {
		Clan clan = clanDAO.find(new Clan(request.getClanId()));
		for (ClanMembers member : clan.getClanMembers()) {
			if (member.getUser().getId().equals(request.getUserId()))
				return true;

		}
		return false;
	}

	public void recruitPlayer(RecruitRequest request) {
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
	}

	public void acceptPlayer(AcceptMemberRequest request) throws ServiceException{
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
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ServiceException("Error in " + e.getMessage());
		}
	}

	public List<CandidatesResponse> getCandidates(CandidatesRequest request) {
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
			can.setUserName(solicitude.getUser().getMail());
			candidates.add(can);
		}
		return candidates;
	}

	public void dropMember(DropMemberRequest request) {
		Clan clan = clanDAO.find(new Clan(request.getClanId()));
		User teamLeader = clanDAO.getLeader(clan);
		User userToDrop = userDAO.find(new User(request.getMemberId()));

		// User that asks is leader but member to drop cant be leader
		if (request.getLeaderId().equals(teamLeader.getId()) && !request.getMemberId().equals(teamLeader.getId())) {
			clanDAO.dropMember(clan, userToDrop);
		}
	}

	public void postular(PostularRequest request) {
		Solicitude solicitude = new Solicitude();
		solicitude.setClan(clanDAO.find(new Clan(request.getClanId())));
		solicitude.setUser(userDAO.find(new User(request.getUserId())));
		solicitude.setSolicitudeType(solicitudeTypeDAO.find(new SolicitudeType(2L))); // POSTULAR
		solicitude.setState(stateDAO.find(new State(1L))); // PENDIENTE
		solicitude.setActive(1);
		solicitude.setCreateDate(new Date());
		solicitudeDAO.save(solicitude);
	}

	public void rankTeam(RankTeamRequest request) throws ServiceException{
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
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ServiceException("Error in " + e.getMessage());
		}
	}

	private ClanComments prepareClanComments(Clan clan, User user, String description, Integer starsNum) {
		ClanComments comment = new ClanComments();
		comment.setClan(clan);
		comment.setUser(user);
		comment.setGame(clan.getGame());
		comment.setDescription(description);
		comment.setStarsNum(starsNum);
		return comment;
	}

	private void updateClanProfileStarsRank(Clan clan) throws ServiceException {
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
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ServiceException("Error in " + e.getMessage());
		}
	}

	public void assignRole(AssignRoleRequest request) {
		Clan clan = clanDAO.find(new Clan(request.getClanId()));
		User teamLeader = clanDAO.getLeader(clan);
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
	}

	private MemberType getMemberType(Clan clan) {
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
	}

	public List<ListClanResponse> getTeamsByUser(Long userId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
