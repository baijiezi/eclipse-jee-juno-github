package com.sun309.gateway.factory;

import com.sun309.gateway.dao.OperatorDao;
import com.sun309.gateway.impl.OperatorDaoImpl;

public class OperatorDaoFactory
{
	private static OperatorDao service;
	public static OperatorDao create()
	{
		if(service == null)
		{
			service = new OperatorDaoImpl();
		}
		return service;
	}
}
