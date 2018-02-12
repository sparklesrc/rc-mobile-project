package pe.com.rc.mobile.dao;

import java.util.List;

import pe.com.rc.mobile.dao.helper.GenericDAO;
import pe.com.rc.mobile.model.Solicitude;
import pe.com.rc.mobile.model.State;
import pe.com.rc.mobile.model.clan.Clan;

public interface SolicitudeDAO extends GenericDAO<Solicitude> {

	List<Solicitude> getSolicitudesByClanAndState(Clan clan, State state, Integer active);
}
