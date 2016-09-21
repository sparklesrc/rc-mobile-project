package pe.com.rc.mobile.service.location.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.rc.mobile.dao.LocationRepository;
import pe.com.rc.mobile.model.Location;
import pe.com.rc.mobile.service.location.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	public List<Location> listLocations() {
		System.out.println("LocationServiceImpl");
		List<Location> listaLocations = null;

		try {
			listaLocations = locationRepository.listLocations();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return listaLocations;
	}

}
