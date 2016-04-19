package com.lin.myzone.beans;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mz_user")
public class User implements Serializable{
	@Id
	@GeneratedValue
	private Integer id;//主键id
	private String createTime;//申请日期
	private String email;//邮箱
	private String name;//昵称
	private Integer mz_number;//登陆号码，唯一标识
	private String password;//登陆密码
	private String sex;//性别   0表示女，1表示男
	private String city;//所在城市
	private String question;//密保问题
	private String answer;//密保答案
	private String telenum;//手机号码
	private int age;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMz_number() {
		return mz_number;
	}
	public void setMz_number(Integer mz_number) {
		this.mz_number = mz_number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getTelenum() {
		return telenum;
	}
	public void setTelenum(String telenum) {
		this.telenum = telenum;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
}
