package pe.com.rc.mobile.dao.helper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class LocationHelper {

	private SimpleJdbcCall simpleJdbcCall;

	public LocationHelper(JdbcTemplate jdbcTemplate) {
//		this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
//				.withProcedureName(Constants.SP_GET_APP_VERSION);
//		simpleJdbcCall.declareParameters(new SqlReturnResultSet(
//				Constants.RESULT, new VersionValidatorMapper()));
	}
}
