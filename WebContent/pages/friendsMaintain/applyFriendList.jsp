<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/pages/common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>申请好友记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  
  <body>
  		<form name="cFM" action="friendAction_applyFriendsList.action?number=${adminUser.mz_number}" method="post">
			<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }"/>
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
									<span class="fB f-blue">好友申请列表</span>
								</td>
							</tr>
							<tr bgcolor="#F3EDEC" style="height: 25px" class="f-blue1"
								style="word-break: break-all;">
								<td>
									<table width="100%" border="1" align="center" cellpadding="2"
										cellspacing="1" bgcolor="#CCCCCC" class="fixTable" align="center">
										<tr bgcolor="#F3EDEC" style="height: 25px">
									        <td class="f-blue1 hide" align="center">申请好友昵称</td>
										    <td class="f-blue1" align="center">申请好友账号</td>
											<td class="f-blue1" align="center">申请好友所在城市</td>
											<td class="f-blue1" align="center">申请好友性别</td>
											<td class="f-blue1" align="center">申请好友年龄</td>
											<td class="f-blue1" align="center">申请好友邮箱地址</td>
											<td class="f-blue1" align="center">申请时间</td>
											<td class="f-blue1" align="center">当前最新状态</td>
											<td class="f-blue1" align="center">对方处理时间</td>
											<td class="f-blue1" align="center">操作</td>
										</tr>

										
												
										 <c:forEach var="applyList" items="${applyList }">
										   <tr bgcolor="#ffffff"  align="center"
												onMouseOver="javascript:this.style.backgroundColor='#E8F2FE';"
												onmouseout="javascript:this.style.backgroundColor='#ffffff';">
												 <td >${applyList.friendUser.name }</td>
									              <td >${applyList.friendUser.mz_number }</td>
									              <td>${applyList.friendUser.city }</td>						
									              <td>${applyList.friendUser.sex=='0'?'女':'男'}</td>
									              <td>${applyList.friendUser.age }</td>	
									               <td>${applyList.friendUser.email }</td>		
									                <td>${applyList.applyTime }</td>
									                <c:if test="${applyList.status ==0}">
									                 <td >已提交好友申请</td>	
									                 </c:if>
									              <c:if test="${applyList.status ==1}">
									                 <td >对方通过已成功成为好友</td>	
									                 </c:if>
									               <c:if test="${applyList.status ==2}">
									                 <td ><font color="red">对方已拒绝你的好友申请</font></td>	
									                 </c:if>
									                 <td>${applyList.replyTime }</td>									        
									              <td>
									              	<c:if test="${applyList.status ==0}">
									                  <a id="method_${applyList.id }"href="#" ">再次提交好友申请</a>
									                  </c:if>
									                  <c:if test="${applyList.status ==2}">
									                  <a id="method_${applyList.id }"href="#" ">再次提交好友申请</a>
									                  </c:if>
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
  </body>
</html>
