package pe.com.rc.mobile.dao;

import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.dao.helper.GenericDAO;
import pe.com.rc.mobile.model.UserGameProfile;

public interface UserGameProfileDAO extends GenericDAO<UserGameProfile> {

	UserGameProfile findByUserIdAndGameId(Long userId, Long gameId) throws DaoException;
}
