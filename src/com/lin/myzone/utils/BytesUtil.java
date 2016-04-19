package com.lin.myzone.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字节数组操作工具类
 * @author wodeyinlimin
 *
 */
public class BytesUtil {

	

	/**
	 * 整数到字节数组转换
	 * @param n
	 * @return
	 */
	 public static byte[] int2bytes(int n) {
		 byte[] ab = new byte[4];
		 ab[3] = (byte) (0xff & n);
		 ab[2] = (byte) ((0xff00 & n) >> 8);
		 ab[1] = (byte) ((0xff0000 & n) >> 16);
		 ab[0] = (byte) ((0xff000000 & n) >> 24);
		 return ab;
	 }
	 /**
		 * 整数到字节数组转换
		 * @param n
		 * @return
		 */
		 public static byte[] int2bytes2(int n) {
			 byte[] ab = new byte[2];
			 ab[1] = (byte) (0xff & n);
			 ab[0] = (byte) ((0xff00 & n) >> 8);			
			 return ab;
		 }
	 
	/**
	 * 字节数组到整数的转换
	 * @param b
	 * @return
	 */
	 public static int bytes2int(byte b[]) {
		 int s = 0;
		 s = ((((b[0] & 0xff) << 8 | (b[1] & 0xff)) << 8) | (b[2] & 0xff)) << 8
		 | (b[3] & 0xff);
		 return s;
	 }
	 
	 
	 /**
		 * 字节数组到整数的转换
		 * @param b
		 * @return
		 */
		 public static int anyBytes2int(byte b[]) {
			 int s = 0;
			 BigDecimal d=new BigDecimal(256);	 
             int len=b.length;
			 for(int i=0;i<len;i++){
				 s+=Integer.parseInt(d.pow(len-i-1).multiply(new BigDecimal((b[i]&0xff))).toString());				
			 }
			 return s;
		 }
	 
		 /**
			 * 字节数组到整数的转换
			 * @param b
			 * @return
			 */
			 public static long anyBytes2Long(byte b[]) {
				 long s = 0;
				 BigDecimal d=new BigDecimal(256);	 
	             int len=b.length;
				 for(int i=0;i<len;i++){
					 s+=Long.parseLong(d.pow(len-i-1).multiply(new BigDecimal((b[i]&0xff))).toString());				
				 }
				 return s;
			 }
	 
	 

	 /**
	  * 获得指定位置，长度的子数组
	  * @param src
	  * @param start
	  * @param length
	  * @return
	  */
	 public static byte[] subBytes(byte[] src,int start,int length){
		 byte[] res=new byte[length];
		 System.arraycopy(src, start, res, 0, length);
		 return res;
	 }
	 
	 /**
	  * 获得指定位置之后的数组
	  * @param src
	  * @param start
	  * @param length
	  * @return
	  */
	 public static byte[] subBytes(byte[] src,int start){
		 int length=src.length-start;
		 byte[] res=new byte[length];
		 System.arraycopy(src, start, res, 0, length);
		 return res;
	 }
	 
	 /**
	  * 拼接各字节数组
	  * @param sbs
	  * @return
	  */
	 public static byte[] concatBytes(byte[]...sbs){
		 int len=0;
		 for(byte[] b:sbs){
			 len+=b.length;
		 }		 
		 byte[] res=new byte[len];
		 int lem=0;
		 for(byte[] b:sbs){			 
			 System.arraycopy(b, 0, res, lem, b.length);
			 lem+=b.length;
		 }		 
		 return res;
	 }
	 
	 /**
	  * 插入
	  * @param src
	  * @param start
	  * @param inserted
	  */
	 public static byte[] insertBytes(byte[] src,int start,byte[] inserted){
		 byte[] head=subBytes(src,0,start);
		 byte[] tail=subBytes(src,start);	
		 return src=concatBytes(head,inserted,tail);
	 }
	 
	 /**
	  * 插入
	  * @param src
	  * @param start
	  * @param inserted
	  */
	 public static byte[] insertBytes(byte[] src,int start,byte inserted){		
		 return src=insertBytes(src,start,new byte[]{inserted});
	 }
	 
	 /**
	  * 移除
	  * @param src
	  * @param start
	  * @param length
	  * @return
	  */
	 public static byte[] removeBytes(byte[] src,int start,int length){
		 byte[] head=subBytes(src,0,start);
		 byte[] tail=subBytes(src,start+length);		 
		 return concatBytes(head,tail);
	 }
	 
	 /**
	  * 数组转化为list
	  * @param src
	  * @return
	  */
	 public static List getList(byte[] src){
		 List list=new ArrayList();
		 for(byte b:src){
			 list.add(b);
		 }
		 return list;
	 }
	 
	 /**
	  * 拼接各字节数组
	  * @param sbs
	  * @return
	  */
	 public static byte[] concatBytes(List sbs){
		 int len=0;
		 for(Object ob:sbs){
			 if(ob instanceof byte[]){
				 byte[] b=(byte[])ob;
				 len+=b.length;
			 }else if(ob instanceof Byte){
				 byte b=(Byte)ob;
				 len++;
			 }
			
		 }		 
		 byte[] res=new byte[len];
		 int lem=0;
		 for(Object ob:sbs){	
			 if(ob instanceof byte[]){
				 byte[] b=(byte[])ob;
				 System.arraycopy(b, 0, res, lem, b.length);
				 lem+=b.length;
			 }else if(ob instanceof Byte){
				 byte b=(Byte)ob;				 
				 System.arraycopy(new byte[]{b}, 0, res, lem, 1);
				 lem++;
			 }
			
		 }		 
		 return res;
	 }
	 
