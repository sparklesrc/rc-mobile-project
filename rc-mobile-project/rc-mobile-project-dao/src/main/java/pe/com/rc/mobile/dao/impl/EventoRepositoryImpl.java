package pe.com.rc.mobile.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import pe.com.rc.mobile.dao.EventoRepository;
import pe.com.rc.mobile.model.Evento;

//@Repository
public class EventoRepositoryImpl implements EventoRepository {

//	@Autowired
//	@Qualifier("dbDataSource")
	private DataSource dataSource;

	public List<Evento> listarEventosPorLugar(String idLugar) {
		// TODO Auto-generated method stub
		return null;
	}

}
