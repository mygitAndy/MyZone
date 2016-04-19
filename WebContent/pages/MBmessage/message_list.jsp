<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/pages/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>留言内容</title>

		<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">

	</head>

	<body background="${ctx}/images/bg1.jpg">
		<jsp:include page="head.jsp" />
		<table border="0" align="center" cellpadding="0" cellspacing="0"
			width="800">
			<tr bgcolor="#FFFFFF">
				<td>
				<div class="div1">
					<table align="center" border="0" width="750" cellpadding="0"
						cellspacing="0">
								<!-- 循环输出留言信息 -->
								<c:forEach items="${messages}" var="m">
									<tr><td>
										<!-- 留言板内容 -->
										<table border="0" width="750" align="center" cellpadding="0"
											cellspacing="0">
											<tr bordercolor="#FFFFFF" bgcolor="#F0F0F0">
												<td height="22" colspan="2">
													<!-- 留言人 -->
													<font class="title1">【${m.otherUser.name}】</font>
												</td>
													<td class="td2">
													<!-- 判断管理员用户是否登录 -->
													<c:if test="${!empty adminUser}">
													<ul>
													<li>
														<a  id="reply_${m.id}" a href="javascript:void(0);" onclick="showreplyfield(${m.id})">回复</a>
														</li>
														<li>
														
														<a href="messageAction_DeteleMBmessage.action?myZoneNumber=${adminUser.mz_number}&id=${m.id}">删除</a>
														</li>
														
														<li>
														<a href="javascript:void(0);" onclick="editMessageContent(${m.id})">编辑</a>
														</li>
													</ul>
													</c:if>
												</td>
											</tr>
											
											<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
												<td colspan="2">
													<!-- 留言内容 -->
													<div class="td1" id ="oldMessageContent_${m.id}">${m.content}</div>
													
														<tr><td colspan="2">
												<div class="hf" align="left">
										<table id="editMessage_table_${m.id}" style="display:none;">
												<tr >
												<td>
													<textarea id ="editMessageContent_${m.id}" rows="2" cols="50"></textarea>	
													</td>
													</tr>
											
											<!-- 留言人编辑留言信息 -->
												<tr>
												<td>
													<input type="button" id="btn_editMessageSubmit_${m.id}" value="发表" onclick="submitEditMessage(${m.id})"/>
													<input type="button" id="btn_editMessageCancel_${m.id}" value="取消" onclick="canceEditMessage(${m.id})">
													</td>
												</tr>
											</table>
											</div>
											</td>
											</tr>
											<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
												<td colspan="2" align="left" class="td2">
													时间：
														${m.messageTime}
												</td>

											</tr>
											
											<!-- 判断是否存在回复信息 -->

										<c:forEach items="${m.replyList}" var="replym">
											<tr><td colspan="2">
												<div class="hf" align="center">
												<table border="0" cellpadding="1" cellspacing="1" width="690">
													<tr>
														<td align="left" valign="middle">
														<img src="images/admin.jpg" width="13" height="18">
														<font class="hf-title">回复：</font></td>

														<td align="right">
															<!-- 判断是否是空间主人本人 -->
															<c:if test="${!empty adminUser}">
																<a href="messageAction_deleteMBreply.action?myZoneNumber=${adminUser.mz_number}&id=${replym.id}">删除</a>
															</c:if>
														</td>
													</tr>
													<tr>
														<td colspan="2" height="2" bgcolor="#F0F0F0">【${replym.replyUser.name}】</td>
													</tr>
													<tr>
														<td colspan="2">${replym.replyContent}</td>
													</tr>
													<tr>
														<td colspan="2" align="right" class="td2">回复时间 ：
														${replym.replyTime}
														</td>
													</tr>
													<!-- 填写重复的内容 -->

												</table>
												</div>
											</td></tr>

											</c:forEach>
											<tr><td colspan="2">
												<div class="hf" align="left">
										<table id="reply_content_${m.id}" style="display:none;">
												<tr >
												<td>
													<textarea id="isreply_content_${m.id}" cols="60" rows="2" name="isreply_content_${m.id}">
													</textarea>
													</td>
													</tr>
											
											<!-- 输出回复信息 -->
												<tr>
												<td>
													<input type="button" id="btn_relySubmit_${m.id}" value="提交" onclick="mbreply(${m.id})"/>
													<input type="button" id="btn_relyCancel_${m.id}" value="取消" onclick="cancelreplyfield(${m.id})">
													</td>
												</tr>
											</table>
											</div>
											</td>
											</tr>
										</table>
									</td></tr>
								</c:forEach>
								
								
								
								
								
								<tr bordercolor="#FFFFFF" bgcolor="#F0F0F0" align="center">
									<td height="8">
									</td>
								</tr>
								<tr bordercolor="#FFFFFF" >
									<td height="10"></td>
								</tr>
								<!-- 分页条 -->
								<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
									
								</tr>

						<tr bordercolor="#FFFFFF" >
							<td height="10">
							
							</td>
							
						</tr>
					</table>
					${page}
				</div>
				</td>
			</tr>
		</table>

		<form action="messageAction_AddMBmessage.action?myZoneNumber=${adminUser.mz_number}" method="post" >
					<table border="0" width="800" align="center" cellpadding="1" cellspacing="1"  bgcolor="#F0F0F0">
						<tr bordercolor="#FFFFFF">
							<td colspan="2" align="center" height="32" bgcolor="#941F53">
								<font class="title2">我 要 留 言</font>
							</td>
						</tr>
						<tr>
							<td height="10"></td>
						</tr>
						<tr bordercolor="#FFFFFF">
							<td align="right">内 容：</td>
							<td>
								<textarea rows="8" cols="50" name="content" id="message"></textarea>
							</td>
						</tr>
						<tr bordercolor="#FFFFFF">
							<td colspan="2" align="center" height="50">
								<input type="submit" value="留 言" />
								
							</td>
						</tr>
					</table>
				</form>
				<jsp:include page="/pages/common/mainHead.jsp" />
		<form name="cFM" action="messageAction_ShowMessageList.action?myZoneNumber=${adminUser.mz_number}" method="post">
			<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }"/>
		</form>
	</body>
