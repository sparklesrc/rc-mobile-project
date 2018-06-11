package pe.com.rc.mobile.service.clan;

import java.util.List;

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

public interface ClanService {

	List<ListClanResponse> listClanes() throws ServiceException;

	TeamSearchResponse getTeam(TeamSearchRequest request);

	GenericResponse buildTeam(TeamBuildRequest request) throws ServiceException;

	GenericResponse deleteTeam(TeamDeleteRequest request) throws ServiceException;

	String recruitPlayer(RecruitRequest request) throws ServiceException;

	void acceptPlayer(AcceptMemberRequest request) throws ServiceException;

	List<CandidatesResponse> getCandidates(CandidatesRequest request);

	void dropMember(DropMemberRequest request);

	void postular(PostularRequest request);

	void rankTeam(RankTeamRequest request) throws ServiceException;

	void assignRole(AssignRoleRequest request);

	List<ListClanResponse> getTeamsByUser(Long userId) throws ServiceException;

	List<SearchRecruitResult> listRecruitResult(SearchRecruit request) throws ServiceException;
}
