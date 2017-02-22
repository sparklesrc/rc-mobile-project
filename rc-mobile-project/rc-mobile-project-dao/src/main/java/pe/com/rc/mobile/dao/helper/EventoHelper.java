package pe.com.rc.mobile.dao.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import pe.com.rc.mobile.dao.util.Constants;
import pe.com.rc.mobile.model.Evento;
import pe.com.rc.mobile.model.Location;

@SuppressWarnings("all")
public class EventoHelper {

	private SimpleJdbcCall simpleJdbcCall;

	public EventoHelper(JdbcTemplate jdbcTemplate) {
		this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(Constants.PROC_GET_EVENTOS_BY_PLACE);
		simpleJdbcCall.declareParameters(new SqlReturnResultSet(
				Constants.RESULT, new EventoMapper()));
	}

	public class EventoMapper implements RowMapper<Evento> {
		public Evento mapRow(ResultSet rs, int arg1) throws SQLException {
			Evento evento = new Evento();
			evento.setId(Long.parseLong(rs.getString("").trim()));
			evento.setFecha(rs.getString("").trim());
			evento.setHoraInicio(rs.getString("").trim());
			evento.setHoraFin(rs.getString("").trim());
			evento.setPrecio(rs.getString("").trim());
			evento.setDescripcion(rs.getString("").trim());
			return evento;
		}
	}
}
