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
import pe.com.rc.mobile.model.clan.UserReqRes.UserTeams;

@Repository
public class ClanDAOH extends BaseHibernateDAO implements ClanDAO {

	private static final Logger logger = LoggerFactory.getLogger(ClanDAOH.class);

	public Clan find(Clan t) {
		Criteria criteria = this.getSession().createCriteria(Clan.class);
		criteria.add(Restrictions.eq("id", t.getId()));
		return (Clan) criteria.uniqueResult();
	}

	public List<Clan> all() throws DaoException {
		List<Clan> clanes = null;
		try {
			Criteria criteria = this.getSession().createCriteria(Clan.class);
			clanes = criteria.list();
//			logger.error("Error trying to list all clans.", e);
//			throw new DaoException("Error trying to list all clans.");
		} catch (Exception e) {
			logger.error("Error trying to list all clans.", e);
			throw new DaoException("Error trying to list all clans.", e);
		}
		return clanes;
	}

	public void save(Clan t) {
		this.getSession().save(t);
	}

	public void update(Clan t) {
		this.getSession().merge(t);
	}

	public void delete(Clan t) {
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
	}

	public List<User> getMembersByClan(Integer clanId) {
		Criteria criteria = this.getSession().createCriteria(Clan.class);
		criteria.add(Restrictions.eq("members", clanId));
		return criteria.list();
	}

	// TODO: Mejorar busqueda por LIKE
	public Clan getClanByNameAndGame(String name, Long gameId) {
		Criteria criteria = this.getSession().createCriteria(Clan.class);
		criteria.add(Restrictions.eq("name", name)).add(Restrictions.eq("game.id", gameId));
		return (Clan) criteria.uniqueResult();
	}

	public void insertMember(ClanMembers member) {
		Query query = getSession().createSQLQuery(
				"INSERT INTO clan_members (USER_ID, CLAN_ID, MEMBER_TYPE_ID, CREATE_DATE, ACTIVE) VALUES (:valor1, :valor2, :valor3, :valor4, :valor5)");
		query.setParameter("valor1", member.getUser().getId());
		query.setParameter("valor2", member.getClan().getId());
		query.setParameter("valor3", member.getMemberType().getId());
		query.setParameter("valor4", new Date());
		query.setParameter("valor5", 1);
		query.executeUpdate();
	}

	public User getLeader(Clan clan) {
		for (ClanMembers member : clan.getClanMembers()) {
			if (member.getMemberType().getId().equals(1L)) {
				return member.getUser();
			}
		}
		return null;
	}

	public void dropMember(Clan clan, User user) {
		Query q = this.getSession()
				.createSQLQuery("delete from clan_members where user_id = :userId and clan_id = :clanId");
		q.setParameter("userId", user.getId());
		q.setParameter("clanId", clan.getId());
		q.executeUpdate();
	}

	public void updateMemberRole(Long memberTypeId, Long userId, Long clanId) {
		Query query = getSession().createSQLQuery(
				"UPDATE clan_members SET member_type_id = :valor1, update_date = :valor2 where user_id :userId and clan_id = :clanId");
		query.setParameter("valor1", memberTypeId);
		query.setParameter("valor2", new Date());
		query.setParameter("userId", userId);
		query.setParameter("clanId", clanId);
		query.executeUpdate();
	}

	public List<UserTeams> getTeamsByUser(Long userId) throws DaoException {
		try {
			Query query = getSession().createSQLQuery(
					"select c.id as gameId, b.id as teamId, a.member_type_id as memberTypeId from clan_members a inner join clan b on a.clan_id = b.id inner join game c on b.game_id = c.id where a.user_id = :userId");
			query.setParameter("userId", userId);
			return query.list();
		} catch (Exception e) {
			return null;
		}
	}
}
