package com.sun309.impl;
/**
 * @author Wormwood OPC_GROUP
 * 网络多支付数据操作实现类
 */
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun309.dto.NetPayData;
import com.sun309.dto.NetPayDataParams;
import com.sun309.factory.Factory;
import com.sun309.jdbc.JdbcDaoFactory;
import com.sun309.jdbc.JdbcDaoService;
import com.sun309.service.IdService;
import com.sun309.service.LogService;
import com.sun309.service.NetPayDataService;
import com.sun309.util.Constants;
import com.sun309.util.DateUtils;

public class NetPayDataServiceImpl implements NetPayDataService
{
	private JdbcDaoService 	jdbc 	= 	JdbcDaoFactory.create();
	private Logger 			syslog 	= 	Logger.getLogger(NetPayDataServiceImpl.class);
	private LogService 		logger 	= 	(LogService)Factory.create(NetPayLogServiceImpl.class);
	private NetPayDataPublicImpl publicImpl = (NetPayDataPublicImpl)Factory.create(NetPayDataPublicImpl.class);
	
	/** 条件查询方法 无LOG*/
	public ArrayList<NetPayData> findByCondition(String condition)
	{
		return publicImpl.findByCondition(condition, "net_pay_data");
	}
	
	/** 数据按ID查找 无LOG*/
	public NetPayData findByTradeID(String tradeID)
	{
		return publicImpl.findByID(tradeID, "net_pay_data");
	}
	
