package pe.com.rc.mobile.service.evento;

import java.util.List;

import pe.com.rc.mobile.model.Evento;
import pe.com.rc.mobile.model.Lugar;

public interface EventoService {

	List<Evento> listarEventosPorLugar(Lugar lugar);
}
