<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="pageContent">
	<form method="post" action="modifyUser.do" class="pageForm" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58" background="#accdf4">
		<div class="unit">
				<label>用户名：</label>
				<label>${user.name}</label>
			</div>
			<div class="unit">
				<label>手机号码：</label>
				<input type="text" name="phone" class="required phone" value="${user.mobile}" id="mobile"/>
			</div>
			<div class="unit">
				<label>原始密码：</label>
				<input type="text" name="old" size="30" class="required" equalto="#hidpwd" />
				<input type="hidden" name="hidpwd" id="hidpwd" value="${user.password}" class="required">
			</div>
			<div class="unit">
				<label>新密码：</label>
				<input id="newpwd" type="text" name="newpwd" size="30" class="required"/>
			</div>
			<div class="unit">
				<label>确认新密码：</label>
				<input type="password" name="password" size="30" class="required" equalto="#newpwd"/>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>

