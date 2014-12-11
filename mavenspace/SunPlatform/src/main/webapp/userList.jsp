<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/page/common/include.inc.jsp"%>

<!-- 自定义标签封装pagerForm，简化分页 -->
<custom:paginationForm action="${contextPath }/user!userList.action">
	<input type="hidden" name="keywords" value="${keywords }"/>
</custom:paginationForm>

<form method="post" action="${contextPath }/user!userList.action" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
       		<label>登录名称:</label><input type="text" name="keywords" value="${keywords}"><button type="submit">检索</button>
		</div>
	</div>
</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="user!dosave.action" target="dialog" mask="true"><span>添加</span></a></li>
		    <!-- 用于数组传送数据 -->
		    
		    <li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="user!del.action" class="delete"><span>删除用户</span></a></li>
			
			<li><a class="edit" href="user!dosave.action?userId={sid_user}" target="dialog" mask="true" warn="请选择一个用户"><span>修改</span></a></li>
		</ul>
	</div>

	<div layoutH="116" id="w_list_print">
	<table class="table" width="98%" targetType="navTab" asc="asc" desc="desc">
		<thead>
			<tr>
			    <th><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th orderField="id" <s:if test="orderField=='id'">class="${orderDirection}"</s:if> title="排序">编号</th> 
				<th>用户姓名</th>
				<th>用户密码</th>
				<th>是否是管理员</th>
				<th orderField="staff_id" <s:if test="orderField=='staff_id'">class="${orderDirection}"</s:if>>staff-id</th>
			    <th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="user" items="${userList}">
			<tr target="sid_user" rel="${user.id}">
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.id}</td>
				<td>${user.loginName}</td>
				<td>${user.passWord}</td>
				<td>${user.adminInd}</td>
				<td>${user.staffId}</td>
				<td>
				    <a title="删除" target="ajaxTodo" href="user!del.action?ids=${user.id}" class="btnDel">删除</a>
					<a title="编辑"  target="dialog" mask="true" href="user!dosave.action?userId=${user.id}" class="btnEdit">编辑</a>
				</td>
			</tr>
		</c:forEach>
		
		</tbody>
	</table>
	</div>
	
	
	<!-- 自定义标签封装pagerForm，简化分页 -->
	<custom:pagination page="${page }"/>
