<%@ include file="include.inc.jsp"%>

<form id="pagerForm" method="post" action="${url}">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="pageSize" value="${pageSize}" />
	<input type="hidden" name="orderNo" value="${vo.orderNo}" />
	<input type="hidden" name="patientName" value="${vo.patientName}" />
	<input type="hidden" name="hospitalName" value="${vo.hospitalName}" />
	<input type="hidden" name="payCardNo" value="${vo.payCardNo}" />
	<input type="hidden" name="mobile" value="${vo.mobile}" />
	<input type="hidden" name="medicalCardNo" value="${vo.medicalCardNo}" />
	<input type="hidden" name="userName" value="${vo.userName}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	<input type="hidden" name="operId" value="${vo.operId}" />
	<input type="hidden" name="startDate" value="${vo.startDate}" />
	<input type="hidden" name="endDate" value="${vo.endDate}" />
	<input type="hidden" name="jzstarTime" value="${vo.jzstarTime}" />
	<input type="hidden" name="jzendTime" value="${vo.jzendTime}" />
	<input type="hidden" name="states" value="${vo.states}" />
</form>