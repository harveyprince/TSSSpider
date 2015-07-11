package cn.edu.nju.tss.tool;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import cn.edu.nju.tss.model.vo.EMail;

public class SendMail {  
	public ApplicationContext ctx = null;  
	public SendMail() {  
		//获取上下文  
		ctx = new ClassPathXmlApplicationContext("classpath:/applicationMail.xml");  
	}
	public void send(EMail email) {  
		//获取JavaMailSender bean  
		JavaMailSender sender = (JavaMailSender) ctx.getBean("mailSender");  
		SimpleMailMessage mail = new SimpleMailMessage(); //<span style="color: #ff0000;">注意SimpleMailMessage只能用来发送text格式的邮件</span>     
		try {  
			mail.setTo(email.getReceiver());//接受者  
			mail.setFrom("harveyland@163.com");//发送者  
			mail.setSubject(email.getTitle());//主题  
			mail.setText(email.getContent());//邮件内容  
			sender.send(mail);  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
	}
}