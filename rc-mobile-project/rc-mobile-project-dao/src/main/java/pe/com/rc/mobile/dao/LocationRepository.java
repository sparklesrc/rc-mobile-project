package pe.com.rc.mobile.dao;

import java.util.List;
import pe.com.rc.mobile.model.Location;
import pe.com.rc.mobile.model.Lugar;
import pe.com.rc.mobile.model.Preferencia;

public interface LocationRepository {

	List<Location> listLocations();

	List<Lugar> listarLugaresByDefault(String distrito,
			List<Preferencia> preferencias);
}
