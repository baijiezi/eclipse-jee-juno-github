package com.sun309.gateway.log;

public class LogFactory
{
	private static LogService service;
	public static String SEND_DATA = "send_data";
	
	public static LogService create(String type, Class c)
	{
		if(LogFactory.SEND_DATA.equals(type))
		{
			service = new LogSendDataImpl();
		}
		service.setC(c);
		return service;
	}
}