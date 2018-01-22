package pe.com.rc.mobile;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import pe.com.rc.mobile.model.User;

public class HibernateModelTest {

	private Session session = HibernateUtil.getSessionFactory().openSession();

	@Test
	public void readUsers() {
		@SuppressWarnings("unchecked")
		List<User> users = session.createQuery("FROM User").list();

		assertNotNull(users);

		for (User user : users) {
			assertNotNull(user.getId());
		}
	}

}
