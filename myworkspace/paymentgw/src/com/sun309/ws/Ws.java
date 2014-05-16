package com.sun309.ws;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun309.dto.Fault;
import com.sun309.dto.NetPayData;
import com.sun309.dto.NetPayDataParams;
import com.sun309.dto.NetPayRequest;
import com.sun309.dto.ParseData;
import com.sun309.dto.Payment;
import com.sun309.dto.TranData;
import com.sun309.factory.BaseRequestFactory;
import com.sun309.factory.Factory;
import com.sun309.impl.FaultServiceImpl;
import com.sun309.impl.LogServiceImpl;
import com.sun309.impl.NetPayDataServiceImpl;
import com.sun309.impl.NetPayLogServiceImpl;
import com.sun309.impl.PaymentServiceImpl;
import com.sun309.service.AlipayService;
import com.sun309.service.BaseRequest;
import com.sun309.service.FaultService;
import com.sun309.service.LogService;
import com.sun309.service.NetPayDataService;
import com.sun309.service.PaymentService;
import com.sun309.service.Request;
import com.sun309.util.Constants;
import com.sun309.util.NetPayXmlTools;
import com.sun309.util.Validate;
import com.sun309.util.Validater;
import com.sun309.util.Xml;

public class Ws implements WsService
{
	private Logger 			syslog 	= Logger.getLogger(Ws.class);
	private LogService 		logger 	= (LogService)Factory.create(NetPayLogServiceImpl.class);
	private NetPayXmlTools	xml		= (NetPayXmlTools)Factory.create(NetPayXmlTools.class);
	private Validater		validater	= (Validater)Factory.create(Validater.class);
	private Validate		validate	= (Validate)Factory.create(Validate.class);
	private NetPayDataService	dataService = (NetPayDataService)Factory.create(NetPayDataServiceImpl.class);
	private BaseRequestFactory	factory	= (BaseRequestFactory)Factory.create(BaseRequestFactory.class);
	
	private LogService log = (LogService) Factory.create(LogServiceImpl.class);
	public String receive(String input)
	{
		Xml xml = new Xml();
		String _result = new String();
		try
		{
			log.debug(input);
			TranData td = new TranData(input);
			ArrayList<TranData> list = td.getList();
			if (list == null || list.size() <= 0)
			{
				_result = xml.createSimpleXml("1", "传入的数据为空,item无结点");
				return _result;
			}
			StringBuffer __result = new StringBuffer();
			for(TranData _td : list)
			{
				Payment payment = new Payment();
				payment.setCardNo(_td.getCardNO());
				payment.setExtOrderId(_td.getExtOrderID());
				payment.setFlag(_td.getFlag());
				payment.setMessage(_td.getMessage());
				payment.setPayOrderId(_td.getPayOrderID());
				payment.setTransType(_td.getTransType());
				PaymentService ps = (PaymentService) Factory.create(PaymentServiceImpl.class);
				boolean result = ps.insert(payment);
				__result.append("<Item>");
				__result.append("<PayOrderID>").append(_td.getPayOrderID()).append("</PayOrderID>");
				__result.append("<CardNO>").append(_td.getCardNO()).append("</CardNO>");
				__result.append("<error>").append(result?0:1).append("</error>");
				__result.append("<message>").append(result?"成功":"失败").append("</message>");
				__result.append("</Item>");
				
				log.debug(new StringBuffer(payment.getPayOrderId()).append(",").append(payment.getExtOrderId()).append("添加的结果：").append(result).toString());
				if(!result)
				{
					Fault fault = new Fault();
					fault.setCardNo(_td.getCardNO());
					fault.setExtOrderId(_td.getExtOrderID());
					fault.setPayOrderId(_td.getPayOrderID());
					fault.setTransType(_td.getTransType());
					FaultService f = (FaultService) Factory.create(FaultServiceImpl.class);
					f.insert(fault);
				}
			}
			StringBuffer ___result = new StringBuffer ("<Items>").append(__result).append("</Items>");  
			_result = ___result.toString();
			return _result;
		}
		catch (Exception e)
		{
			_result = xml.createSimpleXml("1", new StringBuffer("异常").append(e).toString());
			return _result;
		}
		finally
		{
			log.debug(_result);
			log.write();
		}
	}
	
