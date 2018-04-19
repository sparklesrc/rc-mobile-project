package pe.com.rc.mobile.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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

	public UserGameProfile findByUserIdAndGameId(Long userId, Long gameId) throws DaoException {
		Criteria criteria = this.getSession().createCriteria(UserGameProfile.class);
		criteria.add(Restrictions.eq("user.id", userId))
				.add(Restrictions.eq("game.id", gameId))
				.add(Restrictions.eq("active", 1));
		return (UserGameProfile) criteria.uniqueResult();
	}

}
