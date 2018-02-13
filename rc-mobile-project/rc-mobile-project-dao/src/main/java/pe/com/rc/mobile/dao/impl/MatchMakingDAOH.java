package pe.com.rc.mobile.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import pe.com.rc.mobile.dao.MatchMakingDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.MatchMaking;
import pe.com.rc.mobile.model.clan.Clan;

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

	public List<MatchMaking> getMMRsByClan(Clan clan) {
		List<MatchMaking> items = new ArrayList<MatchMaking>();
		try {
			List<Long> ids = Arrays.asList(1L, 2L);
			Query query = this.getSession().createQuery("FROM MatchMaking item WHERE item.state.id IN (:ids)");
			query.setParameterList("ids", ids);
			items = query.list();
		} catch (Exception e) {
			// ERROR BUSCANDO MMRS habilitados para el CLAN
		}
		return items;
	}

}
