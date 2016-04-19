package com.lin.myzone.service;

import com.lin.myzone.beans.FriendMaintain;

public interface FriendShipService {
	/**
	 * 更新好友关系
	 * @param friend
	 */
	public void changeFriendShip(FriendMaintain friend);

	/**
	 * 第一次提出好友申请
	 * @param friend
	 */
	public void applyFriendShip(FriendMaintain friend);
}
