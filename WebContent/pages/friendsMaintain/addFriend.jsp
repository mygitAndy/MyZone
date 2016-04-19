<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/pages/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>查找</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


	<script type="text/javascript">
		$(function(){
		
		
			$(".mysearch").click(function(){
				document.forms[0].submit();
				new load().init();
			});
		
		});
	</script>
	<script type="text/javascript">
	
		function addFriend(id){
		url="friendAction_addFriend.action";
		date={"friendId":id};
		$.post(url,date,function(response){
		
			if(response == "1"){
				alert("已成功向对方发送好友请求！");
			}else if(response == "2"){
				alert("你们已经是好友了，不可重复添加！");
			}
		
		
		});
	}
</script>
  </head>
  
  <body>
    <form name="cFM" action="${ctx }/friendAction_searchFriend.action" method="post">
		<table   align="center">
		    
				<tr>
					<td>
						<table width="100%" align="center" class="outertable">
							<tr>
								<td>
								<input name="searchContent" type="text" value="请输入账号/昵称" style="color='ccc'" onClick="this.style.color='black';this.value='';" onBlur="this.style.color=''"/>
								</td>
								<td align="center">
								    <input type="button" value="查询" class="ButtonLong mySearch"/>
									<input type="button" value="重置" class="ButtonLong myReset" onclick="myReset1()"/>
									<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		    
			</form>
   <table width="99%" border="0" cellpadding="0" cellspacing="0" align="center" class="outertable" style="margin-top:-5px;">
				<tr>
					<td>
						<table width="100%" border="1" align="center" cellpadding="5"
							cellspacing="0"
							style="border: 1px solid #CCCCCC; border-collapse: collapse;">
							<tr class="mydesk">
								<td height="15" colspan="10">
									<img src="${ctx}/images/ico01.jpg" width="10" height="15">
									<span class="fB f-blue">查询结果</span>
								</td>
							</tr>
							<tr bgcolor="#F3EDEC" style="height: 25px" class="f-blue1"
								style="word-break: break-all;">
								<td>
									<table width="100%" border="1" align="center" cellpadding="2"
										cellspacing="1" bgcolor="#CCCCCC" class="fixTable" align="center">
										<tr bgcolor="#F3EDEC" style="height: 25px">
									        <td class="f-blue1 hide" align="center">昵称</td>
										    <td class="f-blue1" align="center">账号</td>
											<td class="f-blue1" align="center">所在城市</td>
											<td class="f-blue1" align="center">性别</td>
											<td class="f-blue1" align="center">年龄</td>
											<td class="f-blue1" align="center">邮箱地址</td>
											<td class="f-blue1" align="center">操作</td>
										</tr>

										
												
										 <c:forEach var="searchUserList" items="${searchUserList }">
										   <tr bgcolor="#ffffff"  align="center"
												onMouseOver="javascript:this.style.backgroundColor='#E8F2FE';"
												onmouseout="javascript:this.style.backgroundColor='#ffffff';">
												 <td >${searchUserList.name }</td>
									              <td >${searchUserList.mz_number }</td>
									              <td>${searchUserList.city }</td>						
									              <td>${searchUserList.sex=='0'?'女':'男'}</td>
									              <td>${searchUserList.telenum }</td>	
									              <td>${searchUserList.age }</td>									        
									              <td>
									                   <a id="friendcontent_${searchUserList.id}" href="javascript:void(0);" onclick="addFriend(${searchUserList.id})" >+好友</a>
									              </td>
											  </tr>
										</c:forEach>
										
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			${page }
  </body>
</html>
