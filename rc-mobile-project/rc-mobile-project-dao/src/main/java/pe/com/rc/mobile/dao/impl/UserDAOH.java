package pe.com.rc.mobile.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.core.exception.ServiceException;
import pe.com.rc.mobile.dao.UserDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.User;

@Repository
public class UserDAOH extends BaseHibernateDAO implements UserDAO {

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

}
