package pe.com.rc.mobile.service.lugar;

import java.util.List;

import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.model.Lugar;
import pe.com.rc.mobile.model.LugarRq;

/**
 * Interface LugarService
 * 
 * @author framirez
 * @since 04/02/2017
 *
 */
public interface LugarService {

	List<Lugar> listarLugaresByDefault(LugarRq lugarRq) throws ServiceException;
}
