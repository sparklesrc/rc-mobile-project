package pe.com.rc.mobile.dao;

import java.util.List;

import pe.com.rc.mobile.dao.helper.GenericDAO;
import pe.com.rc.mobile.model.GeneratedCode;
import pe.com.rc.mobile.model.User;

public interface GeneratedCodeDAO extends GenericDAO<GeneratedCode> {

	GeneratedCode findCodeByUser(User user);
}
