/**
 * 多支付接收返回数据详细
 * 保存到数据库
 */
package com.sun309.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun309.dto.AliPayResult;
import com.sun309.dto.ChinaPayResult;
import com.sun309.dto.NetPayData;
import com.sun309.dto.NetPayDataException;
import com.sun309.dto.TenPayPackageResult;
import com.sun309.dto.TenPayQueryResult;
import com.sun309.dto.TenPayResult;
import com.sun309.factory.Factory;
import com.sun309.jdbc.JdbcDaoFactory;
import com.sun309.jdbc.JdbcDaoService;
import com.sun309.service.BaseReceive;
import com.sun309.service.LogService;
import com.sun309.service.NetPayDetailDataService;
import com.sun309.util.Constants;
import com.sun309.util.DateUtils;

/**
 * @author Wormwood OPC_GROUP
 *
 */
public class NetPayDetailDataServiceImpl implements NetPayDetailDataService
{
	private JdbcDaoService	jdbc	=	JdbcDaoFactory.create();
	private Logger 			syslog	=	Logger.getLogger(NetPayDetailDataServiceImpl.class);
	private LogService 		logger	=	(LogService)Factory.create(NetPayLogServiceImpl.class);
	private NetPayDataPublicImpl publicImpl = (NetPayDataPublicImpl)Factory.create(NetPayDataPublicImpl.class);
	
	/**　插入支付宝接收详细信息结果 */
	public boolean insert(AliPayResult result)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ali_pay_receive_detail (`notify_type`, `notify_id`, `notify_time`, `sign`, `sign_type`")
		   .append(", `trade_no`, `out_trade_no`, `subject`, `price`, `quantity`, `use_coupon`, `is_total_fee_adjust`")
		   .append(", `trade_status`, `seller_email`, `seller_id`, `buyer_email`, `buyer_id`, `body`, `gmt_create`")
		   .append(", `total_fee`, `gmt_payment`, `gmt_close`, `discount`, `paymentType`, `add_time`")
		   .append(") VALUES ('").append(result.getNotifyType()).append("','")
		   .append(result.getNotifyId()).append("','")
		   .append(result.getNotifyTime()).append("','")
		   .append(result.getSign()).append("','")
		   .append(result.getSignType()).append("','")
		   .append(result.getTradeNo()).append("','")
		   .append(result.getOutTradeNo()).append("','")
		   .append(result.getSubject()).append("','")
		   .append(result.getPrice()).append("','")
		   .append(result.getQuatity()).append("','")
		   .append(result.getUseCoupon()).append("','")
		   .append(result.getIsTotalFeeAjust()).append("','")
		   .append(result.getTradeStatus()).append("','")
		   .append(result.getSellerEmail()).append("','")
		   .append(result.getSellerId()).append("','")
		   .append(result.getBuyerEmail()).append("','")
		   .append(result.getBuyerId()).append("','")
		   .append(result.getBody()).append("','")
		   .append(result.getGmtCreateTime()).append("','")
		   .append(result.getTotalFee()).append("','")
		   .append(result.getGmtPaymentTime()).append("','")
		   .append(result.getGmtClose()).append("','")
		   .append(result.getDiscount()).append("','")
		   .append(result.getPaymentType()).append("','")
		   .append(DateUtils.getNowTime()).append("')");
		writeLog(new StringBuilder("AliPayDetail:").append(sql.toString()));
		long res = jdbc.insert(sql.toString());
		return res > 0 ? Boolean.TRUE : Boolean.FALSE;
		//sql.append(result.getGmtSendGoodsTime()).append("','");
		//sql.append(result.getGmtRefund()).append("','");
	}
	
	/**　插入中国银联接收详细信息结果 */
	public boolean insert(ChinaPayResult result)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO china_pay_receive_detail (`trans_date`, `mer_id`, `order_id`, `out_trade_no`, `trans_type`, `trans_amt`")
		   .append(", `cury_id`, `chk_value`, `order_status`, `gate_id`, `add_time`")
		   .append(" ) VALUES ('").append(result.getTransDate()).append("','")
		   .append(result.getMerId()).append("','")
		   .append(result.getOrderId()).append("','")
		   .append(result.getOutTradeNo()).append("','")
		   .append(result.getTransType()).append("','")
		   .append(result.getTransAmt()).append("','")
		   .append(result.getCuryId()).append("','")
		   .append(result.getChkValue()).append("','")
		   .append(result.getOrderStatus()).append("','")
		   .append(result.getGateId()).append("','")
		   .append(DateUtils.getNowTime()).append("')");
		writeLog(new StringBuilder("ChinaPayDetail:").append(sql.toString()));
		return jdbc.insert(sql.toString()) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}
	
