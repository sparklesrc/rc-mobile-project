package pe.com.rc.mobile.service.solicitude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.rc.mobile.dao.SolicitudeDAO;
import pe.com.rc.mobile.model.Solicitude;

@Service
public class SolicitudeServiceImpl implements SolicitudeService {

	@Autowired
	SolicitudeDAO solicitudeDAO;

	public Solicitude findSolicitud(Solicitude solicitud) {
		return solicitudeDAO.find(solicitud);
	}

	public void update(Solicitude solicitud) {
		solicitudeDAO.update(solicitud);
	}

}
