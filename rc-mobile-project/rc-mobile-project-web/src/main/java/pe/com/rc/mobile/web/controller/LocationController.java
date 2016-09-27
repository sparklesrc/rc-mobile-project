package pe.com.rc.mobile.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rc.mobile.model.Location;
import pe.com.rc.mobile.model.Lugar;
import pe.com.rc.mobile.model.LugarRq;
import pe.com.rc.mobile.model.LugarRs;
import pe.com.rc.mobile.model.Preferencia;
import pe.com.rc.mobile.model.Ubicacion;
import pe.com.rc.mobile.service.location.LocationService;
import pe.com.rc.mobile.web.util.Constants;

@RestController
@RequestMapping(Constants.URL_BASE)
public class LocationController {

	@Autowired
	LocationService locationService;

	@RequestMapping(value = Constants.URL_LIST_ALL, method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody List<Location> listLocations() {
		List<Location> listaLocacitons = locationService.listLocations();
		return listaLocacitons;
	}

	/**
	 * Metodo que obtiene por defecto los lugares mas cercanos en base a la
	 * lista de preferencias segun el distrito de ubicacion
	 * 
	 * @param lugarRq
	 * @return List<LugarRs>
	 * @author framirez
	 * @since 26/09/2016
	 */
	@RequestMapping(value = Constants.URL_LIST_DEFAULT, method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody List<LugarRs> listarLugaresDefault(
			@RequestBody LugarRq lugarRq) {

		List<Lugar> lugares = locationService.listarLugaresByDefault(lugarRq);
		List<LugarRs> respo = new ArrayList<LugarRs>();
		respo.add((LugarRs) lugares);
		return respo;
	}
}
