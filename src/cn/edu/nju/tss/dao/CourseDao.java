package cn.edu.nju.tss.dao;

import java.util.List;

import cn.edu.nju.tss.model.Course;

public interface CourseDao {

	public List<Course> getCourseList();
	
	public Course getCourseByCode(String code);

	public List<Course> getCourseByPage(int page);

	public List<Course> getUnfollowedCourseByPage(String address, int page);

	public List<Course> getFollowedCourseListByPage(String email, int page);

	public List<Course> getUnfollowedCourseByPageAndKey(String address,
			int page, String key);

}
