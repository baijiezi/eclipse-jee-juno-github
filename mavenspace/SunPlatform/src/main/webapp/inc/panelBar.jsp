<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="include.inc.jsp"%>
<c:set var="targetType" value="${empty param.targetType ? 'navTab' : param.targetType}"/>
<div class="panelBar">
	<div class="pages">
		<span>当前页面记录：</span>
		<select name="pageSize" onchange="dwzPageBreak({targetType:'${targetType}',data:{numPerPage:this.value}})" disabled="disabled">
			<c:forEach begin="50" end="50" step="10" varStatus="s">
				<option value="${s.index}" ${vo.pageSize eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
			</c:forEach>
		</select> <span>条;总数: ${vo.totalCount}</span>
	</div>
	<div class="pagination" targetType="${targetType}" totalCount="${vo.totalCount}" numPerPage="${vo.pageSize}" pageNumShown="10" currentPage="${vo.pageNum}"></div>
</div>