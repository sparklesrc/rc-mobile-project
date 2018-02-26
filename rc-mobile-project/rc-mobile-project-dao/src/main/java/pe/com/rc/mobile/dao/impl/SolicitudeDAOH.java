package pe.com.rc.mobile.dao.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(SolicitudeDAOH.class);

	public Solicitude find(Solicitude t) throws DaoException {
		try {
			Criteria criteria = getSession().createCriteria(Solicitude.class);
			criteria.add(Restrictions.eq("id", t.getId()));
			return (Solicitude) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Error trying to find Solicitude " + t.getId(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<Solicitude> all() throws DaoException {
		try {
			Criteria criteria = this.getSession().createCriteria(Solicitude.class);
			return criteria.list();
		} catch (Exception e) {
			logger.error("Error trying to List all Solicitudes.", e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void save(Solicitude t) throws DaoException {
		try {
			this.getSession().save(t);
		} catch (Exception e) {
			logger.error("Error trying to save Solicitude " + t.getId(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void update(Solicitude t) throws DaoException {
		try {
			Query query = getSession().createSQLQuery(
					"UPDATE SOLICITUDE SET state_id = :stateId, update_date = :updateDate where user_id = :userId and clan_id = :clanId");
			query.setParameter("stateId", t.getState().getId());
			query.setParameter("updateDate", new Date());
			query.setParameter("userId", t.getUser().getId());
			query.setParameter("clanId", t.getClan().getId());
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("Error trying to update Solicitude " + t.getId(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void delete(Solicitude t) throws DaoException {
		// TODO Auto-generated method stub

	}

	public List<Solicitude> getSolicitudesByClanAndState(Clan clan, State state, Integer active) throws DaoException {
		try {
			Criteria criteria = this.getSession().createCriteria(Solicitude.class);
			criteria.add(Restrictions.eq("clan.id", clan.getId())).add(Restrictions.eq("state.id", state.getId()))
					.add(Restrictions.eq("active", 1));
			return criteria.list();
		} catch (Exception e) {
			logger.error("Error trying to get Solicitudes ByClan And State " + clan.getId() + "/" + state.getId(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<Solicitude> getSolicitudesByUserAndGameAndStateAndType(User user, Game game, State state,
			SolicitudeType type) throws DaoException {
		try {
			Criteria criteria = this.getSession().createCriteria(Solicitude.class);
			criteria.add(Restrictions.eq("user.id", user.getId())).add(Restrictions.eq("game.id", game.getId()))
					.add(Restrictions.eq("state.id", state.getId()))
					.add(Restrictions.eq("solicitudeType.id", type.getId())).add(Restrictions.eq("active", 1));
			return criteria.list();
		} catch (Exception e) {
			logger.error("Error trying to get Solicitudes ByUser And Game And SolicitudeType " + user.getId() + "/"
					+ game.getId() + "/" + type.getId(), e);
			throw new DaoException(e.getMessage(), e);
		}

	}
}
