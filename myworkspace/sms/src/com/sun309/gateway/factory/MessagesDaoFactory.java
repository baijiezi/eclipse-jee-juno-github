package com.sun309.gateway.factory;

import com.sun309.gateway.dao.MessagesDao;
import com.sun309.gateway.impl.MessagesDaoImpl;

public class MessagesDaoFactory
{
	private static MessagesDaoImpl dao = null;
	public static MessagesDao create()
	{
		if(dao == null)
		{
			dao = new MessagesDaoImpl();
		}
		return dao;
	}
}
