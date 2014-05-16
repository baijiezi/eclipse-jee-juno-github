package com.sun309.gateway.impl;

import com.sun309.gateway.dto.SmsLog;

public class Test
{
	public static void main(String[] args)
	{
		SmsLog smsLog = new SmsLog();
		smsLog.setIp("");
		smsLog.setMessageId(1);
		smsLog.setType("mt");
		smsLog.setXml("test");
		SmsLogDaoImpl i = new SmsLogDaoImpl();
		i.insert(smsLog);
	}
}
