package pe.com.rc.mobile.service.clan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.rc.mobile.dao.ClanDAO;
import pe.com.rc.mobile.model.Clan;
import pe.com.rc.mobile.model.User;

@Service
public class ClanServiceImpl implements ClanService{

	@Autowired
	private ClanDAO clanDAO;

	public List<User> getMembersByClan(Clan clan) {
		List<User> members = clanDAO.getMembersByClan(clan);
		return members;
	}
	
	

}