	/** 数据添加方法 有LOG*/
	public String insert(NetPayData netpay)
	{
		IdService id = (IdService) Factory.create(NetPayIdServiceImpl.class);
		String _id = id.createId();
		writeLog(_id);
		if(_id == null ) return "FALSE";
		id = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO net_pay_data (`ID`, `payType`, `tranLineDetail`, `outOrderNo`, `totalFee`, `waresCode`, ")
		   .append("`checkKey`, `checkValue`, `payStatus`, `initTime`, `expireTime`, `receiveTime`, `distributeTime`")
		   .append(", `distributeStatus`, `receiveStatus`, `validateStatus`, `TryTimes`, `clientIP`, `returnURL` ) VALUES ('")
		   .append(_id).append("','")
		   .append(netpay.getPayType()).append("','")
		   .append(netpay.getOutOrderNo()).append("','")
		   .append(netpay.getOutOrderNo()).append("','")
		   .append(netpay.getTotalFee()==null?0:netpay.getTotalFee()).append("','")
		   .append(netpay.getWaresCode()).append("','")
		   .append(netpay.getCheckKey()).append("','")
		   .append(netpay.getOutOrderNo()).append("','")
		   .append(Constants.PAY_STATUS_INIT).append("','")
		   .append(DateUtils.getNowTime()).append("','")
		   .append(netpay.getExpireTime()).append("','")
		   .append(Constants.TIME_INIT).append("','")
		   .append(Constants.TIME_INIT).append("','")
		   .append(Constants.DISTRIBUTE_INIT).append("','")
		   .append(Constants.RECEIVE_INIT).append("','")
		   .append(Constants.VALIDATE_INIT).append("','")
		   .append(Constants.DISTRIBUTE_TRY_TIME).append("','")
		   .append(netpay.getClientIP()).append("','")
		   .append(netpay.getReturnURL()).append("')");
		writeLog(new StringBuilder("NetPayData:").append(sql.toString()));
		Boolean status = jdbc.insert(sql.toString()) > 0 ? Boolean.TRUE : Boolean.FALSE;
		if(status == Boolean.TRUE)
			return _id;
		return "FALSE";
	}
	
	/** 添加网络多支付 参数信息 有LOG */
	public boolean insertParams(NetPayDataParams params)
	{
		try
		{
			writeLog("XXXXXXXXXXXXXX insert Params XXXXXXXXXXXXXX");
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO net_pay_data_async (`ID`, `inputParams`, `outputParams`) VALUES ('")
			   .append(params.getID()).append("','")
			   .append(URLEncoder.encode(params.getInputParams(), "utf-8")).append("','")
			   .append(URLEncoder.encode(params.getOutputParams(), "utf-8")).append("')");
			writeLog(new StringBuilder("insert Params:").append(sql.toString()));
			return jdbc.insert(sql.toString()) > 0 ? Boolean.TRUE : Boolean.FALSE;
		}
		catch(Exception e)
		{
			return Boolean.FALSE;
		}
	}
	
	/** 数据按ID查找 无LOG*/
	public NetPayDataParams findParamsByTradeID(String tradeID)
	{
		try
		{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT `ID`, `inputParams`, `outputParams` FROM net_pay_data_async")
				.append(" WHERE `ID` ='").append(tradeID).append("'");
			List<Map<String, Object>> list = jdbc.select(sql.toString());
			if (list == null || list.size() <= 0)
				return null;
			sql = null;
			ArrayList<NetPayDataParams> netPayList = new ArrayList<NetPayDataParams>();
			for (Map<String, Object> map : list)
			{
				NetPayDataParams p = new NetPayDataParams();
				p.setID(jdbc.ObjectToString(map, "ID"));
				p.setInputParams(URLDecoder.decode(jdbc.ObjectToString(map, "inputParams"), "utf-8"));
				p.setOutputParams(URLDecoder.decode(jdbc.ObjectToString(map, "outputParams"), "utf-8"));
				netPayList.add(p);p=null;
			}
			return netPayList.get(0);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	/** 插入金额异常数据 */
	public boolean insertFeeException(NetPayData netpay)
	{
		writeLog("XXXXXXXXXXXXXX insertFeeException XXXXXXXXXXXXXX");
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO net_pay_data_fee_exception (`ID`, `payType`, `tranLineDetail`, `outOrderNo`, `totalFee`, `waresCode`, ")
		   .append("`checkKey`, `checkValue`, `payStatus`, `initTime`, `expireTime`, `receiveTime`, `distributeTime`")
		   .append(", `distributeStatus`, `receiveStatus`, `validateStatus`, `TryTimes`, `clientIP`, `returnURL` ) VALUES ('")
		   .append(netpay.getID()).append("','")
		   .append(netpay.getPayType()).append("','")
		   .append(netpay.getTranLineDetail()).append("','")
		   .append(netpay.getOutOrderNo()).append("','")
		   .append(netpay.getTotalFee()).append("','")
		   .append(netpay.getWaresCode()).append("','")
		   .append(netpay.getCheckKey()).append("','")
		   .append(netpay.getCheckValue()).append("','")
		   .append(netpay.getPayStatus()).append("','")
		   .append(netpay.getInitTime()).append("','")
		   .append(netpay.getExpireTime()).append("','")
		   .append(netpay.getReceiveTime()).append("','")
		   .append(netpay.getDistributeTime()).append("','")
		   .append(netpay.getDistributeStatus()).append("','")
		   .append(netpay.getReceiveStatus()).append("','")
		   .append(netpay.getValidateStatus()).append("','")
		   .append(netpay.getTryTimes()).append("','")
		   .append(netpay.getClientIP()).append("','")
		   .append(netpay.getReturnURL()).append("')");
		writeLog(new StringBuilder("NetPayDataException:").append(sql.toString()));
		return jdbc.insert(sql.toString()) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}
	
	/** 按ID状态更新方法[单字段] 无LOG*/
	public boolean updateStatus(String id, String statusName, Object status)
	{
		return publicImpl.updateStatus(id, statusName, status, "net_pay_data");
	}
	
	/** 按ID状态更新方法[单字段] 无LOG*/
	public boolean updateParams(String id, String statusName, String status)
	{
		try
		{
			return publicImpl.updateStatus(id, statusName, URLEncoder.encode(status, "utf-8"), "net_pay_data_async");
		}
		catch(Exception e)
		{
			return Boolean.FALSE;
		}
		
	}
	
	/**	按ID多字段更新方法 无LOG*/
	public boolean updateByID(String ID, String stringSet)
	{
		return publicImpl.updateByID(ID, stringSet, "net_pay_data");
	}
	
	/** 条件数据转移方法	无LOG*/
	public boolean moveToLog(ArrayList<NetPayData> datalist , String tableName)
	{
		ArrayList<String> list = new ArrayList<String> ();
		try
		{
			for(NetPayData netpay:datalist)
			{	
				StringBuffer sql = new StringBuffer();
				sql.append("INSERT INTO ").append(tableName).append(" (`ID`, `payType`, `tranLineDetail`, `outOrderNo`, `totalFee`, `waresCode`, ")
				   .append("`checkKey`, `checkValue`, `payStatus`, `initTime`, `expireTime`, `receiveTime`, `distributeTime`,")
				   .append(" `distributeStatus`, `receiveStatus`, `validateStatus`, `TryTimes`, `clientIP`, `returnURL` ) " )
				   .append(" SELECT `ID`, `payType`, `tranLineDetail`, `outOrderNo`, `totalFee`, `waresCode`, ")
				   .append("`checkKey`, `checkValue`, `payStatus`, `initTime`, `expireTime`, `receiveTime`, `distributeTime`,")
				   .append(" `distributeStatus`, `receiveStatus`, `validateStatus`, `TryTimes` , `clientIP`, `returnURL` FROM net_pay_data")
				   .append(" WHERE `ID`='").append(netpay.getID()).append("'");
				list.add(sql.toString());
				
				sql = new StringBuffer();
				sql.append("DELETE FROM net_pay_data WHERE `ID`='").append(netpay.getID()).append("'");
				list.add(sql.toString());
				
				sql=null;
			}
			jdbc.batchExecute(list);
			list = null;
			return true;
		}
		catch (Exception e)
		{
			list = null;
			return false;
		}

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
