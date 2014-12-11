<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/inc/include.inc.jsp"%>
<c:import url="/inc/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="manner/mannerDelta.do" method="post">
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>用户姓名:</td>
				<td><input id="userName" name="userName" type="text" value="${pojo.userName}"></td>
				<td>诊疗卡号:</td>
				<td><input id="medicalCardNo" name="medicalCardNo" type="text" value="${pojo.medicalCardNo}"></td>
				<td>患者姓名:</td>
				<td><input id="patientName" name="patientName" type="text" value="${pojo.patientName}"></td>
			</tr>
			<tr>
				<td>手机号码:</td>
				<td><input id="mobile" name="mobile" type="text" value="${pojo.mobile}"></td>
				<td>终端编号:</td>
				<td><input id="operId" name="operId" type="text" value="${pojo.operId}"></td>
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
				<th>医院名称</th>
				<th>用户名</th>
				<th>患者姓名</th>
				<th>联系电话</th>
				<th>诊疗卡号</th>
				<th>医生态度</th>
				<th>护士态度</th>
				<th>检查态度</th>
				<th>药房态度</th>
				<th>收费态度</th>
				<th>就诊态度</th>
				<th>评价时间</th>
				<th>终端编号</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="v" items="${userList}" varStatus="s">
				<tr>
					<td>${s.index+1}</td>
					<td>${v.hospitalName}</td>
					<td>${v.userName}</td>
					<td>${v.patientName}</td>
					<td>${v.mobile}</td>
					<td>${v.medicalCardNo}</td>
					<td><c:choose><c:when test="${v.mannerDoctId==3}">不满意</c:when><c:when test="${v.mannerDoctId==2}">一般</c:when><c:when test="${v.mannerDoctId==1}">满意</c:when><c:otherwise>未知</c:otherwise></c:choose></td>
					<td><c:choose><c:when test="${v.mannerNursetId==3}">不满意</c:when><c:when test="${v.mannerNursetId==2}">一般</c:when><c:when test="${v.mannerNursetId==1}">满意</c:when><c:otherwise>未知</c:otherwise></c:choose></td>
					<td><c:choose><c:when test="${v.mannerCheckId==3}">不满意</c:when><c:when test="${v.mannerCheckId==2}">一般</c:when><c:when test="${v.mannerCheckId==1}">满意</c:when><c:otherwise>未知</c:otherwise></c:choose></td>
					<td><c:choose><c:when test="${v.mannerPhamacyId==3}">不满意</c:when><c:when test="${v.mannerPhamacyId==2}">一般</c:when><c:when test="${v.mannerPhamacyId==1}">满意</c:when><c:otherwise>未知</c:otherwise></c:choose></td>
					<td><c:choose><c:when test="${v.mannerChargeId==3}">不满意</c:when><c:when test="${v.mannerChargeId==2}">一般</c:when><c:when test="${v.mannerChargeId==1}">满意</c:when><c:otherwise>未知</c:otherwise></c:choose></td>
					<td><c:choose><c:when test="${v.mannerVisitId==3}">不满意</c:when><c:when test="${v.mannerVisitId==2}">一般</c:when><c:when test="${v.mannerVisitId==1}">满意</c:when><c:otherwise>未知</c:otherwise></c:choose></td>
					<td> ${fn:substring(v.mannerTime,0,19)}</td>
					<td>${v.operId}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	   <!-- 分页条 -->
    <c:import url="/inc/panelBar.jsp"></c:import>
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