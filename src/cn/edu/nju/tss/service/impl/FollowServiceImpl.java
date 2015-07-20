package cn.edu.nju.tss.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.tss.dao.BaseDao;
import cn.edu.nju.tss.dao.CourseDao;
import cn.edu.nju.tss.dao.MailDao;
import cn.edu.nju.tss.model.Course;
import cn.edu.nju.tss.model.Mailer;
import cn.edu.nju.tss.model.vo.ResultMessage;
import cn.edu.nju.tss.service.FollowService;
@Service
public class FollowServiceImpl implements FollowService {
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private MailDao mailDao;
	
	@Override
	public ResultMessage changeFollowWayTo(String email, int followWay) {
		// TODO Auto-generated method stub
		ResultMessage rm = new ResultMessage();
		if(followWay<0||followWay>=4){
			rm.setResult(false);
			rm.setComment("未定义的关注方式");
			return rm;
		}
		Mailer mailer = mailDao.getMailerByEmail(email);
		if(mailer.getFollowWay()!=followWay){
			mailer.setFollowWay(followWay);
			try{
				baseDao.update(mailer);
				rm.setResult(true);
			}catch(Exception e){
				e.printStackTrace();
				rm.setResult(false);
				rm.setComment("服务器更新异常");
			}
		}else{
			rm.setResult(false);
			rm.setComment("未做任何改动");
		}
		return rm;
	}

	@Override
	public ResultMessage followCourseByCode(String email, String code) {
		// TODO Auto-generated method stub
		ResultMessage rm = new ResultMessage();
		Mailer mailer = mailDao.getMailerByEmail(email);
		List<Course> courseList = mailer.getFollowedList();
		if(courseList==null){
			courseList = new ArrayList<Course>();
		}
		Course course = courseDao.getCourseByCode(code);
		if(course==null){
			rm.setResult(false);
			rm.setComment("该课程不存在");
		}else{
			courseList.add(course);
			mailer.setFollowedList(courseList);
			try{
				baseDao.update(mailer);
				rm.setResult(true);
			}catch(Exception e){
				e.printStackTrace();
				rm.setResult(false);
				rm.setComment("服务器更新异常");
			}
		}
		return rm;
	}

	@Override
	public ResultMessage unfollowCourseByCode(String email, String code) {
		// TODO Auto-generated method stub
		ResultMessage rm = new ResultMessage();
		Mailer mailer = mailDao.getMailerByEmail(email);
		List<Course> courseList = mailer.getFollowedList();
		if(courseList==null){
			rm.setResult(false);
			rm.setComment("关注列表为空");
			return rm;
		}
		Course course = null;
		for(Course temp:courseList){
			if(temp.getCode().equals(code)){
				course = temp;
			}
		}
		if(course==null){
			rm.setResult(false);
			rm.setComment("该课程不在关注列表内");
		}else{
			courseList.remove(course);
			mailer.setFollowedList(courseList);
			try{
				baseDao.update(mailer);
				rm.setResult(true);
			}catch(Exception e){
				e.printStackTrace();
				rm.setResult(false);
				rm.setComment("服务器更新异常");
			}
		}
		return rm;
	}

}
