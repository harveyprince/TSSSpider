package cn.edu.nju.tss.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.nju.tss.dao.BaseDao;
import cn.edu.nju.tss.dao.MailDao;
import cn.edu.nju.tss.model.Mailer;
@Repository
public class MailDaoImpl implements MailDao {
	@Autowired
	private BaseDao baseDao;

	@Override
	public List<Mailer> getAllMailers() {
		// TODO Auto-generated method stub
		Session session = baseDao.getNewSession();
		try{
			String sql = "select * from Mailer";
			Query query = session.createSQLQuery(sql).addEntity(Mailer.class);
			List<Mailer> list = query.list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
}
