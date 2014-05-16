package com.sun309.gateway.log;


public interface LogService
{
	/**
	 * 记录写日志的SQL
	 * @param logOrders
	 */
	public void debug(Object log);
	public void debug(String content, String mobile);
	public void write();
	/**
	 * 传入调用的类名
	 * @return
	 */
	public Class getC();
	public void setC(Class c);
}
