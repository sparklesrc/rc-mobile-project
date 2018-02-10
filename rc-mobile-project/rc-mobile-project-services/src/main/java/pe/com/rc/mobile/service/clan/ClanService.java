package pe.com.rc.mobile.service.clan;

import java.util.List;

import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.BuildClanRequest;
import pe.com.rc.mobile.model.clan.Clan;
import pe.com.rc.mobile.model.clan.ClanMembersResponse;
import pe.com.rc.mobile.model.clan.ListClanResponse;
import pe.com.rc.mobile.model.clan.TeamSearch.TeamBuildRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.TeamSearchRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.TeamSearchResponse;

public interface ClanService {

	List<ClanMembersResponse> getMembersByClan(Long clanId);

	void buildClan(BuildClanRequest request);

	List<ListClanResponse> listClanes();

	TeamSearchResponse getTeam(TeamSearchRequest request);
	
	void buildTeam(TeamBuildRequest request);
}
