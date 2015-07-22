package cn.edu.nju.tss.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Course")
public class Course {
	/**
	 * 流水号
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	/**
	 * 课程码
	 */
	@Column(unique = true)
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
	/**
	 * 系统捕获时的更新时间
	 */
	private Timestamp time;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
