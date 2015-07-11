package cn.edu.nju.tss.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Mailer")
public class Mailer {
	/**
	 * 序列号
	 */
	@Id
	private long id;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 姓名
	 */
	private String name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	
}
