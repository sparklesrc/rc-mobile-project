package pe.com.rc.mobile.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

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

@Repository
public class LugarRepositoryImpl implements LugarRepository {

	@Autowired
	@Qualifier("dbDataSource")
	private DataSource dataSource;

	private LugarHelper lugarHelper;

	public List<Lugar> listarLugaresByDefault(String distrito,
			List<TipoLugar> tipoLugar, List<MusicaLugar> musicaLugar,
			String idUsuario) throws DaoException {

		List<Lugar> lugares = new ArrayList(0);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		lugarHelper = new LugarHelper(jdbcTemplate);

		String tiposLugar = getTiposLugar(tipoLugar);
		String musicasLugar = getMusicasLugar(musicaLugar);

		lugares = lugarHelper.getLugaresByDefault(distrito, tiposLugar,
				musicasLugar);

		return lugares;
	}

	private String getTiposLugar(List<TipoLugar> tipoLugar) {
		String valor = "";
		int contador = 1;
		for (TipoLugar tl : tipoLugar) {
			if (contador < tipoLugar.size()) {
				valor += "\'".concat(tl.getDescripcion()).concat("\',");
			} else {
				valor += "\'".concat(tl.getDescripcion()).concat("\'");
			}
			contador++;
		}
		return valor;
	}

	private String getMusicasLugar(List<MusicaLugar> musicaLugar) {
		String valor = "";
		int contador = 1;
		for (MusicaLugar tl : musicaLugar) {
			if (contador < musicaLugar.size()) {
				valor += "\'".concat(tl.getDescripcion()).concat("\',");
			} else {
				valor += "\'".concat(tl.getDescripcion()).concat("\'");
			}
			contador++;
		}
		return valor;
	}
}
