package com.sun309.gateway.factory;

import com.sun309.gateway.dao.MtDao;
import com.sun309.gateway.impl.MtDaoImpl;

public class MtDaoFactory
{
	private static MtDaoImpl dao = null;
	public static MtDao create()
	{
		if(dao == null)
		{
			dao = new MtDaoImpl();
		}
		return dao;
	}
}
