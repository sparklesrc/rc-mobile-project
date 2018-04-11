package pe.com.rc.mobile.service.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.dao.ClanDAO;
import pe.com.rc.mobile.dao.GameDAO;
import pe.com.rc.mobile.dao.GeneratedCodeDAO;
import pe.com.rc.mobile.dao.MemberTypeDAO;
import pe.com.rc.mobile.dao.SolicitudeDAO;
import pe.com.rc.mobile.dao.SolicitudeTypeDAO;
import pe.com.rc.mobile.dao.StateDAO;
import pe.com.rc.mobile.dao.UserDAO;
import pe.com.rc.mobile.dao.UserGameProfileDAO;
import pe.com.rc.mobile.model.ClanMembers;
import pe.com.rc.mobile.model.Game;
import pe.com.rc.mobile.model.GeneratedCode;
import pe.com.rc.mobile.model.MemberType;
import pe.com.rc.mobile.model.Rol;
import pe.com.rc.mobile.model.Solicitude;
import pe.com.rc.mobile.model.SolicitudeType;
import pe.com.rc.mobile.model.State;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.UserGameProfile;
import pe.com.rc.mobile.model.clan.Clan;
import pe.com.rc.mobile.model.clan.UserReqRes.AcceptClanRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.InvitationsToTeamRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.InvitationsToTeamResponse;
import pe.com.rc.mobile.model.clan.UserReqRes.SignUpCode;
import pe.com.rc.mobile.model.clan.UserReqRes.SignUpGameProfile;
import pe.com.rc.mobile.model.clan.UserReqRes.SignUpRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.UserByMailResp;
import pe.com.rc.mobile.model.clan.UserReqRes.UserTeams;
import pe.com.rc.mobile.service.clan.ClanServiceImpl;
import pe.com.rc.mobile.service.mail.MailSenderService;
import pe.com.rc.mobile.service.solicitude.SolicitudeService;

@Service
public class UserServiceImpl implements UserService {

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
	@Autowired
	private UserGameProfileDAO userGameProfileDAO;
	@Autowired
	private GeneratedCodeDAO generatedCodeDAO;
	@Autowired
	private MailSenderService mailSender;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public void processClanRequest(AcceptClanRequest request) throws ServiceException {
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
		}
		return memberType;
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

	public UserByMailResp getUserByMail(String mail) throws ServiceException {
		try {
			List<UserTeams> userTeamDetails = null;
			User user = userDAO.findActiveUserByMail(mail);
			if (user != null) {
				userTeamDetails = clanDAO.getTeamsByUser(user.getId());
			}
			return prepareUser(user, userTeamDetails);
		} catch (DaoException e) {
			throw new ServiceException("Error al obtener usuario por mail." + mail);
		}
	}

	private UserByMailResp prepareUser(User user, List<UserTeams> userTeams) {
		UserByMailResp resp = new UserByMailResp();
		resp.setUserId(user.getId());
		resp.setMail(user.getMail());
		resp.setPassword(user.getPassword());
		resp.setRol(user.getRol().getDescription());
		resp.setSteamId(user.getSteamId() == null ? "" : user.getSteamId().toString());
		resp.setUserTeams(userTeams);
		resp.setUserSyncWithSteam(user.getSteamId() != null && !"".equals(user.getSteamId().toString()));
		return resp;
	}

	public UserByMailResp syncSteamUser(Long userId, String steamId) throws ServiceException {
		try {
			userDAO.updateSteamId(userId, new Long(steamId));
			User user = userDAO.find(new User(userId));
			List<UserTeams> userTeamDetails = null;
			if (user != null) {
				userTeamDetails = clanDAO.getTeamsByUser(user.getId());
			}
			return prepareUser(user, userTeamDetails);
		} catch (DaoException e) {
			logger.error("Error en " + e.getMessage() , e);
			throw new ServiceException("Error al Syncronizar Steam Account para usuario." + userId + "con SteamId " + steamId);
		}
	}

