package com.lin.myzone.beans;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="tb_MBreply")
public class MBreply implements Serializable{

	@Id
	@GeneratedValue
	private Integer id;//主键id
    @Lob   
    @Basic(fetch = FetchType.LAZY)   
    @Type(type="text")  
    @Column(name="replyContent", nullable=true)  
	private String replyContent;//回复内容
	private String replyTime;//回复时间
	
	//@ManyToOne(cascade={CascadeType.MERGE})
	@ManyToOne
	@JoinColumn(name="replyuserid")
	private User replyUser;//回复用户的外键

	//@ManyToOne(cascade=CascadeType.REFRESH,optional=false)
	@ManyToOne
	//@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }) 
	@JoinColumn(name="mbMessageid")     //MBreply类中对应外键的属性：mbMessageid 
	private MBmessage mbMessage; 


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	public User getReplyUser() {
		return replyUser;
	}
	public void setReplyUser(User replyUser) {
		this.replyUser = replyUser;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public MBmessage getMbMessage() {
		return mbMessage;
	}
	public void setMbMessage(MBmessage mbMessage) {
		this.mbMessage = mbMessage;
	}
	
}
