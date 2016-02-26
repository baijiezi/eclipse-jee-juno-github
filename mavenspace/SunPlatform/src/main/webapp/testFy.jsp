<%@ page contentType="text/html" pageEncoding="GB2312" language="java"%>
<%@ page import="java.sql.*"%>
<html>
	<head>
		<title>hello</title>
	</head>
	<body>
	<table border="1" spacing="2">
<%!
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String USER = "test203";
	public static final String PASS = "Xash6uYe";
	public static final String URL = "jdbc:mysql://192.168.200.203:3306/frontend_server";
	public static final int PAGESIZE = 100;
	int pageCount;
	int curPage = 1;
%>
<%
	//一页放5个
	String user = null;
	String pass = null;
	try{
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(URL,USER,PASS);
		String sql = "SELECT ID,MEDICINENAME,OUTPATIENTUNIT,CREATETIME,PRICE,PFPRICE FROM FRONTEND_DRUGS";
		PreparedStatement stat = con.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stat.executeQuery();
		rs.last();
		int size = rs.getRow();
		pageCount = (size%PAGESIZE==0)?(size/PAGESIZE):(size/PAGESIZE+1);
		String tmp = request.getParameter("curPage");
		if(tmp==null){
			tmp="1";
		}
		curPage = Integer.parseInt(tmp);
		if(curPage>=pageCount) curPage = pageCount;
		boolean flag = rs.absolute((curPage-1)*PAGESIZE+1);
		out.println(curPage);
		int count = 0;
		
		do{
			if(count>=PAGESIZE)break;
			int empno = rs.getInt(1);
			String ename = rs.getString(2);
			String job = rs.getString(3);
			Date hiredate = rs.getDate(4);
			float sal = rs.getFloat(5);
			int comm = rs.getInt(6);
			count++;
			%>
		<tr>
			<td><%=count%></td>
			<td><%=empno%></td>
			<td><%=ename%></td>
			<td><%=job%></td>
			<td><%=hiredate%></td>
			<td><%=sal%></td>
			<td><%=comm%></td>
		</tr>
			<%
		}while(rs.next());
		con.close();
	}
	catch(Exception e){
		
	}
%>
</table>
<a href = "testFy.jsp?curPage=1" >首页</a>
<a href = "testFy.jsp?curPage=<%=curPage-1%>" >上一页</a>
<a href = "testFy.jsp?curPage=<%=curPage+1%>" >下一页</a>
<a href = "testFy.jsp?curPage=<%=pageCount%>" >尾页</a>
第<%=curPage%>页/共<%=pageCount%>页

</body>
</html>