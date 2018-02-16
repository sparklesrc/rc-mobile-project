package pe.com.rc.mobile.dao;

import java.util.List;

import pe.com.rc.mobile.dao.helper.GenericDAO;
import pe.com.rc.mobile.model.MatchMaking;
import pe.com.rc.mobile.model.State;
import pe.com.rc.mobile.model.clan.Clan;

public interface MatchMakingDAO extends GenericDAO<MatchMaking> {

	// MMRS PENDIENTES DE ACEPTAR
	List<MatchMaking> getMMRsByClan(Clan clan);

	void updateMMRState(MatchMaking mmr, State state);
}
