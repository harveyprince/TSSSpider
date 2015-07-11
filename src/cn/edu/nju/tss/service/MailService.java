package cn.edu.nju.tss.service;

import cn.edu.nju.tss.model.vo.EMail;

public interface MailService {
	public void mailTo(EMail mail);
	
	public void mailToAll(EMail mail);
}
