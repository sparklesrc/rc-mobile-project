package pe.com.rc.mobile.service.user;

import java.util.List;

import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.model.clan.UserReqRes.AcceptClanRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.InvitationsToTeamRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.InvitationsToTeamResponse;
import pe.com.rc.mobile.model.clan.UserReqRes.UserByMailResp;

public interface UserService {

	void processClanRequest(AcceptClanRequest request) throws ServiceException;

	List<InvitationsToTeamResponse> getInvitationsTeams(InvitationsToTeamRequest request) throws ServiceException;

	UserByMailResp getUserByMail(String mail) throws ServiceException;

	UserByMailResp syncSteamUser(Long userId, String steamId) throws ServiceException;
}
