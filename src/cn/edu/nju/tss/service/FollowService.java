package cn.edu.nju.tss.service;

import java.util.List;

import cn.edu.nju.tss.model.Course;
import cn.edu.nju.tss.model.vo.ResultMessage;

public interface FollowService {
	/**
	 * @param email 用户识别
	 * @param followWay 关注方式
	 * @return 返回改变关注方式的结果
	 */
	public ResultMessage changeFollowWayTo(String email, int followWay);
	/**
	 * @param email 用于区分用户
	 * @param code 课程码
	 * @return 返回关注结果
	 */
	public ResultMessage followCourseByCode(String email, String code);
	/**
	 * @param email 用于区分用户
	 * @param code 课程码
	 * @return 返回取消关注的结果
	 */
	public ResultMessage unfollowCourseByCode(String email, String code);
	/**
	 * @param email 用户识别
	 * @param page 页数
	 * @return 返回关注的课程列表
	 */
	public List<Course> getFollowedListByPage(String email, int page);
}
