package cn.edu.nju.tss.dao;

import java.util.List;

import org.hibernate.Session;

public interface BaseDao {
	public Session getSession();

	public Session getNewSession();
	
	public void flush();

	public void clear() ;

	@SuppressWarnings("rawtypes")
	public Object load(Class c, Long id) ;
	@SuppressWarnings("rawtypes")
	public List getAllList(Class c) ;
	@SuppressWarnings("rawtypes")
	public Long getTotalCount(Class c) ;

	public void save(Object bean) ;
	
	public void saveList(List<Object> list);

	public void update(Object bean) ;

	public void delete(Object bean) ;
	@SuppressWarnings("rawtypes")
	public void delete(Class c, String id) ;
	@SuppressWarnings("rawtypes")
	public void delete(Class c, String[] ids) ;
	@SuppressWarnings("rawtypes")
	public Object find(Class c, String coloum, String value);
	@SuppressWarnings("rawtypes")
	public Object findlist(Class c, String coloum, String value);
}
