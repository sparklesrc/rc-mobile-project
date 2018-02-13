package pe.com.rc.mobile.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import pe.com.rc.mobile.dao.MatchMakingDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.MatchMaking;

public class MatchMakingDAOH extends BaseHibernateDAO implements MatchMakingDAO {

	public MatchMaking find(MatchMaking t) {
		Criteria criteria = this.getSession().createCriteria(MatchMaking.class);
		criteria.add(Restrictions.eq("id", t.getId()));
		return (MatchMaking) criteria.uniqueResult();
	}

	public List<MatchMaking> all() {
		Criteria criteria = this.getSession().createCriteria(MatchMaking.class);
		return criteria.list();
	}

	public void save(MatchMaking t) {
		this.getSession().save(t);
	}

	public void update(MatchMaking t) {
		this.getSession().merge(t);
	}

	public void delete(MatchMaking t) {
		this.getSession().delete(t);
	}

}
