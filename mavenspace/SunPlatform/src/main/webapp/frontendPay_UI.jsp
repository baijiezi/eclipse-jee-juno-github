<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/inc/include.inc.jsp"%>
<c:import url="/inc/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="FrontendPay/frontendPayDelta.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>支付日期:</td>
				<td><input id="startDate" type="text" name="startDate" class="date" value="${pojo.startDate}" dateFmt="yyyy-MM-dd 00:00:00" readonly="true">&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;<input class="date" name="endDate" id="endDate" type="text" value="${pojo.endDate}" dateFmt="yyyy-MM-dd 23:59:59" readonly="true"></td>
				<td>医院名称：</td>
				<td><input id="hospitalName" name="hospitalName" type="text" value="${pojo.hospitalName}"></td>
				<td>诊疗卡号:</td>
				<td><input id="medicalCardNo" name="medicalCardNo" type="text" value="${pojo.medicalCardNo}"></td>
				<td>交易状态:</td>
				<td><select name="states" class="required combox">
						<option value="">全部类型</option>
						<option value="0" <c:if test="${pojo.states eq 0 }">selected='selected'</c:if>>扣费成功</option>
						<option value="1" <c:if test="${pojo.states eq 1 }">selected='selected'</c:if>>扣费失败</option>
						<option value="2" <c:if test="${pojo.states eq 2 }">selected='selected'</c:if>>已退费</option>
					</select>
				</td>
			</tr>
			<tr>
			<td>患者姓名：</td>
				<td><input id="userName" name="patientName" type="text" value="${pojo.patientName}"></td>
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
			<!-- <li><a class="icon" href="FrontendPay/exprotExcel.do" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>  -->
			 <li><a class="icon"  href="javascript:;" onclick="testConfirmMsg()"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
<table class="table" targetType="navTab" asc="asc" desc="desc" width="2050px" nowrapTD="false" layoutH="140">
		<thead>
			<tr>
				<th>编号</th>
				<th>医院名称</th>
				<th>患者姓名</th>
				<th>诊疗卡</th>
				<th>手机号码</th>
				<th>订单号</th>
				<th>支付金额</th>
				<th>支付卡号</th>
				<th>支付类型</th>
				<th>支付号</th>
				<th>支付流水</th>
				<th>系统支付流水</th>
				<th>支付渠道</th>
				<th>终端ID</th>
				<th>终端流水</th>
				<th>支付时间</th>
				<th>业务类型</th>
				<th>交易状态</th>
				<th>备注</th>
				<th weigh="20px">操作</th>
				<th>操作备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="v" items="${userList}" varStatus="s">
				<tr>
					<td>${s.index+1}</td>
					<td>${v.hospitalName}</td>
					<td>${v.patientName}</td>
					<td>${v.medicalCardNo}</td>
					<td>${v.mobile}</td>
					<td>${v.orderNo}</td>
					<td>${v.realPayFee}</td>
					<td>${v.payCardNo}</td>
					<td>${v.payType}</td>
					<td>${v.paymentId}</td>
					<td>${v.payTranLine}</td>
					<td>${v.systemPayTranLine}</td>
					<td>${v.optionPayments}</td>
					<td>${v.operId}</td>
					<td>${v.terminalTranLine}</td>
					<td>${fn:substring(v.payTime,0,19)}</td>
					<td>${v.businessType}</td>
					<td><c:choose><c:when test="${v.states==0}">扣费成功</c:when><c:when test="${v.states==1}">扣费失败</c:when><c:when test="${v.states==2}">已退费</c:when><c:otherwise>未知</c:otherwise></c:choose></td>
					<td>${v.remark}</td>
					<td weigh="20px"><c:choose><c:when test="${v.states==0}"><a title="确定要进行退费?" target="ajaxTodo" href="FrontendPay/frontendRef.do?id=${v.id}&states=${v.states}" class="btnDel">退费</a></c:when></c:choose></td>
					<td>${v.operRemark}</td>
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
	    var startDate = $.trim($('#startDate').val());
	    var endDate = $.trim($('#endDate').val());
	    var hospitalName = $.trim($('#hospitalName').val());
	    var medicalCardNo = $.trim($('#medicalCardNo').val());
	    var states = $.trim($('#states').val());
	    var userName = $.trim($('#userName').val());
	    var mobile = $.trim($('#mobile').val());
	    var orderNo = $.trim($('#orderNo').val());
	    
	    // 取得要提交页面的URL
	    var action = "FrontendPay/exprotExcel.do";
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
	    var my_hospitalName = $('<input type="text" name="hospitalName" />');
	    my_hospitalName.attr('value', hospitalName);
	    var my_medicalCardNo = $('<input type="text" name="medicalCardNo" />');
	    my_medicalCardNo.attr('value', medicalCardNo);
	    var my_states = $('<input type="text" name="states" />');
	    my_states.attr('value', states);
	    var my_userName = $('<input type="text" name="userName" />');
	    my_userName.attr('value', userName);
	    var my_mobile = $('<input type="text" name="mobile" />');
	    my_mobile.attr('value', mobile);
	    var my_orderNo = $('<input type="text" name="orderNo" />');
	    my_orderNo.attr('value', orderNo);
	    // 附加到Form  
	    form.append(my_startDate);
	    form.append(my_endDate);
	    form.append(my_hospitalName);
	    form.append(my_medicalCardNo);
	    form.append(my_states);
	    form.append(my_userName);
	    form.append(my_mobile);
	    form.append(my_orderNo);
	    // 提交表单  
	    form.submit();
	    // 注意return false取消链接的默认动作  
	    return false;  
}
</script>