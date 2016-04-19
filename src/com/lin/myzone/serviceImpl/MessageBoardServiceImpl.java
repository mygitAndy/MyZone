package com.lin.myzone.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lin.myzone.beans.MBmessage;
import com.lin.myzone.daoImpl.DaoImpl;
import com.lin.myzone.service.MessageBoardService;
@Service
public class MessageBoardServiceImpl extends DaoImpl<MBmessage> implements MessageBoardService {

	public List<MBmessage> getAllMessage() {
		return queryListAll(MBmessage.class, "");
	}

	
	public void addMessage(MBmessage message) {
		
		sava(message);
	}


	public void flushMessageBoard(MBmessage message) {
		update(message);
		
	}

	
	
	
	
	}