</html>

<script type="text/javascript">
/* 		function message(form){
	        if(form.elements["content"].value == ""){
				alert("留言内容不能空！");
				return false;
	        }
	      } */
	      $(function(){
	      	
	      });
	      //添加回复
	  		function mbreply(v){
			var ReplyContent = $("#isreply_content_"+v+"").val();
			var url="messageAction_AddMBreply.action?myZoneNumber=${adminUser.mz_number}";
			var date={"ReplyContent":ReplyContent,"id":v};
			$.post(url,date,function(response){
			if(response == '1'){
			
				window.location.href="messageAction_ShowMessageList.action?myZoneNumber=${adminUser.mz_number}";
			}
			
			});
		
		}
			//回复
			function showreplyfield(m){
				$("#reply_content_"+m+"").show();
			}
			//取消回复
			function cancelreplyfield(m){
				$("#isreply_content_"+m+"").val("");
				$("#reply_content_"+m+"").hide();
			}
			//留言人点击编辑
			function editMessageContent(e){
				var file = $("#oldMessageContent_"+e+"").text();
				$("#editMessageContent_"+e+"").val(file);
				$("#editMessage_table_"+e+"").show();
				$("#oldMessageContent_"+e+"").hide();
			}
			//取消编辑
			function canceEditMessage(e){
				$("#editMessageContent_"+e+"").val("");
				$("#editMessage_table_"+e+"").hide();
				$("#oldMessageContent_"+e+"").show();
			}
			
			function submitEditMessage(e){
				var editedMessageContent =$("#editMessageContent_"+e+"").val();
				var url = "messageAction_editMBmessage.action?myZoneNumber=${adminUser.mz_number}";
				var date = {"editedMessageContent":editedMessageContent,"messageid":e};
				$.post(url,date,function(response){
					if(response == "1")
					window.location.href="messageAction_ShowMessageList.action?myZoneNumber=${adminUser.mz_number}";
				});
			}
</script>