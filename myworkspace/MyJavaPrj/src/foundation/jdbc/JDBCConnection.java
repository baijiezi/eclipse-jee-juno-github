package foundation.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;


/**
 * JDBC连接各种数据库方法
 *
 */
public class JDBCConnection 
{
	public static void main(String[] args)
	{
		try 
		{
			JDBCConnection myJdbc = new JDBCConnection();
//			Connection conn = myJdbc.getMySqlConnection();
			Connection conn = myJdbc.getSqlServerConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 1、Oracle8/8i/9i数据库（thin模式）
	 * @return Connection
	 */
	public Connection getOracleConnection() throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		//orcl为数据库的SID
	    String url="jdbc:oracle:thin:@localhost:1521:orcl";
	    String user="test";
	    String password="test";
	    Connection conn= DriverManager.getConnection(url,user,password);
	    return conn;
	}
	
	
	/**
	 * 2、DB2数据库
	 * @return Connection
	 * @throws Exception
	 */
	public Connection getDB2Connection() throws Exception
	{
	    Class.forName("com.ibm.db2.jdbc.app.DB2Driver ").newInstance();
	    //sample为你的数据库名
	    String url="jdbc:db2://localhost:5000/sample";
	    String user="admin";
	    String password="";
	    Connection conn= DriverManager.getConnection(url,user,password);
	    return conn;
	}
	
	
	/**
	 * 3、Sql Server 2008 数据库
	 * @return Connection
	 * @throws Exception
	 */
	public Connection getSqlServerConnection() throws Exception
	{
	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	    //不同版本的 Sql Server 的 url 是不同的，下面是 SQL Server 2008 的 url
	    String url="jdbc:sqlserver://localhost:1433;DatabaseName=testdb";
	    //mydb为数据库
	    String user="sa";
	    String password="123456";
	    Connection conn= DriverManager.getConnection(url,user,password);
	    return conn;
	}
	
	
	/**
	 * 4、Sybase数据库
	 * @return Connection
	 * @throws Exception
	 */
	public Connection getSybaseConnection() throws Exception
	{
		Class.forName("com.sybase.jdbc.SybDriver").newInstance();
	    //myDB为你的数据库名
	    String url =" jdbc:sybase:Tds:localhost:5007/myDB";
	    Properties sysProps = System.getProperties();
//	    SysProps.put("user","userid");
//	    SysProps.put("password","user_password");
	    Connection conn = null;
//	    conn = DriverManager.getConnection(url, SysProps);
	    return conn;
	}
	
	
	/**
	 * 5、Informix数据库
	 * @return Connection
	 * @throws Exception
	 */
	public Connection getInformixConnection() throws Exception
	{
	    Class.forName("com.informix.jdbc.IfxDriver").newInstance();
	    String url = null;
	    //myDB为数据库名
//	    url = "jdbc:informix-sqli://123.45.67.89:1533/myDB:INFORMIXSERVER=myserver;
	    String user="testuser";
	    String password="testpassword";
	    Connection conn= DriverManager.getConnection(url);
	    Statement st = conn.createStatement();
	    return conn;
	}
	
	
	
	/**
	 * 6、MySQL数据库
	 * @return Connection
	 * @throws Exception
	 */
	public Connection getMySqlConnection() throws Exception
	{
//		需导入驱动包 mysql-connector-java-5.1.7-bin
	    Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	    String user = "root";
	    String password = "123456";
//	    db 为要连接的数据库的名称
	    String url = new StringBuffer("jdbc:mysql://localhost/db?user=").append(user)
	    						.append("&password=").append(password).toString();
	    Connection conn= DriverManager.getConnection(url);
	    return conn;
	}
	
	
	/**
	 * 7、PostgreSQL数据库
	 * @return Connection
	 * @throws Exception
	 */
	public Connection getPostgreSqlConnection7() throws Exception
	{
	    Class.forName("org.postgresql.Driver").newInstance();
	    //myDB为数据库名
	    String url ="jdbc:postgresql://localhost/myDB";
	    String user="myuser";
	    String password="mypassword";
	    Connection conn= DriverManager.getConnection(url,user,password);
	    return conn;
	}
}
