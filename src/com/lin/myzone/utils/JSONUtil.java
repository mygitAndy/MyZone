package com.lin.myzone.utils;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * JSON工具类。
 * <p>
 * <i>版本：</i>2012-3-15<br>
 * </p>
 */
public class JSONUtil {
	
	/**
	 * 对象转换成JSON串.
	 * 
	 * <p>
	 * <i>版本：</i>2012-3-15<br>
	 * </p> 
	 *
	 * @param obj
	 * @return
	 */
	public static String object2json(Object obj) {
		StringBuilder json = new StringBuilder();
		if (obj == null) {
			json.append("\"\"");
		}
		else if (obj instanceof String || obj instanceof Integer || obj instanceof Float  || obj instanceof Short || obj instanceof Double || obj instanceof Long || obj instanceof BigDecimal || obj instanceof BigInteger || obj instanceof Byte) {
			json.append("\"").append(string2json(obj.toString())).append("\"");
		}
		else if(obj instanceof Boolean) {
			json.append(string2json(obj.toString()));
		}
		else if (obj instanceof Object[]) {
			json.append(array2json((Object[]) obj));
		}
		else if (obj instanceof List<?>) {
			json.append(list2json((List<?>) obj));
		}
		else if (obj instanceof Map<?,?>) {
			json.append(map2json((Map<?, ?>) obj));
		}
		else if (obj instanceof Set<?>) {
			json.append(set2json((Set<?>) obj));
		}
		else {
			json.append(bean2json(obj));
		}
		return json.toString();
	}

	/**
	 * @param bean bean对象
	 * @return String
	 */
	public static String bean2json(Object bean) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
		}
		catch (IntrospectionException e) {
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					String name = object2json(props[i].getName());
					String value = object2json(props[i].getReadMethod().invoke(bean));
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
				}
				catch (Exception e) {
				}
			}
			json.setCharAt(json.length() - 1, '}');
		}
		else {
			json.append("}");
		}
		return json.toString();
	}

	/**
	 * @param list list对象
	 * @return String
	 */
	public static String list2json(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		}
		else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * @param array 对象数组
	 * @return String
	 */
	public static String array2json(Object[] array) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (array != null && array.length > 0) {
			for (Object obj : array) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		}
		else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * @param map map对象
	 * @return String
	 */
	public static String map2json(Map<?, ?> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				json.append(object2json(key));
				json.append(":");
				json.append(object2json(map.get(key)));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		}
		else {
			json.append("}");
		}
		return json.toString();
	}

	/**
	 * @param set 集合对象
	 * @return String
	 */
	public static String set2json(Set<?> set) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (set != null && set.size() > 0) {
			for (Object obj : set) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		}
		else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * @param s 参数
	 * @return String
	 */
	public static String string2json(String s) {
		if (s == null) return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
				case '"':
					sb.append("\\\"");
					break;
				case '\\':
					sb.append("\\\\");
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '\t':
					sb.append("\\t");
					break;
				case '/':
					sb.append("\\/");
					break;
				default:
					if (ch >= '\u0000' && ch <= '\u001F') {
						String ss = Integer.toHexString(ch);
						sb.append("\\u");
						for (int k = 0; k < 4 - ss.length(); k++) {
							sb.append('0');
						}
						sb.append(ss.toUpperCase());
					}
					else {
						sb.append(ch);
					}
			}
		}
		return sb.toString();
	}

	/**
	 * 构建数据总数与数据集.
	 * 
	 * <p>
	 * <i>版本：</i>2010-8-27 <br>
	 * </p> 
	 *
	 * @param totalCount
	 * @param result
	 * @return
	 */
	public static String buildResult(Long totalCount, String result) {
		if (totalCount == null || result == null) {
			totalCount = 0L;
			result = "";
		}
		StringBuffer sb = new StringBuffer("{\"totalCount\":");
		sb.append(totalCount).append(",\"result\":").append(result).append("}");
		return sb.toString();
	}
	
	/**
	 * 将list转换为显示列表用的Json格式 .
	 * 
	 * <p>
	 * <i>版本：</i>2010-8-27 <br>
	 * </p> 
	 *
	 * @param list
	 * @return
	 */
	public static String list2GridJson(List<?> list){
		if(list == null || list.size() == 0){
			return "{\"totalCount\":0, \"result\":[]}";
		}
		return "{\"totalCount\":"+list.size()+", \"result\":"+list2json(list)+"}";
	}
	
	/**
	 * 将set转换为显示列表用的Json格式.
	 * 
	 * <p>
	 * <i>版本：</i>2010-8-27 <br>
	 * </p> 
	 *
	 * @param set
	 * @return
	 */
	public static String set2GridJson(Set<?> set){
		if(set == null || set.size() == 0){
			return "{\"totalCount\":0, \"result\":[]}";
		}
		return "{\"totalCount\":"+set.size()+", \"result\":"+set2json(set)+"}";
	}
	
	
	
	/**
	 * json转成MAP。<br>
	 * 注：本方法只适用于健值对，且只有两个值时有效。
	 * <p>
	 * <i>版本：</i>2010-11-27 <br>
	 * <i>作者：</i>罗军林 <br>
	 * </p> 
	 *
	 * @param json
	 * @param keyId
	 * @param valId
	 * @return
	 */
	public static Map json2Map(String json, String keyId,String valId){
		Map map = new HashMap<Object, Object>();
		JSONArray jo = JSONArray.fromObject(json);
		Object[] result = jo.toArray();
		for(Object item : result){
			JSONObject temp = (JSONObject)item;
			Object tt = temp.get("num");
			map.put(temp.get(keyId), temp.get(valId));
		}
		return map;
	}
}
