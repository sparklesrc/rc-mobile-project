package pe.com.rc.mobile.web.testing;

import java.util.ArrayList;
import java.util.List;

import pe.com.rc.mobile.model.TipoLugar;

public class LogTest {

	/**
	 * Test Logger
	 * 
	 * @param x
	 */
	public static void main(String... x) {

		SimpleClass sc = new SimpleClass();
		// sc.printLog();
		List<TipoLugar> tipoLugar = new ArrayList();
		TipoLugar a = new TipoLugar();
		a.setId("1");
		a.setDescripcion("BAR");

		TipoLugar b = new TipoLugar();
		b.setId("2");
		b.setDescripcion("DISCOTECA");

		TipoLugar c = new TipoLugar();
		c.setId("3");
		c.setDescripcion("PUB");

		TipoLugar d = new TipoLugar();
		d.setId("4");
		d.setDescripcion("RESTOBAR");

		TipoLugar e = new TipoLugar();
		e.setId("5");
		e.setDescripcion("OTROS");

		tipoLugar.add(a);
		tipoLugar.add(b);
		tipoLugar.add(c);
		tipoLugar.add(d);
		tipoLugar.add(e);

		String aaa = sc.getValores(tipoLugar);
		System.out.println(aaa);
	}
}
