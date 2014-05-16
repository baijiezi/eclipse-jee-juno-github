package com.sun309.gateway.factory;

import com.sun309.gateway.dao.InvalidDao;
import com.sun309.gateway.impl.InvalidDaoImpl;

public class InvalidDaoFactory
{
	private static InvalidDaoImpl dao = null;
	public static InvalidDao create()
	{
		if(dao == null)
		{
			dao = new InvalidDaoImpl();
		}
		return dao;
	}
}
