package cn.edu.nju.tss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.tss.model.Course;
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
	@Override
	public void cronTSSSpider() {
		// TODO Auto-generated method stub
		System.out.println("worked");
		List<Course> netCourselist = tssService.getCourseListFromNet();
		List<Course> changedCourselist = tssService.compareCourseList(netCourselist);
		if(changedCourselist.size()==0){
			return;
		}
		String content = "更新的课程有:\n";
		int sum = 0;
		for(Course temp:changedCourselist){
			content += temp.getCode()+"|"+temp.getName()+"|"+temp.getTeacher()+"|"+temp.getLatestUpdateTime();
			content += "\n";
			sum++;
		}
		EMail email = new EMail();
		email.setContent(content);
		email.setTitle("TSSSpider提醒您"+sum+"门课程更新了信息");
		mailService.mailToAll(email);
	}
	
	
}
