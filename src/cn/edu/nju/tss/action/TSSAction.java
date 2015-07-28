package cn.edu.nju.tss.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.nju.tss.model.Account;
import cn.edu.nju.tss.model.Course;
import cn.edu.nju.tss.model.EMailDB;
import cn.edu.nju.tss.model.Mailer;
import cn.edu.nju.tss.model.vo.ResultMessage;
import cn.edu.nju.tss.service.CourseService;
import cn.edu.nju.tss.service.FollowService;
import cn.edu.nju.tss.service.MailService;
import cn.edu.nju.tss.service.TSSService;

@Repository
public class TSSAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7861690796993971267L;
	
	@Autowired
	private TSSService tssService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private FollowService followService;
	@Autowired
	private MailService mailService;
	
	private Map<String, Object> jsonResult;
	private Map<String, Object> fjsonResult;
	private String name;
	
	private int followWay;
	/*
	 * page
	 * */
	public String follow(){
		if(!session.containsKey("account")){
			return UNAUDITED;
		}
		try{
			Account account = (Account) session.get("account");
			Mailer mailer = mailService.getMailerByEmail(account.getEmail());
			name = mailer.getName();
			followWay = mailer.getFollowWay();
		}catch(Exception e){
			e.printStackTrace();
			return UNAUDITED;
		}
		return SUCCESS;
	}
	
	public String mail(){
		if(!session.containsKey("account")){
			return UNAUDITED;
		}
		try{
			Account account = (Account) session.get("account");
			Mailer mailer = mailService.getMailerByEmail(account.getEmail());
			name = mailer.getName();
		}catch(Exception e){
			e.printStackTrace();
			return UNAUDITED;
		}
		return SUCCESS;
	}
	/*
	 * json
	 * */
	public String course(){
		jsonResult = new HashMap<String, Object>();
		try{
			Account account = (Account) session.get("account");
			String pagestr = request.getParameter("page");
			String key = request.getParameter("key");
			int page = 0;
			try{
				page = Integer.parseInt(pagestr);
			}catch(Exception e){
				page = 0;
			}
			List<Course> courselist;
			if(key==null||key.length()<=0){
				courselist = courseService.getUnfollowedCourseListByPage(account.getEmail(), page);
			}else{
				courselist = courseService.getUnfollowedCourseListByPageAndKey(account.getEmail(), page, key);
			}
			if(courselist==null){
				jsonResult.put("code", -1);
				jsonResult.put("comment", "无该页数据");
				return SUCCESS;
			}
			jsonResult.put("code", 1);
			jsonResult.put("course", courselist);
		}catch(Exception e){
			e.printStackTrace();
			jsonResult.put("code", -1);
			jsonResult.put("comment", "身份已过期请重新登录");
		}
		return SUCCESS;
	}
	public String followlist(){
		fjsonResult = new HashMap<String, Object>();
		try{
			Account account = (Account) session.get("account");
			String pagestr = request.getParameter("page");
			int page = 0;
			try{
				page = Integer.parseInt(pagestr);
			}catch(Exception e){
				page = 0;
			}
			List<Course> courselist = followService.getFollowedListByPage(account.getEmail(), page);
			if(courselist==null){
				fjsonResult.put("code", -1);
				fjsonResult.put("comment", "无该页数据");
				return SUCCESS;
			}
			fjsonResult.put("code", 1);
			fjsonResult.put("course", courselist);
		}catch(Exception e){
			e.printStackTrace();
			fjsonResult.put("code", -1);
			fjsonResult.put("comment", "身份已过期请重新登录");
		}
		return SUCCESS;
	}
	public String followCourse(){
		jsonResult = new HashMap<String, Object>();
		try{
			Account account = (Account) session.get("account");
			String code = request.getParameter("code");
			if(code==null){
				jsonResult.put("code", -1);
				jsonResult.put("comment", "数据格式有误");
				return SUCCESS;
			}
			ResultMessage rm = followService.followCourseByCode(account.getEmail(), code);
			if(rm.isResult()){
				jsonResult.put("code", 1);
			}else{
				jsonResult.put("code", -1);
				jsonResult.put("comment", rm.getComment());
			}
		}catch(Exception e){
			e.printStackTrace();
			jsonResult.put("code", -1);
			jsonResult.put("comment", "身份已过期请重新登录");
		}
		return SUCCESS;
	}
	public String unfollowCourse(){
		jsonResult = new HashMap<String, Object>();
		try{
			Account account = (Account) session.get("account");
			String code = request.getParameter("code");
			if(code==null){
				jsonResult.put("code", -1);
				jsonResult.put("comment", "数据格式有误");
				return SUCCESS;
			}
			ResultMessage rm = followService.unfollowCourseByCode(account.getEmail(), code);
			if(rm.isResult()){
				jsonResult.put("code", 1);
			}else{
				jsonResult.put("code", -1);
				jsonResult.put("comment", rm.getComment());
			}
		}catch(Exception e){
			e.printStackTrace();
			jsonResult.put("code", -1);
			jsonResult.put("comment", "身份已过期请重新登录");
		}
		return SUCCESS;
	}
	public String changeway(){
		jsonResult = new HashMap<String, Object>();
		try{
			Account account = (Account) session.get("account");
			String waystr = request.getParameter("way");
			if(waystr==null){
				jsonResult.put("code", -1);
				jsonResult.put("comment", "数据格式有误");
				return SUCCESS;
			}
			int way = Integer.parseInt(waystr);
			ResultMessage rm = followService.changeFollowWayTo(account.getEmail(), way);
			if(rm.isResult()){
				jsonResult.put("code", 1);
			}else{
				jsonResult.put("code", -1);
				jsonResult.put("comment", rm.getComment());
			}
		}catch(Exception e){
			e.printStackTrace();
			jsonResult.put("code", -1);
			jsonResult.put("comment", "身份已过期请重新登录");
		}
		return SUCCESS;
	}
	public String maillist(){
		jsonResult = new HashMap<String, Object>();
		try{
			Account account = (Account) session.get("account");
			String pagestr = request.getParameter("page");
			int page = 0;
			try{
				page = Integer.parseInt(pagestr);
			}catch(Exception e){
				page = 0;
			}
			List<EMailDB> list = mailService.getMailListByPage(account.getEmail(), page);
			if(list==null||list.size()<=0){
				jsonResult.put("code", -1);
				jsonResult.put("comment", "邮件列表为空");
			}else{
				jsonResult.put("code", 1);
				jsonResult.put("mail", list);
			}
		}catch(Exception e){
			e.printStackTrace();
			jsonResult.put("code", -1);
			jsonResult.put("comment", "身份已过期请重新登录");
		}
		return SUCCESS;
	}
	/*
	 * *************************************************************************************
	 * */
	public Map<String, Object> getJsonResult() {
		return jsonResult;
	}

	public int getFollowWay() {
		return followWay;
	}

	public Map<String, Object> getFjsonResult() {
		return fjsonResult;
	}

	public String getName() {
		return name;
	}


}
