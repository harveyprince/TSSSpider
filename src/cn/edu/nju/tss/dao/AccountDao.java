package cn.edu.nju.tss.dao;

import cn.edu.nju.tss.model.Account;

public interface AccountDao {
	/**
	 * @param email 用户识别即邮箱
	 * @return 返回用户账户
	 */
	public Account findAccountByEmail(String email);
	
}
