package pe.com.rc.mobile.dao;

import java.util.List;

import pe.com.rc.mobile.core.exception.DaoException;
import pe.com.rc.mobile.dao.helper.GenericDAO;
import pe.com.rc.mobile.model.Game;
import pe.com.rc.mobile.model.Solicitude;
import pe.com.rc.mobile.model.SolicitudeType;
import pe.com.rc.mobile.model.State;
import pe.com.rc.mobile.model.User;
import pe.com.rc.mobile.model.clan.Clan;

public interface SolicitudeDAO extends GenericDAO<Solicitude> {

	List<Solicitude> getSolicitudesByClanAndState(Clan clan, State state, Integer active) throws DaoException;

	List<Solicitude> getSolicitudesByUserAndGameAndStateAndType(User user, Game game, State state, SolicitudeType type)
			throws DaoException;
}
