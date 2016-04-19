<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/pages/common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>我的好友</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function deleteFriend(v){
		url="friendAction_deleteFriend.action";
		date={"id":v}
		$.post(url,date,function(response){
		
			if(response == "1"){
				window.location.href="friendAction_friendsList.action?number=${adminUser.mz_number}";
			}
			
		});
	}
	
	function visitFriend(v){
		
	}
</script>
  </head>
  
  
  <body>
  		<form name="cFM" action="friendAction_friendsList.action?number=${adminUser.mz_number}" method="post">
			<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }"/>
		</form>
<jsp:include page="/pages/friendsMaintain/friendMain.jsp"></jsp:include>
  	<c:if test="${empty friendList}">
  	<tr align="center">
		<a href="friendAction_add.action" ><h1>你还有没好友噢，赶紧去添加好友吧！</h1></a>
	</tr>
	</c:if>
	<c:if test="${!empty friendList}">

   <table width="99%" border="0" cellpadding="0" cellspacing="0" align="center" class="outertable" style="margin-top:-5px;">
				<tr>
					<td>
						<table width="100%" border="1" align="center" cellpadding="5"
							cellspacing="0"
							style="border: 1px solid #CCCCCC; border-collapse: collapse;">
							<tr class="mydesk">
								<td height="15" colspan="10">
									<img src="${ctx}/images/ico01.jpg" width="10" height="15">
									<span class="fB f-blue">好友列表</span>
								</td>
							</tr>
							<tr bgcolor="#F3EDEC" style="height: 25px" class="f-blue1"
								style="word-break: break-all;">
								<td>
									<table width="100%" border="1" align="center" cellpadding="2"
										cellspacing="1" bgcolor="#CCCCCC" class="fixTable" align="center">
										<tr bgcolor="#F3EDEC" style="height: 25px">
									        <td class="f-blue1 hide" align="center">好友昵称</td>
										    <td class="f-blue1" align="center">好友账号</td>
											<td class="f-blue1" align="center">好友所在城市</td>
											<td class="f-blue1" align="center">好友性别</td>
											<td class="f-blue1" align="center">好友年龄</td>
											<td class="f-blue1" align="center">好友邮箱账号</td>
											<td class="f-blue1" align="center">操作</td>
										</tr>

										
												
										 <c:forEach var="friendList" items="${friendList }">
										   <tr bgcolor="#ffffff"  align="center"
												onMouseOver="javascript:this.style.backgroundColor='#E8F2FE';"
												onmouseout="javascript:this.style.backgroundColor='#ffffff';">
												 <td >${friendList.name }</td>
									              <td >${friendList.mz_number }</td>
									              <td>${friendList.city }</td>						
									              <td>${friendList.sex=='0'?'女':'男'}</td>
									              <td>${friendList.telenum }</td>	
									              <td>${friendList.age }</td>									        
									              <td>
									                  <a href="javascript:void(0)"  onclick="visitFriend(${merchant.merchantNo})" target="_blank">访问空间</a>
									                   <a href="javascript:void(0)" onclick="deleteFriend(${friendList.id})" >删除好友</a>
									              </td>
											  </tr>
										</c:forEach>
										
									</table>
								</td>
							</tr>
						</table>

						${page }
					</td>
				</tr>
			</table>
			</c:if>
  </body>
</html>
