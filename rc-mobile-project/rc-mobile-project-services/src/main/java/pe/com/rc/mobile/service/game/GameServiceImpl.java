package pe.com.rc.mobile.service.game;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.dao.GameDAO;
import pe.com.rc.mobile.model.Game;
import pe.com.rc.mobile.model.game.ListActiveGameResponse;

@Service
public class GameServiceImpl implements GameService {

	private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

	@Autowired
	private GameDAO gameDAO;

	public List<ListActiveGameResponse> getActiveGames() throws ServiceException {
		List<Game> games = null;
		try {
			games = gameDAO.all();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ServiceException("Error in " + e.getMessage());
		}
		return prepareActiveGameResponse(games);
	}

	private List<ListActiveGameResponse> prepareActiveGameResponse(List<Game> games) {
		List<ListActiveGameResponse> activeGames = new ArrayList<ListActiveGameResponse>();
		for (Game g : games) {
			ListActiveGameResponse lag = new ListActiveGameResponse();
			lag.setGameId(g.getId().intValue());
			lag.setName(g.getName());
			activeGames.add(lag);
		}
		return activeGames;
	}
}
