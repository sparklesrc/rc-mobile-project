package pe.com.rc.mobile.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.com.rc.mobile.dao.ClanDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.ClanMembers;
import pe.com.rc.mobile.model.MemberType;
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

//	@Transactional
	public void save(Clan t) {
		this.getSession().save(t);
	}

	public void update(Clan t) {
		this.getSession().merge(t);
	}

	public void delete(Clan t) {
		this.getSession().delete(t);
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
}
