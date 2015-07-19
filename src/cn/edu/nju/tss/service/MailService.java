package cn.edu.nju.tss.service;

import cn.edu.nju.tss.model.vo.EMail;

public interface MailService {
	/**
	 * @param mail 邮件
	 * 单发邮件
	 */
	public void mailTo(EMail mail);
	
	/**
	 * @param mail 邮件
	 * 群发邮件
	 */
	public void mailToAll(EMail mail);
}
