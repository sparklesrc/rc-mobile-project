package pe.com.rc.mobile;

import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import pe.com.rc.mobile.model.ClanMembers;
import pe.com.rc.mobile.model.Game;
import pe.com.rc.mobile.model.MemberType;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.Clan;

public class HibernateModelTest {

	@Test
	public void saveNewClanMemberWithExistingUserAndClan() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<ClanMembers> membersFromDB = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, new Long(1L));
			Clan clan = (Clan) session.get(Clan.class, new Long(1L));
			MemberType memberType = (MemberType) session.get(MemberType.class, new Long(2L));

			ClanMembers clanMembers = new ClanMembers();
			clanMembers.setUser(user);
			clanMembers.setClan(clan);
			clanMembers.setMemberType(memberType);
			clanMembers.setCreateDate(new Date());
			clanMembers.setActive(1);

			session.saveOrUpdate(clanMembers);
			tx.commit();

			membersFromDB = session.createQuery("FROM ClanMembers").list();
			assertNotNull(membersFromDB);

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	private List<Clan> getClanes() {
		Game game = new Game();
		game.setId(1L);
		game.setName("CSGO");
		game.setSteamApp(730);
		game.setActive(1);

		Clan clanA = new Clan();
		clanA.setId(1L);
		clanA.setName("Pepitos");
		clanA.setGame(game);
		clanA.setDecription("Agresivos");
		clanA.setStarsNumber(5);
		clanA.setActive(1);

		Clan clanB = new Clan();
		clanB.setId(2L);
		clanB.setName("PepeLucho");
		clanB.setGame(game);
		clanB.setDecription("Squeezy");
		clanB.setStarsNumber(4);
		clanB.setActive(1);

		Clan clanC = new Clan();
		clanC.setId(3L);
		clanC.setName("Ratas");
		clanC.setGame(game);
		clanC.setDecription("Rat Rat Rat");
		clanC.setStarsNumber(2);
		clanC.setActive(1);

		List<Clan> clanes = new ArrayList<Clan>();
		clanes.add(clanA);
		clanes.add(clanB);
		clanes.add(clanC);

		return clanes;
	}

	private List<User> getUsers() {
		User user = new User();
		user.setId(1L);
		user.setName("SparKLes");
		user.setSteamId(7656714444223L);
		user.setSteamLinkAvatar("this is my link");
		user.setActive(1);

		User user2 = new User();
		user2.setId(2L);
		user2.setName("SandMan");
		user2.setSteamId(88888822223L);
		user2.setSteamLinkAvatar("this is my link");
		user2.setActive(1);

		User user3 = new User();
		user3.setId(3L);
		user3.setName("Kevin");
		user3.setSteamId(1111112223L);
		user3.setSteamLinkAvatar("this is my link");
		user3.setActive(1);

		List<User> users = new ArrayList<User>();
		users.add(user);
		users.add(user2);
		users.add(user3);
		return users;
	}
}
