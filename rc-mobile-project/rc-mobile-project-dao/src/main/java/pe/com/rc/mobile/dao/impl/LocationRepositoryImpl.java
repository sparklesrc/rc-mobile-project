package pe.com.rc.mobile.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.rc.mobile.dao.LocationRepository;
import pe.com.rc.mobile.dao.helper.LocationHelper;
import pe.com.rc.mobile.model.Location;

@Repository
public class LocationRepositoryImpl implements LocationRepository {

	@Autowired
	@Qualifier("dbDataSource")
	private DataSource dataSource;

	private LocationHelper locationHelper;

	public List<Location> listLocations() {
		System.out.println("LocationRepositoryImpl");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		System.out.println("LocationRepositoryImpl  - 2");
		locationHelper = new LocationHelper(jdbcTemplate);
		
		System.out.println("LocationRepositoryImpl  - 3");
		List<Location> listLocations = locationHelper.getLocations();
		
		System.out.println("LocationRepositoryImpl  - 4");
		return listLocations;
	}

}
