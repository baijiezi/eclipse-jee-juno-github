package com.sun309.impl;

import java.util.ArrayList;

import com.sun309.jdbc.JdbcDaoFactory;
import com.sun309.jdbc.JdbcDaoService;
import com.sun309.service.LogService;
import com.sun309.util.Common;
import com.sun309.util.DateUtils;

public class LogServiceImpl implements LogService
{
	private static ArrayList<String> list = new ArrayList<String>();
	private JdbcDaoService jdbc = JdbcDaoFactory.create();
	public void debug(String content)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO log (`log_content`, `add_time`) VALUES ('");
		sql.append(Common.htmlEscaping(content)).append("','");
		sql.append(DateUtils.getNowTime());
		sql.append("')");
		list.add(sql.toString());
	}

	public void write()
	{
		if( list!=null && list.size() > 0 )
			jdbc.batchExecute(list);
		list = null;
		list = new ArrayList<String>();
	}

}
