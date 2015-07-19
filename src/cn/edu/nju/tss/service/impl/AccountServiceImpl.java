package cn.edu.nju.tss.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import cn.edu.nju.tss.conf.RedisDecorate;
import cn.edu.nju.tss.dao.AccountDao;
import cn.edu.nju.tss.dao.BaseDao;
import cn.edu.nju.tss.model.Account;
import cn.edu.nju.tss.model.vo.ResultMessage;
import cn.edu.nju.tss.model.vo.SignUpVO;
import cn.edu.nju.tss.service.AccountService;
import cn.edu.nju.tss.tool.Shuffle;
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
			String activateCodeSource = Shuffle.shuffle("qwertyuiopasdfghjklzxcvbnm_QWERTYUIOPASDFGHJKLZXCVBNM");
			String activateCode = activateCodeSource.substring(0, 20);
//			在redis中采用超时过期的方法保存注册信息
			try{
				valueOps.append(RedisDecorate.activateDec(activateCode), vo.getEmail());
				redisTemplate.expire(RedisDecorate.activateDec(activateCode), 30, TimeUnit.MINUTES);
				valueOps.append(RedisDecorate.nameDec(vo.getEmail()),vo.getName());
				redisTemplate.expire(RedisDecorate.nameDec(vo.getEmail()), 30, TimeUnit.MINUTES);
				valueOps.append(RedisDecorate.passDec(vo.getEmail()),vo.getPassword());
				redisTemplate.expire(RedisDecorate.passDec(vo.getEmail()), 30, TimeUnit.MINUTES);
//				返回携带激活码的结果，用于邮件发送激活码
				rm.setResult(true);
				rm.setObj(activateCode);
			}catch(Exception e){
				e.printStackTrace();
				rm.setResult(false);
				rm.setComment("服务器Redis发生异常");
			}
			
		}
		return rm;
	}

	@Override
	public ResultMessage activate(String activateCode) {
		// TODO Auto-generated method stub
		ResultMessage rm = new ResultMessage();
		try{
			String email = valueOps.get(RedisDecorate.activateDec(activateCode));
			if(email!=null){
				String name = valueOps.get(RedisDecorate.nameDec(email));
				String password = valueOps.get(RedisDecorate.passDec(email));
//				进行实际的注册
				
			}else{
				rm.setResult(false);
				rm.setComment("激活码超时或不存在");
			}
		}catch(Exception e){
			e.printStackTrace();
			rm.setResult(false);
			rm.setComment("激活码超时或不存在或服务器Redis异常");
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
