package com.lin.myzone.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lin.myzone.beans.MBreply;
import com.lin.myzone.daoImpl.DaoImpl;
import com.lin.myzone.service.MBreplyMessageService;
@Service("replyService")
@Transactional
public class MBreplyMessageServiceImpl extends DaoImpl<MBreply> implements
		MBreplyMessageService {

	
	public void addReplyMessage(MBreply reply) {
		sava(reply);
		
	}

	public void deleteReplyMessage(MBreply reply) {
		removeEntity(reply);
		
		
	}

}
