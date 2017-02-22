package pe.com.rc.mobile.dao;

import java.util.List;

import pe.com.rc.mobile.model.Evento;

public interface EventoRepository {

	List<Evento> listarEventosPorLugar(String idLugar);
}
