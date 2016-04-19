package com.lin.myzone.service;

import java.util.List;

import com.lin.myzone.beans.MBmessage;

public interface MessageBoardService {
	/**
	 * 获取所有的留言信息
	 * @return
	 */
	public List<MBmessage> getAllMessage();
	
	/**
	 * 保存新留言
	 * @param message
	 */
	public void addMessage(MBmessage message);
	
	/**
	 * 根据变化刷新留言板
	 * @param message
	 */
	public void flushMessageBoard(MBmessage message);
}
