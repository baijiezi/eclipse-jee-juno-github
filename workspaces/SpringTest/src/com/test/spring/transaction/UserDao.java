package com.test.spring.transaction;

import java.sql.Connection;
import java.sql.Statement;

public class UserDao {

	public void delete(Connection conn) throws Exception{
		String sql = "delete from user where id = 4";
		Statement st = conn.createStatement();
		System.out.println("开始删除记录");
		boolean bool = st.execute(sql);
		
	}
	
	public void save(Connection conn) throws Exception{
		String sql = "insert into user(name, age) values('baijiezi', '18');";
		Statement st = conn.createStatement();
		System.out.println("开始插入记录");
		boolean bool = st.execute(sql);
		
	}
}
