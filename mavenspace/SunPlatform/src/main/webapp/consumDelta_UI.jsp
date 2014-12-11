<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="consumDelta/consumDelta.do" method="post">
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>消费日期:</td>
				<td><input type="text" name="date1" class="date" value="${status}">&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;<input class="date" name="status" type="text" value="${status}"></td>
				<td>用户名：</td>
				<td><input name="userName" type="text" value="${pojo.userName}"></td>
				<td>康众卡号：</td>
				<td><input name="memberCard" type="text" value="${pojo.memberCard}"></td>
			</tr>
			<tr>
				<td>操作员:</td>
				<td><input name="operId" type="text" value="${pojo.operId}"></td>
				<td>外部订单号:</td>
				<td><input name="extOrder" type="text" value="${pojo.extOrder}"></td>
				<td></td>
				<td></td>
				<td>
					<div class="subBar" style="float:right;">
						<ul>
							<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
						</ul>
					</div>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">

<table class="table" targetType="navTab" asc="asc" desc="desc" width="100%" nowrapTD="false" layoutH="112">
		<thead>
			<tr>
				<th>编号</th>
				<th>用户名</th>
				<th>支付号</th>
				<th>外部订单号</th>
				<th>操作员</th>
				<th>状态</th>
				<td>退款日期</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="v" items="${ls}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${v.userName}</td>
					<td>${v.memberCard}</td>
					<td>${v.extOrder}</td>
					<td>${v.operId}</td>
					<td>${v.states}</td>
					<td>${fn:substring(v.createTime,0,19)}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
    <!-- 分页条 -->
    <%@include file="/inc/pageBar.jsp"%>
</div>
<script>
	$(document).ready(function(){
			$("#sreachKeys").bind("keyup", function(event){if (event.keyCode==13) searchDatas();});
			$("#sreachNames").bind("keyup", function(event){if (event.keyCode==13) searchDatas();});
	});

	function searchDatas(){
		$("#pagerForm").submit();
	}
</script>