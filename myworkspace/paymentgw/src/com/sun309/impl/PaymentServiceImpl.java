package com.sun309.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun309.dto.Payment;
import com.sun309.factory.Factory;
import com.sun309.jdbc.JdbcDaoFactory;
import com.sun309.jdbc.JdbcDaoService;
import com.sun309.service.IdService;
import com.sun309.service.PaymentService;
import com.sun309.util.Constants;
import com.sun309.util.DateUtils;

public class PaymentServiceImpl implements PaymentService
{
	private JdbcDaoService jdbc = JdbcDaoFactory.create();

	public ArrayList<Payment> findPaymentByCondition(String condition)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT `ID`, `TransType`, `PayOrderID`, `ExtOrderID`, `CardNO`, `Flag`, `Message`, `Add_Time`, `Status` FROM payment");
		sql.append(jdbc.getSelectCondition(condition));
		List<Map<String, Object>> list = jdbc.select(sql.toString());
		if (list == null || list.size() <= 0)
			return null;
		ArrayList<Payment> paymentList = new ArrayList<Payment>();

		for (Map<String, Object> map : list)
		{
			Payment p = new Payment();
			p.setAddTime(jdbc.ObjectToLong(map, "Add_Time"));
			p.setCardNo(jdbc.ObjectToString(map, "CardNO"));
			p.setExtOrderId(jdbc.ObjectToString(map, "ExtOrderID"));
			p.setFlag(jdbc.ObjectToInteger(map, "Flag"));
			p.setId(jdbc.ObjectToString(map, "ID"));
			p.setMessage(jdbc.ObjectToString(map, "Message"));
			p.setPayOrderId(jdbc.ObjectToString(map, "PayOrderID"));
			p.setStatus(jdbc.ObjectToInteger(map, "Status"));
			p.setTransType(jdbc.ObjectToInteger(map, "TransType"));
			paymentList.add(p);
		}
		return paymentList;
	}

	public boolean insert(Payment payment)
	{
		IdService id = (IdService) Factory.create(IdServiceImpl.class);
		String _id = id.createId();
		if(_id == null ) return false;
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO payment (`ID`, `TransType`, `PayOrderID`, `ExtOrderID`, `CardNO`, `Flag`, `Message`, `Add_Time`, `Status`) VALUES ('");
		sql.append(_id).append("','");
		sql.append(payment.getTransType()).append("','");
		sql.append(payment.getPayOrderId()).append("','");
		sql.append(payment.getExtOrderId()).append("','");
		sql.append(payment.getCardNo()).append("','");
		sql.append(payment.getFlag()).append("','");
		sql.append(payment.getMessage()).append("','");
		sql.append(DateUtils.getNowTime()).append("','");
		sql.append(Constants.PAYMENT_STATUS_0).append("'");
		sql.append(")");
		return jdbc.insert(sql.toString()) > 0 ? true : false;
	}

	public boolean updateStatus(String id, Integer status, boolean autoIncrementTryTime)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE payment SET Status=").append(status);
		if(autoIncrementTryTime) sql.append(", TryTimes=TryTimes+1");
		sql.append(" WHERE ID='").append(id).append("'");
		return jdbc.update(sql.toString()) > 0 ? true : false;
	}

	public boolean moveToLog(String paymentId)
	{
		ArrayList<String> list = new ArrayList<String> ();
		try
		{
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO payment_log ( `ID`, `TransType`, `PayOrderID`, `ExtOrderID`, `CardNO`, `Flag`, `Message`, `Add_Time`, `Status` ) SELECT `ID`, `TransType`, `PayOrderID`, `ExtOrderID`, `CardNO`, `Flag`, `Message`, `Add_Time`, `Status` FROM payment WHERE ID='").append(paymentId).append("'");
			list.add(sql.toString());
			
			sql = new StringBuffer();
			sql.append("DELETE FROM payment WHERE `ID`='").append(paymentId).append("'"); 
			list.add(sql.toString());
			
			jdbc.batchExecute(list);
			
			return true;
		}
		catch (Exception e)
		{
			return false;
		}

	}
}
