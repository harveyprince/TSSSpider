package cn.edu.nju.tss.tester;

import cn.edu.nju.tss.tool.SendMail;

public class MailTester {
	public static void main(String[] args){
		SendMail sm = new SendMail();
		sm.send();
	}
}
