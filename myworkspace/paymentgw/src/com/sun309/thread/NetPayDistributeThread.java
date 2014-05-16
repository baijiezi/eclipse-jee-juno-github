package com.sun309.thread;
/**
 * @author Wormwood OPC_GROUP
 * 网络多支付分发线程
 */
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.sun309.dto.NetPayData;
import com.sun309.factory.Factory;
import com.sun309.impl.NetPayDataServiceImpl;
import com.sun309.impl.NetPayLogServiceImpl;
import com.sun309.service.LogService;
import com.sun309.service.NetPayDataService;
import com.sun309.util.Constants;
import com.sun309.util.DateUtils;
import com.sun309.util.HttpURLConnectionRequest;
import com.sun309.util.NetPayFormatTools;
import com.sun309.util.NetPayXmlTools;

public class NetPayDistributeThread extends Thread
{
	/** 初始化LOG工具 */
	private LogService 		dblog 		= 	(LogService) Factory.create(NetPayLogServiceImpl.class);
	private NetPayXmlTools 	xml	 		= 	(NetPayXmlTools)Factory.create(NetPayXmlTools.class);
	private Logger 			syslog		= 	Logger.getLogger(NetPayDistributeThread.class);
	
	/** 数据操作类*/
	private NetPayDataService ps = (NetPayDataService) Factory.create(NetPayDataServiceImpl.class);
	
