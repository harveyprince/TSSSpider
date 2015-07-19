package cn.edu.nju.tss.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import cn.edu.nju.tss.dao.AccountDao;
import cn.edu.nju.tss.dao.BaseDao;
import cn.edu.nju.tss.model.Account;
import cn.edu.nju.tss.model.vo.ResultMessage;
import cn.edu.nju.tss.model.vo.SignUpVO;
import cn.edu.nju.tss.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
	@Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOps;
	@Override
	public ResultMessage authenticate(String email, String password) {
		// TODO Auto-generated method stub
		ResultMessage rm = new ResultMessage();
		Account account = accountDao.findAccountByEmail(email);
		if(account==null){
			rm.setResult(false);
			rm.setComment("用户不存在");
		}else if(account.getPassword().equals(password)){
			rm.setResult(true);
			rm.setComment("认证成功");
			rm.setObj(account);
		}else{
			rm.setResult(false);
			rm.setComment("密码错误");
		}
		return rm;
	}

	@Override
	public ResultMessage signup(SignUpVO vo) {
		// TODO Auto-generated method stub
		ResultMessage rm = new ResultMessage();
		ResultMessage rm_else = emailFind(vo.getEmail());
		if(rm_else.isResult()){
			rm.setResult(false);
			rm.setComment("该邮箱已被占用");
		}else{
//			激活码
			valueOps.append("activate_", vo.getEmail());
			redisTemplate.expire("key", 30, TimeUnit.MINUTES);
		}
		return rm;
	}

	@Override
	public ResultMessage emailFind(String email) {
		// TODO Auto-generated method stub
		ResultMessage rm = new ResultMessage();
		Account account = accountDao.findAccountByEmail(email);
		if(account==null){
			rm.setResult(false);
		}else{
			rm.setResult(true);
		}
		return rm;
	}

}
