package pe.com.rc.mobile.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.model.clan.ListClanResponse;
import pe.com.rc.mobile.model.clan.TeamSearch.AcceptMemberRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.AssignRoleRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.CandidatesRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.CandidatesResponse;
import pe.com.rc.mobile.model.clan.TeamSearch.DropMemberRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.PostularRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.RankTeamRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.RecruitRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.SearchRecruit;
import pe.com.rc.mobile.model.clan.TeamSearch.SearchRecruitResult;
import pe.com.rc.mobile.model.clan.TeamSearch.TeamBuildRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.TeamDeleteRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.TeamSearchRequest;
import pe.com.rc.mobile.model.clan.TeamSearch.TeamSearchResponse;
import pe.com.rc.mobile.model.clan.UserReqRes.GenericResponse;
import pe.com.rc.mobile.service.clan.ClanService;
import pe.com.rc.mobile.web.util.Constants;

@RestController
@RequestMapping(Constants.URL_BASE)
public class ClanController {

	@Autowired
	private ClanService clanService;

	// LISTAR TODOS LOS CLANES
	@RequestMapping(value = "/team/list", method = RequestMethod.POST, produces = { "application/json" })
	public List<ListClanResponse> listClan() throws ServiceException {
		return clanService.listClanes();
	}

	// DETALLES DEL CLAN - LISTAR MIEMBROS
	@RequestMapping(value = "/team/search", method = RequestMethod.POST, produces = { "application/json" })
	public TeamSearchResponse getTeam(@RequestBody TeamSearchRequest request) throws ServiceException {
		return clanService.getTeam(request);
	}

	// CREAR TEAM
	@RequestMapping(value = "/team/build", method = RequestMethod.POST, produces = { "application/json" })
	public GenericResponse buildTeam(@RequestBody TeamBuildRequest request) throws ServiceException {
		return clanService.buildTeam(request);
	}

	// ELIMINAR CLAN
	@RequestMapping(value = "/team/eliminar", method = RequestMethod.POST, produces = { "application/json" })
	public GenericResponse deleteTeam(@RequestBody TeamDeleteRequest request) throws ServiceException {
		return clanService.deleteTeam(request);
	}

	// POSTULAR
	@RequestMapping(value = "/team/postular", method = RequestMethod.POST, produces = { "application/json" })
	public void getCandidates(@RequestBody PostularRequest request) throws ServiceException {
		clanService.postular(request);
	}

	// RECLUTAR
	@RequestMapping(value = "/team/recruit", method = RequestMethod.POST, produces = { "application/json" })
	public GenericResponse recruit(@RequestBody RecruitRequest request) throws ServiceException {
		return new GenericResponse(clanService.recruitPlayer(request));
	}

	// ACCEPT CLAN MEMBER
	@RequestMapping(value = "/team/acceptMember", method = RequestMethod.POST, produces = { "application/json" })
	public void acceptMember(@RequestBody AcceptMemberRequest request) throws ServiceException {
		clanService.acceptPlayer(request);
	}

	// DROP CLAN MEMBER
	@RequestMapping(value = "/team/dropMember", method = RequestMethod.POST, produces = { "application/json" })
	public void dropMember(@RequestBody DropMemberRequest request) throws ServiceException {
		clanService.dropMember(request);
	}

	// GET CANDIDATES
	@RequestMapping(value = "/team/candidates", method = RequestMethod.POST, produces = { "application/json" })
	public List<CandidatesResponse> getCandidates(@RequestBody CandidatesRequest request) throws ServiceException {
		return clanService.getCandidates(request);
	}

	// RANK CLAN
	@RequestMapping(value = "/team/rank", method = RequestMethod.POST, produces = { "application/json" })
	public void rankClan(@RequestBody RankTeamRequest request) throws ServiceException {
		clanService.rankTeam(request);
	}

	// CAMBIAR ROL
	@RequestMapping(value = "/team/assign", method = RequestMethod.POST, produces = { "application/json" })
	public void assignRole(@RequestBody AssignRoleRequest request) throws ServiceException {
		clanService.assignRole(request);
	}

	// LISTAR RECLUTAR SEARCH
	@RequestMapping(value = "/team/searchRecruit", method = RequestMethod.POST, produces = { "application/json" })
	public List<SearchRecruitResult> searchRecruit(@RequestBody SearchRecruit request) throws ServiceException {
		return clanService.listRecruitResult(request);
	}

	// LISTAR ULTIMOS COMENTARIOS

	// USER ACCPETS REQUEST
}
