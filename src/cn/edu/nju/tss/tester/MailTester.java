package cn.edu.nju.tss.tester;

import cn.edu.nju.tss.model.vo.EMail;
import cn.edu.nju.tss.tool.SendMail;

public class MailTester {
	public static void main(String[] args){
		EMail email = new EMail();
		email.setContent("");
		email.setReceiver("");
		email.setTitle("");
		SendMail sm = new SendMail();
		sm.send(email);
	}
}
