package pe.com.rc.mobile.service.user;

import java.util.List;
import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.model.clan.TeamSearch.RecruitRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.AcceptClanRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.GenericResponse2;
import pe.com.rc.mobile.model.clan.UserReqRes.InvitationsToTeamRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.InvitationsToTeamResponse;
import pe.com.rc.mobile.model.clan.UserReqRes.SignUpCode;
import pe.com.rc.mobile.model.clan.UserReqRes.SignUpGameProfile;
import pe.com.rc.mobile.model.clan.UserReqRes.SignUpRequest;
import pe.com.rc.mobile.model.clan.UserReqRes.UserByMailResp;
import pe.com.rc.mobile.model.clan.UserReqRes.UserGame;

public interface UserService {

	void processClanRequest(AcceptClanRequest request) throws ServiceException;

	GenericResponse2<InvitationsToTeamResponse> getInvitationsTeams(InvitationsToTeamRequest request) throws ServiceException;
	//List<InvitationsToTeamResponse> getInvitationsTeams(InvitationsToTeamRequest request) throws ServiceException;

	UserByMailResp getUserByMail(String mail) throws ServiceException;

	UserByMailResp syncSteamUser(Long userId, String steamId) throws ServiceException;

	String signUp(SignUpRequest request) throws ServiceException;

	String verifyCode(SignUpCode request) throws ServiceException;

	String generateCode(SignUpCode request) throws ServiceException;

	SignUpGameProfile getGameProfile(UserGame request) throws ServiceException;

	String updateGameProfile(SignUpGameProfile request) throws ServiceException;

	boolean userHasTeamByGameId(RecruitRequest request) throws ServiceException;
}
