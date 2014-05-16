package com.sun309.gateway.factory;

import com.sun309.gateway.dao.BlackListDao;
import com.sun309.gateway.impl.BlackListDaoImpl;

public class BlackListDaoFactory
{
	private static BlackListDao service = null;
	public static BlackListDao create() 
	{
		if(service == null)
		{
			service = new BlackListDaoImpl();
		}
		return service;
	}
}
