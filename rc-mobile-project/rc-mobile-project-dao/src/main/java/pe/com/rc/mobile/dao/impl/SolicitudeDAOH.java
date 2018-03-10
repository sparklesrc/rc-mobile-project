package pe.com.rc.mobile.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.dao.SolicitudeDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.Game;
import pe.com.rc.mobile.model.Solicitude;
import pe.com.rc.mobile.model.SolicitudeType;
import pe.com.rc.mobile.model.State;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.Clan;

@Repository
public class SolicitudeDAOH extends BaseHibernateDAO implements SolicitudeDAO {

	public Solicitude find(Solicitude t) {
		Criteria criteria = getSession().createCriteria(Solicitude.class);
		criteria.add(Restrictions.eq("id", t.getId()));
		return (Solicitude) criteria.uniqueResult();
	}

	public List<Solicitude> all() {
		Criteria criteria = this.getSession().createCriteria(Solicitude.class);
		return criteria.list();
	}

	public void save(Solicitude t) {
		this.getSession().save(t);
	}

	public void update(Solicitude t) {
		Query query = getSession()
				.createSQLQuery(
						"UPDATE solicitude SET state_id = :stateId, update_date = :updateDate where user_id = :userId and clan_id = :clanId");
		query.setParameter("stateId", t.getState().getId());
		query.setParameter("updateDate", new Date());
		query.setParameter("userId", t.getUser().getId());
		query.setParameter("clanId", t.getClan().getId());
		query.executeUpdate();
	}

	public void delete(Solicitude t) {
		// TODO Auto-generated method stub

	}

	public List<Solicitude> getSolicitudesByClanAndState(Clan clan,
			State state, Integer active) {
		Criteria criteria = this.getSession().createCriteria(Solicitude.class);
		criteria.add(Restrictions.eq("clan.id", clan.getId()))
				.add(Restrictions.eq("state.id", state.getId()))
				.add(Restrictions.eq("active", 1));
		return criteria.list();
	}

	public List<Solicitude> getSolicitudesByUserAndGameAndStateAndType(User user, Game game,
			State state, SolicitudeType type) throws DaoException {
		Criteria criteria = this.getSession().createCriteria(Solicitude.class);
		criteria.add(Restrictions.eq("user.id", user.getId()))
				.add(Restrictions.eq("game.id", game.getId()))
				.add(Restrictions.eq("state.id", state.getId()))
				.add(Restrictions.eq("solicitudeType.id", type.getId()))
				.add(Restrictions.eq("active", 1));
		return criteria.list();
	}
}
