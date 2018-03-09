package pe.com.rc.mobile.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.model.game.ListActiveGameResponse;
import pe.com.rc.mobile.service.game.GameService;
import pe.com.rc.mobile.web.util.Constants;

@RestController
@RequestMapping(Constants.URL_BASE)
public class GameController {

	@Autowired
	private GameService gameService;

	// LISTAR ACTIVE GAMES
	@RequestMapping(value = "/game/getActiveGames", method = RequestMethod.POST, produces = { "application/json" })
	public List<ListActiveGameResponse> getActiveGames() throws ServiceException {
		return gameService.getActiveGames();
	}
}
