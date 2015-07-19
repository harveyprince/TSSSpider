package cn.edu.nju.tss.service;

import cn.edu.nju.tss.model.vo.ResultMessage;

public interface FollowService {
	/**
	 * @param followWay 关注方式
	 * @return 返回改变关注方式的结果
	 */
	public ResultMessage changeFollowWayTo(int followWay);
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
}
