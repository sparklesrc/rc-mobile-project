package pe.com.rc.mobile.dao;

import java.util.List;

import pe.com.rc.mobile.dao.helper.GenericDAO;
import pe.com.rc.mobile.model.ClanComments;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.Clan;

public interface ClanCommentsDAO extends GenericDAO<ClanComments> {

	ClanComments findByClanAndUser(Clan clan, User user);

	List<ClanComments> listCommentsByClan(Clan clan);
}
