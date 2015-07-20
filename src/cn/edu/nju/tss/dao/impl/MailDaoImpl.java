package cn.edu.nju.tss.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.nju.tss.conf.PageConfig;
import cn.edu.nju.tss.dao.BaseDao;
import cn.edu.nju.tss.dao.MailDao;
import cn.edu.nju.tss.model.EMailDB;
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
			@SuppressWarnings("unchecked")
			List<Mailer> list = query.list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

	@Override
	public List<EMailDB> getMailListByPage(String address, int page) {
		// TODO Auto-generated method stub
		Session session = baseDao.getNewSession();
		try{
			String sql = "select * from EMailDB where address='?' order by time desc limit ?,?";
			Query query = session.createSQLQuery(sql).addEntity(EMailDB.class);
			query.setString(0, address);
			query.setInteger(1, page*PageConfig.EMAIL_PAGE);
			query.setInteger(2, PageConfig.EMAIL_PAGE);
			@SuppressWarnings("unchecked")
			List<EMailDB> list = query.list();
			if(list!=null){
				if(list.size()<=0){
					return null;
				}
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
}
