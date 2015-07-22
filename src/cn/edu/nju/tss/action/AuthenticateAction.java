package cn.edu.nju.tss.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.nju.tss.model.Account;
import cn.edu.nju.tss.model.vo.EMail;
import cn.edu.nju.tss.model.vo.ResultMessage;
import cn.edu.nju.tss.model.vo.SignUpVO;
import cn.edu.nju.tss.service.AccountService;
import cn.edu.nju.tss.service.MailService;

@Repository
public class AuthenticateAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2572880720558093639L;
	@Autowired
	private AccountService accountService;
	@Autowired
	private MailService mailService;
	
	private Map<String, Object> jsonResult;
	private ResultMessage pageResult;
	/*
	 * page
	 * */
	public String welcome(){
		return SUCCESS;
	}
	public String signout(){
		if(session.containsKey("account")){
			session.remove("account");
		}
		return SUCCESS;
	}
	public String activate(){
		String code = request.getParameter("code");
		if(code==null){
			return UNAUDITED;
		}
		ResultMessage rm = accountService.activate(code);
		pageResult = rm;
		return SUCCESS;
	}
	/*
	 * ajax
	 * */
	public String signin(){
		jsonResult = new HashMap<String, Object>();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(email==null||password==null){
			jsonResult.put("code", -1);
			jsonResult.put("comment", "数据格式有误");
		}else{
			ResultMessage rm = accountService.authenticate(email, password);
			if(rm.isResult()){
				try{
					Account account = (Account) rm.getObj();
					session.put("account", account);
					jsonResult.put("code", 1);
				}catch(Exception e){
					e.printStackTrace();
					jsonResult.put("code", -1);
					jsonResult.put("comment","服务器出现故障");
				}
			}else{
				jsonResult.put("code", -1);
				jsonResult.put("comment", rm.getComment());
			}
		}
		return SUCCESS;
	}
	public String signup(){
		jsonResult = new HashMap<String, Object>();
		String surname = request.getParameter("surname");
		String psname = request.getParameter("psname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(surname==null||
				psname==null||
				email == null||
				password == null){
			jsonResult.put("code", -1);
			jsonResult.put("comment", "数据格式有误");
		}else{
			SignUpVO vo = new SignUpVO();
			vo.setEmail(email);
			vo.setPassword(password);
			vo.setName(surname+psname);
			ResultMessage rm = accountService.signup(vo);
			try{
				if(rm.isResult()){
					String activateCode = (String) rm.getObj();
					EMail activateEmail = new EMail();
					activateEmail.setTitle("TSSSpider激活邮件");
					String content = "请在30分钟内访问以下地址激活账户\n";
					String path = request.getContextPath();
					String basePath = request.getScheme() + "://"
							+ request.getServerName() + ":" + request.getServerPort()
							+ path + "/";
					content += basePath+"activate?code="+activateCode;
					activateEmail.setContent(content);
					activateEmail.setReceiver(email);
//					mailService.mailTo(activateEmail);
					jsonResult.put("code", 1);
				}else{
					jsonResult.put("code", -1);
					jsonResult.put("comment", rm.getComment());
				}
			}catch(Exception e){
				e.printStackTrace();
				jsonResult.put("code", -1);
				jsonResult.put("comment", "激活码机制出现故障");
			}
		}
		return SUCCESS;
	}
	/*
	 * ************************************************************************************
	 * */
	public Map<String, Object> getJsonResult() {
		return jsonResult;
	}

	public ResultMessage getPageResult() {
		return pageResult;
	}
}
