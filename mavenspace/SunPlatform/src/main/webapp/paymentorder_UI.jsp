<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="paymentorder/paymentorder.do" method="post">
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>创建日期:</td>
				<td><input type="text" name="date1" class="date" value="${status}">&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;<input class="date" name="status" type="text" value="${status}"></td>
				<td>用户姓名:</td>
				<td><input id="userName" name="userName" type="text" value="${pojo.userName}"></td>
				<td>诊疗卡号:</td>
				<td><input id="medicalCardNo" name="medicalCardNo" type="text" value="${pojo.medicalCardNo}"></td>
				<td>身份证号:</td>
				<td><input id="idCard" name="idCard" type="text" value="${pojo.idCard}"></td>
			</tr>
			<tr>
				<td>支付卡号:</td>
				<td><input id="payCardNo" name="payCardNo" type="text" value="${pojo.payCardNo}"></td>
				<td>手机号码:</td>
				<td><input id="mobile" name="mobile" type="text" value="${pojo.mobile}"></td>
				<td>订单编号:</td>
				<td><input id="orderNo" name="orderNo" type="text" value="${pojo.orderNo}"></td>
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
			<li><a class="icon" href="paymentorder/exportPayMent.do" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	
<table class="table" targetType="navTab" asc="asc" desc="desc" width="100%" nowrapTD="false" layoutH="112">
		<thead>
			<tr>
				<th>编号</th>
				<th>医院名称</th>
				<th>订单号</th>
				<th>诊疗卡</th>
				<th>身份证</th>
				<th>手机号码</th>
				<th>挂号费</th>
				<th>支付卡号</th>
				<th>帐户类型</th>
				<th>支付日期</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="v" items="${ls}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${v.hospitalName}</td>
					<td>${v.orderNo}</td>
					<td>${v.medicalCardNo}</td>
					<td>${v.idCard}</td>
					<td>${v.mobile}</td>
					<td>${v.fee}</td>
					<td>${v.payCardNo}</td>
					<td>${v.payMent}</td>
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