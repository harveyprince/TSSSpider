package cn.edu.nju.tss.dao;

import java.util.List;

import cn.edu.nju.tss.model.EMailDB;
import cn.edu.nju.tss.model.Mailer;

public interface MailDao {
	/**
	 * @return 获取所有收件人
	 */
	public List<Mailer> getAllMailers();

	/**
	 * @param address 用户识别
	 * @param page 页数
	 * @return 按页返回用户所有的邮件列表
	 */
	public List<EMailDB> getMailListByPage(String address, int page);
	/**
	 * @param email 用户邮箱
	 * @return 返回用户
	 */
	public Mailer getMailerByEmail(String email);
}