	/** 构造函数 给线程命名 */
	public NetPayDistributeThread()
	{
		super("NetPayDistributeThread");
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
				Thread.sleep(Constants.SLEEP_TIME);
				System.out.println("NetPay DistributeThread Start...");
				this.doDistribte();
				this.doMove();
				dblog.write();
			}
			catch(Exception e)
			{
				dblog.write();
				System.out.println(e); 
			}
		}
	}
	
	/** 数据转移方法 */
	private void doMove()
	{
		try
		{
			/** 交易成功 发送成功 进成功记录表 	*/
			ArrayList<NetPayData> succeedList = ps.findByCondition(new StringBuilder("`distributeStatus`='").append(Constants.DISTRIBUTE_SUCCEED)
					.append("' AND `payStatus`='").append(Constants.PAY_STATUS_SUCCEED).append("'").toString());
			if(succeedList !=null && succeedList.size() > 0)
				moveDataToLog(succeedList,Constants.PAY_STATUS_SUCCEED);
			succeedList = null;
			/** 交易失败 发送成功 进失败记录表 	*/
			ArrayList<NetPayData> failList = ps.findByCondition(new StringBuilder("`distributeStatus`='").append(Constants.DISTRIBUTE_SUCCEED)
					.append("' AND `payStatus`='").append(Constants.PAY_STATUS_FAIL).append("'").toString());
			if(failList !=null && failList.size() > 0)
				moveDataToLog(failList,Constants.PAY_STATUS_FAIL);
			failList = null;
			/** 交易超时 发送成功 进超时记录表 	*/
			ArrayList<NetPayData> expireList = ps.findByCondition(new StringBuilder("`distributeStatus`='").append(Constants.DISTRIBUTE_SUCCEED)
					.append("' AND `payStatus`='").append(Constants.PAY_STATUS__EXPIRE).append("'").toString());
			if(expireList !=null && expireList.size() > 0)
				moveDataToLog(expireList,Constants.PAY_STATUS__EXPIRE);
			expireList = null;
			/** 发送失败 进警告表				*/
			ArrayList<NetPayData> warningList = ps.findByCondition(new StringBuilder("`distributeStatus`='").append(Constants.DISTRIBUTE_FAIL).append("'").toString());
			if(warningList !=null && warningList.size() > 0)
				moveDataToLog(warningList,Constants.DISTRIBUTE_FAIL);
			warningList = null;
			/** 生成失败的记录 进LOG表 			*/
			ArrayList<NetPayData> initFailList = ps.findByCondition(new StringBuilder("`receiveStatus`='").append(Constants.RECEIVE_INIT)
					.append("' AND (`initTime`+`expireTime`) <='").append(DateUtils.getNowTime()).append("'").toString());
			if(initFailList !=null && initFailList.size() > 0)
				moveDataToLog(initFailList,Constants.RECEIVE_INIT);
			initFailList = null;
		}
		catch(Exception e)
		{
			writeLog("XXXXXXXXXXXXXXXXX Data doMove Exception XXXXXXXXXXXXXXXX");
			e.printStackTrace();
		}
	}
	
	/** 数据分发方法 */
	private void doDistribte()
	{
		try
		{
			/** 状态: 接收成功未发送 	*/
			ArrayList<NetPayData> succeedList	= ps.findByCondition(new StringBuffer("`receiveStatus`='").append(Constants.RECEIVE_SUCCEED)
					.append("' AND `distributeStatus`='").append(Constants.DISTRIBUTE_INIT).append("'").toString());
			if(succeedList !=null && succeedList.size() > 0)
				doReceiveSucceedDistribute(succeedList);
			
			/** 状态: 接收超时[接状态不考虑]	*/
			ArrayList<NetPayData> expireList	= ps.findByCondition(new StringBuffer("`distributeStatus`='")
					.append(Constants.DISTRIBUTE_INIT).append("' AND (`initTime`+`expireTime`) <=").append(DateUtils.getNowTime()).toString());
			if(expireList !=null && expireList.size() > 0)
				doReceiveExpireDistribute(expireList);
		}
		catch (Exception e)
		{
			writeLog("XXXXXXXXXXXXXXXXX Data doDistribte Exception XXXXXXXXXXXXXXXX");
			e.printStackTrace();
		}
	}

	/** 状态: 接收成功未发送 */
	private void doReceiveSucceedDistribute(ArrayList<NetPayData> succeedList)
	{
		for (NetPayData payData : succeedList)
		{
			if(checkTryNumbers(payData))
			{
				Boolean resultPost = distribute(payData);
				updateStatus(resultPost,payData);
			}
		}
	}
	
	/** 状态: 等待接收超时 	*/
	private void doReceiveExpireDistribute(ArrayList<NetPayData> expireList)
	{
		for (NetPayData payData : expireList)
		{
			if(checkTryNumbers(payData))
			{
				if(payData.getPayStatus().equals(Constants.PAY_STATUS_INIT))
				{
					ps.updateStatus(payData.getID(), "payStatus", Constants.PAY_STATUS__EXPIRE);
				}
				Boolean resultPost = distribute(payData);
				updateStatus(resultPost,payData);
			}
		}
	}
	
	/** 实际数据方法方法 */
	private boolean distribute(NetPayData payData)
	{
		Hashtable<String, String> params = new Hashtable<String, String>();
		/** 生成POST XML数据 */
		String _xml = xml.createXml(payData).toString();
		params.put("XML", _xml);
		Boolean resultPost = Boolean.FALSE;
		/** 调用发送数据方法 */
		try
		{
			String wareCode = payData.getWaresCode();
			writeLog(new StringBuffer("Data Distribute 传出：").append(_xml)
					.append(" 商品代码:").append(wareCode).toString());
			String url = NetPayFormatTools.getNetPayDistribute(wareCode);
			writeLog(new StringBuilder("Data Distribute 分发 URL：").append(url));
			
			resultPost = this.doRequest(url, payData.getID(), params);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		params = null; _xml = null;
		return resultPost;
	}
	
	/** 发送结果状态更新方法 **/
	private void updateStatus(boolean resultPost,NetPayData payData)
	{
		if(resultPost == Boolean.TRUE)
		{
			/** 发送成功则 标记发状态为成功 并记录发送时间 */
			ps.updateByID(payData.getID(), new StringBuilder("`distributeStatus`='").append(Constants.DISTRIBUTE_SUCCEED)
					.append("' ,`distributeTime`='").append(DateUtils.getNowTime()).append("'").toString());
		}
		else
		{
			/** 发送不成功则 记录最近一次发送时间 并把次数减 1 */
			ps.updateByID(payData.getID(), new StringBuilder("`distributeTime`='").append(DateUtils.getNowTime())
					.append("' ,`TryTimes`=`TryTimes`-1").toString());
		}
	}
	/** 发送之前判定 次数  或者更新为发送失败*/
	private boolean checkTryNumbers(NetPayData payData)
	{
		/** 判定是否已用尽发送次数 */
		if(payData.getTryTimes()<0 && payData.getDistributeStatus() == Constants.DISTRIBUTE_INIT)
		{
			/** 直接标记发送状态为失败 */
			ps.updateStatus(payData.getID(), "distributeStatus", Constants.DISTRIBUTE_FAIL);
			return Boolean.FALSE;
		}
		else if(payData.getDistributeStatus() == Constants.DISTRIBUTE_FAIL)
		{
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/** POST请求方法 **/
	private Boolean doRequest(String url,String ID,Hashtable<String, String> params)
	{ 
		try
		{  
			writeLog(new StringBuilder("Data Distribute POST 结果数据到：").append(url));
			HttpURLConnectionRequest http = new HttpURLConnectionRequest();
			String postUrl = url;
			String result = http.doSendPostRequest(postUrl, params);
			result = URLDecoder.decode(result, "utf-8");
			Boolean isReceive = Boolean.FALSE;
			writeLog(new StringBuilder("Data Distribute POST ：返回结果为：").append(result));
			if(result.equals("SUCCEED"))
			{
				return Boolean.TRUE;
			}
			else if(result.length() != 0 && result.trim() != "" )
			{
				isReceive = parseXml(result);
			}
			else
			{
				return Boolean.FALSE;
			}
			postUrl = null; params = null; ID = null; result = null; url = null;
			return isReceive;
		}
		catch (Exception e) 
		{
			writeLog(new StringBuilder("DataDistribute Expction:").append(e.getMessage()).append("cause:")
					.append(e.getCause()).append("stack:").append(e.getStackTrace()).append("class:").append(e.getClass()));
			return Boolean.FALSE;
		}
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
	
	/** 数据转移方法 根据不同 参数转移到不同的表 */
	public void moveDataToLog(ArrayList<NetPayData>list,int target)
	{
		String	tableName 	= "`net_pay_data_log`";
		switch(target)
		{
			case Constants.PAY_STATUS_SUCCEED:
				tableName = "`net_pay_data_succeed`";
				break;
			case Constants.PAY_STATUS__EXPIRE:
				tableName = "`net_pay_data_expire`";
				break;
			case Constants.PAY_STATUS_FAIL:
				tableName = "`net_pay_data_fail`";
				break;
			case Constants.DISTRIBUTE_FAIL:
				tableName = "`net_pay_data_warning`";
				break;
			case Constants.RECEIVE_INIT:
				tableName = "`net_pay_data_log`";
				break;
			default :
				tableName = "`net_pay_data_log`";
		}
		boolean res = ps.moveToLog(list,tableName);
		if(res == Boolean.FALSE)
			writeLog(new StringBuilder("Data Distribute 转移数据失败 条数：").append(list.size()).append("目标表：").append(tableName));
	}
	
	public static void main(String[] args)
	{
 		NetPayDistributeThread d = new NetPayDistributeThread();
 		Hashtable<String, String> params = new Hashtable<String, String>();
 		params.put("XML", "<?xml version=\"1.0\" encoding=\"utf-8\"?><netpayresult><outTtradeNo>OPCN20100902000001097</outTtradeNo><waresCode>OPCN201009020000010970020303009</waresCode><totalFee>1</totalFee><payType>8</payType><payStatus>SUCCEED</payStatus><tranLineDetail>1207498801201009020000000010</tranLineDetail></netpayresult>");
		d.doRequest(NetPayFormatTools.getNetPayExceptionDistribute("MCB2011011800000002900000000"), "NPGW20110120000000560", params);
	}
}