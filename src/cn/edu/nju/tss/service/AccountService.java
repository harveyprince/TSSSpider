package cn.edu.nju.tss.service;

import cn.edu.nju.tss.model.vo.ResultMessage;
import cn.edu.nju.tss.model.vo.SignUpVO;

public interface AccountService {
	/**
	 * @param email 邮箱
	 * @param password 密码
	 * @return 认证结果
	 */
	public ResultMessage authenticate(String email,String password);
	/**
	 * @param vo 注册信息
	 * @return 注册结果[注册结束采用邮件发送激活码的形式进行激活
	 */
	public ResultMessage signup(SignUpVO vo);
	/**
	 * @param activateCode 激活码
	 * @return 返回激活结果
	 */
	public ResultMessage activate(String activateCode);
	/**
	 * @param email 邮箱
	 * @return 检测结果
	 */
	public ResultMessage emailFind(String email);
}
