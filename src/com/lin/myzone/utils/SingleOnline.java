package com.lin.myzone.utils;

import java.util.HashMap;
import java.util.Map;

public class SingleOnline {
	
	private static Map<String,String>  mapOnline=new HashMap<String, String>();
	
	
	//添加在线用户
	public static synchronized void addOnlineUser(String usercode,String sessionId){
		if(mapOnline.containsKey(usercode)){
			mapOnline.remove(usercode);
		}
		mapOnline.put(usercode,sessionId);
	}
	
	
	//是否为合法用户
	public static  boolean isValidUser(String usercode,String sessionId){
		if(!mapOnline.containsKey(usercode)){
			return false;
		}
		if(!mapOnline.get(usercode).equals(sessionId)){
			return false;
		}
		return true;
	}

}
