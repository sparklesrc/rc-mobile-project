package pe.com.rc.mobile.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.rc.mobile.dao.LocationRepository;
import pe.com.rc.mobile.dao.helper.AmistadHelper;
import pe.com.rc.mobile.dao.helper.LocationHelper;
import pe.com.rc.mobile.model.Amistad;
import pe.com.rc.mobile.model.Location;
import pe.com.rc.mobile.model.Lugar;
import pe.com.rc.mobile.model.Preferencia;

@Repository
public class LocationRepositoryImpl implements LocationRepository {

	@Autowired
	@Qualifier("dbDataSource")
	private DataSource dataSource;

	private LocationHelper locationHelper;

	private AmistadHelper amistadHelper;

	public List<Location> listLocations() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		locationHelper = new LocationHelper(jdbcTemplate);
		List<Location> listLocations = locationHelper.getLocations();
		return listLocations;
	}

	public List<Lugar> listarLugaresByDefault(String distrito,
			List<Preferencia> preferencias, String idUsuario) {
		List<Lugar> lugares = new ArrayList<Lugar>(0);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		locationHelper = new LocationHelper(jdbcTemplate);

		for (Preferencia prefer : preferencias) {
			String idPreferencia = prefer.getId();
			List<Lugar> listaByPreferencia = locationHelper
					.getLugaresByDefault(distrito, idPreferencia);

			lugares.addAll(listaByPreferencia);
		}

		// Lugares que coinciden en el distrito y preferencia
		amistadHelper = new AmistadHelper(jdbcTemplate);
		if (!lugares.isEmpty()) {
			for (Lugar lugar : lugares) { // Por cada lugar ver si hay amistades
				String idLugar = lugar.getId();
				List<Amistad> amistades = amistadHelper.getAmistadesByPlace(
						idLugar, idUsuario);
				lugar.setAmistad(amistades);
			}
		}

		return lugares;
	}
}
