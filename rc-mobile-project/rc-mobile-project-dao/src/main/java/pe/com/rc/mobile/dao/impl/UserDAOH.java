package pe.com.rc.mobile.dao.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.dao.UserDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.User;

@Repository
public class UserDAOH extends BaseHibernateDAO implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAOH.class);
	
	public User find(User t) {
		Criteria criteria = this.getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("id", t.getId()));
		return (User) criteria.uniqueResult();
	}

	public List<User> all() {
		Criteria criteria = this.getSession().createCriteria(User.class);
		return criteria.list();
	}

	public void save(User t) {
		this.getSession().save(t);
	}

	public void update(User t) {
		this.getSession().merge(t);
	}

	public void delete(User t) {
		this.getSession().delete(t);
	}

	public User findByMail(String mail) throws DaoException {
		try {
			Criteria criteria = this.getSession().createCriteria(User.class);
			criteria.add(Restrictions.eq("mail", mail));
			return (User) criteria.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("Error al obtener el usuario por mail." + mail);
		}
	}

	public void updateSteamId(Long userId, Long steamId) throws DaoException {
		try {
			Query query = getSession()
					.createSQLQuery("UPDATE user SET steam_id = :valor1, update_date = :valor2 where id = :userId");
			query.setParameter("valor1", steamId);
			query.setParameter("valor2", new Date());
			query.setParameter("userId", userId);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DaoException("Error al Actualizar SteamId para Usuario." + userId + "con SteamId " + steamId);
		}
	}

	public List<User> getCandidates() throws DaoException {
		return null;
	}
}
