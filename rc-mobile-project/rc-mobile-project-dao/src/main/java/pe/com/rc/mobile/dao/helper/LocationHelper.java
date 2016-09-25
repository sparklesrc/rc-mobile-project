package pe.com.rc.mobile.dao.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import pe.com.rc.mobile.model.Location;

@SuppressWarnings("all")
public class LocationHelper {

	private SimpleJdbcCall simpleJdbcCall;

	public LocationHelper(JdbcTemplate jdbcTemplate) {
		this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("PROC_GET_LOCATIONS");
		simpleJdbcCall.declareParameters(new SqlReturnResultSet("RS",
				new LocationMapper()));
	}

	public List<Location> getLocations() {
		System.out.println("LocationHelper.getLocations() - INICIO");
		Map<String, Object> results = simpleJdbcCall.execute();
		List<Location> appVersionRs = (List<Location>) results.get("RS");
		System.out.println("LocationHelper.getLocations() - FIN");
		return appVersionRs;
	}

	public class LocationMapper implements RowMapper<Location> {
		public Location mapRow(ResultSet rs, int arg1) throws SQLException {
			Location location = new Location();
			location.setLocationId(rs.getString("location_id"));
			location.setLocationName(rs.getString("location_name").trim());
			location.setStoreId(rs.getString("store_id").trim());
			location.setAddress(rs.getString("address").trim());
			location.setAddressFind(rs.getString("address_find").trim());
			location.setDistrict(rs.getString("district").trim());
			location.setDistrictFind(rs.getString("district_find").trim());
			location.setProvince(rs.getString("province").trim());
			location.setDepartment(rs.getString("deparment").trim());
			location.setChannelId(rs.getString("channel_id").trim());
			location.setScheduleRef(rs.getString("schedule_ref").trim());
			location.setLatitude(rs.getString("latitude").trim());
			location.setLongitude(rs.getString("longitude").trim());
			return location;
		}
	}
}
