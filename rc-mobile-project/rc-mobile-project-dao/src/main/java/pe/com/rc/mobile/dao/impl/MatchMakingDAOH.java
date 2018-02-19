package pe.com.rc.mobile.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pe.com.rc.mobile.dao.MatchMakingDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.MatchMaking;
import pe.com.rc.mobile.model.State;
import pe.com.rc.mobile.model.clan.Clan;

@Repository
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
//		List<MatchMaking> items = new ArrayList<MatchMaking>();
//		try {
//			List<Long> ids = Arrays.asList(1L, 2L);
//			Query query = this.getSession().createQuery("FROM MatchMaking item WHERE item.state.id IN (:ids)");
//			query.setParameterList("ids", ids);
//			items = query.list();
//		} catch (Exception e) {
//			// ERROR BUSCANDO MMRS habilitados para el CLAN
//		}
//		return items;
		
		Criteria criteria = this.getSession().createCriteria(MatchMaking.class);
		criteria.add(Restrictions.eq("teamA.id", clan.getId())).add(Restrictions.eq("state.id", 1L));
		return criteria.list();
	}

	public void updateMMRState(MatchMaking mmr, State state) {
		Query query = getSession().createSQLQuery(
				"UPDATE MATCH_MAKING SET state_id = :valor1, update_date = :valor2 where id = :mmrId");
		query.setParameter("valor1", state.getId());
		query.setParameter("valor2", new Date());
		query.setParameter("mmrId", mmr.getId());
		query.executeUpdate();
	}
}
