package cn.edu.nju.tss.service;

import java.util.List;

import cn.edu.nju.tss.model.Course;

public interface CourseService {
	/**
	 * @return 获取本地最新的所有课程
	 */
	public List<Course> getCourseListByPage(int page);
	
}
