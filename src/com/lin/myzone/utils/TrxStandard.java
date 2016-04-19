package com.lin.myzone.utils;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


public class TrxStandard {

	public static  String configPath = "/config.properties";
	
	private static Properties properties = new Properties();
	
	
	
	private TrxStandard() {
	}
	
	static {		
		doConfigure(configPath,properties);	
	}
	
	private static void doConfigure(String  fileName,Properties prop) {	
	    InputStream istream = null;
	    try {	    	
	    	istream =TrxStandard.class.getResourceAsStream(fileName);
	    	prop.load(istream);
	    	istream.close();
	    } 
	    catch (Exception e) {
	    } finally {
	        if(istream != null) {
	            try {
	                istream.close();
	            } catch(Throwable ignore) {}
	        }
	    }
	}
	
	
	public static String getReport(String reource,String key) {
		Properties myProperties = new Properties();
		doConfigure("/"+reource, myProperties);
		String value = myProperties.getProperty(key);
		if (null == value) {			
			throw new RuntimeException("�Ҳ������� [" + key + "]!");
		}
		return value;
	}
	
	
	public static String getReport(String reource,String key,int site) {
		Properties myProperties = new Properties();
		doConfigure("/"+reource, myProperties);
		String value = myProperties.getProperty(key);
		if (null == value) {			
			throw new RuntimeException("�Ҳ������� [" + key + "]!");
		}
		String[] strs = value.split("\\,");
		if(site==1){
			return strs[0].substring(1);
		}else if(site==2){
			return strs[1];
		}else if(site==3){
			return strs[2].substring(0,strs[2].length()-1);
		}
		return value;
	}
	
	
	public static String getProperty(String key) {
		String value = properties.getProperty(key);
		if (null == value) {			
			throw new RuntimeException("[" + key + "]!");
		}
		return value;
	}
	
	public static void setProperty(String key,String value){
		properties.setProperty(key, value);
	}
	
	public static void main(String[] args) {
		String value = getReport("detail_report.properties","report.title.cols");
		System.out.println(value);
	}
}