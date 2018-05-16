package pe.com.rc.mobile.dao.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.dao.UserDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.ClanMembers;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.UserGameProfile;
import pe.com.rc.mobile.model.clan.TeamSearch.SearchRecruit;

@Repository
public class UserDAOH extends BaseHibernateDAO implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAOH.class);
	
	public User find(User t) {
		Criteria criteria = this.getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("id", t.getId()));
		return (User) criteria.uniqueResult();
	}

	public List<User> all() {
		Criteria criteria = this.getSession().createCriteria(User.class);
		return criteria.list();
	}

	public void save(User t) {
		this.getSession().save(t);
	}

	public void update(User t) {
		this.getSession().merge(t);
	}

	public void delete(User t) {
		this.getSession().delete(t);
	}

	public User findByMail(String mail) throws DaoException {
		try {
			Criteria criteria = this.getSession().createCriteria(User.class);
			criteria.add(Restrictions.eq("mail", mail));
			return (User) criteria.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("Error al obtener el usuario por mail." + mail);
		}
	}

	public void updateSteamId(Long userId, Long steamId) throws DaoException {
		try {
			Query query = getSession()
					.createSQLQuery("UPDATE user SET steam_id = :valor1, update_date = :valor2 where id = :userId");
			query.setParameter("valor1", steamId);
			query.setParameter("valor2", new Date());
			query.setParameter("userId", userId);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DaoException("Error al Actualizar SteamId para Usuario." + userId + "con SteamId " + steamId);
		}
	}

	public List<User> getCandidates() throws DaoException {
		return null;
	}

	public User findActiveUserByMail(String mail) throws DaoException {
		try {
			Criteria criteria = this.getSession().createCriteria(User.class);
			criteria.add(Restrictions.eq("mail", mail)).add(Restrictions.eq("active", 1));
			return (User) criteria.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("Error al obtener el usuario por mail." + mail);
		}
	}

	public List<User> searchByCriteria(SearchRecruit request) throws DaoException {
		Criteria criteria = this.getSession().createCriteria(User.class, "user");
		DetachedCriteria sizeCriteria = DetachedCriteria.forClass(ClanMembers.class, "clanMembers");
		DetachedCriteria sizeCriteria2 = DetachedCriteria.forClass(UserGameProfile.class, "userGameProfile");
		criteria.add(Restrictions.eq("active", 1));
		criteria.add(Restrictions.ne("id", request.getUserId()));
		// email absolute search
		if (request.getEmail() != null) {
			criteria.add(Restrictions.eq("mail", request.getEmail()));
			return criteria.list();
		}
		if (request.getEdad() != null) {
			criteria.add(Restrictions.eq("edad", request.getEdad()));
		}
		if (request.getPais() != null) {
			criteria.add(Restrictions.eq("pais", request.getPais()));
		}
		// NickName look in user_game_profile
		if (request.getNickName() != null) {
			sizeCriteria2.add(Restrictions.eq("userGameProfile.nickname", request.getNickName()));
			sizeCriteria2.setProjection(Projections.property("userGameProfile.user.id"));
			criteria.add(Subqueries.propertyEq("id", sizeCriteria2));
		}
		// Rol look in user_game_profile
		if (request.getRol() != null) {
			for (String rol : request.getRol()) {
				sizeCriteria2.add(Restrictions.like("userGameProfile.roles", rol, MatchMode.ANYWHERE));
			}
			sizeCriteria2.setProjection(Projections.property("userGameProfile.user.id"));
			criteria.add(Subqueries.propertyEq("id", sizeCriteria2));
		}
		// Estado look user not exists on clan_members 
		if (request.getEstado() != null && request.getEstado() == 1) {
			sizeCriteria.add(Property.forName("clanMembers.primaryKey.user.id").eqProperty("user.id"));
			criteria.add(Subqueries.notExists(sizeCriteria.setProjection(Projections.property("clanMembers.primaryKey.user.id"))));
		}
		if (request.getEstado() != null && request.getEstado() == 2) {
			sizeCriteria.add(Property.forName("clanMembers.primaryKey.user.id").eqProperty("user.id"));
			criteria.add(Subqueries.exists(sizeCriteria.setProjection(Projections.property("clanMembers.primaryKey.user.id"))));
		}
		return criteria.list();
	}
}
