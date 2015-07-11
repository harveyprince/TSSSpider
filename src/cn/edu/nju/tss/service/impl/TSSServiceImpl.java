package cn.edu.nju.tss.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.tss.dao.BaseDao;
import cn.edu.nju.tss.dao.CourseDao;
import cn.edu.nju.tss.model.Course;
import cn.edu.nju.tss.service.TSSService;
import cn.edu.nju.tss.tool.HttpRequest;
@Service
public class TSSServiceImpl implements TSSService {
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public List<Course> getCourseListFromNet() {
		// TODO Auto-generated method stub
		List<Course> courselist = new ArrayList<Course>();
		String param = "";
		String s=HttpRequest.sendGet("http://218.94.159.102/tss/en/home/courselist.html", param);
		String str = s;
		String regex = "<tr >(.+?)</tr>";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(str);
		while(m.find()){
			Course course = new Course();
			String course_html = m.group();
			System.out.println(course_html);
			Pattern pattern_code = Pattern.compile("<td.align=\"center\">(.+?)</td>");
			Matcher m_code = pattern_code.matcher(course_html);
			while(m_code.find()){
				String code = m_code.group(1);
				course.setCode(code);
			}
			Pattern pattern_name = Pattern.compile("<a.href=\".+\">(.+?)</a>");
			Matcher m_name = pattern_name.matcher(course_html);
			while(m_name.find()){
				String name = m_name.group(1).trim();
				course.setName(name);
			}
			Pattern pattern_teacher = Pattern.compile("<td >&nbsp;&nbsp;(.*?)</td>");
			Matcher m_teacher = pattern_teacher.matcher(course_html);
			while(m_teacher.find()){
				String teacher = m_teacher.group(1).trim();
				if(teacher.contains("href")){
					continue;
				}
				course.setTeacher(teacher);
			}
			Pattern pattern_time = Pattern.compile("<td >([0-9]{2}-[0-9]{2}.[0-9]{2}:[0-9]{2})</td>");
			Matcher m_time = pattern_time.matcher(course_html);
			while(m_time.find()){
				String time = m_time.group(1).trim();
				course.setLatestUpdateTime(time);
			}
			courselist.add(course);
		}
		return courselist;
	}

	@Override
	public List<Course> compareCourseList(List<Course> info) {
		// TODO Auto-generated method stub
		List<Course> courselist = new ArrayList<Course>();
		for(Course temp:info){
			Course cdb = courseDao.getCourseByCode(temp.getCode());
			if(cdb==null){
				courselist.add(temp);
				baseDao.save(temp);
			}
			else if(cdb.getLatestUpdateTime()!=temp.getLatestUpdateTime()){
				courselist.add(temp);
				baseDao.update(temp);
			}
			else{
				//nothing to be done
			}
		}
		return courselist;
	}

}
