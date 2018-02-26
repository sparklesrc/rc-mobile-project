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

	private static final Logger logger = LoggerFactory.getLogger(SolicitudeServiceImpl.class);

	@Autowired
	SolicitudeDAO solicitudeDAO;

	public Solicitude findSolicitud(Solicitude solicitud) throws ServiceException {
		logger.info("Find Solicitude " + solicitud.getId());
		try {
			return solicitudeDAO.find(solicitud);
		} catch (DaoException e) {
			logger.error("DaoException trying to find Solicitude " + solicitud.getId(), e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Error trying to find Solicitude " + solicitud.getId(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	public void update(Solicitude solicitud) throws ServiceException {
		logger.info("Update Solicitude " + solicitud.getId());
		try {
			solicitudeDAO.update(solicitud);
		} catch (DaoException e) {
			logger.error("DaoException trying to update Solicitude " + solicitud.getId(), e);
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Error trying to update Solicitude " + solicitud.getId(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
