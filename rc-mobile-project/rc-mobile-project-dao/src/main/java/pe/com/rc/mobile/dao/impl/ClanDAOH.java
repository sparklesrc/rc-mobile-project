package pe.com.rc.mobile.dao.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pe.com.rc.mobile.dao.ClanDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.ClanMembers;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.Clan;

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
		// delete members
		System.out.println("DELETE");
		for (ClanMembers member : t.getClanMembers()) {
				System.out.println("BORRANDO USERS " + member.getUser().getName() +" id "+ member.getUser().getId());
				System.out.println("BORRANDO USERS " + member.getClan().getName() +" id "+ member.getClan().getId());
				Query q = this.getSession().createSQLQuery("delete from CLAN_MEMBERS where user_id = :userId and clan_id = :clanId");
				q.setParameter("userId", member.getUser().getId());
				q.setParameter("clanId", member.getClan().getId());
				q.executeUpdate();
		}
		// delete clan
		System.out.println("BORRANDO CLAN " + t.getName());
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
		criteria.add(Restrictions.eq("name", name)).add(
				Restrictions.eq("game.id", gameId));
		return (Clan) criteria.uniqueResult();
	}

	public void insertMember(ClanMembers member){
        Query query = getSession().createSQLQuery("INSERT INTO CLAN_MEMBERS (USER_ID, CLAN_ID, MEMBER_TYPE_ID, CREATE_DATE, ACTIVE) VALUES (:valor1, :valor2, :valor3, :valor4, :valor5)");
        query.setParameter("valor1", member.getUser().getId());
        query.setParameter("valor2", member.getClan().getId());
        query.setParameter("valor3", member.getMemberType().getId());
        query.setParameter("valor4", new Date());
        query.setParameter("valor5", 1);
        query.executeUpdate();
	}

	public User getLeader(Clan clan) {
		for(ClanMembers member : clan.getClanMembers()) {
			if (member.getMemberType().getId().equals(1L)) {
				return member.getUser();
			}
		}
		return null;
	}

	public void dropMember(Clan clan, User user) {
		Query q = this.getSession().createSQLQuery("delete from CLAN_MEMBERS where user_id = :userId and clan_id = :clanId");
		q.setParameter("userId", user.getId());
		q.setParameter("clanId", clan.getId());
		q.executeUpdate();
	}
}
