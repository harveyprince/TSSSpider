package cn.edu.nju.tss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.tss.dao.CourseDao;
import cn.edu.nju.tss.model.Course;
import cn.edu.nju.tss.service.CourseService;
@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	@Override
	public List<Course> getCourseListByPage(int page) {
		// TODO Auto-generated method stub
		List<Course> list = courseDao.getCourseByPage(page);
		return list;
	}
	@Override
	public List<Course> getUnfollowedCourseListByPage(String address, int page) {
		// TODO Auto-generated method stub
		List<Course> list = courseDao.getUnfollowedCourseByPage(address, page);
		return list;
	}
	@Override
	public List<Course> getUnfollowedCourseListByPageAndKey(String address,
			int page, String key) {
		// TODO Auto-generated method stub
		List<Course> list = courseDao.getUnfollowedCourseByPageAndKey(address, page, key);
		return list;
	}

}
