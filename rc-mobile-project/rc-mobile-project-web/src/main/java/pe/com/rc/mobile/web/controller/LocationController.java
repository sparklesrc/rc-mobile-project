package pe.com.rc.mobile.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rc.mobile.model.Location;
import pe.com.rc.mobile.service.location.LocationService;

@RestController
@RequestMapping("/location")
public class LocationController {

	@Autowired
	LocationService locationService;

	@RequestMapping(value = "/listAll", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody List<Location> listLocations() {
		System.out.println("LIST ALL");
		 List<Location> listaLocacitons = locationService.listLocations();
//		List<Location> listaLocacitons = getLista();
		return listaLocacitons;
	}

	private List<Location> getLista() {
		List<Location> listaLocacitons = new ArrayList<>();

		Location l1 = new Location();
		l1.setAddress("Address 1");
		l1.setDepartment("Department 1");

		Location l2 = new Location();
		l2.setAddress("Address 2");
		l2.setDepartment("Department 2");

		listaLocacitons.add(l1);
		listaLocacitons.add(l2);

		return listaLocacitons;
	}
}
