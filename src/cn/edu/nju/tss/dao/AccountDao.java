package cn.edu.nju.tss.dao;

import cn.edu.nju.tss.model.Account;

public interface AccountDao {
	public Account findAccountByEmail(String email);
	
}
