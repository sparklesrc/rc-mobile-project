package pe.com.rc.mobile.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pe.com.rc.mobile.dao.ClanCommentsDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.ClanComments;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.Clan;

@Repository
public class ClanCommentsDAOH extends BaseHibernateDAO implements ClanCommentsDAO {

	public ClanComments find(ClanComments t) {
		Criteria criteria = this.getSession().createCriteria(ClanComments.class);
		criteria.add(Restrictions.eq("id", t.getId()));
		return (ClanComments) criteria.uniqueResult();
	}

	public List<ClanComments> all() {
		Criteria criteria = this.getSession().createCriteria(ClanComments.class);
		return criteria.list();
	}

	public void save(ClanComments t) {
		this.getSession().save(t);
	}

	public void update(ClanComments t) {
		this.getSession().merge(t);
	}

	public void delete(ClanComments t) {
		this.getSession().delete(t);
	}

	public ClanComments findByClanAndUser(Clan clan, User user) {
		Criteria criteria = this.getSession().createCriteria(ClanComments.class);
		criteria.add(Restrictions.eq("clan.id", clan.getId())).add(Restrictions.eq("user.id", user.getId()))
				.add(Restrictions.eq("game.id", clan.getGame().getId()));
		return (ClanComments) criteria.uniqueResult();
	}

	public List<ClanComments> listCommentsByClan(Clan clan) {
		Criteria criteria = this.getSession().createCriteria(ClanComments.class);
		criteria.add(Restrictions.eq("clan.id", clan.getId())).add(Restrictions.eq("game.id", clan.getGame().getId()));
		return criteria.list();
	}
}
