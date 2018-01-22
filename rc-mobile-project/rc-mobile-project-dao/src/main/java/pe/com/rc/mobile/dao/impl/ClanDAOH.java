package pe.com.rc.mobile.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pe.com.rc.mobile.dao.ClanDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.Clan;

@Repository
public class ClanDAOH extends BaseHibernateDAO implements ClanDAO {

	public Clan find(Clan t) {
		Criteria criteria = this.getSession().createCriteria(Clan.class);
		criteria.add(Restrictions.eq("id", t.getId()));
		return (Clan) criteria.uniqueResult();
	}

	public List<Clan> all() {
		Criteria criteria = this.getSession().createCriteria(Clan.class);
        return criteria.list();
	}

	public void save(Clan t) {
		this.getSession().save(t);
	}

	public void update(Clan t) {
		this.getSession().merge(t);
	}

	public void delete(Clan t) {
		this.getSession().delete(t);
	}

}
