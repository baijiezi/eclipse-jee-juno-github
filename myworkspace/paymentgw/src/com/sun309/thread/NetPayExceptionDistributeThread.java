package com.sun309.thread;
/**
 * @author Wormwood OPC_GROUP
 * 网络多支付异常分发线程
 */
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.sun309.dto.NetPayData;
import com.sun309.dto.NetPayDataException;
import com.sun309.factory.Factory;
import com.sun309.impl.NetPayDetailDataServiceImpl;
import com.sun309.impl.NetPayExceptionLogServiceImpl;
import com.sun309.service.LogService;
import com.sun309.service.NetPayDetailDataService;
import com.sun309.util.Constants;
import com.sun309.util.DateUtils;
import com.sun309.util.HttpURLConnectionRequest;
import com.sun309.util.NetPayFormatTools;
import com.sun309.util.NetPayXmlTools;

public class NetPayExceptionDistributeThread extends Thread
{
	/** 初始化LOG工具 */
	private LogService 		dblog 		= 	(LogService) Factory.create(NetPayExceptionLogServiceImpl.class);
	private NetPayXmlTools 	xml	 		= 	(NetPayXmlTools)Factory.create(NetPayXmlTools.class);
	private Logger 			syslog		= 	Logger.getLogger(NetPayExceptionDistributeThread.class);
	
	/** 详细数据操作类*/
	private NetPayDetailDataService detail = (NetPayDetailDataService) Factory.create(NetPayDetailDataServiceImpl.class);
	
	/** 构造函数 给线程命名 */
	public NetPayExceptionDistributeThread()
	{
		super("NetPayExceptionDistributeThread");
	}
	
	/** 日志方法 */
	private void writeLog(Object obj)
	{
		if(Constants.LOG_STATUS)
		{
			syslog.debug(obj);
		}
		if(Constants.DBLOG_STATUS)
		{
			dblog.debug(obj.toString());
		}
		obj=null;
	}
	
