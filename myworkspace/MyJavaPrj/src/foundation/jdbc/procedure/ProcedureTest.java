package foundation.jdbc.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

//�洢���̲�����
public class ProcedureTest 
{
	public static void main(String[] args)
	{
		ProcedureTest procedureTest = new ProcedureTest();
//		procedureTest.procedure();
		procedureTest.procedureSelect();
		
		
		
	}
	
	
	
	
	/**
	 *  �����õĴ洢������䣺
	    DELIMITER $
		CREATE PROCEDURE p36(IN k INT, OUT person_name VARCHAR(10))    
		  BEGIN
		    DECLARE _no VARCHAR(10);
		    DECLARE i INT;
		    SET i = 0;
		    SELECT user.no INTO _no FROM user WHERE user.age=23 LIMIT 1;
		    SELECT user.name INTO person_name FROM user WHERE user.age=23 LIMIT 1;
		    WHILE !ISNULL(_no) && i<k DO
		      INSERT INTO user_log SELECT * FROM user WHERE user.no=_no;
		      DELETE FROM user WHERE user.no=_no;  
		      SELECT user.no INTO _no FROM user WHERE user.age=23 LIMIT 1; 
		      SET i = i + 1;
		    END WHILE;
		  END
		$
	**/
	public void procedure()
	{
		Connection connection = null;
		CallableStatement callableStatement = null;
		try 
		{
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		    String user = "root";
		    String password = "123456";
		    String url = new StringBuffer("jdbc:mysql://localhost/test?user=").append(user).append("&password=").append(password).toString();
		    connection= DriverManager.getConnection(url);
            callableStatement=connection.prepareCall("{call p36(?, ?)}");  //*{call �洢������(�����б�1,�����б�2)}����?����
            callableStatement.setInt(1, 1);                               //�������������������ţ�ֵ��
            callableStatement.registerOutParameter(2, Types.CHAR);        //�������������������ţ�ֵ��
            
            callableStatement.executeQuery();                             //ִ�д洢����
            System.out.println(callableStatement.getString(2));            //��������ţ�
           
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
		finally
		{
			try 
			{
				connection.close();
				 callableStatement.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	/**
	    �����õĴ洢������䣺
		CREATE PROCEDURE p38()    
		  BEGIN
		    SELECT * FROM user LIMIT 2;
		  END
		$
	 */
	public void procedureSelect()
	{
		Connection connection = null;
		CallableStatement callableStatement = null;
		try 
		{
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		    String user = "root";
		    String password = "123456";
		    String url = new StringBuffer("jdbc:mysql://localhost/test?user=").append(user).append("&password=").append(password).toString();
		    connection= DriverManager.getConnection(url);
            callableStatement=connection.prepareCall("{call p38()}");       //*{call �洢������(�����б�1,�����б�2)}����?����
            
            //����������Ĵ洢���̣��������ֻ�ܷ���ĳ���ֶε�ֵ��
            //�����Ҫ�����������������Ҫ�õ� ResultSet
            ResultSet rs = callableStatement.executeQuery();             
            System.out.println("no" + "		" + "name" + "		" + "age");
            while (rs.next())
            {
            	System.out.println(rs.getString("no") + "		" + rs.getString("name") + "		" + rs.getInt("age"));
            } 
           
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
		finally
		{
			try 
			{
				connection.close();
				callableStatement.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
}
