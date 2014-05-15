package com.test.spring.transaction;

import java.sql.Connection;

import com.test.spring.transaction.c3p0.DBConnectionManager;

public class Client {
	public static void main(String[] args){
		UserDao userDao = new UserDao();
		Connection conn = DBConnectionManager.getConnection();
		try{
			userDao.save(conn);
			int i = 1/0;
			userDao.delete(conn);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
	}
}
