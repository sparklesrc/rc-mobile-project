package pe.com.rc.mobile.service.clan;

import java.util.List;

import pe.com.rc.mobile.model.Clan;
import pe.com.rc.mobile.model.User;

public interface ClanService {

	List<User> getMembersByClan(Clan clan);
}
