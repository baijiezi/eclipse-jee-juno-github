package com.sun309.gateway.factory;

import com.sun309.gateway.dao.MasRptDao;
import com.sun309.gateway.impl.MasRptDaoImpl;

public class MasRptDaoFactory
{
	private static MasRptDaoImpl dao = null;
	public static MasRptDao create()
	{
		if(dao == null)
		{
			dao = new MasRptDaoImpl();
		}
		return dao;
	}
}
