package foundation.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//��SYS�û���¼���ݿ�ʱ��Ҫʹ��SYSDBA��ɫ����ORACLE���ݿ�,�﷨�����������û�д����ͬ������д����¼Ȩ�ޡ�
//��DriverManager.getConnectionͨ�� Properties �������ӵ�defaultRowPrefetch��nternal_logon ����Ϣ��

public class JdbcForOracleSYS 
{
	public static void main(String args[])
{
		try
		{
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Properties props= new Properties();
			props.put("user","SYS");
			props.put("password","ShaoGuocui860512");
			props.put("internal_logon","sysdba");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl",props);
			System.out.println(conn);
			
//			Statement stmt=conn.createStatement();
//			stmt.addBatch("grant select on sys.dba_pending_transactions to sys_0000000623 ");
//			stmt.addBatch("grant select on sys.pending_trans$ to sys_0000000623  ");
//			stmt.addBatch("grant select on sys.dba_2pc_pending to sys_0000000623 ");
//			stmt.addBatch("grant execute on sys.dbms_system to sys_0000000623 ");
//			stmt.executeBatch();
//	
//			ResultSet rset=stmt.executeQuery("select username from x$kzsrt");
//			while(rset.next())
//			{
//				System.out.println("username========= "+rset.getString(1));
//			}
//			rset=stmt.executeQuery("select * from dba_tab_privs where grantee = 'SYS_0000000623'");
//			while(rset.next())
//			{
//				System.out.println("username========= "+rset.getString(3));
//			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	} 
}
