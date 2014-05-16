package com.sun309.impl;
/**
 * @author WormwoodLeaf OPC_GROUP
 * POST数据接收基础数据操作类
 */
import org.apache.log4j.Logger;

import com.sun309.dto.NetPayData;
import com.sun309.factory.Factory;
import com.sun309.service.BaseReceive;
import com.sun309.service.LogService;
import com.sun309.service.NetPayDataService;
import com.sun309.service.NetPayDetailDataService;
import com.sun309.util.Constants;
import com.sun309.util.DateUtils;

public class BaseResultProcess 
{
	private NetPayDataService		service		=	(NetPayDataService)Factory.create(NetPayDataServiceImpl.class);
	private NetPayDetailDataService dataDetail	=	(NetPayDetailDataService)Factory.create(NetPayDetailDataServiceImpl.class);
	private LogService 				logger 		=	(LogService)Factory.create(NetPayLogServiceImpl.class);
	private Logger 					syslog 		=	Logger.getLogger(BaseResultProcess.class);
	
	/** 接收详细数据的保存方法 */
	public void processSaveDetail(BaseReceive result,int type)
	{
		boolean res = dataDetail.insert(result, type);
		if(res == Boolean.FALSE)
			writeLog(new StringBuilder("保存数据到支付详细信息失败  多支付流水号：").append(result.getOutTradeNo()));
		else
			writeLog(new StringBuilder("保存数据到支付详细信息成功  多支付流水号：").append(result.getOutTradeNo()));
	}
	
	/** 数据真实性验证成功后按实际支付系统外部订单号[多支付流水号] 查找 这个订单的初始化交易记录*/
	public NetPayData processAfterValidate(BaseReceive result)
	{
		return service.findByTradeID(result.getOutTradeNo());
	}
	
	/** 处理异常数据：正确接收但是已超时的数据,记录到Exception表 */
	public void processExceptionData(BaseReceive result)
	{
		boolean res = dataDetail.insertException(result);
		if(res == Boolean.FALSE)
			writeLog(new StringBuilder("导常数据保存失败  多支付流水号：").append(result.getOutTradeNo()));
		writeLog(new StringBuilder("导常数据保存成功  多支付流水号：").append(result.getOutTradeNo()));
	}
	
	/** 交易状态验证成功	  按成功处理 多支付交易流水记录 */
	public void processValidateSuccess(BaseReceive result,NetPayData data)
	{
		/** 交易状态验证成功	 */
		service.updateStatus(data.getID(),"validateStatus",Constants.VALIDATE_SUCCEED);
		writeLog(new StringBuilder("按实际支付系统外部订单号[多支付流水号]更新验证状态为SUCCEED,多支付流水号：")
			.append(data.getID()));
		if(result.getTotalFee().equals(data.getTotalFee()))
		{
			/** 总金额相同 成功交易 通知外部业务系统 */
			service.updateStatus(data.getID(), "payStatus", Constants.PAY_STATUS_SUCCEED);
			service.updateStatus(data.getID(), "tranLineDetail", result.getTradeNo());
			writeLog(new StringBuilder("金额与原始外部订单相等  成功的多支付流水号：").append(data.getID())
				.append("业务系统外部订单号:").append(data.getOutOrderNo()));
		}
		else
		{
			/** 因为总金额 不相同而失败的 最后一条交易流水 用于通知外部业务系统 */
			service.updateStatus(data.getID(), "payStatus", Constants.PAY_STATUS_FAIL);
			service.updateStatus(data.getID(), "tranLineDetail", result.getTradeNo());
			writeLog(new StringBuilder("金额与原始外部订单不相等  失败的多支付流水号：").append(data.getID())
				.append("业务系统外部订单号:").append(data.getOutOrderNo()));
			
			/** 保存金额异常记录到Fee_Exception表 */
			data.setPayStatus(new Integer(Constants.PAY_STATUS_FAIL));
			data.setValidateStatus(new Integer(Constants.VALIDATE_SUCCEED));
			data.setReceiveStatus(new Integer(Constants.RECEIVE_SUCCEED));
			data.setTranLineDetail(result.getTradeNo());
			boolean res = service.insertFeeException(data);
			if(res == Boolean.FALSE)
				writeLog(new StringBuilder("金额与原始外部订单不相等  保存数据到Fee_Exception异常表 失败！多支付流水号：")
					.append(data.getID()));
			writeLog(new StringBuilder("金额与原始外部订单不相等  保存数据到Fee_Exception异常表成功！多支付流水号：")
					.append(data.getID()));
		}
		/** 最后更新接收状态准备分发数据 */
		processReady(data);
	}
	
	/** 交易状态验证失败	 按失败处理 多支付交易流水记录 */
	public void processValidateFail(BaseReceive result,NetPayData data)
	{
		/** 接到的交易状态为失败	 */
		service.updateStatus(data.getID(),"validateStatus",Constants.VALIDATE_FAIL);
		writeLog(new StringBuilder("按实际支付系统外部订单号[多支付流水号]更新验证状态为FAIL,多支付流水号：")
			.append(data.getID()));
		
		/** 接到的交易状态为失败 通知外部业务系统 */
		service.updateStatus(data.getID(), "payStatus", Constants.PAY_STATUS_FAIL);
		service.updateStatus(data.getID(), "tranLineDetail", result.getTradeNo());
		writeLog(new StringBuilder("交易状态为失败的多支付流水号：").append(data.getID())
			.append("业务系统外部订单号:").append(data.getOutOrderNo()));
		
		/** 最后更新接收状态准备分发数据 */
		processReady(data);
	}
	
	/** 处理交易记录 为可分发状态 接收状态设置为成功*/
	private void processReady(NetPayData data)
	{
		/** 最后更新接收状态准备分发数据 */
		service.updateStatus(data.getID(), "receiveTime", DateUtils.getNowTime());
		boolean res = service.updateStatus(data.getID(), "receiveStatus", Constants.RECEIVE_SUCCEED);
		if(res == Boolean.FALSE)
			writeLog(new StringBuilder("进入分发队列失败异常！ 多支付流水号：").append(data.getID()));
	}
	
	/** 私有日志方法 不负责写入DB */
	private void writeLog(Object obj)
	{
		if(Constants.LOG_STATUS == Boolean.TRUE)
		{
			syslog.debug(obj);
		}
		if(Constants.DBLOG_STATUS == Boolean.TRUE)
		{
			logger.debug(obj.toString());
		}
		obj=null;
	}
}
