<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/pages/common/taglib.jsp" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
  <head>
    
    <title>MyZone注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <h1 align="center">注册</h1>
  <form  id="loginform"  method="post" action="userAction_register.action">
    <table align="center">
    	<tr>
    		<td>
    			<label>昵称：</label>
    		</td>
    		<td>
    			<input type="text" name="user.name" id="name" autofocus required>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			<label>密码：</label>
    		</td>
    		<td>
    			<input type="password" name="user.password" id="password" required>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			<label>确认密码：</label>
    		</td>
    		<td>
    			<input type="password" name="password1" id="password1" required>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			<label>性别：</label>
    		</td>
    		<td>
    			<input type="hidden" name="user.sex" id="sex" >
    			<input type="radio" name="sex" id="sexm" checked="checked" value="1">男
    			<input type="radio" name="sex" id="sexfm" value="0">女
    		</td>
    	</tr>
		<tr>
    		<td>
    			<label>邮箱：</label>
    		</td>
    		<td>
    			<input type="text" name="user.email" id="email" required>
    		</td>
    	</tr>  
		<tr>
    		<td>
    			<label></label>
    		</td>
    		<td>
    			<input type="button" name="btn_submit" id="btn_submit" value="立即注册">
    		</td>
    	</tr>      	  	    	
    </table>
    </form>
  </body>
</html>
<script type="text/javascript">

$(function(){


$("#btn_submit").click(function(){
	var password = $("#password").val();
	var password1 = $("#password1").val();
/* 	if($("#sexfm").attr("checked")){
		$("#sex").val("0");
	}else{
		$("#sex").val("1");
	} */
	var value=$('input:radio[name=sex]:checked').val();
	$("#sex").val(value);
	if(password != password1){
		alert("两次密码输入不一致，请重新输入！");
	}else{
		$("#loginform").submit();
	}



});






});





</script>