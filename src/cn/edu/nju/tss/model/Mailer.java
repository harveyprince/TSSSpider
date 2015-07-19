package cn.edu.nju.tss.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="Mailer")
public class Mailer {
	/**
	 * 地址
	 */
	@Id
	private String address;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 关注方式
	 * ０：全部关注
	 * １：白名单
	 * ２：黑名单
	 */
	@ColumnDefault(value = "0")
	private int followWay;
	/**
	 * 所联系的课程
	 */
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.EAGER)
	@JoinTable(name = "FollowCourse", joinColumns = { @JoinColumn(name ="address" )}, inverseJoinColumns = { @JoinColumn(name = "code") })
	private List<Course> followedList;

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFollowWay() {
		return followWay;
	}
	public void setFollowWay(int followWay) {
		this.followWay = followWay;
	}
	public List<Course> getFollowedList() {
		return followedList;
	}
	public void setFollowedList(List<Course> followedList) {
		this.followedList = followedList;
	}
	
}
