package mywebprj.test.com.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 待处理的问题：c3p0如何处理事务?
public class Client {
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		DBConnectionManager c=new DBConnectionManager();
		Connection conn= c.getConnection();
		
		Statement s=conn.createStatement();
		String sql="select * from tbl_user";
		
		ResultSet rs=s.executeQuery(sql);
		
		while(rs.next()){
			String a=rs.getString("name");
			System.out.println("a="+a);
		}
		
	}
}
