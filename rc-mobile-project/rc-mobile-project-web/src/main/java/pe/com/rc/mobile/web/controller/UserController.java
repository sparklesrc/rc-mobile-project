package pe.com.rc.mobile.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.model.clan.UserReqRes.AcceptClanRequest;
import pe.com.rc.mobile.service.user.UserService;
import pe.com.rc.mobile.web.util.Constants;

@RestController
@RequestMapping(Constants.URL_BASE)
public class UserController {

	@Autowired
	UserService userService;

	// ACEPTAR o RECHAZAR INVITACION
	@RequestMapping(value = "/user/processClanRequest", method = RequestMethod.POST, produces = { "application/json" })
	public void processClanRequest(@RequestBody AcceptClanRequest request) throws ServiceException {
		userService.processClanRequest(request);
	}

	// MANEJAR LAS SOLICITUDES DE RECLUTAR Y POSTULAR CUANDO EL CLAN ESTA LLENO

	// LISTAR PROPUESTA DE TEAMS
}
