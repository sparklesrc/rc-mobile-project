package pe.com.rc.mobile.service.mmr;

import java.util.List;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRBuildRequest;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRCancelRequest;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRSearchRequest;
import pe.com.rc.mobile.model.clan.MMRSearch.MMRSearchResponse;
import pe.com.rc.mobile.model.clan.MMRSearch.PendingMMRByTeam;

public interface MMRService {

	void buildMMR(MMRBuildRequest request);

	void acceptMMR(MMRBuildRequest request);

	List<MMRSearchResponse> listMMR(MMRSearchRequest request);

	void cancelMMR(MMRCancelRequest request);

	List<MMRSearchResponse> listPendingMMR(PendingMMRByTeam request);
}
