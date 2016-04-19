package com.lin.myzone.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.lin.myzone.beans.MBmessage;
import com.lin.myzone.beans.MBreply;
import com.lin.myzone.beans.PageInfo;
import com.lin.myzone.beans.User;
import com.lin.myzone.dao.DAO;
import com.lin.myzone.service.MBreplyMessageService;
import com.lin.myzone.service.MessageBoardService;
import com.lin.myzone.service.UserService;
import com.lin.myzone.utils.DateUtil;
import com.lin.myzone.utils.PageUtil;

public class MessageBoardAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource(name="daoImpl") private DAO comdao;
	@Resource(name="messageBoardServiceImpl") private MessageBoardService mbService;
	@Resource(name="userServiceImpl") private UserService userService;
	@Resource(name="replyService") private MBreplyMessageService replyService;
	private MBmessage message;
	private MBreply reply;
	private PageInfo pageInfo = new PageInfo();
	
	
	/**
	 * 显示留言板信息
	 * @return
	 */
	public String ShowMessageList(){
		flushMessageBoard();
		return "success";
	}
	/**
	 * 添加留言
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String AddMBmessage() throws UnsupportedEncodingException{
		MBmessage newMBmessage = new MBmessage();
		String content = getRequest().getParameter("content");
		if (content.indexOf("\n") != -1) {
			content = content.replaceAll("\n", "<br>");
		}
		int num =Integer.parseInt(getParameter("myZoneNumber"));
		String createTime = DateUtil.getNow();
		String con = "and mz_number="+num;
		User adminUser = (User)comdao.queryByCod(User.class, con);
		User user = (User)getSession().getAttribute("adminUser");
		
		
		newMBmessage.setContent(content);
		newMBmessage.setMessageTime(createTime);
		newMBmessage.setOwnUser(adminUser);
				
		if(num == user.getMz_number()){
			newMBmessage.setOtherUser(adminUser);
			
		}else{
			newMBmessage.setOtherUser(user);
		}
		mbService.addMessage(newMBmessage);
		flushMessageBoard();
		return SUCCESS;
	}
	
	/**
	 * 删除留言
	 * @return
	 */
	public String DeteleMBmessage(){
		Integer messageid = Integer.parseInt(getParameter("id"));
		comdao.delete(MBmessage.class, messageid);
		flushMessageBoard();
		return SUCCESS;
	}
	
	/**
	 * 添加回复
	 * @return
	 */
	public void AddMBreply(){
		try{
		MBreply mbReply = new MBreply();
		int adminNumber = Integer.parseInt(getParameter("myZoneNumber"));
		Integer messageid =Integer.parseInt(getParameter("id"));
		String conMessage = "and o.id='"+messageid+"'";
		String conUser = "and o.mz_number='"+adminNumber+"'";
		String replyContent = null;
		String ReplyContent = getParameter("ReplyContent").trim();
		replyContent = ReplyContent;
		//此时登录账号将作为访客进行判断，当与目标账号相同时即为主人，不同即为访客
		User visitor = (User)getSession().getAttribute("adminUser");
		User  adminUser = (User)comdao.queryByCod(User.class, conUser);
		MBmessage message = (MBmessage)comdao.queryByCod(MBmessage.class, conMessage);
		mbReply.setMbMessage(message);		
		mbReply.setReplyContent(replyContent);
		mbReply.setReplyTime(DateUtil.getNow());
		mbReply.setReplyUser(visitor);
		message.addReply(mbReply);
		mbService.flushMessageBoard(message);
		flushMessageBoard();
		setText("1");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除回复
	 * @return
	 */
	public String deleteMBreply(){
		try{
		Integer replyid = Integer.parseInt(getParameter("id"));
		MBreply reply = (MBreply)comdao.queryOne(MBreply.class,replyid);		
		//MBmessage mainMessage = reply.getMbMessage();
/*		System.out.println("11111"+mainMessage.getReplyList().size());
		mainMessage.getReplyList().remove(reply);
		System.out.println("2222"+mainMessage.getReplyList().size());*/
		//mainMessage.getReplyList().clear();
		//comdao.delete(MBreply.class, replyid);
		//mbService.flushMessageBoard(mainMessage);
		//replyService.deleteReplyMessage(reply);
		comdao.removePersistentEntity(reply);
/*		String sql = "delete from tb_mbreply where id='"+replyid+"'";
		comdao.deleteEntityBysql(sql);*/
		flushMessageBoard();
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 留言人更改留言信息
	 * @return
	 */
	public void editMBmessage(){
		Integer messageid = Integer.parseInt(getParameter("messageid"));
		String editedMessageContent = getParameter("editedMessageContent").trim();
		MBmessage message = (MBmessage)comdao.queryOne(MBmessage.class, messageid);
		message.setContent(editedMessageContent);
		mbService.flushMessageBoard(message);
		flushMessageBoard();
		setText("1");
	}
	
	/**
	 * 获取数据库中所有留言信息在前台展示
	 */
	public void flushMessageBoard(){
		User user = (User)getSession().getAttribute("adminUser");
		String con="and o.ownUser.id = "+user.getId()+"order by o.id desc";
		PageUtil.pageInfoPreSet(pageInfo, request);
		List<MBmessage> messages = comdao.queryList4Page(MBmessage.class, con, pageInfo);
		PageUtil.pageInfoSet(pageInfo, request);
		request.setAttribute("messages", messages);		
	}
	
	public MBmessage getMessage() {
		return message;
	}
	public void setMessage(MBmessage message) {
		this.message = message;
	}
	
	
}
