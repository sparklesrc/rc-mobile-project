package pe.com.rc.mobile.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import pe.com.rc.mobile.dao.SolicitudeDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.Game;
import pe.com.rc.mobile.model.Solicitude;
import pe.com.rc.mobile.model.State;
import pe.com.rc.mobile.model.clan.Clan;

public class SolicitudeDAOH extends BaseHibernateDAO implements SolicitudeDAO{

	public Solicitude find(Solicitude t) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Solicitude> all() {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Solicitude t) {
		this.getSession().save(t);
	}

	public void update(Solicitude t) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Solicitude t) {
		// TODO Auto-generated method stub
		
	}

	public List<Solicitude> getSolicitudesByClanAndState(Clan clan, State state, Integer active) {
		Criteria criteria = this.getSession().createCriteria(Solicitude.class);
		criteria.add(Restrictions.eq("clan.id", clan.getId())).add(Restrictions.eq("state.id", state.getId()))
				.add(Restrictions.eq("active", 1));
		return criteria.list();
	}
}
