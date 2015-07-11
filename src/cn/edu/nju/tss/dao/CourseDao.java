package cn.edu.nju.tss.dao;

import java.util.List;

import cn.edu.nju.tss.model.Course;

public interface CourseDao {

	public List<Course> getCourseList();
	
	public Course getCourseByCode(String code);

}
