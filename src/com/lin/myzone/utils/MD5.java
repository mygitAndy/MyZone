package com.lin.myzone.utils;

import java.security.MessageDigest;

public class MD5 {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static MessageDigest md5;


	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			if(md5==null){
			   md5 = MessageDigest.getInstance("MD5");
			}
			resultString = byteArrayToHexString(md5.digest(resultString
					.getBytes()));
		} catch (Exception ex) {

		}
		return resultString;
	}
	
	public static void main(String[] args){
		String str = "123456";
		System.out.println(encode(str));
	}

}