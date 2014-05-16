package com.sun309.gateway.factory;

import com.sun309.gateway.dao.SpMessageMappingDao;
import com.sun309.gateway.impl.SpMessageMappingDaoImpl;

public class SpMessageMappingDaoFactory
{
	private static SpMessageMappingDao service;
	public static SpMessageMappingDao create()
	{
		if(service == null)
			service = new SpMessageMappingDaoImpl();
		return service;
	}
}
