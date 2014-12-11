<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="w_removeSelected.html" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>系统流水号：</label>
				<input type="text" name="keywords" value=""/>
			</li>
			
			<li>
				<label>交易码：</label>
				<input type="text" name="keywords" value=""/>
			</li>
			
			<li>
				<label>用户号码：</label>
				<input type="text" name="keywords" value=""/>
			</li>
			
			<li>
				<label>支付卡号：</label>
				<input type="text" name="keywords" value=""/>
			</li>
		</ul>
		<!--
		<table class="searchContent">
			<tr>
				<td>
					我的客户：<input type="text"/>
				</td>
				<td>
					<select class="combox" name="province">
						<option value="">所有省市</option>
						<option value="北京">北京</option>
						<option value="上海">上海</option>
						<option value="天津">天津</option>
						<option value="重庆">重庆</option>
						<option value="广东">广东</option>
					</select>
				</td>
			</tr>
		</table>
		-->
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	
	<table class="table" width="1200" layoutH="138">
		<thead>
			<tr>
				<!-- <th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>   -->
				<th width="120" orderField="accountNo" class="asc">系统流水号</th>
				<th orderField="accountName">交易码</th>
				<th width="80" orderField="accountType">POS商户号</th>
				<th width="130" orderField="accountCert">POS终端号</th>
				<th width="60" align="center" orderField="accountLevel">POS流水号</th>
				<th width="70">设备终端号</th>
				<th width="70">交易时间</th>
				<th width="70">用户ID</th>
				<th width="70">交易卡号</th>
				<th width="70">交易金额</th>
				<th width="70">结算日期</th>
				<!-- <th width="70">操作</th> -->
			</tr>
		</thead>
		<tbody>
			<tr target="sid_user" rel="1">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="2">
				<!--<td><input name="ids" value="xxx" type="checkbox"></td>-->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="3">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td> -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="4">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="5">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="6">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="7">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="8">
			<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="9">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="10">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="11">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="12">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="13">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="14">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="15">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="16">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="17">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="18">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="19">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="20">
				<!-- <td><input name="ids" value="xxx" type="checkbox"></td>  -->
				<td>A120113196309052434</td>
				<td>2369974556666474117745525744</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>2009-05-21</td>
				<td>
					<a title="删除" target="ajaxTodo" href="demo/common/ajaxDone.html?id=xxx" class="btnDel">删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=xxx" class="btnEdit">编辑</a>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>
	</div>
</div>
