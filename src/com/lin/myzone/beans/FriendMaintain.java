package com.lin.myzone.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.ws.rs.DefaultValue;

@Entity
@Table(name="tb_friendMaintain")
public class FriendMaintain implements Serializable{

	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User selfUser;
	@ManyToOne
	@JoinColumn(name="friend_id")
	private User friendUser;
	/**
	 * 好友状态：0发出邀请，1对方同意互成为好友，2对方拒绝成为好友，1、2为已发出0状态后的改变
	 * 3脱离好友关系
	 */
	private int status;
	private String applyTime;//申请时间
	private String replyTime;//回复时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getSelfUser() {
		return selfUser;
	}
	public void setSelfUser(User selfUser) {
		this.selfUser = selfUser;
	}
	public User getFriendUser() {
		return friendUser;
	}
	public void setFriendUser(User friendUser) {
		this.friendUser = friendUser;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	
}
