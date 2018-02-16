package pe.com.rc.mobile.service.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.dao.ClanDAO;
import pe.com.rc.mobile.dao.MemberTypeDAO;
import pe.com.rc.mobile.dao.SolicitudeDAO;
import pe.com.rc.mobile.dao.StateDAO;
import pe.com.rc.mobile.dao.UserDAO;
import pe.com.rc.mobile.model.ClanMembers;
import pe.com.rc.mobile.model.MemberType;
import pe.com.rc.mobile.model.Solicitude;
import pe.com.rc.mobile.model.State;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.Clan;
import pe.com.rc.mobile.model.clan.UserReqRes.AcceptClanRequest;
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

	public void processClanRequest(AcceptClanRequest request) throws ServiceException {
		// User
		User user = userDAO.find(new User(request.getUserId()));
		// User response STATE
		State state = stateDAO.find(new State(request.getState()));
		// Clan
		Clan clan = clanDAO.find(new Clan(request.getClanId()));
		// Solicitud
		Solicitude solicitud = solicitudeService.findSolicitud(new Solicitude(request.getSolicitudeId()));

		System.out.println("user " + user.getName());
		System.out.println("state " + state.getDescription());
		System.out.println("clan " + clan.getName());
		System.out.println("solicitud " + solicitud.getSolicitudeType().getDescription());
		
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
		} else if (state.getId().equals(6L)) {
			// RECHAZA
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

	
}
