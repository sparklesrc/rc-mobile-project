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

import pe.com.rc.mobile.dao.helper.LocationHelper.LocationMapper;
import pe.com.rc.mobile.dao.helper.LocationHelper.LugarMapper;
import pe.com.rc.mobile.dao.util.Constants;
import pe.com.rc.mobile.model.Amistad;
import pe.com.rc.mobile.model.Lugar;
import pe.com.rc.mobile.model.MacroPlace;
import pe.com.rc.mobile.model.TipoLugar;
import pe.com.rc.mobile.model.Ubicacion;

@SuppressWarnings("all")
public class AmistadHelper {

	private SimpleJdbcCall simpleJdbcCall;

	public AmistadHelper(JdbcTemplate jdbcTemplate) {
		this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(Constants.PROC_GET_AMISTADES_BY_PLACE);
		simpleJdbcCall.declareParameters(new SqlReturnResultSet(
				Constants.RESULT, new AmistadMapper()));
	}

	public List<Amistad> getAmistadesByPlace(String idLugar, String idUsuario) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constants.PARAM_LUGAR_ID, idLugar);
		params.put(Constants.PARAM_USUARIO_ID, idUsuario);

		Map<String, Object> results = simpleJdbcCall.execute(params);
		List<Amistad> listAmistades = (List<Amistad>) results
				.get(Constants.RESULT);
		return listAmistades;
	}

	public class AmistadMapper implements RowMapper<Amistad> {
		public Amistad mapRow(ResultSet rs, int rowNum) throws SQLException {
			Amistad amistad = new Amistad();
			amistad.setId(rs.getString("amistad_id"));
			amistad.setIdAmistad(rs.getString("usuario_id"));
			return amistad;
		}
	}
}
