package com.sun309.gateway.impl;

import com.sun309.gateway.dao.SpMessageMappingDao;
import com.sun309.gateway.dto.SpMessageMapping;
import com.sun309.gateway.jdbc.Dao;
import com.sun309.gateway.util.DateUtils;

public class SpMessageMappingDaoImpl extends Dao implements SpMessageMappingDao
{
	public boolean insert(SpMessageMapping mapping)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO sp_message_mapping(`message_id`, `sp_message_id`, `add_time`, `access`) VALUES('")
			.append(mapping.getMessageId())
			.append("','")
			.append(mapping.getSpMessageId())
			.append("','")
			.append(DateUtils.getNowTime())
			.append("','")
			.append(mapping.getAccess())
		   .append("')");
		try
		{
			this.insert(sql.toString());
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

}
