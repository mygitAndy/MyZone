package com.lin.myzone.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class StringUtil {
	 
	private static String regEx = "[\u4e00-\u9fa5]";   
	private static Pattern pattern = Pattern.compile(regEx);  
	
	public static String null2Str(Object str){
		if (str!=null && !str.toString().trim().equals("") && !str.toString().trim().equals("null")) {
			return str.toString().trim();
		}
		return "";
	}
	
	public static Integer null2int(Object obj){
		if (obj==null || "".equals(obj.toString().trim())) {
			return null;
		}
		return Integer.parseInt(obj.toString());
	}
	
	
	public static boolean isNullOrEmptyStr(Object str){
		if (str==null ||"".equals(str)||str.toString().trim().equals("null")||str.toString().trim().length()<=0) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotNull(Object str){
		if (str==null  || str.toString().trim().equals("null") || str.toString().trim().length()<=0) {
			return false;
		}
		return true;
	}
	
	public static String format(String str){
		BigDecimal numBigDecimal=new BigDecimal(str);
		Double result = numBigDecimal.intValue()/100D;
		String format = new DecimalFormat("0.00").format(result);
		return format;
	}

	public static String douStr2Int(String str){
		if(isNullOrEmptyStr(str))return "0";
		BigDecimal numBigDecimal=new BigDecimal(str);
//		Double result = numBigDecimal.intValue()/100D;
		String format = new DecimalFormat("0").format(numBigDecimal);
		return format;
	}
	
	public static String ObjTo0_00(Object obj) {
		if (obj==null || "".equals(obj.toString().trim()) || obj.toString().trim().equals("null")) {
			return "0.00";
		}
		if (obj.toString().indexOf(".")!=-1) {
			return format(obj.toString());
		}
		return obj+".00";
	}
	
	public static String fen2yuan(String money){
		if (money==null || money.equals("") ||money.equals("0")) {
			return "0.00";
		}
		if (money.length()==1) {
			return "0.0"+money;
		}
		if (money.length()==2) {
			return "0."+money;
		}
		String yuanStr = money.substring(0,money.length()-2);
		while(yuanStr.startsWith("0")){
			yuanStr = yuanStr.substring(1);
		}
		if (yuanStr.length()==0) {
			yuanStr="0";
		}
		String jiaoStr = money.substring(money.length()-2,money.length()-1);
		
		String fenStr = money.substring(money.length()-1);
		
		return yuanStr+"."+jiaoStr+fenStr;
	}
	
	/**
	 * 对字符串进行初级加密；如136****9876
	 * @param sourceStr：要加密数据
	 * @param begNum：前面显示出来个数：136
	 * @param endNum:后面显示出来个数：9876
	 * @param flag:隐藏符合：*
	 * @param flagNum:隐藏符号个数：(0:原数据长度显示)
	 * @return
	 */
	public static String strEncrypt(String sourceStr,int begNum,int endNum,String flag,int flagNum){
		if(sourceStr==null||"".equals(sourceStr)){
			return "";
		}
//		if(flag==null||"".equals(flag)){
//			return sourceStr;
//		}
		if(sourceStr.length()<(begNum+endNum)){
			return sourceStr;
		}
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(sourceStr.substring(0,begNum));
		if (flagNum==0) {
			for (int i = 0; i < (sourceStr.length()-begNum-endNum); i++) {
				sBuffer.append(flag);
			}
		}else {
			for (int i = 0; i < flagNum; i++) {
				sBuffer.append(flag);
			}
		}
		sBuffer.append(sourceStr.substring(sourceStr.length()-endNum,sourceStr.length()));
		return sBuffer.toString();
	}
	
	 
	  /**
	   * 判断字符串中是否含有中文
	   * @param str
	   * @return
	   */
	   public static boolean isContainsChinese(String str){
	        boolean flg = false;  
		   if (str==null||"".equals(str)) {
			return true;
		}
	        Matcher matcher = pattern.matcher(str);     
	        if (matcher.find())    {    
	            flg = true;   
	        }   
	        return flg;
	   }
	   
	   /**
		 * 判断字符串是否为数字
		 * 
		 * @param String
		 *            str 待判断的字符串
		 * @return boolean b 是数字返回true否则返回false。
		 */
		public static boolean isNumeric(String str){
			Pattern pattern=Pattern.compile("^[1-9]{1}[0-9]*$");
			return pattern.matcher(str).matches();
		}
		
		/**   
	     *   中文字符检验   
	     *   @param   s   String   
	     *   @return   包含中文字符返回true,否则返回false   
	    */   
		public static boolean haveChineseValid(String s){   
	    	if (s==null||"".equals(s)) {
				return true;
			}
	        int length = s.length();   
	        byte [] b;   
	        for(int i=0;i<length;i++){   
	           b =  s.substring(i).getBytes();   
	         if((b[0]&0xff)>128)   
	           return   true;   
	          }   
	        return   false;   
	    }
	
		/**
		 * 罗马数字1-10转换为中文一到十
		 * @param num
		 * @return
		 */
		public static String num2Str(int num){
			String result = "";
			switch (num) {
			case 1:
				result = "一";
				break;
			case 2:
				result = "二";
				break;
			case 3:
				result = "三";
				break;
			case 4:
				result = "四";
				break;
			case 5:
				result = "五";
				break;
			case 6:
				result = "六";
				break;
			case 7:
				result = "七";
				break;
			case 8:
				result = "八";
				break;
			case 9:
				result = "九";
				break;
			case 10:
				result = "十";
				break;
			default:
				break;
			}
			return result;
		}
		
		public static String num2abc(int num){
			String result = "";
			switch (num) {
			case 1:
				result = "A";
				break;
			case 2:
				result = "B";
				break;
			case 3:
				result = "C";
				break;
			case 4:
				result = "D";
				break;
			case 5:
				result = "E";
				break;
			default:
				break;
			}
			return result;
		}
	
	/**
	 * 生成 随机数
	 * @param digit
	 * @return
	 */
	public static String genRandomNum(int digit){
		String str = "";
		Random random = new Random();
		for(int i=0;i<digit;i++){
			int tmp = random.nextInt(10);
			str += tmp;
		}
		return str;
	}

	
	
	/**
	 * 根据字符串s截取得到符合要求的字符串
	 * @param s			输入字符串
	 * @param minLength	最小长度
	 * @return	符合要求的字符串
	 */
	public static String getAdjustedString(String s, int minLength) {
		char[] cs = s.toCharArray();
		int index = 0;
		for (int i = 0; i < cs.length; i++) {
			if (cs[i] != '0') {
				index = i;
				break;
			}
		}
		return ((cs.length-index-1)>=minLength)? s.substring(index): s.substring(cs.length-4);
	}
	
	
 
  
}
