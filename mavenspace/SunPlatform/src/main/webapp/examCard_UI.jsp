<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/inc/include.inc.jsp"%>
<c:import url="/inc/pagerForm.jsp"></c:import>


<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="Exam/examDelta.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>用户姓名：</td>
				<td><input id="patientName" name="patientName" type="text" value="${pojo.patientName}"></td>
				<td>医院名称：</td>
				<td><input id="hospitalName" name="hospitalName" type="text" value="${pojo.hospitalName}"></td>
				<td>诊疗卡号:</td>
				<td><input id="medicalCardNo" name="medicalCardNo" type="text" value="${pojo.medicalCardNo}"></td>
				<td>手机号码：</td>
				<td><input id="mobile" name="mobile" type="text" value="${pojo.mobile}"></td>
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
			<!-- <li><a class="icon" href="Exam/exprotExcel.do" target="dwzExport" targetType="dialog" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>  -->
			<li><a class="icon"  href="javascript:;" onclick="testConfirmMsg()"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
<table class="table" targetType="navTab" asc="asc" desc="desc" width="100%" nowrapTD="false" layoutH="112">
		<thead>
			<tr>
				<th>编号</th>
				<th>医院名称</th>
				<th>诊疗卡</th>
				<th>姓&nbsp;名</th>
				<th>性&nbsp;别</th>
				<th>身份证</th>
				<th>手机号</th>
				<th>地址</th>
				<th>终端编号</th>
				<th>发卡日期</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="v" items="${userList}" varStatus="s">
				<tr>
					<td>${s.index+1}</td>
					<td>${v.hospitalName}</td>
					<td>${v.medicalCardNo}</td>
					<td>${v.patientName}</td>
					<td>${v.patientSex}</td>
					<td>${v.idCard}</td>
					<td>${v.mobile}</td>
					<td>${v.address}</td>
					<td>${v.operId}</td>
					<td>${fn:substring(v.createTime,0,19)}</td>
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
	
	function testConfirmMsg(){
    var patientName = $.trim($('#patientName').val());
    var hospitalName = $.trim($('#hospitalName').val());
    var medicalCardNo = $.trim($('#medicalCardNo').val());
    var mobile = $.trim($('#mobile').val());
    // 取得要提交页面的URL
    var action = "Exam/exprotExcel.do";
    // 创建Form
    var form = $('<form></form>');
    // 设置属性  
    form.attr('action', action);
    form.attr('method', 'post');
    // form的target属性决定form在哪个页面提交  
    // _self -> 当前页面 _blank -> 新页面  
    form.attr('target', '_self');
    // 创建Input  
    var my_patientName = $('<input type="text" name="patientName" />');  
    my_patientName.attr('value', patientName);
    var my_hospitalName = $('<input type="text" name="hospitalName" />');  
    my_hospitalName.attr('value', hospitalName);
    var my_medicalCardNo = $('<input type="text" name="medicalCardNo" />');  
    my_medicalCardNo.attr('value', medicalCardNo);
    var my_mobile = $('<input type="text" name="mobile" />');  
    my_mobile.attr('value', mobile);
    // 附加到Form  
    form.append(my_patientName);
    form.append(my_hospitalName);
    form.append(my_medicalCardNo);
    form.append(my_mobile);
    // 提交表单  
    form.submit();
    // 注意return false取消链接的默认动作  
    return false;  
}
</script>