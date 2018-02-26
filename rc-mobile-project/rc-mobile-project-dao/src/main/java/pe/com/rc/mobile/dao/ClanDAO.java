package pe.com.rc.mobile.dao;

import java.util.List;
import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.dao.helper.GenericDAO;
import pe.com.rc.mobile.model.ClanMembers;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.Clan;

public interface ClanDAO extends GenericDAO<Clan> {

	List<User> getMembersByClan(Integer clanId) throws DaoException;

	Clan getClanByNameAndGame(String name, Long gameId) throws DaoException;

	void insertMember(ClanMembers member) throws DaoException;

	void dropMember(Clan clan, User user) throws DaoException;

	void updateMemberRole(Long memberTypeId, Long userId, Long clanId) throws DaoException;

}
