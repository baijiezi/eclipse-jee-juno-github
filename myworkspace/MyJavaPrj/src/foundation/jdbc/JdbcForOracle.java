package foundation.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;



//oracle��jdbc���ӷ�ʽ:oci��thin
//oci��thin��Oracle�ṩ������Java����Oracle���ݿⷽʽ��
//thin��һ���ݿͻ��˵����ӷ�ʽ���������������ӷ�ʽ����Ҫ��װoracle�ͻ���,ֻҪ��classpath�а���jdbc������jar�����С�

//��SYS�û���¼���ݿ�ʱ���﷨�����������û�д����ͬ������д����¼Ȩ�ޡ�

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
		//orclΪ���ݿ��SID
	    String url="jdbc:oracle:thin:@localhost:1521:testdbsid";  //1521    orcl
	    String user="SYSTEM";
	    String password="ShaoGuocui860512";
	    Connection conn= DriverManager.getConnection(url,user,password);
	    return conn;
	}
}
