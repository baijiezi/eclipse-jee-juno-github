package com.sun309.gateway.log;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.sun309.gateway.util.Common;
import com.sun309.gateway.util.DateUtils;

public class LogSendDataImpl extends _LogService
{
	private final Logger log = Logger.getLogger(LogSendDataImpl.class);
	private int STORE_DAYS = 10;
	public void debug(String content, String mobile)
	{
		log.info(content);
		this.setTableName(this.getTableName("log_data", STORE_DAYS));
		StringBuffer sql = new StringBuffer();
		DateUtils date = new DateUtils();
		try
		{
			sql.append("INSERT INTO ");
			sql.append(this.getTableName());
			sql.append("(`add_time`, `mobile`, `log_content`) VALUES ('");
			sql.append(date.getDate() ).append( "','");
			sql.append(mobile ).append( "','");
			sql.append(Common.htmlEscaping(content) ).append( "'");
			sql.append(")");
			_sql.add(sql.toString());
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/**
	 * 创建下订单的用户操作日志表
	 * 
	 * @return
	 */
	public ArrayList<String> table()
	{
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS ");
		sql.append(this.getTableName());
		sql.append("(");
		sql.append("`id` bigint(20) unsigned NOT NULL auto_increment,");
		sql.append("`add_time` datetime NOT NULL,");
		sql.append("`mobile` char(250) NOT NULL,");
		sql.append("`log_content` text NOT NULL,");
		sql.append("PRIMARY KEY  (`id`)");
		sql.append(")");
		ArrayList<String> _sql = new ArrayList<String>();
		_sql.add(sql.toString());
		return _sql;
	}

	public void write()
	{
		
		ArrayList<String> execSql = new ArrayList<String>();
		
		this.setTableName(this.getTableName("log_data", STORE_DAYS));
		ArrayList<String> sql = table();
		for(String sql1 : sql)
		{
			execSql.add(sql1);
		}
		
		for(String sql2 : _sql)
		{
			execSql.add(sql2);
		}
		
		this.batchExecute(execSql);
		execSql = null;
		sql = null;
		_sql = null;
		_sql = new ArrayList<String>();
	}

}
