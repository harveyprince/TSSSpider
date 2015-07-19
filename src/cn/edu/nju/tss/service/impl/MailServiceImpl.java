package cn.edu.nju.tss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.tss.dao.BaseDao;
import cn.edu.nju.tss.dao.MailDao;
import cn.edu.nju.tss.model.EMailDB;
import cn.edu.nju.tss.model.Mailer;
import cn.edu.nju.tss.model.vo.EMail;
import cn.edu.nju.tss.service.MailService;
import cn.edu.nju.tss.tool.SendMail;
@Service
public class MailServiceImpl implements MailService {
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private MailDao mailDao;
	
	@Override
	public void mailTo(EMail mail) {
		// TODO Auto-generated method stub
		SendMail sm = new SendMail();
		System.out.println(mail.getReceiver());
		System.out.println(mail.getContent());
		System.out.println(mail.getTitle());
		sm.send(mail);
	}

	@Override
	public void mailToAll(EMail mail) {
		// TODO Auto-generated method stub
		List<Mailer> mailerlist = mailDao.getAllMailers();
		EMail email = mail;
		for(Mailer temp:mailerlist){
			email.setReceiver(temp.getAddress());
			mailTo(email);
		}
	}

	@Override
	public List<EMailDB> getMailListByPage(int page) {
		// TODO Auto-generated method stub
		return null;
	}

}