	public String signUp(SignUpRequest request) throws ServiceException {
		try {
			// Validate if mail is not in use
			User fromDB = userDAO.findByMail(request.getEmail());
			if (fromDB != null) {
				return "user exists";
			}
			// Create User
			User user = new User();
			user.setMail(request.getEmail());
			user.setPassword(request.getPassword());
			user.setEdad(request.getEdad());
			user.setPais(request.getPais());
			user.setLastLogin(new Date());
			user.setCreateDate(new Date());
			user.setActive(0);
			user.setRol(new Rol(1L));
			userDAO.save(user);
			saveGameProfile(request);
			generateCode(user);
			return "user created";
		} catch (Exception e) {
			logger.error("Error en " + e.getMessage() , e);
			throw new ServiceException("Error al Crear Usuario." + request.getEmail());
		}
	}

	private void saveGameProfile(SignUpRequest request) throws ServiceException {
		// User Created successfully
		if (request.getGameProfile() != null) {
			try {
				SignUpGameProfile reqProfile = request.getGameProfile();
				Game game = gameDAO.find(new Game(new Long(reqProfile.getGameId())));
				User user = userDAO.findByMail(request.getEmail());

				UserGameProfile userGameProfile = new UserGameProfile();
				userGameProfile.setCelular(reqProfile.getCelular());
				userGameProfile.setDescription(reqProfile.getDescription());
				userGameProfile.setGame(game);
				userGameProfile.setNickname(reqProfile.getNickname());
				userGameProfile.setUser(user);
				
				String userRoles = "";
				for(String rol : reqProfile.getRoles()) {
					userRoles += rol.concat(", ");
				}
				
				userGameProfile.setRoles(userRoles);
				userGameProfile.setCreateDate(new Date());
				userGameProfile.setActive(1);
				userGameProfileDAO.save(userGameProfile);
			} catch (Exception e) {
				logger.error("Error en " + e.getMessage(), e);
				throw new ServiceException("Error al Crear Game Profile for user." + request.getEmail());
			}
		}
	}

	private void generateCode(User user) throws ServiceException {
		// Crear Codigo, Guardar y Enviar
		try {
			Random rand = new Random();
			Integer code = rand.nextInt(999999 + 1);

			GeneratedCode gC = new GeneratedCode();
			gC.setUser(user);
			gC.setCode(code);
			gC.setCreateDate(new Date());
			gC.setActive(1);
			generatedCodeDAO.save(gC);
			
			sendCode(user, code);
		} catch (Exception e) {
			logger.error("Error en Generating Code " + e.getMessage(), e);
		}
	}

	private void sendCode(User user, Integer code) {
		try {
			mailSender.sendMail("fbramirezc@gmail.com", code.toString());
		} catch (Exception e) {
			logger.error("Error en Envio de mail. " + e.getMessage(), e);
		}
	}

	@Transactional
	public String verifyCode(SignUpCode request) throws ServiceException {
		try {
			User user = userDAO.findByMail(request.getMail());
			GeneratedCode gC = generatedCodeDAO.findCodeByUser(user);
			if (gC == null) {
				return "code not exists";
			}
			// Validate Code
			if (!gC.getCode().toString().equals(request.getCode())) {
				return "wrong code";
			}
			// If Code is right, then activate User and delete Code
			activateUser(user);
			deleteGeneratedCode(gC);
			return "verified";
		} catch (Exception e) {
			logger.error("Error en Verify Code " + e.getMessage(), e);
			throw new ServiceException("Error en Verify Code " + e.getMessage(), e);
		}
	}

	private void activateUser(User user) throws DaoException {
		user.setActive(1);
		user.setUpdateDate(new Date());
		userDAO.update(user);
	}

	private void deleteGeneratedCode(GeneratedCode gC) {
		generatedCodeDAO.delete(gC);
	}

	@Transactional
	public String generateCode(SignUpCode request) throws ServiceException {
		try {
			User user = userDAO.findByMail(request.getMail());
			GeneratedCode gC = generatedCodeDAO.findCodeByUser(user);
			if (gC != null) {
				deleteGeneratedCode(gC);
			}
			generateCode(user);
			return "code generated";
		} catch (Exception e) {
			logger.error("Error en Generate Code " + e.getMessage(), e);
			throw new ServiceException("Error en Generate Code " + e.getMessage(), e);
		}
	}
}
