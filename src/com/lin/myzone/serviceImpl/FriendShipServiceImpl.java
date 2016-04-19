package com.lin.myzone.serviceImpl;

import org.springframework.stereotype.Service;

import com.lin.myzone.beans.FriendMaintain;
import com.lin.myzone.daoImpl.DaoImpl;
import com.lin.myzone.service.FriendShipService;
@Service("friendImpl")
public class FriendShipServiceImpl extends DaoImpl<FriendMaintain>implements FriendShipService {

	public void changeFriendShip(FriendMaintain friend) {
		update(friend);

	}


	public void applyFriendShip(FriendMaintain friend) {
		sava(friend);
		
	}

}
