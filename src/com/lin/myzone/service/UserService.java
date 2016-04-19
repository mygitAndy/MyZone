package com.lin.myzone.service;

import java.util.List;

import com.lin.myzone.beans.User;

public interface UserService {

	/**
	 * 用户注册添加账号信息
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * 登陆验证,根据账号返回对象
	 * @param name 登陆账号
	 * @return
	 */
	public User login(int number);
	
	public List<User> getUserList();
}
