package pe.com.rc.mobile.dao.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import pe.com.rc.mobile.model.Location;

public class LocationHelper {

	private SimpleJdbcCall simpleJdbcCall;

	public LocationHelper(JdbcTemplate jdbcTemplate) {
		this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("PROC_GET_LOCATIONS");
		simpleJdbcCall.declareParameters(new SqlReturnResultSet("RS",
				new LocationMapper()));
	}

	public List<Location> getLocations() {
		Map<String, Object> results = simpleJdbcCall.execute();
		List<Location> appVersionRs = (List<Location>) results.get("RS");
		return appVersionRs;
	}

	public class LocationMapper implements RowMapper<Location> {
		public Location mapRow(ResultSet rs, int arg1) throws SQLException {
			Location location = new Location();
			location.setLocationId(rs.getString("locationId"));
			location.setLocationName(rs.getString("locationName").trim());
			location.setAddress(rs.getString("address").trim());
			location.setDistrict(rs.getString("district").trim());
			location.setProvince(rs.getString("province").trim());
			location.setDepartment(rs.getString("department").trim());
			location.setLatitude(rs.getString("latitude").trim());
			location.setLongitude(rs.getString("longitude").trim());
			return location;
		}
	}
}
