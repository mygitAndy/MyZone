<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/pages/common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>好友申请列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
	function admit_applyfriend(id){
		url="friendAction_admitApplyfriend.action";
		date={"friendShipId":id};
		$.post(url,date,function(response){
			if(response == "1"){
				window.location.href="friendAction_appliedFriendsList.action?number=${adminUser.mz_number}";
			}
		});
	}
	
	function reject_applyfriend(id){
		url="friendAction_rejectApply.action";
		date={"friendShipId":id};
		$.post(url,date,function(response){
			if(response == "1"){
				window.location.href="friendAction_appliedFriendsList.action?number=${adminUser.mz_number}";
			}
		});
	}
</script>
  </head>
  
  
  <body>
  		<form name="cFM" action="friendAction_appliedFriendsList.action?number=${adminUser.mz_number}" method="post">
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
									        <td class="f-blue1 hide" align="center">申请人昵称</td>
										    <td class="f-blue1" align="center">申请人账号</td>
											<td class="f-blue1" align="center">申请人所在城市</td>
											<td class="f-blue1" align="center">申请人性别</td>
											<td class="f-blue1" align="center">申请人年龄</td>
											<td class="f-blue1" align="center">申请人邮箱地址</td>
											<td class="f-blue1" align="center">申请时间</td>
											<td class="f-blue1" align="center">操作</td>
										</tr>

										
												
										 <c:forEach var="appliyedList" items="${appliedList }">
										   <tr bgcolor="#ffffff"  align="center"
												onMouseOver="javascript:this.style.backgroundColor='#E8F2FE';"
												onmouseout="javascript:this.style.backgroundColor='#ffffff';">
												 <td >${appliyedList.selfUser.name }</td>
									              <td >${appliyedList.selfUser.mz_number }</td>
									              <td>${appliyedList.selfUser.city }</td>						
									              <td>${appliyedList.selfUser.sex=='0'?'女':'男'}</td>
									              <td>${appliyedList.selfUser.age }</td>	
									               <td>${appliyedList.selfUser.email }</td>		
									                <td>${appliyedList.applyTime }</td>
									                <td>
									                  <a href="javascript:void(0)" onclick="admit_applyfriend(${appliyedList.id })" >同意</a>
									                   <a href="javascript:void(0)" onclick="reject_applyfriend(${appliyedList.id })"  >拒绝</a>
									              </td>
											  </tr>
										</c:forEach>
										
									</table>
									
								</td>
							</tr>
							
						</table>
						${page}
					</td>
				</tr>
			</table>
  </body>
</html>
