package com.sun309.gateway.log;

import java.util.ArrayList;

import com.sun309.gateway.jdbc.Dao;
import com.sun309.gateway.util.DateUtils;

public abstract class _LogService extends Dao implements LogService
{
	protected ArrayList<String> _sql = new ArrayList<String>();
	//一个日志表记录多少天的数据
	protected int STORE_DAYS = 31;
	private Class c;
	public Class getC()
	{
		return c;
	}
	public void setC(Class c)
	{
		this.c = c;
	}
	public void debug(String content, String type, boolean writeFile) {}
	public void debug(Object log) {} 


	private String tableName;
	public String getTableName(String tableName, int cycle)
	{
		DateUtils date = new DateUtils();
		StringBuffer _tableName = new StringBuffer(tableName);
		_tableName.append("_");
		_tableName.append(date.getYear());
		_tableName.append("_");
		_tableName.append(date.getMonth());
		_tableName.append("_");
		_tableName.append(date.getDay()/cycle);
		this.setTableName(_tableName.toString());
		return _tableName.toString();
	}
	public String getTableName()
	{
		return tableName;
	}
	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}
}
