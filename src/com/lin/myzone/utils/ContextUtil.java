package com.lin.myzone.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;

/**
 * @author xiegaolong
 */
public class ContextUtil{


	
	
	
	//保存页面传地数据
	public static Object saveValue(Object o,HttpServletRequest request){
		try {
			Object object = o.getClass().newInstance();
			String[] classNames = o.getClass().toString().split("\\.");
			String className = classNames[classNames.length-1];
			className = className.toLowerCase()+".";
			Field[] files = o.getClass().getDeclaredFields();
			String methodName = null;
			Method method = null;
			String type = null;
			String tmpStr = null;
			int tmpInt = 0;
			for (Field f:files) {
				String fullNname = className;
				String name = f.getName();
				methodName = "set"+name.substring(0,1).toUpperCase()+name.substring(1);
				method = o.getClass().getDeclaredMethod(methodName, f.getType());
				type = f.getType().toString();
				fullNname += name;
				if (type.indexOf(".")!=-1) {
					type = type.substring(type.lastIndexOf(".")+1);
				}
				tmpStr = request.getParameter(fullNname);
				if (tmpStr!=null && !tmpStr.equals("")) {
					if (type.equals("int")) {
						tmpInt = Integer.parseInt(tmpStr);
						method.invoke(object, tmpInt);
					}else if (type.equals("String")) {
						method.invoke(object, tmpStr);
					}else if (type.equals("Double")) {
						Double dbtmp = Double.valueOf(tmpStr);
						method.invoke(object, dbtmp);
					}else if (type.equals("BigDecimal")) {
						BigDecimal bdtmp = new BigDecimal(tmpStr);
						method.invoke(object, bdtmp);
					}else if (type.equals("char")) {
						char ctmp = tmpStr.toCharArray()[0];
						method.invoke(object, ctmp);
					}
				}
			}
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//更新数据时调用保存
		public static Object newSaveValue(Object o,HttpServletRequest request){
			if (o==null){
				throw new RuntimeException();
			}
			try {
				Object object = o;
				String[] classNames = o.getClass().toString().split("\\.");
				String className = classNames[classNames.length-1];
//				char[] chars=new char[1];
//				chars[0]=className.charAt(0);
//				String temp=new String(chars);
//				className = className.replaceFirst(temp,temp.toLowerCase())+".";
				className = className.toLowerCase()+".";
				Field[] files = o.getClass().getDeclaredFields();
				String methodName = null;
				Method method = null;
				String type = null;
				String tmpStr = null;
				int tmpInt = 0;
				for (Field f:files) {
					String fullNname = className;
					String name = f.getName();
					methodName = "set"+name.substring(0,1).toUpperCase()+name.substring(1);
					method = o.getClass().getDeclaredMethod(methodName, f.getType());
					type = f.getType().toString();
					fullNname += name;
					if (type.indexOf(".")!=-1) {
						type = type.substring(type.lastIndexOf(".")+1);
					}
					tmpStr = request.getParameter(fullNname);
					if (tmpStr!=null && !tmpStr.equals("")) {
						if (type.equals("int")) {
							tmpInt = Integer.parseInt(tmpStr);
							method.invoke(object, tmpInt);
						}else if (type.equals("String")) {
							method.invoke(object, tmpStr);
						}else if (type.equals("Double")) {
							Double dbtmp = Double.valueOf(tmpStr);
							method.invoke(object, dbtmp);
						}else if (type.equals("BigDecimal")) {
							BigDecimal bdtmp = new BigDecimal(tmpStr);
							method.invoke(object, bdtmp);
						}else if (type.equals("char")) {
							char ctmp = tmpStr.toCharArray()[0];
							method.invoke(object, ctmp);
						}
					}
				}
				return object;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	
}
