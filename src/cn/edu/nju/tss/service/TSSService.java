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
	 * @return 返回发生更新的新增课程列表
	 */
	public List<Course> compareAddCourseList(List<Course> info);
	/**
	 * @param info 课程列表
	 * @return 返回发生更新的已有课程列表
	 */
	public List<Course> compareUpdateCourseList(List<Course> info);
}
