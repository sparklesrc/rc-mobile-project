package pe.com.rc.mobile.service.lugar.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.com.rc.mobile.dao.LugarRepository;
import pe.com.rc.mobile.model.Amistad;
import pe.com.rc.mobile.model.Lugar;
import pe.com.rc.mobile.model.LugarRq;
import pe.com.rc.mobile.model.MusicaLugar;
import pe.com.rc.mobile.model.Preferencia;
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

	LugarRepository lugarRepository;

	/**
	 * Lista los Lugares por default al iniciar sesion
	 * 
	 * @param LugarRq
	 * @return List<Lugar>
	 * @author framirez
	 * @since 04/02/2017
	 */
	public List<Lugar> listarLugaresByDefault(LugarRq lugarRq) {
		List<Lugar> lugares = null;
		String distrito = lugarRq.getUbicacion().getCodigoPostal();
		String idUsuario = lugarRq.getIdUsuario(); // Obtener las Amistades
		// List<Amistad> amistades = lugarRq.getAmistad();
		List<TipoLugar> tipoLugar = lugarRq.getPreferencia().getTipoLugar();
		List<MusicaLugar> musicaLugar = lugarRq.getPreferencia()
				.getMusicaLugar();
		try {
			lugares = lugarRepository.listarLugaresByDefault(distrito,
					tipoLugar, musicaLugar, idUsuario);
		} catch (Exception e) {
		}
		return lugares;
	}
}
