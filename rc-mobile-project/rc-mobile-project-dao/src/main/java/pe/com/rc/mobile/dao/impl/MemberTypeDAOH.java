package pe.com.rc.mobile.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pe.com.rc.mobile.dao.MemberTypeDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.MemberType;

@Repository
public class MemberTypeDAOH extends BaseHibernateDAO implements MemberTypeDAO {

	public MemberType find(MemberType t) {
		Criteria criteria = this.getSession().createCriteria(MemberType.class);
		criteria.add(Restrictions.eq("id", t.getId()));
		return (MemberType) criteria.uniqueResult();
	}

	public List<MemberType> all() {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(MemberType t) {
		// TODO Auto-generated method stub

	}

	public void update(MemberType t) {
		// TODO Auto-generated method stub

	}

	public void delete(MemberType t) {
		// TODO Auto-generated method stub

	}

}
