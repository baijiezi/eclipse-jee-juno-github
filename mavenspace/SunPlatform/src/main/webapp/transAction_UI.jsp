<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/inc/include.inc.jsp"%>
<c:import url="/inc/pagerForm.jsp"></c:import>



<div class="pageHeader" layoutH="700">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="transorder/transAction_query.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>下单日期:</td>
				<td><input id="startDate" type="text" name="startDate" class="date" value="${pojo.startDate}" dateFmt="yyyy-MM-dd 00:00:00" readonly="true">&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;<input class="date" id="endDate" name="endDate" type="text" value="${pojo.endDate}" dateFmt="yyyy-MM-dd 23:59:59" readonly="true"></td>
				<td>患者姓名：</td>
				<td><input id="patientName" name="patientName" type="text" value="${pojo.patientName}"></td>
				<td>医院名称：</td>
				<td><input id="hospitalName" name="hospitalName" type="text" value="${pojo.hospitalName}"></td>
				<td>诊疗卡号:</td>
				<td><input id="medicalCardNo" name="medicalCardNo" type="text" value="${pojo.medicalCardNo}"></td>
			</tr>
			<tr>
				<td>就诊日期:</td>
				<td><input id="jzstarTime" type="text" name="jzstarTime" class="date" value="${pojo.jzstarTime}" dateFmt="yyyy-MM-dd 00:00:00" readonly="true">&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;<input id="jzendTime" class="date" name="jzendTime" type="text" value="${pojo.jzendTime}" dateFmt="yyyy-MM-dd 23:59:59" readonly="true"></td>
				<td>支付卡号：</td>
				<td><input id="payCardNo" name="payCardNo" type="text" value="${pojo.payCardNo}"></td>
				<td>手机号码：</td>
				<td><input id="mobile" name="mobile" type="text" value="${pojo.mobile}"></td>
				<td>订单号：</td>
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
			<!-- <li><a class="icon" href="transorder/exportTrans.do" target="dwzExport" targetType="dialog" title="确实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
			<li><a class="icon"  href="javascript:;" onclick="testConfirmMsg()"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
<table class="table" targetType="navTab" asc="asc" desc="desc" width="110%" nowrapTD="false" layoutH="140">
		<thead>
			<tr>
				<th>编号</th>
				<th>医院名称</th>
				<th>订单号</th>
				<th>诊疗卡</th>
				<th>下单时间</th>
				<th>就诊时间</th>
				<th>号球</th>
				<th>科室</th>
				<th>医生</th>
				<th>身份证</th>
				<th>手机号码</th>
				<th>患者姓名</th>
				<th>性别</th>
				<th>操作员</th>
				<th>地址</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="v" items="${userList}" varStatus="s">
				<tr>
					<td>${s.index+1}</td>
					<td>${v.hospitalName}</td>
					<td>${v.orderNo}</td>
					<td>${v.medicalCardNo}</td>
					<td>${fn:substring(v.orderTime,0,19)}</td>
					<td>${fn:substring(v.visitTime,0,19)}</td>
					<td>${v.serialNo}</td>
					<td>${v.deptName}</td>
					<td>${v.doctorName}</td>
					<td>${v.idCard}</td>
					<td>${v.mobile}</td>
					<td>${v.patientName}</td>
					<td>${v.patientSex}</td>
					<td>${v.operId}</td>
					<td>${v.address}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
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
    var startDate = $.trim($('#startDate').val());
    var endDate = $.trim($('#endDate').val());
    var patientName = $.trim($('#patientName').val());
    var hospitalName = $.trim($('#hospitalName').val());
    var medicalCardNo = $.trim($('#medicalCardNo').val());
    var jzstarTime = $.trim($('#jzstarTime').val());
    var jzendTime = $.trim($('#jzendTime').val());
    var payCardNo = $.trim($('#payCardNo').val());
    var mobile = $.trim($('#mobile').val());
    var orderNo = $.trim($('#orderNo').val());
    // 取得要提交页面的URL
    var action = "transorder/exportTrans.do";
    // 创建Form
    var form = $('<form></form>');
    // 设置属性  
    form.attr('action', action);
    form.attr('method', 'post');
    // form的target属性决定form在哪个页面提交  
    // _self -> 当前页面 _blank -> 新页面  
    form.attr('target', '_self');
    // 创建Input  
    var my_startDate = $('<input type="text" name="startDate" />');  
    my_startDate.attr('value', startDate);
    var my_endDate = $('<input type="text" name="endDate" />');  
    my_endDate.attr('value', endDate);
    var my_patientName = $('<input type="text" name="patientName" />');  
    my_patientName.attr('value', patientName);
    var my_hospitalName = $('<input type="text" name="hospitalName" />');  
    my_hospitalName.attr('value', hospitalName);
    var my_medicalCardNo = $('<input type="text" name="medicalCardNo" />');
    my_medicalCardNo.attr('value', medicalCardNo);
    var my_jzstarTime = $('<input type="text" name="jzstarTime" />');
    my_jzstarTime.attr('value', jzstarTime);
    var my_jzendTime = $('<input type="text" name="jzendTime" />');
    my_jzendTime.attr('value', jzendTime);
    var my_payCardNo = $('<input type="text" name="payCardNo" />');  
    my_payCardNo.attr('value', payCardNo);
    var my_mobile = $('<input type="text" name="mobile" />');  
    my_mobile.attr('value', mobile);
    var my_orderNo = $('<input type="text" name="orderNo" />');
    my_orderNo.attr('value', orderNo);
    // 附加到Form  
    form.append(my_startDate);
    form.append(my_endDate);
    form.append(my_patientName);
    form.append(my_hospitalName);
    form.append(my_medicalCardNo);
    form.append(my_jzstarTime);
    form.append(my_jzendTime);
    form.append(my_payCardNo);
    form.append(my_mobile);
    form.append(my_orderNo);
    // 提交表单  
    form.submit();
    // 注意return false取消链接的默认动作  
    return false;  
}
</script>