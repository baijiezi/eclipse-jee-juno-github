<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch2(this);" action="TransType/queryDelta.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>交易类型:</td>
				<td><select name="businessType" class="required combox">
						<option value="1" <c:if test="${pojo.businessType eq 1 }">selected='selected'</c:if>>预约挂号</option>
						<option value="2" <c:if test="${pojo.businessType eq 2 }">selected='selected'</c:if>>用户登记</option>
						<option value="3" <c:if test="${pojo.businessType eq 3 }">selected='selected'</c:if>>自助缴费</option>
					</select>
				</td>
				<td>医院名称:</td>
				<td><input id="hospitalName" name="hospitalName" type="text" value="${pojo.hospitalName}"></td>
				<td>下单日期:</td>
				<td><input id="starTime1" type="text" name="starTime" class="date" value="${pojo.starTime}" dateFmt="yyyy-MM-dd 00:00:00" readonly="true">&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;<input id="endTime1" class="date" name="endTime" type="text" value="${pojo.endTime}" dateFmt="yyyy-MM-dd 23:59:59" readonly="true"></td>
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
				<th>业务类型</th>
				<th>交易笔数</th>
				<th>交易金额</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="v" items="${ls}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${v.hospitalName}</td>
					<td>${v.businessType}</td>
					<td>${v.count}</td>
					<td>${v.sumAmount}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
    
</div>
<script>
function navTabSearch2(form){
	  if($("#starTime1").attr("value")!=''&& $("#endTime1").attr("value")!=''){
	  	 navTab.reload(form.action, $(form).serializeArray());
	  }else{
	  	alertMsg.error('请填写下单日期后重新再提交！！')
	  }
      return false;
}
</script>