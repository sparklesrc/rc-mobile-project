package pe.com.rc.mobile.service.user;

import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.model.clan.UserReqRes.AcceptClanRequest;

public interface UserService {

	void processClanRequest(AcceptClanRequest request) throws ServiceException;

}
