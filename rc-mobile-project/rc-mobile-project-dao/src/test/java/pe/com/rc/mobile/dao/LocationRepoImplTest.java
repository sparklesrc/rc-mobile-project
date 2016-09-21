package pe.com.rc.mobile.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.com.rc.mobile.dao.helper.LocationHelper;
import pe.com.rc.mobile.model.Location;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("all")
public class LocationRepoImplTest {

	@Mock
	SimpleJdbcCall simpleJdbcCall;

	@Mock
	LocationHelper locationHelper;

	@Test
	public void testSanidad() {
		assertThat(simpleJdbcCall, is(not(nullValue())));
		assertThat(locationHelper, is(not(nullValue())));
	}

	@Test
	public void testGetLocations() {
		List<Location> expected = new ArrayList<Location>(0);

		Mockito.when(locationHelper.getLocations()).thenReturn(expected);

		List<Location> respo = locationHelper.getLocations();

		Mockito.verify(locationHelper, Mockito.times(1)).getLocations();
		assertThat(respo, not(nullValue()));
	}

}
