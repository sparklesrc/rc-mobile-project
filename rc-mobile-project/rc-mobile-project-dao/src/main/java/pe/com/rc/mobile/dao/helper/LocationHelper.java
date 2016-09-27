package pe.com.rc.mobile.dao.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.annotation.Transactional;

import pe.com.rc.mobile.dao.util.Constants;
import pe.com.rc.mobile.model.Location;
import pe.com.rc.mobile.model.Lugar;
import pe.com.rc.mobile.model.MacroPlace;
import pe.com.rc.mobile.model.Preferencia;
import pe.com.rc.mobile.model.TipoLugar;
import pe.com.rc.mobile.model.Ubicacion;

@SuppressWarnings("all")
public class LocationHelper {

	private SimpleJdbcCall simpleJdbcCall;
	private SimpleJdbcCall simpleJdbcCall2;

	public LocationHelper(JdbcTemplate jdbcTemplate) {
		this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(Constants.PROC_GET_LOCATIONS);
		simpleJdbcCall.declareParameters(new SqlReturnResultSet(
				Constants.RESULT, new LocationMapper()));

		this.simpleJdbcCall2 = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(Constants.PROC_GET_LUGARES_BY_DEFAULT);
		simpleJdbcCall2.declareParameters(new SqlReturnResultSet(
				Constants.RESULT, new LugarMapper()));
	}

	public List<Location> getLocations() {
		Map<String, Object> results = simpleJdbcCall.execute();
		List<Location> appVersionRs = (List<Location>) results
				.get(Constants.RESULT);
		return appVersionRs;
	}

	public class LocationMapper implements RowMapper<Location> {
		public Location mapRow(ResultSet rs, int arg1) throws SQLException {
			Location location = new Location();
			location.setLocationId(rs.getString(Constants.PARAM_LOCATION_ID));
			location.setLocationName(rs
					.getString(Constants.PARAM_LOCATION_NAME).trim());
			location.setStoreId(rs.getString(Constants.PARAM_STORE_ID).trim());
			location.setAddress(rs.getString(Constants.PARAM_ADDRESS).trim());
			location.setAddressFind(rs.getString(Constants.PARAM_ADDRESS_FIND)
					.trim());
			location.setDistrict(rs.getString(Constants.PARAM_DISTRICT).trim());
			location.setDistrictFind(rs
					.getString(Constants.PARAM_DISTRICT_FIND).trim());
			location.setProvince(rs.getString(Constants.PARAM_PROVINCE).trim());
			location.setDepartment(rs.getString(Constants.PARAM_DEPARMENT)
					.trim());
			location.setChannelId(rs.getString(Constants.PARAM_CHANNEL_ID)
					.trim());
			location.setScheduleRef(rs.getString(Constants.PARAM_SCHEDULE_REF)
					.trim());
			location.setLatitude(rs.getString(Constants.PARAM_LATITUDE).trim());
			location.setLongitude(rs.getString(Constants.PARAM_LONGITUDE)
					.trim());
			return location;
		}
	}

	public List<Lugar> getLugaresByDefault(String distrito,
			List<Preferencia> preferencias) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constants.PARAM_DISTRITO, distrito);
		params.put(Constants.PARAM_PREFERENCIAS, preferencias);

		Map<String, Object> results = simpleJdbcCall2.execute(params);
		List<Lugar> listaLugares = (List<Lugar>) results.get(Constants.RESULT);
		return listaLugares;
	}

	public class LugarMapper implements RowMapper<Lugar> {

		public Lugar mapRow(ResultSet rs, int rowNum) throws SQLException {
			Lugar lugar = new Lugar();
			lugar.setId(rs.getString("lugar_id"));
			lugar.setRazonSocial(rs.getString("razon_social"));
			lugar.setRuc(rs.getString("ruc"));
			lugar.setTelefono(rs.getString("telefono"));
			lugar.setDireccion(rs.getString("direccion"));
			lugar.setRepresentanteLegal(rs.getString("representante_legal"));
			lugar.setEmail(rs.getString("email"));
			lugar.setPaginaWeb(rs.getString("pagina_web"));

			MacroPlace mp = new MacroPlace();
			mp.setDistrito(rs.getString("distrito"));
			mp.setDepartamento(rs.getString("departamento"));
			mp.setProvincia(rs.getString("provincia"));
			mp.setPais(rs.getString("pais"));
			lugar.setMacroPlace(mp);

			List<TipoLugar> tipoLugar = new ArrayList<TipoLugar>();
			lugar.setTipoLugar(tipoLugar);

			Ubicacion ub = new Ubicacion();
			ub.setCodigoPostal(rs.getString("codigo_postal"));
			ub.setLatitude("latitude");
			ub.setLongitude("longitude");
			lugar.setUbicacion(ub);

			return lugar;
		}

	}
}
