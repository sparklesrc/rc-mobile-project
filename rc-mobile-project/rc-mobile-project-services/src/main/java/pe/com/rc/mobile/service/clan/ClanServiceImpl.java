package pe.com.rc.mobile.service.clan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import pe.com.rc.mobile.model.clan.BuildClanRequest;
import pe.com.rc.mobile.model.clan.Clan;
import pe.com.rc.mobile.model.clan.ClanMembersResponse;
import pe.com.rc.mobile.model.clan.ListClanResponse;
import pe.com.rc.mobile.model.clan.TeamSearch.AcceptMemberRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.CandidatesRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.CandidatesResponse;
import pe.com.rc.mobile.model.clan.TeamSearch.DropMemberRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.PostularRequest;
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
	
	private static final Logger logger = LoggerFactory
			.getLogger(ClanServiceImpl.class);

	public List<ClanMembersResponse> getMembersByClan(Long clanId) {
		Clan clan = clanDAO.find(new Clan(clanId));

		System.out.println("FRAMIREZ :: " + clan.getName());

		List<ClanMembersResponse> miembros = new ArrayList<ClanMembersResponse>();
		// for (ClanMembers cm : clan.getClanMembers()) {
		// ClanMembersResponse cmr = new ClanMembersResponse();
		// cmr.setClanName(cm.getClan().getName());
		// cmr.setSteamName(cm.getUser().getName());
		// cmr.setMemberType(cm.getMemberType().getDescription());
		// cmr.setSteamId(cm.getUser().getSteamId().toString());
		// cmr.setSteamAvatar(cm.getUser().getSteamLinkAvatar());
		// miembros.add(cmr);
		// }
		return miembros;
	}

	// public void buildClan(BuildClanRequest request) {
	// clanDAO.save(prepareClan(request));
	// }

	public void buildClan(BuildClanRequest request) {
		Game game = gameDAO.find(new Game(request.getGameId()));
		User user = userDAO.find(new User(request.getUserId()));

		Clan clan = new Clan();
		clan.setGame(game);
		clan.setActive(1);
		clan.setName(request.getName());
		clan.setDecription(request.getDescription());
		clan.setStarsNumber(0);
		clan.setCreateDate(new Date());

		clanDAO.save(clan);

		ClanMembers clanMembers = new ClanMembers();
		System.out.println("clan.getId() " + clan.getId());
		// clanMembers.setClan(new Clan(clan.getId()));
		// clanMembers.setUser(user);
		clanMembers.setMemberType(new MemberType(1L)); // TEAM LEADER BY DEFAULT
		clanMembers.setCreateDate(new Date());
		clanMembers.setActive(1);

		clan.getClanMembers().add(clanMembers);

		try {
			clanDAO.update(clan);
		} catch (Exception e) {
			System.out.println("FALLO");
		}

	}

	public List<ListClanResponse> listClanes() {
		List<ListClanResponse> lista = new ArrayList<ListClanResponse>();
		List<Clan> clanes = clanDAO.all();
		for (Clan clan : clanes) {
			ListClanResponse c = new ListClanResponse();
			c.setName(clan.getName());
			c.setDescription(clan.getDecription());
			c.setStarsNum(clan.getStarsNumber());
			c.setGame(clan.getGame().getName());
			lista.add(c);
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
			clan = clanDAO.getClanByNameAndGame(request.getCriteria(),
					game.getId());
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
		System.out.println("FALSE");
		return false;
	}

	public void recruitPlayer(RecruitRequest request) {
		// INSERT SOLICITUDE TABLE
		Solicitude solicitude = new Solicitude();
		solicitude.setClan(clanDAO.find(new Clan(request.getClanId())));
		solicitude.setUser(userDAO.find(new User(request.getUserId())));
		solicitude.setSolicitudeType(solicitudeTypeDAO.find(new SolicitudeType(1L))); // RECLUTAR
		solicitude.setState(stateDAO.find(new State(1L))); // PENDIENTE
		solicitude.setActive(1);
		solicitude.setCreateDate(new Date());
		solicitudeDAO.save(solicitude);
	}

	public void acceptPlayer(AcceptMemberRequest request) {
		// ACTUALIZA SOLICITUDE
		Solicitude solicitude = solicitudeDAO.find(new Solicitude(request.getSolicitudeId()));
		if (solicitude != null && (request.getState().equals(2L) || request.getState().equals(3L))) {
			solicitude.setState(stateDAO.find(new State(request.getState())));
			solicitude.setUpdateDate(new Date());
			solicitudeDAO.update(solicitude);
			// INSERT CLAN MEMBER
			MemberType memberType = memberTypeDAO.find(new MemberType(2L));
			Clan clan = clanDAO.find(new Clan(request.getClanId()));
			User user = userDAO.find(new User(request.getUserId()));
			ClanMembers members = new ClanMembers();
			members.setMemberType(memberType);
			members.setCreateDate(new Date());
			members.setClan(clan);
			members.setUser(user);
			members.setActive(1);
			clanDAO.insertMember(members);
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
			can.setUserName(solicitude.getUser().getName());
			candidates.add(can);
		}
		return candidates;
	}

	public void dropMember(DropMemberRequest request) {
		Clan clan = clanDAO.find(new Clan(request.getClanId()));
		User teamLeader = clanDAO.getLeader(clan);
		User userToDrop = userDAO.find(new User(request.getMemberId()));
		
		// User that asks is leader but member to drop cant be leader
		if(request.getLeaderId().equals(teamLeader.getId()) && !request.getMemberId().equals(teamLeader.getId())) {
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
}
