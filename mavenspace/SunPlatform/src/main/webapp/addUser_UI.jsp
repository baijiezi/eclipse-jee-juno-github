<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="pageContent">
	<form method="post" action="addUser.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>工 牌 号：</label>
				<input name="sn" type="text" size="30" />
			</p>
			<p>
				<label>用户名称：</label>
				<input name="name" class="required" type="text" size="30" alt="请输入客户名称"/>
			</p>
			<p>
				<label>医院名称：</label>
					<select name="department" class="required combox">
						<option value="" selected>请选择</option>
						<c:forEach var="v" items="${ls}" varStatus="status">
							<option value="${v.hospital_id}">${v.hospital_name}</option>
						</c:forEach>
					</select>
			</p>
			<p>
				<label>手机号码：</label>
				<input type="text" value="" name="mobile" class="textInput">
			</p>
			<p>
				<label>用户密码：</label>
				<input name="password" class="digits" type="text" size="30" alt="请输入数字"/>
			</p>
			<p>
				<label>用户类型：</label>
				<select name="user_level" class="required combox">
					<option value="">请选择</option>
					<option value="1" selected>职员</option>
					<option value="2">客户</option>
				</select>
			</p>
			
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>