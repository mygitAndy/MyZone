package com.lin.myzone.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="db_MBmessage")
public class MBmessage implements Serializable{

	@Id
	@GeneratedValue
	private Integer id;//主键id
	private String messageTime;//留言时间
    @Lob   
    @Basic(fetch = FetchType.LAZY)   
    @Type(type="text")  
    @Column(name="content", nullable=true)  
	private String content;//留言内容
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="ownuserid")
	private User ownUser;//主用户外键
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="otheruserid")
	private User otherUser;//访客用户外键
	//@OneToMany(cascade={CascadeType.REMOVE,CascadeType.PERSIST}, mappedBy="mbMessage" ,fetch=FetchType.EAGER)
	@OneToMany(mappedBy="mbMessage",cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	private List<MBreply> replyList = new ArrayList<MBreply>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getOwnUser() {
		return ownUser;
	}
	public void setOwnUser(User ownUser) {
		this.ownUser = ownUser;
	}
	public User getOtherUser() {
		return otherUser;
	}
	public void setOtherUser(User otherUser) {
		this.otherUser = otherUser;
	}
	public List<MBreply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<MBreply> replyList) {
		this.replyList = replyList;
	}
	public void addReply(MBreply reply){
		reply.setMbMessage(this);
		this.replyList.add(reply);
	}
}
