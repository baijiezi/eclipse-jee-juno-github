package com.sun309.gateway.factory;

import com.sun309.gateway.dao.MoDao;
import com.sun309.gateway.impl.MoDaoImpl;

public class MoDaoFactory
{
	private static MoDaoImpl dao = null;
	public static MoDao create()
	{
		if(dao == null)
		{
			dao = new MoDaoImpl();
		}
		return dao;
	}
}
