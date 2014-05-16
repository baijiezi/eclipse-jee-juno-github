package com.sun309.jdbc;

public class JdbcDaoFactory
{
	private static JdbcDaoService service = null;
	public static JdbcDaoService create()
	{
		if(service == null)
		{
			service = new JdbcDaoServiceImpl();
		}
		return service;
	}
}
