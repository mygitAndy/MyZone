package com.lin.myzone.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport  implements ServletRequestAware, ServletResponseAware {
	
	public HttpServletRequest request;
    public HttpServletResponse response;

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	public String getParameter(String name) {
		String str = getRequest().getParameter(name);
		return str;
	}
	public String getParameter1(String name) {
		String str = getRequest().getParameter(name);
		try {
			str = new String(str.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	public String getParameter2(String name) {
		String str="";
		try {
			str =  URLDecoder.decode(getRequest().getParameter(name), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	

	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	

	public void setText(String context){
		try {
			getResponse().setCharacterEncoding("utf-8");
			PrintWriter writer =  getResponse().getWriter();
			writer.write(context);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * <p>
	 * <i>用于生成信息提示页面</i>
	 * <i>2012-3-13</i>
	 * <i>xgl</i>
	 * </p>
	 */
	public void setMsg(String title, String msg, String go_one,String go_one_url, String go_two, String go_two_url) {
		getSession().setAttribute("title", title);
		getSession().setAttribute("msg", msg);
		getSession().setAttribute("go_one", go_one);
		getSession().setAttribute("go_one_url", go_one_url);
		getSession().setAttribute("go_two", go_two);
		getSession().setAttribute("go_two_url", go_two_url);
	}
	
	public static Object getBean(String name){
		WebApplicationContext ctx=ContextLoader.getCurrentWebApplicationContext();
		return ctx.getBean(name);
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
