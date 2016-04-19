package com.lin.myzone.action;

import java.util.List;

import javax.annotation.Resource;

import com.lin.myzone.beans.FriendMaintain;
import com.lin.myzone.beans.MBmessage;
import com.lin.myzone.beans.PageInfo;
import com.lin.myzone.beans.User;
import com.lin.myzone.dao.DAO;
import com.lin.myzone.service.UserService;
import com.lin.myzone.utils.DateUtil;
import com.lin.myzone.utils.MD5;
import com.lin.myzone.utils.PageUtil;

public class UserAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Resource(name="daoImpl") private DAO comdao;
	@Resource(name="userServiceImpl") private UserService userService;
	private User user;
	private PageInfo pageInfo = new PageInfo();
	public String register(){
		
		User newUser =new User();
		newUser.setEmail(user.getEmail());
		newUser.setSex(user.getSex());
		newUser.setName(user.getName());
		newUser.setPassword(MD5.encode(user.getPassword()));
		newUser.setCreateTime(DateUtil.getNow());
		String con = "order by mz_number desc limit 1";
		int number = 1000000;
		User oldUser = (User)comdao.queryByCod(User.class, con);
		if(oldUser == null){
			newUser.setMz_number(number);
		}else{
			newUser.setMz_number(oldUser.getMz_number()+1);
		}
		
		//newUser.setFriends(friendShip);
		userService.addUser(newUser);
		getRequest().setAttribute("number", newUser.getMz_number());
		return SUCCESS;
	}
	
	
	public void login(){
		try{
		int num = Integer.parseInt(getParameter2("mz_number"));
		String password = getParameter2("password");
		
		String loginPassword = MD5.encode(password);
		User loginUser = userService.login(num);	
		if(loginUser == null){
			setText("0");
		}else{
			if(!loginPassword.equals(loginUser.getPassword())){
				setText("1");
			}else{
				setText("2");
			
				getSession().setAttribute("adminUser", loginUser);//将主人登录情况存储在session中即登录人信息
/*				getSession().setAttribute("user", loginUser);*/
			}
			
		}
		}catch(Exception x){
			x.printStackTrace();
		}
		
	}
	/**
	 * 临时跳转留言板
	 * @return
	 */
	public String index(){
		
		List<User> userList = userService.getUserList();
		User user = (User)getSession().getAttribute("adminUser");
		String adminNum = getParameter("number");
		getSession().setAttribute("adminNum", adminNum);//存储主人账号，用于比较访客

		String con=" and o.ownUser.id  = "+user.getId()+"order by o.id desc";
		PageUtil.pageInfoPreSet(pageInfo, request);
		List<MBmessage> messages = comdao.queryList4Page(MBmessage.class, con, pageInfo);
		PageUtil.pageInfoSet(pageInfo, request);
		request.setAttribute("messages", messages);		
		return "list";
	}
	public String goRegister(){
		return "register";
	}
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
