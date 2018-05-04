package pe.com.rc.mobile.web.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.model.clan.UserReqRes.AcceptClanRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.GenericResponse;
import pe.com.rc.mobile.model.clan.UserReqRes.InvitationsToTeamRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.InvitationsToTeamResponse;
import pe.com.rc.mobile.model.clan.UserReqRes.SignUpCode;
import pe.com.rc.mobile.model.clan.UserReqRes.SignUpGameProfile;
import pe.com.rc.mobile.model.clan.UserReqRes.SignUpRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.SyncSteamUser;
import pe.com.rc.mobile.model.clan.UserReqRes.UserByMailReq;
import pe.com.rc.mobile.model.clan.UserReqRes.UserByMailResp;
import pe.com.rc.mobile.model.clan.UserReqRes.UserGame;
import pe.com.rc.mobile.service.user.UserService;
import pe.com.rc.mobile.web.util.Constants;

@RestController
@RequestMapping(Constants.URL_BASE)
public class UserController {

	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	// ACEPTAR o RECHAZAR INVITACION
	@RequestMapping(value = "/user/processClanRequest", method = RequestMethod.POST, produces = { "application/json" })
	public void processClanRequest(@RequestBody AcceptClanRequest request) throws ServiceException {
		userService.processClanRequest(request);
	}

	// LISTAR PROPUESTA DE TEAMS
	@RequestMapping(value = "/user/invitationsTeams", method = RequestMethod.POST, produces = { "application/json" })
	public List<InvitationsToTeamResponse> getInvitationsTeams(@RequestBody InvitationsToTeamRequest request)
			throws ServiceException {
		return userService.getInvitationsTeams(request);
	}

	// LISTAR POSTULACIONES
	@RequestMapping(value = "/user/postulaciones", method = RequestMethod.POST, produces = { "application/json" })
	public List<InvitationsToTeamResponse> getPostulaciones(@RequestBody InvitationsToTeamRequest request)
			throws ServiceException {
		return userService.getInvitationsTeams(request);
	}

	// USER BY MAIL
	@RequestMapping(value = "/user/findByMail", method = RequestMethod.POST, produces = { "application/json" })
	public UserByMailResp findUserByMail(@RequestBody UserByMailReq user) throws ServiceException {
		return userService.getUserByMail(user.getMail());
	}

	// SYNCRONIZE STEAM USER
	@RequestMapping(value = "/user/syncSteamAccount", method = RequestMethod.POST, produces = { "application/json" })
	public UserByMailResp syncSteamAccount(@RequestBody SyncSteamUser user) throws ServiceException {
		return userService.syncSteamUser(user.getUserId(), user.getSteamId());
	}

	// SIGN UP
	@RequestMapping(value = "/user/signUp", method = RequestMethod.POST, produces = { "application/json" })
	public GenericResponse signUpAccount(@RequestBody SignUpRequest request) throws ServiceException {
		return new GenericResponse(userService.signUp(request));
	}

	// VERIFY CODE
	@RequestMapping(value = "/user/verifyCode", method = RequestMethod.POST, produces = { "application/json" })
	public GenericResponse verifyCode(@RequestBody SignUpCode request) throws ServiceException {
		return new GenericResponse(userService.verifyCode(request));
	}

	// GENERE VERIFY CODE
	@RequestMapping(value = "/user/generateCode", method = RequestMethod.POST, produces = { "application/json" })
	public GenericResponse generateCode(@RequestBody SignUpCode request) throws ServiceException {
		return new GenericResponse(userService.generateCode(request));
	}

	// GAME PROFILE BY MAIL AND GAME
	@RequestMapping(value = "/user/findUserGameProfile", method = RequestMethod.POST, produces = { "application/json" })
	public SignUpGameProfile findUserGameProfile(@RequestBody UserGame request) throws ServiceException {
		return userService.getGameProfile(request);
	}

	// GAME PROFILE BY MAIL AND GAME
	@RequestMapping(value = "/user/updateUserGameProfile", method = RequestMethod.POST, produces = { "application/json" })
	public GenericResponse updateUserGameProfile(@RequestBody SignUpGameProfile request) throws ServiceException {
		return new GenericResponse(userService.updateGameProfile(request));
	}
}
