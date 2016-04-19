<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/pages/common/taglib.jsp" %>
<table border="0" align="center" cellpadding="0" cellspacing="0" width="800">
<tr>
  <td> <a href="friendAction_friendsList.action?number=${adminUser.mz_number}">好友列表</a>
  </td>
  <td>
   <a href="friendAction_applyFriendsList.action?number=${adminUser.mz_number}">好友申请记录</a>
</td>

 <td>
   <a href="friendAction_appliedFriendsList.action?number=${adminUser.mz_number}">查看被申请好友记录 </a>
</td>

<td>
	<a href="friendAction_add.action">查找</a>
</td>
</tr>
</table>

