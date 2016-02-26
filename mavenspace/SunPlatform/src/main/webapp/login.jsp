<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>阳光康众自助终端管理平台</title>
<link href="themes/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="#"><img src="themes/default/images/sunlog.png" /></a>
			</h1>
			<div class="login_headerContent">
				<h2 class="login_title"><img style="left" src="themes/default/images/login_title201.png" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="login.do" method="post">
					<p>
						<label>用户名：</label>
						<input type="text" name="name" size="20" class="login_input" /><br/>
					</p>
					<p>
						<label>密&nbsp;&nbsp;&nbsp;码：</label>
						<input type="password" name="password" size="20" class="login_input" />
					</p>
					<p align="center"><font color="red"><c:out value="${loginer}"/></font></p>
					<div class="login_bar">
						<input class="sub" type="submit" value="" />
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="themes/default/images/login_banner66.png" /></div>
			<div class="login_main">
				<div class="login_inner">
					<p>您可以使用阳光康众自助终端管理平台</p>
					<p>非常感谢您使用本平台，祝您工作愉快</p>
					<p>有问题请及时联系管理员</p>
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2014 阳光康众 Inc. All Rights Reserved.
		</div>
	</div>
</body>
</html>