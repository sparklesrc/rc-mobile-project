package pe.com.rc.mobile.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import pe.com.rc.mobile.dao.SolicitudeTypeDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.SolicitudeType;

public class SolicitudeTypeDAOH extends BaseHibernateDAO implements SolicitudeTypeDAO{

	public SolicitudeType find(SolicitudeType t) {
		Criteria criteria = this.getSession().createCriteria(SolicitudeType.class);
		criteria.add(Restrictions.eq("id", t.getId()));
		return (SolicitudeType) criteria.uniqueResult();
	}

	public List<SolicitudeType> all() {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(SolicitudeType t) {
		// TODO Auto-generated method stub
		
	}

	public void update(SolicitudeType t) {
		// TODO Auto-generated method stub
		
	}

	public void delete(SolicitudeType t) {
		// TODO Auto-generated method stub
		
	}
}
