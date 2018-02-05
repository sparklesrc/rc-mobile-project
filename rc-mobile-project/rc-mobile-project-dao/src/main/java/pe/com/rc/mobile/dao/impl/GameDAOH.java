package pe.com.rc.mobile.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pe.com.rc.mobile.dao.GameDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.Game;

//@Repository
public class GameDAOH extends BaseHibernateDAO implements GameDAO {

	public Game find(Game t) {
		Criteria criteria = this.getSession().createCriteria(Game.class);
		criteria.add(Restrictions.eq("id", t.getId()));
		return (Game) criteria.uniqueResult();
	}

	public List<Game> all() {
		Criteria criteria = this.getSession().createCriteria(Game.class);
		 return criteria.list();
	}

	public void save(Game t) {
		this.getSession().save(t);
	}

	public void update(Game t) {
		this.getSession().merge(t);
	}

	public void delete(Game t) {
		this.getSession().delete(t);
	}

}
