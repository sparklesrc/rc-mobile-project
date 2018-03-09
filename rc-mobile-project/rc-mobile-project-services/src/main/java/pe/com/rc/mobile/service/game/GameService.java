package pe.com.rc.mobile.service.game;

import java.util.List;
import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.model.game.ListActiveGameResponse;

public interface GameService {

	List<ListActiveGameResponse> getActiveGames() throws ServiceException;
}
