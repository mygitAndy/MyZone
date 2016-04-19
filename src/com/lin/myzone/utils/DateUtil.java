package com.lin.myzone.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {

	// 默认日期格式
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    //小时分钟秒
    public static final String TIME_FORMAT = "HH:mm:ss";

    // 默认日期时间格式
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // 8为日期格式
    public static final String DATE_FORMAT_8 = "yyyyMMdd";

    // 14为日期时间格式
    public static final String DATE_TIME_FORMAT_14 = "yyyyMMddHHmmss";

    public final static String YEAR = " year ";

    public final static String MONTH = " month ";

    public final static String DAY = " day ";

    public final static String WEEK = " week ";

    public final static String HOUR = " hour ";

    public final static String MINUTE = " minute ";

    public final static String SECOND = " second ";
	
	/**
     * 判断参数是否等于null或者空
     * @param para
     * @return boolean
     */
    private static boolean checkPara(Object para) {
        return null == para || "" .equals(para);
    }
	public static String getToday(String fmt){
		SimpleDateFormat format = new SimpleDateFormat(fmt);
		return format.format(new Date());
	}
	
	public static String getYear(int type){
		if (type==0) {
			return getToday("yyyy")+"0101";
		}else {
			return getToday("yyyy")+"1231";
		}
	}

	public static String getNow(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	public static String getNowDate(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}
	
	//返回今天+dayNum天后的日期
	public static String getAfterToday(int dayNum){
		Date d=new Date();   
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   

		return df.format(new Date(d.getTime() + (long)dayNum * 24 * 60 * 60 * 1000));

	}
	
	//返回今天+hourNum小时后的日期
	public static String getAfterTodayByHour(int hourNum){
		Date d=new Date();   
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   

		return df.format(new Date(d.getTime() + (long)hourNum * 60 * 60 * 1000));

	}
	

	//返回今天+dayNum天后的日期
	public static String getAfterToday(int dayNum,String fmt){
		Date d=new Date();   
		SimpleDateFormat df=new SimpleDateFormat(fmt);   

		return df.format(new Date(d.getTime() + (long)dayNum * 24 * 60 * 60 * 1000));

	}

	//返回N个月后的日期
	public static String getAfterMonth(int n,String fmt){
		Date d=new Date();   
		SimpleDateFormat df=new SimpleDateFormat(fmt);   

		return df.format(new Date(d.getTime() + (long)n * 24 * 60 * 60 * 1000 * 30));

	}
	
	//返回N个月前的日期
	public static String getBeforeMonth(int n,String fmt){
		Date d=new Date();   
		SimpleDateFormat df=new SimpleDateFormat(fmt);   

		return df.format(new Date(d.getTime() - (long)n * 24 * 60 * 60 * 1000 * 30));

	}
	
	 /**
     * 按时间格式相加
     * @param dateStr 原来的时间
     * @param pattern 时间格式
     * @param type    "year"年、"month"月、"day"日、"week"周、
     *                "hour"时、"minute"分、"second"秒
     *                通过常量来设置,e.g.DateFormatUtils.YEAR
     * @param count   相加数量
     * @return
     */
    public static String addDate(String dateStr, String pattern,
                                 String type, int count) {
        if (checkPara(dateStr) || checkPara(pattern) || checkPara(type)) {
            return "";
        }
        if (0 == count) {
            return dateStr;
        }
        Date date = parseStrToCustomPatternDate(dateStr, pattern);
        Locale loc = Locale.getDefault();
        Calendar cal = new GregorianCalendar(loc);
        cal.setTime(date);

        if (YEAR.equals(type)) {
            cal.add(Calendar.YEAR, count);
        } else if (MONTH.equals(type)) {
            cal.add(Calendar.MONTH, count);
        } else if (DAY.equals(type)) {
            cal.add(Calendar.DAY_OF_MONTH, count);
        } else if (WEEK.equals(type)) {
            cal.add(Calendar.WEEK_OF_MONTH, count);
        } else if (HOUR.equals(type)) {
            cal.add(Calendar.HOUR, count);
        } else if (MINUTE.equals(type)) {
            cal.add(Calendar.MINUTE, count);
        } else if (SECOND.equals(type)) {
            cal.add(Calendar.SECOND, count);
        } else {
            return "";
        }

        return formatDate(cal.getTime(), pattern);
    }
    
    /**
     * 将时间或者时间日期字符串按照指定格式转换为Date
     * @param dateStr
     * @param pattern
     * @return Date
     */
    public static Date parseStrToCustomPatternDate(String dateStr, String pattern) {
        if (checkPara(pattern) || checkPara(dateStr)) {
            return null;
        }
        SimpleDateFormat dateFormator = new SimpleDateFormat(
                pattern);
        Date resDate = dateFormator.parse(dateStr, new ParsePosition(0));

        return resDate;
    }
    
    public static   String   GetSysDate(String   format,   String   StrDate,   int   year,   int   month,  
            int   day)   {  
		Calendar   cal   =   Calendar.getInstance();  
		SimpleDateFormat   sFmt   =   new   SimpleDateFormat(format);  
		cal.setTime(sFmt.parse(   (StrDate),   new   ParsePosition(0)));  
		if(day!= 0)   {  
		   cal.add(cal.DATE,   day);  
		}  
		if(month!= 0)   {  
		   cal.add(cal.MONTH,   month);  
		}  
		if (year!= 0)   {  
		   cal.add(cal.YEAR,   year);  

        }  
         return   sFmt.format(cal.getTime());  
   }

	public static String getMonth(String fmt){
		Date d=new Date();   
		SimpleDateFormat df=new SimpleDateFormat(fmt);   

		return df.format(new Date(d.getTime()));
	}


	//返回day日期 + x分钟后的日期
	public static String getAfterMimutes(String day, int x) {   
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制   
		Date date = null;   
		try {   
			date = format.parse(day);   
		} catch (Exception ex) {   
			ex.printStackTrace();   
		}   
		if (date == null)   
			return "";   
		Calendar cal = Calendar.getInstance();   
		cal.setTime(date);   
		cal.add(Calendar.MINUTE, x);// 24小时制   
		date = cal.getTime();   
		cal = null;   
		return format.format(date);   
	}  

	//返回day日期 + x秒钟后的日期
	public static String getAfterSeconds(String day, int x) {   
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");// 24小时制   
		Date date = null;   
		try {   
			date = format.parse(day);   
		} catch (Exception ex) {   
			ex.printStackTrace();   
		}   
		if (date == null)   
			return "";   
		Calendar cal = Calendar.getInstance();   
		cal.setTime(date);   
		cal.add(Calendar.SECOND, x);// 24小时制   
		date = cal.getTime();   
		cal = null;   
		return format.format(date);   
	}  
	
	//获取时间序列，赋值给ID
	public static String getTimeID(){
		String now = getToday("yyyyMMddHHmmss");
		
		return now+"0000";
	}

	public static String formatTime(String time,String str) {
		String[] strs = time.split("-");
		time = "";
		for(String s:strs){
			time += s;
		}
		time += str;
		return time;
	}
	
	public static String formatDateAddSplit(String date) {
		if(date==null||"".equals(date)){
			return "";
		}
		if(date.length()!=8){
			return date;
		}
		StringBuffer sbuBuffer = new StringBuffer();
		sbuBuffer.append(date.substring(0,4)).append("-");
		sbuBuffer.append(date.substring(4,6)).append("-");
		sbuBuffer.append(date.substring(6,8));
		
		return sbuBuffer.toString();
	}
	

	public static String formatTimeAddSplit(String time) {
		if(time==null||"".equals(time)){
			return "";
		}
		if(time.length()!=14){
			return time;
		}
		StringBuffer sbuBuffer = new StringBuffer();
		sbuBuffer.append(time.substring(0,4)).append("-");
		sbuBuffer.append(time.substring(4,6)).append("-");
		sbuBuffer.append(time.substring(6,8)).append(" ");
		sbuBuffer.append(time.substring(8,10)).append(":");
		sbuBuffer.append(time.substring(10,12)).append(":");
		sbuBuffer.append(time.substring(12,14));
		
		return sbuBuffer.toString();
	}
	
	/**
     * 将时间字符串从一种格式转换成另一种格式.
     *
     * @param dateStr     e.g. String dateStr = "2006-10-12 16:23:06";
     * @param patternFrom e.g. "yyyy-MM-dd HH:mm:ss"
     * @param patternTo   e.g. "yyyyMMddHHmmss"
     * @return string
     */
    public static String convertDatePattern(String dateStr,
                                            String patternFrom, String patternTo) {
        if (checkPara(patternFrom) || checkPara(patternTo) || checkPara(dateStr)) {
            return "";
        }

        SimpleDateFormat dateFormator = new SimpleDateFormat(patternFrom);
        Date resDate = dateFormator.parse(dateStr, new ParsePosition(0));
        return formatDate(resDate, patternTo);
    }
	
    /**
     * 输入日期，按照指定格式返回
     */
    public static String formatDate(Date date, String pattern) {
        if (checkPara(pattern) || checkPara(date)) {
            return "";
        }
        SimpleDateFormat dateFormator = new SimpleDateFormat(pattern);

        return dateFormator.format(date);
    }
    
    //将20130816013057 格式转换为
    public static String formatDate(String time){
    	Date date=null;
    	String after = "";
		try {
			if (StringUtil.isNotNull(time)) {
				date = new SimpleDateFormat("yyyyMMddHHmmss").parse(time);
				after = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(date);
			}
		} catch (ParseException e) {
			after = time;
			e.printStackTrace();
			
		}
    	return after;
    }
    //将20130816013057 格式转换为2013-08-16
    //只能为yyyyMMddHHmmss格式输入，输出格式可配    
    public static String formatDate(String time,String fmt){
    	Date date=null;
    	String after = "";
		try {
			if (StringUtil.isNotNull(time)) {
				date = new SimpleDateFormat("yyyyMMddHHmmss").parse(time);
				after = new SimpleDateFormat(fmt).format(date);
			}
		} catch (ParseException e) {
			after = time;
			e.printStackTrace();
		}
    	return after;
    }
	
    /**
     * 将一定格式的时间数据转换为格式数据
     * @param infmt		数据格式
     * @param time		数据
     * @param outfmt	输出格式
     * @return
     */
    public static String formatDate(String infmt,String time,String outfmt){
    	Date date=null;
    	String after = "";
		try {
			if (StringUtil.isNotNull(time)) {
				date = new SimpleDateFormat(infmt).parse(time);
				after = new SimpleDateFormat(outfmt).format(date);
			}
		} catch (ParseException e) {
			after = time;
			e.printStackTrace();
		}
    	return after;
    }
    
	public static void main(String[] args) {
		//System.out.println(DateUtil.addDate("20130212",DateUtil.DATE_FORMAT_8,DateUtil.DAY,20));
		//System.out.println(getToday("MM月dd日"));
		//System.out.println(getToday("yyyyMMddHHmmss"));
		//System.out.println(getAfterMonth(3, "yyyyMMddHHmmss"));
		//System.out.println(GetSysDate("yyyyMMddHHmmss",   "20130828143819",   0,   1,   0));  
		

		//System.out.println(formatDate("yyyy-MM-dd HH:mm:ss","2012-10-26 11:42:53", "yyyyMMddHHmmss"));  
		System.out.println(getAfterSeconds("20131212121212",68));
		/*Date date;
		try {
			date = new SimpleDateFormat("yyyyMMddHHmmss").parse("20130816013057");
			String now = new SimpleDateFormat("yyyy年MM月dd日HH分mm秒").format(date);
			System.out.println(now);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
	}

}
