package cn.edu.nju.tss.service;

import java.util.List;

import cn.edu.nju.tss.model.EMailDB;
import cn.edu.nju.tss.model.Mailer;
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
	
	/**
	 * @param address 用户识别
	 * @param page 页数
	 * @return 获取已发送的邮件列表
	 */
	public List<EMailDB> getMailListByPage(String address, int page);
	
	/**
	 * @param email 用户识别
	 * @return 返回用户
	 */
	public Mailer getMailerByEmail(String email);
}
