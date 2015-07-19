package cn.edu.nju.tss.service;

import java.util.List;

import cn.edu.nju.tss.model.Course;

public interface TSSService {
	/**
	 * @return 返回获取到的所有课程
	 */
	public List<Course> getCourseListFromNet();
	/**
	 * @param info 课程列表
	 * @return 返回发生更新的课程列表
	 */
	public List<Course> compareCourseList(List<Course> info);
}
