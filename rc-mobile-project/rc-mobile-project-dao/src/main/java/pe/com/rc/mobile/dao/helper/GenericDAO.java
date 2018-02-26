package pe.com.rc.mobile.dao.helper;

import java.util.List;

import pe.com.rc.mobile.core.exception.DaoException;

public interface GenericDAO<T> {

	public T find(T t) throws DaoException;

	public List<T> all() throws DaoException;

	public void save(T t) throws DaoException;

	public void update(T t) throws DaoException;

	public void delete(T t) throws DaoException;
}
