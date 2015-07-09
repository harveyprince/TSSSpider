package cn.edu.nju.tss.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.nju.tss.dao.BaseDao;



@Repository
public class BaseDaoImpl implements BaseDao {
	/** * Autowired 自动装配 相当于get() set() */
	@Autowired
	protected SessionFactory sessionFactory;

	/** * gerCurrentSession 会自动关闭session，使用的是当前的session事务 * * @return */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/** * openSession 需要手动关闭session 意思是打开一个新的session * * @return */
	public Session getNewSession() {
		return sessionFactory.openSession();
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	/**
	 * 根据 id 查询信息 
	 * @param id 
	 * @return 
	 */
	@SuppressWarnings("rawtypes")
	public Object load(Class c, Long id) {
		Session session = getSession();
		return session.get(c, id);
	}

	/** * 获取所有信息 * * @param c * * @return */

	public List getAllList(Class c) {
		String hql = "from " + c.getName();
		Session session = getSession();
		return session.createQuery(hql).list();

	}

	/** * 获取总数量 * * @param c * @return */

	public Long getTotalCount(Class c) {
		Session session = getNewSession();
		String hql = "select count(*) from " + c.getName();
		Long count = (Long) session.createQuery(hql).uniqueResult();
		session.close();
		return count != null ? count.longValue() : 0;
	}

	/** * 保存 * * @param bean * */
	public void save(Object bean) {
		try {
			Session session = getNewSession();
			session.save(bean);
			session.flush();
			session.clear();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** * 更新 * * @param bean * */
	public void update(Object bean) {
		Session session = getNewSession();
		session.update(bean);
		session.flush();
		session.clear();
		session.close();
	}

	/** * 删除 * * @param bean * */
	public void delete(Object bean) {

		Session session = getNewSession();
		session.delete(bean);
		session.flush();
		session.clear();
		session.close();
	}

	/** * 根据ID删除 * * @param c 类 * * @param id ID * */
	@SuppressWarnings({ "rawtypes" })
	public void delete(Class c, String id) {
		Session session = getNewSession();
		Object obj = session.get(c, id);
		session.delete(obj);
		session.flush();
		session.clear();
	}

	/** * 批量删除 * * @param c 类 * * @param ids ID 集合 * */
	@SuppressWarnings({ "rawtypes" })
	public void delete(Class c, String[] ids) {
		for (String id : ids) {
			Object obj = getSession().get(c, id);
			if (obj != null) {
				getSession().delete(obj);
			}
		}
	}

	@Override
	public Object find(Class c, String coloum, String value) {
		Session session = getNewSession();
        Transaction tx=session.beginTransaction();
		
		try {			
            StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("FROM ");
			stringBuilder.append(c.getName());
			stringBuilder.append(" AS u WHERE u.");
			stringBuilder.append(coloum);
			stringBuilder.append(" = ?");
			String hql = stringBuilder.toString();
			Query query = session.createQuery(hql);
			query.setString(0, value);
			List<?> list = query.list();
			tx.commit();
			if ((list.size()) == 0){
				return null;
			}
			else{
				return (Object) list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
            tx.rollback();
            return null;
		}finally{
			session.close();
		}
	}

	@Override
	public Object findlist(Class c, String coloum, String value) {
		Session session = getNewSession();
        Transaction tx=session.beginTransaction();
		
		try {			
            StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("FROM ");
			stringBuilder.append(c.getName());
			stringBuilder.append(" AS u WHERE u.");
			stringBuilder.append(coloum);
			stringBuilder.append(" = ?");
			String hql = stringBuilder.toString();
			Query query = session.createQuery(hql);
			query.setString(0, value);
			List<?> list = query.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
            tx.rollback();
            return null;
		}finally{
			session.close();
		}
	}

	/*
	 * 批量插入.
	 */
	@Override
	public void saveList(List list) {
		try {
			Session session = getNewSession();
			for (int i = 0; i < list.size(); i++) {
				session.save(list.get(i));
				if (i % 20 == 0) {
					session.flush();
					session.clear();
				}
			}
			session.flush();
			session.clear();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
