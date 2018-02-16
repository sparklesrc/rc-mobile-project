package pe.com.rc.mobile.service.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.dao.ClanDAO;
import pe.com.rc.mobile.dao.SolicitudeDAO;
import pe.com.rc.mobile.dao.StateDAO;
import pe.com.rc.mobile.dao.UserDAO;
import pe.com.rc.mobile.model.Solicitude;
import pe.com.rc.mobile.model.State;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.Clan;
import pe.com.rc.mobile.model.clan.UserReqRes.AcceptClanRequest;

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

	public void processClanRequest(AcceptClanRequest request) throws ServiceException {
		// User
		User user = userDAO.find(new User(request.getUserId()));
		// User response
		State state = stateDAO.find(new State(request.getState()));
		// Clan
		Clan clan = clanDAO.find(new Clan(request.getClanId()));
		// Solicitud
		Solicitude solicitud = solicitudeDAO.find(new Solicitude(request.getSolicitudeId()));

		// ACTUALIZAR SOLICITUD
		solicitud.setState(state);
		solicitud.setUpdateDate(new Date());
		solicitudeDAO.update(solicitud);

		// SI EL USUARIO ACEPTA INGRESA AL CLAN, ELSE EL USUARIO RECHAZA

		
	}

}
