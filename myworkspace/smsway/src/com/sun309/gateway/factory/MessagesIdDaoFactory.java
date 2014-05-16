package com.sun309.gateway.factory;

import com.sun309.gateway.dao.MessagesIdDao;
import com.sun309.gateway.impl.MessagesIdDaoImpl;

public class MessagesIdDaoFactory
{
	private static MessagesIdDaoImpl dao = null;
	public static MessagesIdDao create()
	{
		if(dao == null)
		{
			dao = new MessagesIdDaoImpl();
		}
		return dao;
	}
}
