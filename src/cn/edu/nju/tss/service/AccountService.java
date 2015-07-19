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
	 * @return 注册结果
	 */
	public ResultMessage signup(SignUpVO vo);
	/**
	 * @param email 邮箱
	 * @return 检测结果
	 */
	public ResultMessage emailTest(String email);
}
