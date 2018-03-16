package pe.com.rc.mobile.dao;

import java.util.List;
import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.dao.helper.GenericDAO;
import pe.com.rc.mobile.model.ClanMembers;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.Clan;
import pe.com.rc.mobile.model.clan.UserReqRes.UserTeams;

public interface ClanDAO extends GenericDAO<Clan> {

	List<User> getMembersByClan(Integer clanId);

	Clan getClanByNameAndGame(String name, Long gameId);

	void insertMember(ClanMembers member);

	User getLeader(Clan clan);

	void dropMember(Clan clan, User user);

	void updateMemberRole(Long memberTypeId, Long userId, Long clanId);

	List<UserTeams> getTeamsByUser(Long userId) throws DaoException;
}
