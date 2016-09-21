package pe.com.rc.mobile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.com.rc.mobile.dao.helper.LocationHelper;
import pe.com.rc.mobile.model.Location;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/spring/context-test.xml" })
public class LocationRepositoryImplTest {

	@Autowired
	@Qualifier("dbDataSource")
	private DataSource dataSource;

	private LocationHelper locationHelper;

	@Test
	public void testSanidad() {
		assertThat(dataSource, is(not(nullValue())));
	}

	// @Test
	// public void listLocationsTest() {
	//
	// System.out.println("LocationRepositoryImpl");
	// JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	//
	// System.out.println("LocationRepositoryImpl  - 2");
	// locationHelper = new LocationHelper(jdbcTemplate);
	//
	// System.out.println("LocationRepositoryImpl  - 3");
	// List<Location> listLocations = locationHelper.getLocations();
	//
	// System.out.println("LocationRepositoryImpl  - 4");
	//
	// }
}
