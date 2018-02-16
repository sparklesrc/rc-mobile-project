package pe.com.rc.mobile.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRBuildRequest;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRCancelRequest;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRSearchRequest;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRSearchResponse;
import pe.com.rc.mobile.model.clan.MMRSearch.PendingMMRByTeam;
import pe.com.rc.mobile.service.mmr.MMRService;
import pe.com.rc.mobile.web.util.Constants;

@RestController
@RequestMapping(Constants.URL_BASE)
public class MatchMakingController {

	@Autowired
	MMRService mmrService;

	// CREAR MATCHMAKING
	@RequestMapping(value = "/mmr/build", method = RequestMethod.POST, produces = { "application/json" })
	public void buildMMR(@RequestBody MMRBuildRequest request) throws ServiceException {
		mmrService.buildMMR(request);
	}

	// ACCEPT MATCHMAKING
	@RequestMapping(value = "/mmr/accept", method = RequestMethod.POST, produces = { "application/json" })
	public void acceptMMR(@RequestBody MMRBuildRequest request) throws ServiceException {
		mmrService.acceptMMR(request);
	}

	// TODO: DEFINIR CRITERIOS DE BUSQUEDA
	// SEARCH MATCHMAKING
	@RequestMapping(value = "/mmr/list", method = RequestMethod.POST, produces = { "application/json" })
	public List<MMRSearchResponse> searchMMR(@RequestBody MMRSearchRequest request) throws ServiceException {
		return mmrService.listMMR(request);
	}

	// CANCEL MATCHMAKING
	@RequestMapping(value = "/mmr/cancel", method = RequestMethod.POST, produces = { "application/json" })
	public void cancelMMR(@RequestBody MMRCancelRequest request) throws ServiceException {
		mmrService.cancelMMR(request);
	}

	// MOSTRAR MATCHS PENDIENTES DE JUEGO CERCA A LA ZONA
	
	// LISTAR MMR PENDIENTES POR CLAN
	@RequestMapping(value = "/mmr/team", method = RequestMethod.POST, produces = { "application/json" })
	public List<MMRSearchResponse> mmrByTeam(@RequestBody PendingMMRByTeam request) throws ServiceException {
		return mmrService.listPendingMMR(request);
	}
}
