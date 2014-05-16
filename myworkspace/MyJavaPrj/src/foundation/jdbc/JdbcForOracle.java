package foundation.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;



//oracle的jdbc连接方式:oci和thin
//oci和thin是Oracle提供的两套Java访问Oracle数据库方式。
//thin是一种瘦客户端的连接方式，即采用这种连接方式不需要安装oracle客户端,只要求classpath中包含jdbc驱动的jar包就行。

//用SYS用户登录数据库时，语法与其它操作用户写法不同，必须写明登录权限。

public class JdbcForOracle 
{
	public static void main(String[] args)
	{
		JdbcForOracle jdbcForOracle = new JdbcForOracle();
		try 
		{
			Connection conn = jdbcForOracle.getOracleConnection();
			System.out.println(conn);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	public Connection getOracleConnection() throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		//orcl为数据库的SID
	    String url="jdbc:oracle:thin:@localhost:1521:testdbsid";  //1521    orcl
	    String user="SYSTEM";
	    String password="ShaoGuocui860512";
	    Connection conn= DriverManager.getConnection(url,user,password);
	    return conn;
	}
}