	/**　插入财付通接收详细信息结果 */
	public boolean insert(TenPayResult result)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ten_pay_receive_detail (`cmdno`, `pay_result`, `date`, `transaction_id`, `sp_billno`, `total_fee`")
		   .append(", `fee_type`, `attach`, `sign`, `bargainor_id`, `add_time`")
		   .append(" ) VALUES ('").append(result.getCmdNo()).append("','")
		   .append(result.getPayResult()).append("','")
		   .append(result.getDate()).append("','")
		   .append(result.getTransactionId()).append("','")
		   .append(result.getSpBillNo()).append("','")
		   .append(result.getTotalFee()).append("','")
		   .append(result.getFeeType()).append("','")
		   .append(result.getAttach()).append("','")
		   .append(result.getSign()).append("','")
		   .append(result.getBargainorID()).append("','")
		   .append(DateUtils.getNowTime()).append("')");
		writeLog(new StringBuilder("TenPayDetail:").append(sql.toString()));
		return jdbc.insert(sql.toString()) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	/**　插入接收详细信息结果 */
	public boolean insert(BaseReceive result ,int type)
	{
		// TODO Auto-generated method stub
		if(type == Constants.ALI_PAY)
			return insert((AliPayResult)result);
		if(type == Constants.CHINA_PAY)
			return insert((ChinaPayResult)result);
		if(type == Constants.TEN_PAY)
			return insert((TenPayResult)result);
		if(type == Constants.TENPAYPACKAGE_PAY)
			return insert((TenPayPackageResult)result);
		if(type == Constants.TEN_PAY_QUERY)
			return insert((TenPayQueryResult)result);
		return Boolean.FALSE;
	}
	
	private boolean insert(TenPayPackageResult result)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ten_pay_package_receive_detail (`bank_type`, `trade_mode`, `discount`, `input_charset`, `total_fee`, `transport_fee`")
		   .append(" ,`notify_id`, `transaction_id`, `sign`, `partner`, `product_fee`, `time_end` , `out_trade_no`, `sign_type`, `fee_type`")
		   .append(", `trade_state`, `add_time`) VALUES ('")
		   .append(result.getBankType()).append("','")
		   .append(result.getTradeMode()).append("','")
		   .append(result.getDiscount()).append("','")
		   .append(result.getInputCharset()).append("','")
		   .append(result.getTotalFee()).append("','")
		   .append(result.getTransportFee()).append("','")
		   .append(result.getNotifyId()).append("','")
		   .append(result.getTransactionId()).append("','")
		   .append(result.getSign()).append("','")
		   .append(result.getPartner()).append("','")
		   .append(result.getProductFee()).append("','")
		   .append(result.getTimeEnd()).append("','")
		   .append(result.getOutTradeNo()).append("','")
		   .append(result.getSignType()).append("','")
		   .append(result.getFeeType()).append("','")
		   .append(result.getTradeState()).append("','")
		   .append(DateUtils.getNowTime()).append("')");
		logger.debug(new StringBuilder("TenPayPackageDetail:").append(sql.toString()).toString());
		return jdbc.insert(sql.toString()) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	/**　插入财付通查询结果 */
	private boolean insert(TenPayQueryResult result) 
	{
		/** 未启用的方法 财付通有返回数据不需要再次查询 */
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ten_pay_receive_query_detail (`cmdno`, `pay_result`, `date`, `transaction_id`, `sp_billno`, `total_fee`")
		   .append(", `fee_type`, `attach`, `sign`, `bargainor_id`, `retmsg`, `retcode`, `return_url`, `charset`, `output_xml`, `pay_info`, `add_time`")
		   .append(" ) VALUES ('").append(result.getCmdNo()).append("','")
		   .append(result.getPayResult()).append("','")
		   .append(result.getDate()).append("','")
		   .append(result.getTransactionId()).append("','")
		   .append(result.getSpBillNo()).append("','")
		   .append(result.getTotalFee()).append("','")
		   .append(result.getFeeType()).append("','")
		   .append(result.getAttach()).append("','")
		   .append(result.getSign()).append("','")
		   .append(result.getBargainorID()).append("','")
		   .append(result.getRetMsg()).append("','")
		   .append(result.getRetCode()).append("','")
		   .append(result.getReturnURL()).append("','")
		   .append(result.getCharset()).append("','")
		   .append(result.getOutputXml()).append("','")
		   .append(result.getPayInfo()).append("','")
		   .append(DateUtils.getNowTime()).append("')");
		writeLog(new StringBuilder("TenPayQueryDetail:").append(sql.toString()));
		return jdbc.insert(sql.toString()) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	/** 处理异常数据 **/
	public boolean insertException(BaseReceive result)
	{
		writeLog("XXXXXXXXXXXXXX insert Data Exception XXXXXXXXXXXXXX");
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO net_pay_data_exception (`ID`, `tranLineDetail`, `totalFee`, `payType`, `payStatus`,")
			.append(" `distributeStatus`, `distributeTime`, `addTime`) VALUES ('")
			.append(result.getOutTradeNo()).append("','")
			.append(result.getTradeNo()).append("','")
			.append(result.getTotalFee()).append("','")
			.append(result.getRealPayType()).append("','")
			.append(result.validateTradeStatus()?Constants.PAY_STATUS_SUCCEED:Constants.PAY_STATUS_FAIL).append("','")
			.append(Constants.DISTRIBUTE_INIT).append("','")
			.append(Constants.TIME_INIT).append("','")
			.append(DateUtils.getNowTime()).append("')");
		writeLog(new StringBuilder("NetPayDataException:").append(sql.toString()));
		return jdbc.insert(sql.toString()) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}
	
	/** 按条件查找 data_exception 表 */
	public ArrayList<NetPayDataException> findExceptionByCondition(String condition)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT `ID`, `payType`, `tranLineDetail`, `totalFee`, `payStatus`,")
		   .append(" `distributeStatus`, `distributeTime`, `addTime` FROM net_pay_data_exception");
		sql.append(jdbc.getSelectCondition(condition));
		List<Map<String, Object>> list = jdbc.select(sql.toString());
		
		writeLog(new StringBuilder("Detail Select [tableName:").append("net_pay_data_exception").append(" condition:")
			.append(condition).append(" size:").append(list==null?"0":list.size()).append("]"));
		
		if (list == null || list.size() <= 0)
			return null;
		sql = null;
		ArrayList<NetPayDataException> netPayList = new ArrayList<NetPayDataException>();
		for (Map<String, Object> map : list)
		{
			NetPayDataException p = new NetPayDataException();
			p.setID(jdbc.ObjectToString(map, "ID"));
			p.setPayType(jdbc.ObjectToInteger(map, "payType"));
			p.setTranLineDetail(jdbc.ObjectToString(map, "tranLineDetail"));
			p.setTotalFee(jdbc.ObjectToLong(map, "totalFee"));
			p.setPayStatus(jdbc.ObjectToInteger(map, "payStatus"));
			p.setDistributeTime(jdbc.ObjectToLong(map, "distributeTime"));
			p.setDistributeStatus(jdbc.ObjectToInteger(map, "distributeStatus"));
			p.setDistributeTime(jdbc.ObjectToLong(map, "addTime"));
			netPayList.add(p);p=null;
		}
		return netPayList;
	}
	
	/** 私有日志方法 不负责写入DB */
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
	
	/** 按ID条件查询 超时表				*/
	public NetPayData findExpireByID(String ID)
	{
		return publicImpl.findByID(ID, "net_pay_data_expire");
	}
	
	/** 按条件查询 警告表 					*/
	public ArrayList<NetPayData> findWarningByCondition(String condition)
	{
		return publicImpl.findByCondition(condition, "net_pay_data_warning");
	}
	
	/** 按ID条件查询 警告表				*/
	public NetPayData findWarningByID(String ID)
	{
		return publicImpl.findByID(ID, "net_pay_data_warning");
	}
	/** 按ID更新异常记录表 [单条]	*/
	public boolean updateExceptionStatus(String ID, String statusName, Object status)
	{
		return publicImpl.updateStatus(ID, statusName, status, "net_pay_data_exception");
	}
	/** 按ID更新超时记录表 [单条]	*/
	public boolean updateExpireStatus(String ID, String statusName,	Object status)
	{
		return publicImpl.updateStatus(ID, statusName, status, "net_pay_data_expire");
	}
	/** 按ID更新警告记录表 [单条]	*/
	public boolean updateWarningStatus(String ID, String statusName, Object status)
	{
		return publicImpl.updateStatus(ID, statusName, status, "net_pay_data_warning");
	}
}