	 /**
	  * 取得字节的某一位
	  * @param b
	  * @param pos
	  * @return
	  */
	 public static int getBitValue(byte b,int pos){
	    BigDecimal bd=new BigDecimal(2);	    
	    byte p= bd.pow(pos-1).byteValue();		  
	    int res=(b & p)>>(pos-1);
	    return res;
	 }
	 
	 /**
	  * 将字节按位转化为字符串
	  * @param b
	  * @return
	  */
	 public static String bitStr(byte b){
		 StringBuffer sb=new StringBuffer();
		 for(int i=8;i>0;i--){
			 sb.append(getBitValue(b,i));
		 }
		 return sb.toString();
	 }
	 
	 
	 public static byte[] replace(byte[] src,int start,byte[] newBytes){
	    int s=src.length-start;
	    if(newBytes.length>s)
	    	return src;		  
	    System.arraycopy(newBytes, 0, src, start, newBytes.length);
	    return src;		    
	 }
	 
	 /**
	  * 将字节数组按位转化为字符串
	  * @param b
	  * @return
	  */
	 public static String bitStr(byte[] b){
		 StringBuffer sb=new StringBuffer();
		 for(int i=0;i<b.length;i++){
			 sb.append(bitStr(b[i]));
		 }
		 return sb.toString();
	 }
	 
	 /**
	  * 判定两个字节数组是否相等
	  * @param src1
	  * @param src2
	  * @return
	  */
	 public static boolean equals(byte[] src1,byte[] src2){
		 if(src1==null && src2==null)
			 return true;
		 if(src1==null && src2!=null)
			 return false;
		 if(src1!=null && src2==null)
			 return false;
		 /*int len1=src1.length;
		 int len2=src2.length;
		 if(len1!=len2)
			 return false;
		 for(int i=0;i<len1;i++){
			 if(src1[i]!=src2[i])
				 return false;
		 }*/
		 return Arrays.equals(src1, src2);		 
	 }
	 
//	 
//	 public static boolean contains(byte[] src,byte[] sub){
////		 String sc=HexCodec.hexEncode(src);
////		 String sb=HexCodec.hexEncode(sub);	
//		 return sc.matches(".*"+sb+".*");
//	 }
	 
     public static int indexOf(byte[] src,byte sub){    	 
    	 int len=src.length;
    	 for(int i=0;i<len;i++){
    		 if(src[i]==sub)return i;
    	 }
		 return -1;
	 }
     
     public static byte[] mendBytes(byte[] b, int length) {
    	 if (b.length > length) {
    		 return subBytes(b, 0, length);
    	 } else if (b.length < length) {
    		 int concatLength = length - b.length;
    		 byte[] concatbytes = new byte[concatLength];
    		 for (int i = 0; i < concatLength; i++) {
    			 concatbytes[i] = ' ';
    		 }
    		 return concatBytes(b, concatbytes);
    	 } else {
    		 return b;
    	 }
     }
	 
     public static int getUnsignedByte (byte data){      //将data字节型数据转换为0~255 (0xFF 即BYTE)。
         return data&0x0FF;
      }
     
     public static String BcdToAsc(byte[] buffer)
 	{
 		char[] HEX = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };		
 		if (buffer.length == 0) {
 			return "";
 		}
 		int holder = 0;
 		char[] chars = new char[buffer.length * 2];
 		for (int i = 0; i < buffer.length; i++) {
 			holder = (buffer[i] & 0xf0) >> 4;
 			chars[i * 2] = HEX[holder];
 			holder = buffer[i] & 0x0f;
 			chars[(i * 2) + 1] = HEX[holder];
 		}
 		return new String(chars);
 		
 	}

	 
	 public static void main(String[] args){
		 /*byte[] src={0x33,0x11,0x02};
		 byte[] sub={0x33,0x11,0x00};
		 
		 POSPTrace.trace(HexCodec.hexEncode(int2bytes(153)));*/
		 
		 /*String s1 = "天利名城A影院";
		 byte[] b1 = s1.getBytes();
		 System.out.println(Util.printMsg(mendBytes(b1, 20)));
		 
		 String s2 = "第一放映厅";
		 byte[] b2 = s2.getBytes();
		 System.out.println(Util.printMsg(b2));
		 
		 String s3 = "龙门飞甲3D";
		 byte[] b3 = s3.getBytes();
		 System.out.println(Util.printMsg(b3));
		 
		 String s4 = "1,4;1,5";
		 byte[] b4 = s4.getBytes();
		 System.out.println(Util.printMsg(b4));
		 
		 String s5 = "10:00am";
		 byte[] b5 = s5.getBytes();
		 System.out.println(Util.printMsg(b5));
		 
		 System.out.println(new String(b1) + "-" + new String(b2) + "-" 
		 		+ new String(b3) + "-" + new String(b4) + "-" + new String(b5));
		 
		 String s = "35";
		 byte b = Byte.valueOf(s);
		 System.out.println(Util.printMsg(b));*/
		 
		 byte b = 127;
		 System.out.println(BytesUtil.getBitValue(b, 1)+","+BytesUtil.getBitValue(b, 2)+","+BytesUtil.getBitValue(b, 3)
				 +","+BytesUtil.getBitValue(b, 4)+","+BytesUtil.getBitValue(b, 5)+","+BytesUtil.getBitValue(b, 6)
				 +","+BytesUtil.getBitValue(b, 7)+","+BytesUtil.getBitValue(b, 8));
		 System.out.println(BytesUtil.bitStr(b));
	 }

}
