package com.lin.myzone.action;

import java.util.List;

import javax.annotation.Resource;

import com.lin.myzone.beans.FriendMaintain;
import com.lin.myzone.beans.PageInfo;
import com.lin.myzone.beans.User;
import com.lin.myzone.dao.DAO;
import com.lin.myzone.daoImpl.DaoImpl;
import com.lin.myzone.service.FriendShipService;
import com.lin.myzone.utils.DateUtil;
import com.lin.myzone.utils.PageUtil;

public class FriendsAction extends BaseAction {

	@Resource(name="daoImpl") private DAO comdao;
	@Resource(name="friendImpl") private FriendShipService friendImpl;
	private PageInfo pageInfo = new PageInfo();
	//返回好友列表
	public String friendsList(){
		try{
		int number = Integer.parseInt(getParameter("number"));
		String getUserCon = "and o.mz_number="+number+"";
		User userSelf = (User)comdao.queryByCod(User.class, getUserCon);
		PageUtil.pageInfoPreSet(pageInfo, request);
		//String getFriendsSql = "select * from mz_user u where u.id in(select user_id from tb_friendmaintain as f where f.status=1 and f.friend_id="+userSelf.getId()+") or u.id in(select friend_id from tb_friendmaintain as f  where f.status=1 and f.user_id="+userSelf.getId()+")";
		String getFriendsJpql="and o.id in (select u.selfUser.id from FriendMaintain u where u.status=1 and u.friendUser.id="+userSelf.getId()+") or o.id in(select u.friendUser.id from FriendMaintain u where u.status=1 and u.selfUser.id="+userSelf.getId()+")";
		List<User> friendsListJpql = comdao.queryList4Page(User.class, getFriendsJpql, pageInfo);
		PageUtil.pageInfoSet(pageInfo, request);
		//List<User> friendsList = comdao.queryListAllBySql(User.class, getFriendsSql);
		getRequest().setAttribute("friendList", friendsListJpql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "friendslist";
	}
	
	//查询用户
	public String searchFriend(){
		List<User> userAList = null;
		String name = getParameter("searchContent");
		String conB = " and o.name like '%"+name+"%'";
		PageUtil.pageInfoPreSet(pageInfo, request);
		String con="";
		List<User> userBList =comdao.queryList4Page(User.class, conB,pageInfo);
		
		if( userBList.size() == 0){
			try{
				int number = Integer.parseInt(getParameter("searchContent"));
				String conA = "and o.mz_number="+number+"";
				userAList= comdao.queryList4Page(User.class, conA,pageInfo);
			}catch(Exception e){
				getRequest().setAttribute("searchmessage", "查无此人");
				return "search";
			}
			if(userAList.size() == 0){
				getRequest().setAttribute("searchmessage", "查无此人");
			}else if(userAList.size() != 0){
				PageUtil.pageInfoSet(pageInfo, request);
				getRequest().setAttribute("searchUserList", userAList);
			}
		}else{
			PageUtil.pageInfoSet(pageInfo, request);
			getRequest().setAttribute("searchUserList", userBList);
		}
		
		return "search";
		
	}
	/**
		 使用ajax返回添加好友的关系
		添加好友:一方如果第一次发出申请好友的请求,status为0
		添加方若已经是对方好友，则提示已为好友
		若之前的好友请求已经发起，但未成为好友，则更新好友申请状态，status更新为2
	 */
	public void addFriend(){

		User selfUser = (User)getSession().getAttribute("adminUser");
		Integer friendUserId = Integer.parseInt(getParameter("friendId"));
		User friendUser = (User)comdao.queryOne(User.class,friendUserId);
		String getFriendsShipJpql = "and ((o.selfUser.id="+selfUser.getId()+"and o.friendUser.id="+friendUserId+") or (o.friendUser.id="+selfUser.getId()+"and o.selfUser.id="+friendUserId+"))";
		//判断二者之前是否已经提起过关系
		FriendMaintain isfriendShip = (FriendMaintain)comdao.queryByCod(FriendMaintain.class, getFriendsShipJpql);
		//如果之前二者不存在关系，则从新发起
		if(isfriendShip == null){
			FriendMaintain friendShip = new FriendMaintain();
			friendShip.setApplyTime(DateUtil.getNow());
			friendShip.setSelfUser(selfUser);
			friendShip.setFriendUser(friendUser);
			friendShip.setStatus(0);
			friendImpl.applyFriendShip(friendShip);
			setText("1");//返回成功第一次建立好友关系
		}else{
			//二者之前已经成为了好友关系
			if(isfriendShip.getStatus() == 1){
				setText("2");
			}else{
				//申请人再次提请好友关系
				isfriendShip.setStatus(0);
				isfriendShip.setApplyTime(DateUtil.getNow());
				friendImpl.applyFriendShip(isfriendShip);//更新情况
				//同为新添好友，故返回值一致
				setText("1");
			}
		}
		
	}
	//同意好友请求
	public void admitApplyfriend(){
		Integer friendShipId = Integer.parseInt(getParameter("friendShipId"));
		FriendMaintain friendShip = (FriendMaintain)comdao.queryOne(FriendMaintain.class, friendShipId);
		friendShip.setReplyTime(DateUtil.getNow());
		friendShip.setStatus(1);
		friendImpl.changeFriendShip(friendShip);//更新情况
		setText("1");
	}
	
	
	
	
	
	//删除好友,未删除数据库，数据状态改为3
	public void deleteFriend(){
		User adminUser = (User)getSession().getAttribute("adminUser");
		Integer friendId = Integer.parseInt(getParameter("id"));
		User friendUser = (User)comdao.queryOne(User.class, friendId);
		String con = "and o.selfUser.id ="+adminUser.getId()+" and o.friendUser.id="+friendId+"";
		FriendMaintain friendShip = (FriendMaintain)comdao.queryByCod(FriendMaintain.class, con);
		if(friendShip == null){
			con = "and o.friendUser.id ="+adminUser.getId()+" o.selfUser.id="+friendId+"";
			friendShip = (FriendMaintain)comdao.queryByCod(FriendMaintain.class, con);
			friendShip.setStatus(3);//好友关系为删除
			
		}else{
			friendShip.setStatus(3);//好友关系为删除
		}
		friendImpl.changeFriendShip(friendShip);
		setText("1");
	}
	
	//拒绝好友请求
	public void rejectApply(){
		User adminUser = (User)getSession().getAttribute("adminUser");
		Integer friendShipId = Integer.parseInt(getParameter("friendShipId"));
		FriendMaintain friendShip = (FriendMaintain)comdao.queryOne(FriendMaintain.class, friendShipId);
		friendShip.setStatus(2);//拒绝好友请求
		friendShip.setReplyTime(DateUtil.getNow());
		friendImpl.changeFriendShip(friendShip);
		setText("1");
		
	}
	public String add(){
		return "addjsp";
	}
	/**
	 * 返回主动好友申请列表，显示状态
	 * @return
	 */
	public String applyFriendsList(){
		User adminUser = (User)getSession().getAttribute("adminUser");
		PageUtil.pageInfoPreSet(pageInfo, request);
		String con = "and o.selfUser.id="+adminUser.getId()+" order by o.applyTime desc";
		List<FriendMaintain> applyList = comdao.queryList4Page(FriendMaintain.class, con, pageInfo);
		PageUtil.pageInfoSet(pageInfo, request);
		getRequest().setAttribute("applyList", applyList);
		return "applylist";
	}
	
	/**
	 * 返回被申请列表，显示状态
	 * @return
	 */
	public String appliedFriendsList(){
		User adminUser = (User)getSession().getAttribute("adminUser");
		PageUtil.pageInfoPreSet(pageInfo, request);
		String con = "and  o.friendUser.id="+adminUser.getId()+" and o.status=0 order by o.applyTime desc";
		List<FriendMaintain> appliedList = comdao.queryList4Page(FriendMaintain.class, con, pageInfo);
		PageUtil.pageInfoSet(pageInfo, request);
		getRequest().setAttribute("appliedList", appliedList);
		return "appliedlist";
	}
}
