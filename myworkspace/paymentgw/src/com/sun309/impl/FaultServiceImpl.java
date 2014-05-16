package com.sun309.impl;

import com.sun309.dto.Fault;
import com.sun309.jdbc.JdbcDaoFactory;
import com.sun309.jdbc.JdbcDaoService;
import com.sun309.service.FaultService;
import com.sun309.util.DateUtils;

public class FaultServiceImpl implements FaultService
{
	private JdbcDaoService jdbc = JdbcDaoFactory.create();
	
	public boolean insert(Fault fault)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO fault (add_time, ExtOrderID, CardNO, PayOrderID, TransType) VALUES('");
		sql.append(DateUtils.getNowTime()).append("','");
		sql.append(fault.getExtOrderId()).append("','");
		sql.append(fault.getCardNo()).append("','");
		sql.append(fault.getPayOrderId()).append("','");
		sql.append(fault.getTransType()).append("')");
		return jdbc.insert(sql.toString()) > 0 ? true : false;
	}

}
