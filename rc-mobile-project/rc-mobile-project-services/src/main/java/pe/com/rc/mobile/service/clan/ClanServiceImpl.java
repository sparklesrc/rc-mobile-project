package pe.com.rc.mobile.service.clan;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.rc.mobile.dao.ClanDAO;
import pe.com.rc.mobile.dao.GameDAO;
import pe.com.rc.mobile.model.Game;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.BuildClanRequest;
import pe.com.rc.mobile.model.clan.Clan;

@Service
public class ClanServiceImpl implements ClanService {

	@Autowired
	private ClanDAO clanDAO;
	@Autowired
	private GameDAO gameDAO;

	public List<User> getMembersByClan(Clan clan) {
		List<User> members = clanDAO.getMembersByClan(clan);
		return members;
	}

	public void buildClan(BuildClanRequest request) {
		clanDAO.save(prepareClan(request));
	}

	private Clan prepareClan(BuildClanRequest request) {
		Game game = gameDAO.find(new Game(request.getGameId()));
		Clan clan = new Clan();
		clan.setGame(game);
		clan.setActive(1);
		clan.setName(request.getName());
		clan.setDecription(request.getDescription());
		clan.setStarsNumber(0);
		clan.setCreateDate(new Date());
		return clan;
	}

}
