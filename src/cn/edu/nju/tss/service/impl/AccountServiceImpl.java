package cn.edu.nju.tss.service.impl;

import org.springframework.stereotype.Service;

import cn.edu.nju.tss.model.vo.ResultMessage;
import cn.edu.nju.tss.model.vo.SignUpVO;
import cn.edu.nju.tss.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {

	@Override
	public ResultMessage authenticate(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage signup(SignUpVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage emailTest(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
