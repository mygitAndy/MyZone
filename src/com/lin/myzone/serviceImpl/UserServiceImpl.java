package com.lin.myzone.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lin.myzone.beans.User;
import com.lin.myzone.daoImpl.DaoImpl;
import com.lin.myzone.service.UserService;
@Service
@Transactional
public class UserServiceImpl extends DaoImpl<User> implements UserService {

	
	public void addUser(User user) {
		
		sava(user);
	}

	
	public User login(int number) {
		
		String con="and mz_number = '"+number+"'";
		
		return queryByCod(User.class,con);
	}


	public List<User> getUserList() {
		String con="";
		return queryListAll(User.class, con);
	}

}
