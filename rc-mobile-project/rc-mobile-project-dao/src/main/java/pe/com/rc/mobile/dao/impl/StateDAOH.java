package pe.com.rc.mobile.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pe.com.rc.mobile.dao.StateDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.State;

@Repository
public class StateDAOH extends BaseHibernateDAO implements StateDAO {

	public State find(State t) {
		Criteria criteria = this.getSession().createCriteria(State.class);
		criteria.add(Restrictions.eq("id", t.getId()));
		return (State) criteria.uniqueResult();
	}

	public List<State> all() {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(State t) {
		// TODO Auto-generated method stub

	}

	public void update(State t) {
		// TODO Auto-generated method stub

	}

	public void delete(State t) {
		// TODO Auto-generated method stub

	}

}
