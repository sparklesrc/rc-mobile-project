package pe.com.rc.mobile.web.testing;

import java.util.List;

import org.apache.log4j.Logger;

import pe.com.rc.mobile.model.TipoLugar;

public class SimpleClass {

	private Logger logger = Logger.getLogger(SimpleClass.class);

	void printLog() {
		System.out.println("Valores...");
		logger.info("Caso");
		logger.error("Error");
		logger.debug("PRUEBA");
		try {
			int x = 9 / 0;
		} catch (Exception e) {
			logger.error("Error en division.", e);
		}
	}

	String getValores(List<TipoLugar> tipoLugar) {
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
}
