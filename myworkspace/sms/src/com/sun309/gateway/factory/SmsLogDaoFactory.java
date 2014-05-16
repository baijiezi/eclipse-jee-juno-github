package com.sun309.gateway.factory;

import com.sun309.gateway.dao.SmsLogDao;
import com.sun309.gateway.impl.SmsLogDaoImpl;

public class SmsLogDaoFactory
{
	private static SmsLogDaoImpl dao = null;
	public static SmsLogDao create()
	{
		if(dao == null)
		{
			dao = new SmsLogDaoImpl();
		}
		return dao;
	}
}
