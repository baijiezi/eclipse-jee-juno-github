package foundation.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class JdbcForMysql {
	public static void main(String[] args){
		try{
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		    String user = "juguang";
		    String password = "jgled88";
		    String url = new StringBuffer("jdbc:mysql://juguang.gotoftp4.com/juguang?useUnicode=true&characterEncoding=utf8").toString();
		    Connection conn= DriverManager.getConnection(url, user, password);
		    
		    String sql = "select * from jobs";
		    ResultSet rs = conn.createStatement().executeQuery(sql);
		    while(rs.next()){
		    	String name = rs.getString(2);
		    	System.out.println(name);
		    	System.out.println(new String(name.getBytes("utf-8"), "utf-8"));
		    	printHexString(name.getBytes());
		    	
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}
	    
	    
	}
	
	
	////将指定byte数组以16进制的形式打印到控制台
	public static void printHexString( byte[] b) {  
	   for (int i = 0; i < b.length; i++) { 
	     String hex = Integer.toHexString(b[i] & 0xFF); 
	     if (hex.length() == 1) { 
	       hex = '0' + hex; 
	     } 
	     System.out.print(hex.toUpperCase() ); 
	   } 
	   System.out.println();
	}
}
