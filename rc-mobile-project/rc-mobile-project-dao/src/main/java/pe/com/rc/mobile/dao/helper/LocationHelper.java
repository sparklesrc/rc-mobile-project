package pe.com.rc.mobile.dao.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.annotation.Transactional;

import pe.com.rc.mobile.dao.util.Constants;
import pe.com.rc.mobile.model.Location;

@SuppressWarnings("all")
public class LocationHelper {

	private SimpleJdbcCall simpleJdbcCall;

	public LocationHelper(JdbcTemplate jdbcTemplate) {
		this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(Constants.PROC_GET_LOCATIONS);
		simpleJdbcCall.declareParameters(new SqlReturnResultSet(
				Constants.RESULT, new LocationMapper()));
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
			location.setLocationName(rs.getString(Constants.PARAM_LOCATION_NAME).trim());
			location.setStoreId(rs.getString(Constants.PARAM_STORE_ID).trim());
			location.setAddress(rs.getString(Constants.PARAM_ADDRESS).trim());
			location.setAddressFind(rs.getString(Constants.PARAM_ADDRESS_FIND).trim());
			location.setDistrict(rs.getString(Constants.PARAM_DISTRICT).trim());
			location.setDistrictFind(rs.getString(Constants.PARAM_DISTRICT_FIND).trim());
			location.setProvince(rs.getString(Constants.PARAM_PROVINCE).trim());
			location.setDepartment(rs.getString(Constants.PARAM_DEPARMENT).trim());
			location.setChannelId(rs.getString(Constants.PARAM_CHANNEL_ID).trim());
			location.setScheduleRef(rs.getString(Constants.PARAM_SCHEDULE_REF).trim());
			location.setLatitude(rs.getString(Constants.PARAM_LATITUDE).trim());
			location.setLongitude(rs.getString(Constants.PARAM_LONGITUDE).trim());
			return location;
		}
	}

	// @Transactional(rollbackFor=Exception.class)
	// public void performBothSProcsTransactionally(){
	// //executeSP1
	//
	// //executeSP2
	// }
}
