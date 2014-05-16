package com.sun309.gateway.impl;

import com.sun309.gateway.dao.ResponseResultDao;
import com.sun309.gateway.dto.ResponseResult;
import com.sun309.gateway.jdbc.Dao;
import com.sun309.gateway.util.Common;

public class ResponseResultDaoImpl extends Dao implements ResponseResultDao
{
	public void insert(ResponseResult rr, String type)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO response_result(xml, add_time, type) VALUES(");
		sql.append("'" + rr.getXml() + "',");
		sql.append("'" + Common.getNowTime() + "',");
		sql.append("'" + type + "'");
		sql.append(")");
		
		this.insert(sql.toString());
	}
}
