package pe.com.rc.mobile.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rc.mobile.model.Usuario;
import pe.com.rc.mobile.web.util.Constants;

@RestController
@RequestMapping(Constants.URL_BASE)
public class LoginController {

	@RequestMapping(value = Constants.URL_LOGIN, method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody Usuario login(@RequestHeader HttpHeaders headers) {
		headers.get("valor");
		return null;
	}
}
