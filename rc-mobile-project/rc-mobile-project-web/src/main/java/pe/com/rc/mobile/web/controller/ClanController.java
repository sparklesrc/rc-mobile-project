package pe.com.rc.mobile.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.BuildClanRequest;
import pe.com.rc.mobile.model.clan.Clan;
import pe.com.rc.mobile.model.clan.ClanMembersResponse;
import pe.com.rc.mobile.model.clan.ListClanResponse;
import pe.com.rc.mobile.model.clan.TeamSearch.TeamBuildRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.TeamSearchRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.TeamSearchResponse;
import pe.com.rc.mobile.service.clan.ClanService;
import pe.com.rc.mobile.web.util.Constants;

@RestController
@RequestMapping(Constants.URL_BASE)
public class ClanController {

	@Autowired
	private ClanService clanService;

	// GET MEMBERS BY CLAN
	@RequestMapping(value = Constants.URL_CLAN + Constants.URL_GET_CLAN_MEMBERS, method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody List<ClanMembersResponse> listarLugaresDefault(
			@RequestParam Integer clanId) throws ServiceException {
		System.out.println("clanId :: " + clanId);
		return clanService.getMembersByClan(new Long(clanId));
	}

	// CREAR CLAN
	@RequestMapping(value = Constants.URL_CLAN + Constants.URL_BUILD, method = RequestMethod.POST, produces = { "application/json" })
	public void buildClan(@RequestBody BuildClanRequest request)
			throws ServiceException {
		clanService.buildClan(request);
	}

	// ACCEPT CLAN MEMBER

	// DROP CLAN MEMBER

	@RequestMapping(value = Constants.URL_CLAN + "/list", method = RequestMethod.POST, produces = { "application/json" })
	public List<ListClanResponse> listClan() throws ServiceException {
		return clanService.listClanes();
	}

	@RequestMapping(value = "/team/search", method = RequestMethod.POST, produces = { "application/json" })
	public TeamSearchResponse getTeam(@RequestBody TeamSearchRequest request)
			throws ServiceException {
		return clanService.getTeam(request);
	}

	@RequestMapping(value = "/team/build", method = RequestMethod.POST, produces = { "application/json" })
	public void buildTeam(@RequestBody TeamBuildRequest request)
			throws ServiceException {
		clanService.buildTeam(request);
	}
}
