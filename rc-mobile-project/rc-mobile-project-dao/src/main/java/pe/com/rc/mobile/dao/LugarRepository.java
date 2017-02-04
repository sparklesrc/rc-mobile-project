package pe.com.rc.mobile.dao;

import java.util.List;

import pe.com.rc.mobile.model.Lugar;
import pe.com.rc.mobile.model.MusicaLugar;
import pe.com.rc.mobile.model.Preferencia;
import pe.com.rc.mobile.model.TipoLugar;

public interface LugarRepository {

	List<Lugar> listarLugaresByDefault(String distrito,
			List<TipoLugar> tipoLugar, List<MusicaLugar> musicaLugar,
			String idUsuario);
}