	/** 生成多支付需要的ActionUrl和ActionForm返按指定结果返回 */
	public String getPayInfo(String input) 
	{
		StringReader 	reader	= new StringReader(input);
		StringBuilder	writer	= new StringBuilder("! INIT BUILDER !");
		try
		{
			/** 日志记录入口参数XML并解释为Request对象 对象返回后会对成员进行非空验证 */
			writeLog(input);
			Request req = xml.parseRequest(input);
			req.setValidater(validate);
			if(validater.validate(req))
			{
				/** 日志记录成功的验证结果信息,并按PayType参数初始化基础信息类 考虑到多线程问题工厂使用NEW方式返回一个实例 */
				writeLog(new StringBuilder("GetPayInfo pre:").append(req.getValidateInfo()));
				writer = createAction(req);
				writeLog(new StringBuilder("GetPayInfo Result OK!"));
			}
			else
			{
				writer = xml.simpleMeesage(1,req.getValidateInfo());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//writeLog(new StringBuilder("expction:").append(e.getMessage()).append("cause:")
				//	.append(e.getCause()).append("stack:").append(e.getStackTrace()).append("class:").append(e.getClass()));
			writer = xml.simpleMeesage(1,e.getMessage());
		}
		finally
		{
			reader.close();
			writeLog(writer.toString());
			logger.write();
		}
		return writer.toString();
	}
	
	/**生成支付宝批量退款需要的表单提交信息	 */
	public String getRefundInfo(String input)
	{
		ParseData pd = new ParseData(input);
		Map<String,String> sParaTemp = pd.getSParaTemp();
		
		String writer = "";
		try 
		{
			writeLog(input);
			writer = AlipayService.refund_fastpay_by_platform_pwd(sParaTemp);
		} 
		catch (Exception e) 
		{
			writeLog(new StringBuilder("Refund exception:").append(e.getMessage()).append("cause:")
					.append(e.getCause()).append("stack:").append(e.getStackTrace()).append("class:").append(e.getClass()));
		}
		
		return writer;
	}
	
	/** 生成多支付需要的流水ID 返回ID 并插入流水记录[用于记时，防止超时没有点击支付的不成功订单] */
	public String getPayInfoAsync(String input) 
	{
		StringReader 	reader	= new StringReader(input);
		StringBuilder	writer	= new StringBuilder("! INIT BUILDER !");
		try
		{
			/** 日志记录入口参数XML并解释为Request对象 对象返回后会对成员进行非空验证 */
			writeLog(input);
			Request req = xml.parseRequest(input);
			req.setValidater(validate);
			if(validater.validate(req))
			{
				/** 日志记录成功的验证结果信息,并按PayType参数初始化基础信息类 考虑到多线程问题工厂使用NEW方式返回一个实例 */
				writeLog(new StringBuilder("Async pre:").append(req.getValidateInfo()));
				BaseRequest base = factory.create(req.getPayType());
				base.setValidater(validate);
				/** 日志记录BaseRequest初始化结果*/
				writeLog(new StringBuilder("Async Info:").append(base.LoadInfo()));
				
				/** 使用Request对象 对BaseRequest对象部分成员进初始化,并向数据添加一条多支付流水记录 日志记录最终初始化结果*/
				base.Init(req);
				base.setID(dataService.insert(base.getData()));
				validater.validate(base);
				writeLog(new StringBuilder("Async Init:").append(base.InitInfo()));
				
				/** 保存请求参数到数据库 */
				NetPayDataParams params = new NetPayDataParams();
				params.setID(base.getID());
				params.setInputParams(input);
				params.setOutputParams(writer.toString());
				dataService.insertParams(params);
				
				/** 生成只有流水号的 响应XML */
				writer = xml.setNetPayResponseOnlyOrderNo(base);
				
				/** 释放NEW出来的变量 */
				params 	= null;
				base	= null;
			}
			else
			{
				writer = xml.simpleMeesage(1,req.getValidateInfo());
			}
		}
		catch(Exception e)
		{
			writeLog(new StringBuilder("Async expction:").append(e.getMessage()).append("cause:")
					.append(e.getCause()).append("Async stack:").append(e.getStackTrace()).append("class:").append(e.getClass()));
			writer = xml.simpleMeesage(1,e.getMessage());
		}
		finally
		{
			reader.close();
			writeLog(writer.toString());
			logger.write();
		}
		return writer.toString();
	}
	
	/** 异步取得支付生成结果 如果已经存在就直接返回，没就生成!  */
	public String getPayInfoResultAsync(String input)
	{
		StringBuilder	writer	= new StringBuilder("! INIT BUILDER !");
		try
		{
			/** 日志记录入口参数XML并解释为Request对象 对象返回后会对成员进行非空验证 */
			writeLog(input);
			Request req = xml.parseRequest(input);
			if(validate.isNetPayID(req.getNPGWID()))
			{
				NetPayData data = dataService.findByTradeID(req.getNPGWID());
				if(data != null && data.getID().equals(req.getNPGWID()))
				{
					NetPayDataParams params = dataService.findParamsByTradeID(data.getID());
					if(params !=null && params.getID().equals(req.getNPGWID()) && params.getOutputParams().length()>25)
					{
						/** 再次取参数时加长一个单位超时时间     		 */
						dataService.updateStatus(data.getID(), "expireTime", data.getExpireTime()+Constants.MIN_EXPIRE_TIME);
						
						/** 已生成  成功的链接参数直接返回 否则重生成 */
						writeLog(new StringBuilder("getResultAsync直接返回XML参数[并加长一个单位超时时间]：").append(params.getOutputParams()));
						return params.getOutputParams();
					}
					else if(params !=null && params.getID().equals(req.getNPGWID()))
					{
						/** 重新生成ActionUrl 或者 ActionForm  并返回  设置接收状态为等待 	*/
						Request initRequest = xml.parseRequest(params.getInputParams());
						NetPayRequest baseReq = new NetPayRequest();
						
						/** 重新构造Request 对象 结合两次请求的参数 第一部分使用原始参数 	*/
						baseReq.setClientIP(initRequest.getClientIP());
						baseReq.setExpireTime(initRequest.getExpireTime());
						baseReq.setOutTtradeNo(initRequest.getOutTtradeNo());
						baseReq.setPayType(initRequest.getPayType());
						baseReq.setWaresCode(initRequest.getWaresCode());
						baseReq.setInitTag(initRequest.getInitTag());
						
						/** 第二次部分使用新请求中展示相关参数 							*/
						baseReq.setNPGWID(req.getNPGWID());
						baseReq.setClientIP(req.getClientIP());
						baseReq.setResultType(req.getResultType());
						baseReq.setReturnUrl(req.getReturnUrl());
						baseReq.setTotalFee(req.getTotalFee());
						
						/** 开始调用生成相关流程		*/
						writer = createAction(baseReq);
						/** 开始写结果日志 			*/
						writeLog(new StringBuilder("getResultAsync生成返回XML参数"));
					}
					else
					{
						writer = xml.simpleMeesage(1,"查找已生成的XML结果出错!,建议重试!");
					}
				}
				else
				{
					writer = xml.simpleMeesage(1,"异常的支付流水ID,不存在或者已超时的流水记录!");
				}
				data = null;
			}
			else
			{
				writer = xml.simpleMeesage(1,"多支付流水ID格式错误 !");
			}
		}
		catch(Exception e)
		{
			writeLog(new StringBuilder("Async Result expction:").append(e.getMessage()).append("cause:")
					.append(e.getCause()).append("Async Result stack:").append(e.getStackTrace()).append("class:").append(e.getClass()));
			writer = xml.simpleMeesage(1,e.getMessage());
		}
		finally
		{
			writeLog(writer.toString());
			logger.write();
		}
		return writer.toString();
	}
	
	/** 按Request对象生成 ActionUrl或者 ActionForm 要求：必须有NPGW流水存在未超时 */
	private StringBuilder createAction(Request req)
	{
		StringBuilder	writer	= new StringBuilder("! INIT CREATE !");
		req.setValidater(validate);
		if(validater.validate(req))
		{
			/** 日志记录成功的验证结果信息,并按PayType参数初始化基础信息类 考虑到多线程问题工厂使用NEW方式返回一个实例 */
			writeLog(new StringBuilder("Create pre Info:").append(req.getValidateInfo()));
			BaseRequest base = factory.create(req.getPayType());
			base.setValidater(validate);
			/** 日志记录BaseRequest初始化结果*/
			writeLog(new StringBuilder("Create Load Info:").append(base.LoadInfo()));
			
			/** 使用Request对象 对BaseRequest对象部分成员进初始化				*/
			base.Init(req);
			if(req.getInitTag())
				base.setID(dataService.insert(base.getData()));
			else
				base.setID(req.getNPGWID());
			validater.validate(base);
			writeLog(new StringBuilder("Create After Init:").append(base.InitInfo()));
			
			/** 对基础数据进行签名 并验证,日志记录验证结果,更新签名结果到数据库 	*/
			base.sign();
			validater.validate(base);
			writeLog(new StringBuilder("Create After Sign:").append(base.SignInfo()));
			dataService.updateStatus(base.getID(), "checkValue", base.getData().getCheckValue());
			
			/** 根据BaseRequest 对象最后一次验证结果生成返回的XML结果 并更新到参数表，如果成功更新接收状态为WAIT!*/
			if(validater.validate(base))
			{
				/** 准备返回数据 */
				writer = xml.setNetPayResponse(base);
				
				/** 更新到参数表  异步时更新 同步时插入[为了兼容getPayInfo] */
				StringBuilder newwriter = xml.createInputParam(req);
				if(req.getInitTag().equals(Boolean.FALSE))
				{
					dataService.updateParams(base.getID(),"outputParams",writer.toString());
					dataService.updateParams(base.getID(),"inputParams", newwriter.toString());
					writeLog(new StringBuilder("Create For Async: Upate Params SUCCEED!"));
					
					dataService.updateStatus(base.getID(), "totalFee", req.getTotalFee());
					dataService.updateStatus(base.getID(), "returnURL", req.getReturnUrl());
					dataService.updateStatus(base.getID(), "clientIP", req.getClientIP());
					writeLog(new StringBuilder("Create For Async: Upate NetPayData SUCCEED!"));
				}
				else
				{
					NetPayDataParams params = new NetPayDataParams();
					params.setID(base.getID());
					params.setInputParams(newwriter.toString());
					params.setOutputParams(writer.toString());
					dataService.insertParams(params);
					writeLog(new StringBuilder("Create For Sync: Insert Params SUCCEED!"));
					params = null;
				}
				
				/** 更新接收状态  */
				dataService.updateStatus(base.getID(),"receiveStatus", Constants.RECEIVE_WAIT);
				
				newwriter = null;
				base = null;
			}
			else
			{
				writer = xml.simpleMeesage(1,base.getValidateInfo());
				base = null;
			}
		}
		else
		{
			writer = xml.simpleMeesage(1,req.getValidateInfo());
		}
		return writer;
	}
	
	/** WS私有日志方法 记录生成过程中的各种日志信息 */
	private void writeLog(Object obj)
	{
		if(Constants.LOG_STATUS)
		{
			syslog.debug(obj);
		}
		if(Constants.DBLOG_STATUS)
		{
			logger.debug(obj.toString());
		}
		obj=null;
	}
	
	/* (non-Javadoc)
	 * @see com.sun309.NetPay.NetPayService#setPayInfo(java.lang.String)
	 */
	public String setPayInfo(String input) 
	{
		return "XXXXXXXXXXX  FOR  TEST XXXXXXXXXX";
	}
}
