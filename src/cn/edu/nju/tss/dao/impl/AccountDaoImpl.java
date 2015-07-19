package cn.edu.nju.tss.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.nju.tss.dao.AccountDao;
import cn.edu.nju.tss.dao.BaseDao;
import cn.edu.nju.tss.model.Account;
@Repository
public class AccountDaoImpl implements AccountDao {
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public Account findAccountByEmail(String email) {
		// TODO Auto-generated method stub
		Session session = baseDao.getNewSession();
		try{
			String sql = "select * from Account where email = ?";
			Query query = session.createSQLQuery(sql).addEntity(Account.class);
			query.setString(0, email);
			@SuppressWarnings("unchecked")
			List<Account> list = query.list();
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

}
