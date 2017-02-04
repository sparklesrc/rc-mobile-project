package pe.com.rc.mobile.service.location.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.rc.mobile.dao.LocationRepository;
import pe.com.rc.mobile.model.Location;
import pe.com.rc.mobile.model.Lugar;
import pe.com.rc.mobile.model.LugarRq;
import pe.com.rc.mobile.model.Preferencia;
import pe.com.rc.mobile.service.location.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	public List<Location> listLocations() {
		List<Location> listaLocations = null;
		try {
			listaLocations = locationRepository.listLocations();
		} catch (Exception e) {
			System.out.println("ERROR AL LISTAR");
		}
		return listaLocations;
	}

	public List<Lugar> listarLugaresByDefault(LugarRq lugarRq) {
		List<Lugar> lugares = null;
		String distrito = lugarRq.getUbicacion().getCodigoPostal();
		List<Preferencia> preferencias = lugarRq.getPreferencia();
		String idUsuario = lugarRq.getIdUsuario();
		try {
			lugares = locationRepository.listarLugaresByDefault(distrito,
					preferencias, idUsuario);
		} catch (Exception e) {
		}
		return lugares;
	}
}
