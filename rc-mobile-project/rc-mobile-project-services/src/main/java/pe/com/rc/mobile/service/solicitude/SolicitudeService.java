package pe.com.rc.mobile.service.solicitude;

import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.model.Solicitude;

public interface SolicitudeService {

	Solicitude findSolicitud(Solicitude solicitud);

	void update(Solicitude solicitud) throws ServiceException;
}
