package pe.com.rc.mobile.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.model.clan.UserReqRes.AcceptClanRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.InvitationsToTeamRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.InvitationsToTeamResponse;
import pe.com.rc.mobile.model.clan.UserReqRes.UserByMailResp;
import pe.com.rc.mobile.service.user.UserService;
import pe.com.rc.mobile.web.util.Constants;

@RestController
@RequestMapping(Constants.URL_BASE)
public class UserController {

	@Autowired
	UserService userService;

	// ACEPTAR o RECHAZAR INVITACION
	@RequestMapping(value = "/user/processClanRequest", method = RequestMethod.POST, produces = { "application/json" })
	public void processClanRequest(@RequestBody AcceptClanRequest request)
			throws ServiceException {
		userService.processClanRequest(request);
	}

	// LISTAR PROPUESTA DE TEAMS
	@RequestMapping(value = "/user/invitationsTeams", method = RequestMethod.POST, produces = { "application/json" })
	public List<InvitationsToTeamResponse> getInvitationsTeams(
			@RequestBody InvitationsToTeamRequest request)
			throws ServiceException {
		return userService.getInvitationsTeams(request);
	}

	// LISTAR POSTULACIONES
	@RequestMapping(value = "/user/postulaciones", method = RequestMethod.POST, produces = { "application/json" })
	public List<InvitationsToTeamResponse> getPostulaciones(
			@RequestBody InvitationsToTeamRequest request)
			throws ServiceException {
		return userService.getInvitationsTeams(request);
	}

	// USER BY MAIL
	@RequestMapping(value = "/user/findByMail", method = RequestMethod.POST, produces = { "application/json" })
	public UserByMailResp getPostulaciones(@RequestBody String mail) throws ServiceException {
		return userService.getUserByMail(mail);
	}
}
