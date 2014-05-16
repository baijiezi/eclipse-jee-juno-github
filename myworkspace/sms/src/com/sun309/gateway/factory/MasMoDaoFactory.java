package com.sun309.gateway.factory;

import com.sun309.gateway.dao.MasMoDao;
import com.sun309.gateway.impl.MasMoDaoImpl;

public class MasMoDaoFactory
{
	private static MasMoDaoImpl dao = null;
	public static MasMoDao create()
	{
		if(dao == null)
		{
			dao = new MasMoDaoImpl();
		}
		return dao;
	}
}
