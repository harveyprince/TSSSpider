package cn.edu.nju.tss.dao;

import java.util.List;

import cn.edu.nju.tss.model.Mailer;

public interface MailDao {
	public List<Mailer> getAllMailers();
}
