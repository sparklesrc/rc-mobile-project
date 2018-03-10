package pe.com.rc.mobile.service.solicitude;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.dao.SolicitudeDAO;
import pe.com.rc.mobile.model.Solicitude;

@Service
public class SolicitudeServiceImpl implements SolicitudeService {

	@Autowired
	private SolicitudeDAO solicitudeDAO;

	private static final Logger logger = LoggerFactory.getLogger(SolicitudeServiceImpl.class);

	public Solicitude findSolicitud(Solicitude solicitud) {
		return solicitudeDAO.find(solicitud);
	}

	public void update(Solicitude solicitud) throws ServiceException {
		try {
			solicitudeDAO.update(solicitud);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ServiceException("Error in " + e.getMessage());
		}
	}

}
