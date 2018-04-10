package pe.com.rc.mobile.dao;

import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.dao.helper.GenericDAO;
import pe.com.rc.mobile.model.User;

public interface UserDAO extends GenericDAO<User> {

	User findActiveUserByMail(String mail) throws DaoException;

	User findByMail(String mail) throws DaoException;

	void updateSteamId(Long userId, Long steamId) throws DaoException;

}
