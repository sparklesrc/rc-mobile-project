package pe.com.rc.mobile.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pe.com.rc.mobile.model.App;

@RestController
@RequestMapping("/test")
public class HelloWorldController {
	
	@RequestMapping(value = "/holaMundo", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody App prueba() {
		App p = new App();
		p.setMessage("hola1");
		return p;
	}
}
