package pe.com.rc.mobile.service.clan;

import java.util.List;

import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.BuildClanRequest;
import pe.com.rc.mobile.model.clan.Clan;

public interface ClanService {

	List<User> getMembersByClan(Clan clan);

	void buildClan(BuildClanRequest request);
}
