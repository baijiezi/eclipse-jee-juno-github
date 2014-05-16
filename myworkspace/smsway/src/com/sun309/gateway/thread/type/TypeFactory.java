package com.sun309.gateway.thread.type;

public class TypeFactory
{
	private static TypeService service = null;
	public static TypeService create(String access)
	{
		String callClass = new StringBuffer("com.sun309.gateway.thread.type.TypeTo").append(access).toString();
		Class c = null;
		try
		{
			c = Class.forName(callClass);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			service = (TypeService) c.newInstance();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return service;
	}
}