	/** 线程方法 */
	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				Thread.sleep(Constants.EXCEPTION_SLEEP_TIME);
				System.out.println("NetPayException DistributeThread Start...");
				this.doDistribte();
				dblog.write();
			}
			catch(Exception e)
			{
				dblog.write();
				System.out.println(e); 
			}
		}
	}
	
	/** 数据分发方法 */
	private void doDistribte()
	{
		try
		{
			/** 状态: 发现 Exception 表 有两天之内未分发数据  */
			ArrayList<NetPayDataException> exceptionList	= detail.findExceptionByCondition(new StringBuffer("`distributeStatus`='")
				.append(Constants.DISTRIBUTE_INIT).append("' AND `addTime`>'").append(DateUtils.getNowTime()-Constants.PROCESS_MAX_TIME).append("'").toString());
			if(exceptionList !=null && exceptionList.size() > 0)
				doExceptionDistribute(exceptionList);
			
			/** 状态: 发现 Warning 表 有有两天之内未分发成功数据  */
			ArrayList<NetPayData> warningList	= detail.findWarningByCondition(new StringBuffer("`distributeStatus`='")
				.append(Constants.DISTRIBUTE_FAIL).append("' AND `initTime`>'").append(DateUtils.getNowTime()-Constants.PROCESS_MAX_TIME).append("'").toString());
			if(warningList !=null && warningList.size() > 0)
				doWarningDistribute(warningList);
		}
		catch (Exception e)
		{
			writeLog("XXXXXXXXXXXXXXXXX Data Exception doDistribte Exception XXXXXXXXXXXXXXXX");
			e.printStackTrace();
		}
	}
	
	/** 状态: Warning 表 有未分发成功的数据
	 * 直接再次发送，直到成功为止 [超过最大发送次数直接置为发送失败]。
	 */
	private void doWarningDistribute(ArrayList<NetPayData> warningList)
	{
		for(NetPayData payData : warningList)
		{
			/** 如果分发成功 就更新状态 ，不成功不作处理 [超过最大发送次数不再发送]*/
			if(checkMaxTryTime(payData.getTryTimes()))
			{
				if(distribute(payData,Boolean.FALSE))
				{
					detail.updateWarningStatus(payData.getID(), "distributeTime", DateUtils.getNowTime());
					detail.updateWarningStatus(payData.getID(), "distributeStatus", Constants.DISTRIBUTE_SUCCEED);
				}
				else
				{
					detail.updateWarningStatus(payData.getID(), "distributeTime", DateUtils.getNowTime());
					detail.updateWarningStatus(payData.getID(), "TryTimes", new Integer(payData.getTryTimes()+1));
				}
			}
			else
			{
				/** 超过最大发送次数不再发送 不做任何处理 */
			}
		}
	}

	/** 状态: Exception 表 有未分发数据
	 * 只处理原来超时现在又接收到数据的记录。
	 * 更新超时表交易状态字段和实际支付流水字段，并再次分发。  
	 * 如果没有在超时表说明可能数据分发失败 。
	 * 再次查找警告记录表 如果交易状态是初始化或者超时更新他的交易状态字段和实际支付流水字段
	 */
	private void doExceptionDistribute(ArrayList<NetPayDataException> exceptionList)
	{
		for (NetPayDataException excepData : exceptionList)
		{
			NetPayData payData = detail.findExpireByID(excepData.getID());
			if(payData != null && (payData.getPayStatus().equals(new Integer(Constants.PAY_STATUS_INIT)) 
					|| payData.getPayStatus().equals(new Integer(Constants.PAY_STATUS__EXPIRE))))
			{
				payData.setPayStatus(excepData.getPayStatus());
				payData.setTranLineDetail(excepData.getTranLineDetail());
				payData.setTotalFee(excepData.getTotalFee());
				/** 如果分发成功 就更新状态 ，不成功不作处理 [超过最大发送次数直接置为发送失败]*/
				if(checkMaxTryTime(payData.getTryTimes()))
				{
					if(distribute(payData,Boolean.TRUE))
					{
						detail.updateExpireStatus(payData.getID(), "payStatus", payData.getPayStatus());
						detail.updateExpireStatus(payData.getID(), "tranLineDetail", payData.getTranLineDetail());
						detail.updateExpireStatus(payData.getID(), "totalFee", payData.getTotalFee());
						detail.updateExceptionStatus(payData.getID(), "distributeTime", DateUtils.getNowTime());
						detail.updateExceptionStatus(payData.getID(), "distributeStatus", Constants.DISTRIBUTE_SUCCEED);
					}
					else
					{
						detail.updateExpireStatus(payData.getID(), "TryTimes", new Integer(payData.getTryTimes()+1));
					}
				}
				else
				{
					detail.updateExceptionStatus(payData.getID(), "distributeStatus", Constants.DISTRIBUTE_FAIL);
				}
			}
			else
			{
				/** 如果没有在超时表说明可能数据分发失败  再次查找警告记录表 */
				payData = detail.findWarningByID(excepData.getID());
				if(payData != null && (payData.getPayStatus().equals(new Integer(Constants.PAY_STATUS_INIT)) 
						|| payData.getPayStatus().equals(new Integer(Constants.PAY_STATUS__EXPIRE))))
				{
					detail.updateWarningStatus(payData.getID(), "payStatus", payData.getPayStatus());
					detail.updateWarningStatus(payData.getID(), "tranLineDetail", payData.getTranLineDetail());
					detail.updateWarningStatus(payData.getID(), "totalFee", payData.getTotalFee());
				}
				else
				{
					/** 如果再次为空说明 不是真的异常 可能是重复接到的数据  直接把发送状态设置为成功 区别真实处理过的记录可以看分发时间 */
					detail.updateExceptionStatus(excepData.getID(), "distributeStatus", Constants.DISTRIBUTE_SUCCEED);
				}
			}
			
		}
	}
	
	/** 实际数据方法方法 */
	private boolean distribute(NetPayData payData,Boolean isException)
	{
		Hashtable<String, String> params = new Hashtable<String, String>();
		/** 生成POST XML数据 */
		String _xml = xml.createXml(payData).toString();
		String wareCode = payData.getWaresCode();
		writeLog(new StringBuffer("Exception Distribute 传出：").append(_xml)
				.append(" 商品代码:").append(wareCode).append(" 是否异常：").append(isException).toString());
		params.put("XML", _xml);
		String URL;
		/** 异否向异常地址发送数据 */
		if(isException)
		{
			URL = NetPayFormatTools.getNetPayExceptionDistribute(payData.getWaresCode());
		}
		else
		{
			URL = NetPayFormatTools.getNetPayDistribute(payData.getWaresCode());
		}
		writeLog(new StringBuilder("Exception Distribute 分发 URL：").append(URL));
		
		/** 调用发送数据方法 */
		Boolean resultPost = this.doRequest( URL, payData.getID(), params);
		params = null; _xml = null;
		return resultPost;
	}
	
	/** POST请求方法 **/
	private Boolean doRequest(String url,String ID,Hashtable<String, String> params)
	{ 
		try
		{   
			HttpURLConnectionRequest http = new HttpURLConnectionRequest();
			String postUrl = url;
			String result = http.doSendPostRequest(postUrl, params);
			Boolean isReceive = Boolean.FALSE;
			if(result.equals("SUCCEED"))
			{
				return Boolean.TRUE;
			}
			else if(result.length() != 0 && result.trim() != "" && result.length()>20)
			{
				result = URLDecoder.decode(result, "utf-8");
				isReceive = parseXml(result);
			}
			writeLog(new StringBuilder("Exception Distribute POST 结果数据到：").append(postUrl)
					.append("返回结果为：").append(result).append("数据为：")
					.append(params.get("XML")));
			postUrl = null; params = null; ID = null; result = null; url = null;
			return isReceive;
		}
		catch (Exception e) 
		{
			writeLog(new StringBuilder("expction:").append(e.getMessage()).append("cause:")
					.append(e.getCause()).append("stack:").append(e.getStackTrace()).append("class:").append(e.getClass()));
			return Boolean.FALSE;
		}
	}
	
	/** 检查是否超过了最大发送次数 */
	private Boolean checkMaxTryTime(Integer times)
	{
		if(times.intValue()>=Constants.DISTRIBUTE_TRY_MAX)
		{
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/** POST返回结果是XML时是处理XML数据 */
	protected Boolean parseXml(String param) 
	{
		if(param.contains("<error>0</error>") || param.contains("订单状态不是0,4") ||
				param.contains("订单已处理过"))
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
	}
	
	public static void main(String[] args)
	{
 		NetPayExceptionDistributeThread d = new NetPayExceptionDistributeThread();
		d.start();
	}
}