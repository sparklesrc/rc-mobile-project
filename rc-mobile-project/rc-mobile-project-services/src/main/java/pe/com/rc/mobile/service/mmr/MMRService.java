package pe.com.rc.mobile.service.mmr;

import java.util.List;

import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRBuildRequest;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRCancelRequest;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRSearchRequest;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRSearchResponse;
import pe.com.rc.mobile.model.clan.MMRSearch.PendingMMRByTeam;

public interface MMRService {

	void buildMMR(MMRBuildRequest request) throws ServiceException;

	void acceptMMR(MMRBuildRequest request) throws ServiceException;

	List<MMRSearchResponse> listMMR(MMRSearchRequest request) throws ServiceException;

	void cancelMMR(MMRCancelRequest request) throws ServiceException;

	List<MMRSearchResponse> listPendingMMR(PendingMMRByTeam request) throws ServiceException;
}
