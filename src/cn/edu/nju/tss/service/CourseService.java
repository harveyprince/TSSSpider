package cn.edu.nju.tss.service;

import java.util.List;

import cn.edu.nju.tss.model.Course;

public interface CourseService {
	/**
	 * @param page 页数
	 * @return 获取本地最新的所有课程
	 */
	public List<Course> getCourseListByPage(int page);
	/**
	 * @param address 用户识别
	 * @param page 页数
	 * @return 获取未关注的所有课程
	 */
	public List<Course> getUnfollowedCourseListByPage(String address, int page);
	/**
	 * @param email 用户识别
	 * @param page 页数
	 * @param key 搜索条件
	 * @return 根据条件获取未关注的所有课程
	 */
	public List<Course> getUnfollowedCourseListByPageAndKey(String email,
			int page, String key);
	
}
