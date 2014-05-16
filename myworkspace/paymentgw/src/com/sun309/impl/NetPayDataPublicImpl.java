package com.sun309.impl;
/**
 * @author Wormwood OPC_GROUP
 * 网络多支付数据操作实现类 公用实现类
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun309.dto.NetPayData;
import com.sun309.factory.Factory;
import com.sun309.jdbc.JdbcDaoFactory;
import com.sun309.jdbc.JdbcDaoService;
import com.sun309.service.LogService;
import com.sun309.util.Constants;

public class NetPayDataPublicImpl
{
	private JdbcDaoService 	jdbc 	= 	JdbcDaoFactory.create();
	private Logger 			syslog 	= 	Logger.getLogger(NetPayDataPublicImpl.class);
	private LogService 		logger 	= 	(LogService)Factory.create(NetPayLogServiceImpl.class);
	
	/** 公用条件查询方法 有LOG*/
	public ArrayList<NetPayData> findByCondition(String condition,String tableName)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT `ID`, `payType`, `tranLineDetail`, `outOrderNo`, `totalFee`, `waresCode`, ")
		   .append("`checkKey`, `checkValue`, `payStatus`, `initTime`, `expireTime`, `receiveTime`, `distributeTime`")
		   .append(", `distributeStatus`, `receiveStatus`, `validateStatus`, `TryTimes`, `clientIP`, `returnURL` FROM ");
		sql.append(tableName);
		sql.append(jdbc.getSelectCondition(condition));
		List<Map<String, Object>> list = jdbc.select(sql.toString());
		writeLog(new StringBuilder("Public Select [tableName:").append(tableName).append(" condition:")
				.append(condition).append(" size:").append(list==null?"0":list.size()).append("]"));
		if (list == null || list.size() <= 0)
			return null;
		sql = null;
		ArrayList<NetPayData> netPayList = new ArrayList<NetPayData>();
		for (Map<String, Object> map : list)
		{
			NetPayData p = new NetPayData();
			p.setID(jdbc.ObjectToString(map, "ID"));
			p.setPayType(jdbc.ObjectToInteger(map, "payType"));
			p.setTranLineDetail(jdbc.ObjectToString(map, "tranLineDetail"));
			p.setOutOrderNo(jdbc.ObjectToString(map, "outOrderNo"));
			p.setTotalFee(jdbc.ObjectToLong(map, "totalFee"));
			p.setWaresCode(jdbc.ObjectToString(map, "waresCode"));
			p.setCheckKey(jdbc.ObjectToString(map, "checkKey"));
			p.setCheckValue(jdbc.ObjectToString(map, "checkValue"));
			p.setPayStatus(jdbc.ObjectToInteger(map, "payStatus"));
			p.setInitTime(jdbc.ObjectToLong(map, "initTime"));
			p.setExpireTime(jdbc.ObjectToLong(map, "expireTime"));
			p.setReceiveTime(jdbc.ObjectToLong(map, "receiveTime"));
			p.setDistributeTime(jdbc.ObjectToLong(map, "distributeTime"));
			p.setDistributeStatus(jdbc.ObjectToInteger(map, "distributeStatus"));
			p.setReceiveStatus(jdbc.ObjectToInteger(map, "receiveStatus"));
			p.setValidateStatus(jdbc.ObjectToInteger(map, "validateStatus"));
			p.setTryTimes(jdbc.ObjectToInteger(map, "TryTimes"));
			p.setClientIP(jdbc.ObjectToString(map, "clientIP"));
			p.setReturnURL(jdbc.ObjectToString(map, "returnURL"));
			netPayList.add(p);p=null;
		}
		return netPayList;
	}
	
	/** 公用条件查询方法 获得一条记录 或者 NULL 有LOG*/
	public NetPayData findByID(String ID,String tableName)
	{
		StringBuilder condition = new StringBuilder("`ID`='").append(ID).append("'");
		ArrayList<NetPayData> list = findByCondition(condition.toString(),tableName);
		if (list == null || list.size() <= 0)
			return null;
		condition = null;
		return list.get(0);
	}
	
	/** 公用按多支付ID更新方法  有LOG*/
	public boolean updateStatus(String id, String statusName, Object status, String tableName)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ").append(tableName).append(" SET `").append(statusName).append("`='").append(status).append("'")
		   .append(jdbc.getSelectCondition("`ID` ='")).append(id).append("'");
		writeLog(new StringBuilder("Public Update [tableName:").append(tableName).append(" ID:")
			.append(id).append(" statusName:").append(statusName).append(" value:").append(status).append("]"));
		return jdbc.update(sql.toString()) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}
	
	public boolean updateByID(String ID, String stringSet,String tableName)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ").append(tableName).append(" SET ").append(stringSet).append(" ")
		   .append(jdbc.getSelectCondition(new StringBuilder("`ID` ='").append(ID).toString())).append("'");
		return jdbc.update(sql.toString()) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}
	
	/** 私有LOG方法 用于记录 ID生成和数据添加 */
	private void writeLog(Object obj)
	{
		if(Constants.LOG_METHOD)
		{
			if(Constants.LOG_STATUS)
			{
				syslog.debug(obj);
			}
			if(Constants.DBLOG_STATUS)
			{
				logger.debug(obj.toString());
			}
		}
		obj=null;
	}
}
