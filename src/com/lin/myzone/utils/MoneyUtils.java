package com.lin.myzone.utils;

import java.text.DecimalFormat;

public class MoneyUtils {
	
	public static String yuan2fen(String money){
		String strTmp=money;
		String strSign="";
		int nLen=0;

		if (money==null || money.equals("") ||money.equals("0")) {
			return "0000000000000000";
		}
		if(strTmp.startsWith("-")||strTmp.startsWith("+")){
			strSign=strTmp.substring(0,1);
			money=strTmp.substring(1);
		}
		
		long yuan = 0;
		int jiao = 0;
		int fen = 0;
		String total = "";
		int flag = money.indexOf(".");
		if (flag > 0) {
			String yuanStr = money.substring(0,flag);
			yuan = Integer.parseInt(yuanStr);
			
			String jiaoStr = money.substring(flag+1,flag+2);
			jiao = Integer.parseInt(jiaoStr);
			
			int len = money.split("\\.")[1].length();
			if (len>=2) { 
				String fenStr = money.substring(flag+2,flag+3);
				fen = Integer.parseInt(fenStr);
			}
		}else {
			yuan = Long.parseLong(money);
		}
		
		total = bu0(yuan+"") +jiao+fen;
		return total;
	}

	public static String bu0(String yuan){
		while (yuan.length()!=14) {
			yuan = "0"+yuan;
		}
		return yuan;
	}
	
	
	public static String fen2yuan(String money){
		String strTmp=money;
		String strSign="";
		int nLen=0;

		if (money==null || money.equals("") ||money.equals("0")) {
			return "0.00";
		}
		if(strTmp.startsWith("-")||strTmp.startsWith("+")){
			strSign=strTmp.substring(0,1);
			money=strTmp.substring(1);
		}
		money = money.split("\\.")[0];
		nLen=money.length();
		if(nLen==0){
			return "0.00";			
		}
		Long nMoney=Long.parseLong(money);
		if(nMoney==0){
			return "0.00";				
		}
		if (nLen==1) {
			return strSign+"0.0"+money;
		}
		if (nLen==2) {
			return strSign+"0."+money;
		}
		String yuanStr = money.substring(0,nLen-2);
		while(yuanStr.startsWith("0")){
			yuanStr = yuanStr.substring(1);
		}
		if (yuanStr.length()==0) {
			yuanStr="0";
		}
		String jiaoStr = money.substring(nLen-2,nLen-1);
		
		String fenStr = money.substring(nLen-1);
		
		return strSign+yuanStr+"."+jiaoStr+fenStr;
	}
	
	
	public static void main(String[] args) {
		//String fen = yuan2fen("000.01");
		//System.out.println(fen);
		String strTmp="18013.0";
		
		System.out.println(fen2yuan(strTmp));
	}
}
