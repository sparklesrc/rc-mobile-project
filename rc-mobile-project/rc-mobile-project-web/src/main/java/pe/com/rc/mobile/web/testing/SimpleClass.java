package pe.com.rc.mobile.web.testing;

import org.apache.log4j.Logger;

public class SimpleClass {

	private Logger logger = Logger.getLogger(SimpleClass.class);

	void printLog() {
		System.out.println("Valores...");
		logger.info("Caso");
		logger.error("Error");
		logger.debug("PRUEBA");
		try {
			int x = 9/0;
		} catch (Exception e) {
			logger.error("Error en division.", e);
		}
	}
}
