package com.sun309.gateway.impl;

import com.sun309.gateway.dao.SmsLogDao;
import com.sun309.gateway.dto.SmsLog;
import com.sun309.gateway.jdbc.Dao;
import com.sun309.gateway.util.Common;

public class SmsLogDaoImpl extends Dao implements SmsLogDao
{
	public void insert(SmsLog smsLog)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO sms_log(`message_id`, `xml`, `type`, `add_time`, `ip`) VALUES('")
		   .append(smsLog.getMessageId().intValue())
		   .append("','")
		   .append(smsLog.getXml())
		   .append("','")
		   .append(smsLog.getType())
		   .append("','")
		   .append(Common.getNowTime())
		   .append("','")
		   .append(smsLog.getIp())
		   .append("')");
		
		this.insert(sql.toString());
	}
}
