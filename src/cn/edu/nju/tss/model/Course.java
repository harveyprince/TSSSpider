package cn.edu.nju.tss.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Course")
public class Course {
	/**
	 * 课程码
	 */
	@Id
	private String code;
	/**
	 * 课程名
	 */
	private String name;
	/**
	 * 教师名
	 */
	private String teacher;
	/**
	 * 最后更新时间
	 */
	private String latestUpdateTime;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getLatestUpdateTime() {
		return latestUpdateTime;
	}
	public void setLatestUpdateTime(String latestUpdateTime) {
		this.latestUpdateTime = latestUpdateTime;
	}
	
}
