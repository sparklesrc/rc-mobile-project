package pe.com.rc.mobile.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.dao.GeneratedCodeDAO;
import pe.com.rc.mobile.dao.helper.BaseHibernateDAO;
import pe.com.rc.mobile.model.GeneratedCode;
import pe.com.rc.mobile.model.User;

@Repository
public class GeneratedCodeDAOH extends BaseHibernateDAO implements GeneratedCodeDAO {

	private static final Logger logger = LoggerFactory.getLogger(GeneratedCodeDAOH.class);

	public GeneratedCode find(GeneratedCode t) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<GeneratedCode> all() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(GeneratedCode t) {
		this.getSession().save(t);
	}

	public void update(GeneratedCode t) throws DaoException {
		this.getSession().merge(t);
	}

	public void delete(GeneratedCode t) {
		this.getSession().delete(t);
	}

	public GeneratedCode findCodeByUser(User user) {
		Criteria criteria = this.getSession().createCriteria(GeneratedCode.class);
		criteria.add(Restrictions.eq("user.id", user.getId()))
				.add(Restrictions.eq("active", 1));
		return (GeneratedCode) criteria.uniqueResult();
//		Query query = getSession()
//				.createSQLQuery("select * from generated_codes where user_id = :userId and active = 1");
//		query.setParameter("userId", user.getId());
//		List<Object> result = (List<Object>) query.list();
//		Iterator itr = result.iterator();
//		GeneratedCode gc = null;
//		while (itr.hasNext()) {
//			Object[] obj = (Object[]) itr.next();
//			// now you have one array of Object for each row
//			gc = new GeneratedCode();
//			Long id = Long.parseLong(String.valueOf(obj[0]));
//			Integer code = Integer.parseInt(String.valueOf(obj[2]));
//			gc.setId(id);
//			gc.setCode(code);
//		}
//		return gc;
	}

}
