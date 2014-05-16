package com.sun309.factory;

import java.util.Hashtable;

import org.apache.log4j.Logger;

public class Factory
{
	private static Hashtable<String, Object> ht = new Hashtable<String, Object>();
	private static Object service = null;
	private static Logger log = Logger.getLogger(Factory.class);
	public static Object create(Class className)
	{
		if(ht.get(className.toString()) == null)
		{
			try
			{
				service = (Object) className.newInstance();
				ht.put(className.toString(), service);
			}
			catch (InstantiationException e)
			{
				log.debug(e);
			}
			catch (IllegalAccessException e)
			{
				log.debug(e);
			}
		}
		else
		{
			service = ht.get(className.toString());
		}
		return service;
	}
}
