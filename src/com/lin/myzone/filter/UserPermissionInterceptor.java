package com.lin.myzone.filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lin.myzone.beans.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


public class UserPermissionInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			ActionContext ac = invocation.getInvocationContext();
			HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
			HttpSession session=request.getSession();
			request.setCharacterEncoding("utf-8");  //设置编码集为utf-8.
			
			
			
			if (doBefore(request)) {
				return invocation.invoke();
			}
			
			
			
			User user = (User)session.getAttribute("adminUser");
			if(user == null) {//session失效或非法登录
				request.getSession().setAttribute("session_logonerr","由于你长时间未操作，账号已经自动注销，请重新登录！");
				return "nologin";
			}else{
				//logService.saveMerchantLog(request);
				return invocation.invoke();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
	}
	//哪些不拦截
		public boolean doBefore(HttpServletRequest request) {
			String url = request.getRequestURI();
			boolean bl = url.endsWith("login.action");  		//登陆不拦截
			boolean b2 = url.endsWith("regUser.action");		//跳转到注册页面不拦截
			boolean b3 = url.endsWith("checkName.action"); 		//检查用户名不拦截
			boolean b4 = url.endsWith("reg.action");			//注册不拦截
			boolean b5 = url.endsWith("logOut.action");			//退出不拦截
			boolean b6 = url.endsWith("callBack2.action");			//注册不拦截
			boolean b7 = url.endsWith("callBack.action");			//退出不拦截
			boolean b8 = url.contains("ota");                        //OTA不拦截
			boolean b9 = url.contains("appStore_getOneApp.action");  //获取单个应用信息不拦截
			boolean b10 = url.contains("appStore_getAllApp.action"); //获取所有应用信息不拦截
			boolean b11 = url.contains("appStore_getNumApp.action"); //获取指定个数的app信息不拦截
			boolean b12 = url.contains("appStore_updateApp.action"); //修改app信息不拦截
			boolean b13 = url.contains("appStore_getHignUrl.action"); //获取高清图片地址不拦截
			boolean b14 = url.contains("appStore_existAppDevice.action"); //查询终端是否存在不拦截
			boolean b15 = url.contains("appStore_getAppMerchants.action"); //查询终端所在的商户信息列表不拦截
			boolean b16 = url.contains("appStore_updateRemark.action"); //修改备注信息不拦截
			boolean b17 = url.contains("appStore_updateAppDevice.action"); //修改终端信息不拦截
			boolean b18 = url.contains("appStore_getAppByName.action"); //根据appName查询app信息不拦截
			boolean b19 = url.contains("appStore_getAppMerByBid.action"); //根据商户ID获取商户信息
			boolean b20 = url.contains("appStore_getAppDevByTid.action"); //根据终端ID获取终端信息
			boolean b21 = url.contains("appStore_getAppDevByTnumber.action"); //根据终端序列号获取单个终端
			return bl || b2 || b3 || b4 || b5 || b6 || b7 || b8 || b9 || b10 || b11 || b12 || b13 || b14 || b15 || b16 || b17 || b18 || b19 || b20 || b21;
		}
		
}
