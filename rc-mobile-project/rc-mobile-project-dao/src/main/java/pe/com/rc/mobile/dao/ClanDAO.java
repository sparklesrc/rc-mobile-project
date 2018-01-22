package pe.com.rc.mobile.dao;

import java.util.List;

import pe.com.rc.mobile.dao.helper.GenericDAO;
import pe.com.rc.mobile.model.Clan;
import pe.com.rc.mobile.model.User;

public interface ClanDAO extends GenericDAO<Clan> {

	List<User> getMembersByClan(Clan clan);
}
