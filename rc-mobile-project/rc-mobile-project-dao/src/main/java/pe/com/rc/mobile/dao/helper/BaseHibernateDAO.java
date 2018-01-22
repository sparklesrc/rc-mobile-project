package pe.com.rc.mobile.dao.helper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class BaseHibernateDAO extends HibernateDaoSupport {

	@Autowired
	public void initSession(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public Session getSession() {
		return this.getSessionFactory().getCurrentSession();

	}
}
