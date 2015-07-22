package cn.edu.nju.tss.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.tss.dao.BaseDao;
import cn.edu.nju.tss.dao.CourseDao;
import cn.edu.nju.tss.dao.MailDao;
import cn.edu.nju.tss.model.Course;
import cn.edu.nju.tss.model.EMailDB;
import cn.edu.nju.tss.model.Mailer;
import cn.edu.nju.tss.model.vo.EMail;
import cn.edu.nju.tss.service.CronService;
import cn.edu.nju.tss.service.MailService;
import cn.edu.nju.tss.service.TSSService;
@Service
public class CronServiceImpl implements CronService{
	@Autowired
	private MailService mailService;
	@Autowired
	private TSSService tssService;
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private MailDao mailDao;
	@Override
	public void cronTSSSpider() {
		// TODO Auto-generated method stub
		List<Course> nowCourselist = courseDao.getCourseList();
		List<Course> netCourselist = tssService.getCourseListFromNet();
//		区分新增与更新
//		新增
		List<Course> addedCourselist = tssService.compareAddCourseList(netCourselist);
//		更新
		List<Course> updatedCourselist = tssService.compareUpdateCourseList(netCourselist);
		if(addedCourselist.size()==0&&updatedCourselist.size()==0){
			return;
		}
		if(nowCourselist==null){
			nowCourselist = new ArrayList<Course>();
		}
		if(netCourselist==null){
			netCourselist = new ArrayList<Course>();
		}
		try{

//		针对用户进行个性化通知
		List<Mailer> mailerlist =  mailDao.getAllMailers();
		List<Course> nnowCourselist = courseDao.getCourseList();
		for(Mailer temp:mailerlist){
			EMail email = null;
//			全部关注
			if(temp.getFollowWay()==0){
				email = packingNotice(addedCourselist,updatedCourselist,temp.getAddress());
			}
//			白名单
			else if(temp.getFollowWay()==1){
				List<Course> followedCourselist = temp.getFollowedList();
				List<Course> unfollowedList = new ArrayList<Course>();
				unfollowedList.addAll(nnowCourselist);
				unfollowedList = removeCourseList(unfollowedList,followedCourselist);
				List<Course> fupdatedCourselist = new ArrayList<Course>();
				fupdatedCourselist.addAll(updatedCourselist);
				fupdatedCourselist = removeCourseList(fupdatedCourselist,unfollowedList);
				email = packingNotice(addedCourselist,fupdatedCourselist,temp.getAddress());
			}
//			黑名单
			else if(temp.getFollowWay()==2){
				List<Course> unfollowedCourselist = temp.getFollowedList();
				List<Course> fupdatedCourselist = new ArrayList<Course>();
				fupdatedCourselist.addAll(updatedCourselist);
				fupdatedCourselist = removeCourseList(fupdatedCourselist,unfollowedCourselist);
				email = packingNotice(addedCourselist,fupdatedCourselist,temp.getAddress());
			}
			else{
				continue;
			}
			if(email!=null){
				mailService.mailTo(email);
				EMailDB emaildb = new EMailDB();
				emaildb.setMailer(temp);
				emaildb.setTitle(email.getTitle());
				emaildb.setContent(email.getContent());
				Date date = new Date();
				emaildb.setTime(new Timestamp(date.getTime()));
				try{
					baseDao.save(emaildb);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private List<Course> removeCourseList(List<Course> big,List<Course> small){
		List<Course> toremove = new ArrayList<Course>();
		List<Course> tbig = big;
		for(Course temp:big){
			for(Course t:small){
				if(temp.getCode().equals(t.getCode())){
					toremove.add(temp);
				}
			}
		}
		tbig.removeAll(toremove);
		return tbig;
	}
	
	private EMail packingNotice(List<Course> addedlist, List<Course> updatedlist,String address){
		try{
			if((addedlist==null||addedlist.size()==0)&&(updatedlist==null||updatedlist.size()==0)){
				return null;
			}
		}catch(Exception e){
			return null;
		}
		EMail email = new EMail();
		String addedContent = "新增的课程有:\n";
		for(Course added:addedlist){
			addedContent += added.getCode()+"|"+added.getName()+"|"+added.getTeacher()+"|"+added.getLatestUpdateTime();
			addedContent += "\n";
		}
		String updatedContent = "更新的课程有:\n";
		for(Course updated:updatedlist){
			updatedContent += updated.getCode()+"|"+updated.getName()+"|"+updated.getTeacher()+"|"+updated.getLatestUpdateTime();
			updatedContent += "\n";
		}
		String content = "";
		content += addedlist.size()>0?addedContent:"";
		content += updatedlist.size()>0?updatedContent:"";
		String title = "TSSSpider提醒您";
		title += addedlist.size()>0?"新增"+addedlist.size()+"门课程|":"";
		title += updatedlist.size()>0?"更新"+updatedlist.size()+"门课程":"";
		email.setTitle(title);
		email.setContent(content);
		email.setReceiver(address);
		return email;
	}
	
	
}
