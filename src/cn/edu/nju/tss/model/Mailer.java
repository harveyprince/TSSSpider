package cn.edu.nju.tss.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
