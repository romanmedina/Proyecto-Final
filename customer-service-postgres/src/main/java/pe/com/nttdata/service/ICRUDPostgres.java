package pe.com.nttdata.service;

import java.util.List;

public interface ICRUDPostgres<T, V> {

	T create(T obj);
	T update(T obj);
	List<T> findAll();
	T findById(V id);
	boolean delete(V id);
}
