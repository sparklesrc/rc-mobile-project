package pe.com.rc.mobile.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.dao.LugarRepository;
import pe.com.rc.mobile.dao.helper.LugarHelper;
import pe.com.rc.mobile.model.Lugar;
import pe.com.rc.mobile.model.MusicaLugar;
import pe.com.rc.mobile.model.TipoLugar;

//@Repository
public class LugarRepositoryImpl implements LugarRepository {

	private static final Logger logger = LoggerFactory
			.getLogger(LugarRepositoryImpl.class);

//	@Autowired
//	@Qualifier("dbDataSource")
	private DataSource dataSource;

	private LugarHelper lugarHelper;

	public List<Lugar> listarLugaresByDefault(String distrito,
			List<TipoLugar> tipoLugar, List<MusicaLugar> musicaLugar,
			String idUsuario) throws DaoException {

		logger.info("LugarRepositoryImpl.listarLugaresByDefault");

		List<Lugar> lugares = new ArrayList(0);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		lugarHelper = new LugarHelper(jdbcTemplate);
		lugares = lugarHelper.getLugaresByDefault(distrito, tipoLugar,
				musicaLugar);

		return lugares;
	}
}
