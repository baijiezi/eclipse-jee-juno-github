<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
目前在线人数：

<font color=red><%=getServletContext().getAttribute("onLine")%></font><br>

退出会话：

<form action="exit.jsp" method=post> 
<input type=submit value="exit"> 
</form>
</body>
</html>