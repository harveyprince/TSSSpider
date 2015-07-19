package cn.edu.nju.tss.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="EMailDB")
public class EMailDB {
	/**
	 * 流水ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	/**
	 * 记录时间
	 */
	private Timestamp time;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	@Type(type="text")
	private String content;
	/**
	 * 收件人
	 */
	@ManyToOne(targetEntity=Mailer.class,fetch = FetchType.EAGER)
	@JoinColumn(name="address")
	private Mailer mailer;
	public long getId() {
		return id;
	}
	public Timestamp getTime() {
		return time;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public Mailer getMailer() {
		return mailer;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setMailer(Mailer mailer) {
		this.mailer = mailer;
	}
	

}
