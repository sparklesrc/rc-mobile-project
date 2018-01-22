package pe.com.rc.mobile.dao.helper;

import java.util.List;

public interface GenericDAO<T> {

	public T find(T t);

	public List<T> all();

	public void save(T t);

	public void update(T t);

	public void delete(T t);
}
