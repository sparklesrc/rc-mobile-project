package pe.com.rc.mobile.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.model.Lugar;
import pe.com.rc.mobile.model.LugarRq;
import pe.com.rc.mobile.model.LugarRs;
import pe.com.rc.mobile.service.lugar.LugarService;
import pe.com.rc.mobile.web.util.Constants;

@RestController
@RequestMapping(Constants.URL_BASE)
public class LugarController {

//	private static final Logger logger = LoggerFactory
//			.getLogger(LugarController.class);
//
//	@Autowired
//	LugarService lugarService;
//
//	/**
//	 * Metodo que obtiene al logar los lugares mas cercanos en base a la lista
//	 * de preferencias segun el distrito de ubicacion
//	 * 
//	 * @param lugarRq
//	 * @return List<LugarRs>
//	 * @author framirez
//	 * @since 26/09/2016
//	 */
//	@RequestMapping(value = Constants.URL_LUGAR + Constants.URL_LIST_DEFAULT, method = RequestMethod.POST, produces = { "application/json" })
//	public @ResponseBody LugarRs listarLugaresDefault(
//			@RequestBody LugarRq lugarRq) throws ServiceException {
//		logger.info("Obtener Lugares - LugarController.listarLugaresDefault");
//
//		List<Lugar> lugares = lugarService.listarLugaresByDefault(lugarRq);
//
//		LugarRs respo = new LugarRs();
//		respo.setLugar(lugares);
//		return respo;
//
//	}
}
