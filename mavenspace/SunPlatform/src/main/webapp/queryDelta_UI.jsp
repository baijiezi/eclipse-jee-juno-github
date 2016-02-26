<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="payrecord/queryDelta.do" method="post">
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>充值日期:</td>
				<td><input type="text" name="date1" class="date" value="${status}">&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;<input class="date" name="status" type="text" value="${status}"></td>
				<td>用户名：</td>
				<td><input name="userName" type="text" value="${pojo.userName}"></td>
				<td>支付卡号：</td>
				<td><input name="payCardNo" type="text" value="${pojo.payCardNo}"></td>
			</tr>
			<tr>
				<td>康众卡号:</td>
				<td><input name="memberCard" type="text" value="${pojo.memberCard}"></td>
				<td>订单号:</td>
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="icon" href="payrecord/exportPayRec.do" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
<table class="table" targetType="navTab" asc="asc" desc="desc" width="100%" nowrapTD="false" layoutH="112">
		<thead>
			<tr>
				<th>编号</th>
				<th>订单号</th>
				<th>支付卡号</th>
				<th>康众卡号</th>
				<th>用户名</th>
				<th>金额</th>
				<th>终端号</th>
				<th>状态</th>
				<td>充值日期</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="v" items="${ls}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${v.extOrder }</td>
					<td>${v.payCardNo}</td>
					<td>${v.memberCard}</td>
					<td>${v.userName}</td>
					<td>${v.fee}</td>
					<td>${v.operId}</td>
					<td>${v.states}</td>
					<td> ${fn:substring(v.createTime,0,19)}</td>
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