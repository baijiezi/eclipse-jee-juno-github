package com.sun309.gateway.factory;

import com.sun309.gateway.dao.MasMtDao;
import com.sun309.gateway.impl.MasMtDaoImpl;

public class MasMtDaoFactory
{
	private static MasMtDaoImpl dao = null;
	public static MasMtDao create()
	{
		if(dao == null)
		{
			dao = new MasMtDaoImpl();
		}
		return dao;
	}
}
