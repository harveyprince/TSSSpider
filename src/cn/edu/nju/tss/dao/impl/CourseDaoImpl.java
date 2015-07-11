package cn.edu.nju.tss.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.nju.tss.dao.BaseDao;
import cn.edu.nju.tss.dao.CourseDao;
import cn.edu.nju.tss.model.Course;

@Repository
public class CourseDaoImpl implements CourseDao {
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public List<Course> getCourseList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course getCourseByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

}
