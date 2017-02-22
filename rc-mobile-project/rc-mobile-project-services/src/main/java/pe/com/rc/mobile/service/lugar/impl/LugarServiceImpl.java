package pe.com.rc.mobile.service.lugar.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.dao.LugarRepository;
import pe.com.rc.mobile.model.Lugar;
import pe.com.rc.mobile.model.LugarRq;
import pe.com.rc.mobile.model.MusicaLugar;
import pe.com.rc.mobile.model.TipoLugar;
import pe.com.rc.mobile.service.lugar.LugarService;

/**
 * Clase Implementacion LugarService
 * 
 * @author framirez
 * @since 04/02/2017
 *
 */
@Service
public class LugarServiceImpl implements LugarService {

	private Logger logger = Logger.getLogger(LugarServiceImpl.class);

	@Autowired
	LugarRepository lugarRepository;

	/**
	 * Lista los Lugares por default al iniciar sesion
	 * 
	 * @param LugarRq
	 * @return List<Lugar>
	 * @author framirez
	 * @since 04/02/2017
	 */
	public List<Lugar> listarLugaresByDefault(LugarRq lugarRq)
			throws ServiceException {
		List<Lugar> lugares = null;
		String distrito = lugarRq.getUbicacion().getCodigoPostal();
		String idUsuario = lugarRq.getIdUsuario();
		List<TipoLugar> tipoLugar = lugarRq.getPreferencia().getTipoLugar();
		List<MusicaLugar> musicaLugar = lugarRq.getPreferencia()
				.getMusicaLugar();

		try {
			lugares = lugarRepository.listarLugaresByDefault(distrito,
					tipoLugar, musicaLugar, idUsuario);
		} catch (DaoException e) {
			logger.error("Error al obtener la Lista de Lugares por Default", e);
		}

		return lugares;
	}
}
