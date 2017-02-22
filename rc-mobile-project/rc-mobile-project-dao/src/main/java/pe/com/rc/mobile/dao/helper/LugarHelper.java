package pe.com.rc.mobile.dao.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import pe.com.rc.mobile.dao.util.Constants;
import pe.com.rc.mobile.model.Lugar;
import pe.com.rc.mobile.model.MacroPlace;
import pe.com.rc.mobile.model.MusicaLugar;
import pe.com.rc.mobile.model.TipoLugar;
import pe.com.rc.mobile.model.Ubicacion;

@SuppressWarnings("all")
public class LugarHelper {

	private SimpleJdbcCall simpleJdbcCall;

	public LugarHelper(JdbcTemplate jdbcTemplate) {
		this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(Constants.PROC_GET_LUGARES_BY_DEFAULT);
		simpleJdbcCall.declareParameters(new SqlParameter(
				Constants.PARAM_DISTRITO, Types.VARCHAR));
		simpleJdbcCall.declareParameters(new SqlParameter(
				Constants.PARAM_TIPO_LUGAR, Types.ARRAY));
		simpleJdbcCall.declareParameters(new SqlParameter(
				Constants.PARAM_MUSICA_LUGAR, Types.ARRAY));
		simpleJdbcCall.declareParameters(new SqlReturnResultSet(
				Constants.RESULT, new LugarMapper()));
	}

	public List<Lugar> getLugaresByDefault(String distrito,
			List<TipoLugar> tipoLugar, List<MusicaLugar> musicaLugar) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constants.PARAM_DISTRITO, distrito);
		params.put(Constants.PARAM_TIPO_LUGAR, tipoLugar);
		params.put(Constants.PARAM_MUSICA_LUGAR, musicaLugar);

		Map<String, Object> results = simpleJdbcCall.execute(params);
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
