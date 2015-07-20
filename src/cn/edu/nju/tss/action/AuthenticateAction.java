package cn.edu.nju.tss.action;

import org.springframework.stereotype.Repository;

@Repository
public class AuthenticateAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2572880720558093639L;
	
	public String welcome(){
		return SUCCESS;
	}
}
