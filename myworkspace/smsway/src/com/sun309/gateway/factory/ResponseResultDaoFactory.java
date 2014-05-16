package com.sun309.gateway.factory;

import com.sun309.gateway.dao.ResponseResultDao;
import com.sun309.gateway.impl.ResponseResultDaoImpl;

public class ResponseResultDaoFactory
{
	private static ResponseResultDaoImpl dao = null;
	public static ResponseResultDao create()
	{
		if(dao == null)
		{
			dao = new ResponseResultDaoImpl();
		}
		return dao;
	}
}
