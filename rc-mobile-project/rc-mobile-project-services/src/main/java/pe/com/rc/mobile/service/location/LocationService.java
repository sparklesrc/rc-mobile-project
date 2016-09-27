package pe.com.rc.mobile.service.location;

import java.util.List;

import pe.com.rc.mobile.model.Location;
import pe.com.rc.mobile.model.Lugar;
import pe.com.rc.mobile.model.LugarRq;

public interface LocationService {

	List<Location> listLocations();

	List<Lugar> listarLugaresByDefault(LugarRq lugarRq);

}
