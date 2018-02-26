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
import pe.com.rc.mobile.dao.ClanDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.ClanMembers;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.Clan;

@Repository
public class ClanDAOH extends BaseHibernateDAO implements ClanDAO {

	private static final Logger logger = LoggerFactory.getLogger(ClanDAOH.class);

	public Clan find(Clan t) throws DaoException {
		try {
			Criteria criteria = this.getSession().createCriteria(Clan.class);
			criteria.add(Restrictions.eq("id", t.getId()));
			return (Clan) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Error trying to Find Team. " + t.getId(), e);
			throw new DaoException("Error trying to Find Team.", e);
		}
	}

	public List<Clan> all() throws DaoException {
		try {
			Criteria criteria = this.getSession().createCriteria(Clan.class);
			return criteria.list();
		} catch (Exception e) {
			logger.error("Error trying to list all clans.", e);
			throw new DaoException("Error trying to list all clans.", e);
		}
	}

	public void save(Clan t) throws DaoException {
		try {
			this.getSession().save(t);
		} catch (Exception e) {
			logger.error("Error trying to save Team. " + t.getId(), e);
			throw new DaoException("Error trying to save Team.", e);
		}
	}

	public void update(Clan t) throws DaoException {
		try {
			this.getSession().merge(t);
		} catch (Exception e) {
			logger.error("Error trying to update Team. " + t.getId(), e);
			throw new DaoException("Error trying to update Team.", e);
		}
	}

	public void delete(Clan t) throws DaoException {
		try {
			// delete members
			for (ClanMembers member : t.getClanMembers()) {
				Query q = this.getSession()
						.createSQLQuery("delete from CLAN_MEMBERS where user_id = :userId and clan_id = :clanId");
				q.setParameter("userId", member.getUser().getId());
				q.setParameter("clanId", member.getClan().getId());
				q.executeUpdate();
			}
			// delete clan
			Query q = this.getSession().createSQLQuery("delete from CLAN where id = :clanId");
			q.setParameter("clanId", t.getId());
			q.executeUpdate();
		} catch (Exception e) {
			logger.error("Error trying to delete Team. " + t.getId(), e);
			throw new DaoException("Error trying to delete Team.", e);
		}
	}

	public List<User> getMembersByClan(Integer clanId) throws DaoException {
		try {
			Criteria criteria = this.getSession().createCriteria(Clan.class);
			criteria.add(Restrictions.eq("members", clanId));
			return criteria.list();
		} catch (Exception e) {
			logger.error("Error trying to getMembersByClan. " + clanId, e);
			throw new DaoException("Error trying to delete Team.", e);
		}
	}

	// TODO: Mejorar busqueda por LIKE
	public Clan getClanByNameAndGame(String name, Long gameId) throws DaoException {
		try {
			Criteria criteria = this.getSession().createCriteria(Clan.class);
			criteria.add(Restrictions.eq("name", name)).add(Restrictions.eq("game.id", gameId));
			return (Clan) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Error trying to getClanByNameAndGame. clan/game " + name + "/" + gameId, e);
			throw new DaoException("Error trying to getClanByNameAndGame.", e);
		}
	}

	public void insertMember(ClanMembers member) throws DaoException {
		try {
			Query query = getSession().createSQLQuery(
					"INSERT INTO CLAN_MEMBERS (USER_ID, CLAN_ID, MEMBER_TYPE_ID, CREATE_DATE, ACTIVE) VALUES (:valor1, :valor2, :valor3, :valor4, :valor5)");
			query.setParameter("valor1", member.getUser().getId());
			query.setParameter("valor2", member.getClan().getId());
			query.setParameter("valor3", member.getMemberType().getId());
			query.setParameter("valor4", new Date());
			query.setParameter("valor5", 1);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("Error trying to insert Member " + member.getUser().getId() + " into Clan "
					+ member.getClan().getId(), e);
			throw new DaoException("Error trying to insert Member.", e);
		}
	}

	public void dropMember(Clan clan, User user) throws DaoException {
		try {
			Query q = this.getSession()
					.createSQLQuery("delete from CLAN_MEMBERS where user_id = :userId and clan_id = :clanId");
			q.setParameter("userId", user.getId());
			q.setParameter("clanId", clan.getId());
			q.executeUpdate();
		} catch (Exception e) {
			logger.error("Error trying to drop Member " + user.getId() + " from Clan " + clan.getId(), e);
			throw new DaoException("Error trying to drop Member.", e);
		}
	}

	public void updateMemberRole(Long memberTypeId, Long userId, Long clanId) throws DaoException {
		try {
			Query query = getSession().createSQLQuery(
					"UPDATE CLAN_MEMBERS SET member_type_id = :valor1, update_date = :valor2 where user_id :userId and clan_id = :clanId");
			query.setParameter("valor1", memberTypeId);
			query.setParameter("valor2", new Date());
			query.setParameter("userId", userId);
			query.setParameter("clanId", clanId);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("Error trying to update Role/Member/Clan " + memberTypeId + "/" + userId + "/" + clanId, e);
			throw new DaoException("Error trying to updateMemberRole.", e);
		}
	}
}
