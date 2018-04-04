package pe.com.rc.mobile.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.dao.UserGameProfileDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.UserGameProfile;

@Repository
public class UserGameProfileDAOH extends BaseHibernateDAO implements UserGameProfileDAO {

	public UserGameProfile find(UserGameProfile t) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserGameProfile> all() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(UserGameProfile t) {
		this.getSession().save(t);
	}

	public void update(UserGameProfile t) throws DaoException {
		// TODO Auto-generated method stub

	}

	public void delete(UserGameProfile t) {
		// TODO Auto-generated method stub

	}

}
