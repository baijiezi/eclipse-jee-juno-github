/**
 * 财付通请求基本信息
 * 实现BaseRequest接口
 */
package com.sun309.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.sun309.dto.NetPayData;
import com.sun309.dto.TenPayQueryResult;
import com.sun309.factory.Factory;
import com.sun309.service.LogService;
import com.sun309.util.Constants;
import com.sun309.util.NetPayFormatTools;
import com.sun309.util.TenPayMD5Util;
import com.sun309.util.Validate;

/**
 * @author Wormwood OPC_GROUP
 *
 */
/**
 * @author Wormwood OPC_GROUP
 *
 */
public class TenPayBaseQuery
{
	private Logger 		syslog 	= 	Logger.getLogger(TenPayBaseQuery.class);
	private LogService 	logger 	= 	(LogService)Factory.create(NetPayLogServiceImpl.class);
	
	/** 外部业务系统交易订单号 API_NOT_NULL sp_billno */
	private String 	outTradeNo;
	
	/** 交易日期 date 20051212*/
	private String 	date;	
	
	/**
	 * 生成签名参数列表
	 */
	/** 财付通服务地址 API_NOT_NULL */
	private String 	payGateway	="http://mch.tenpay.com/cgi-bin/cfbi_query_order_v3.cgi";
	
	/** 支付服务接口类型 API_NOT_NULL cmdno*/
	private String 	service		="2";
	
	/** 签名方式 MD5结果 API_NOT_NULL */
	private String 	sign;	
	
	/** 商品名称 API_NOT_NULL desc */
	private String 	subject;
	
	/** 字符编码：用于签名 生成的加密URL API_NOT_NULL */
	private String inputCharset	="UTF-8";
	
	/** 合做商户签约ID API_NOT_NULL bargainor_id */
	private String partner		="1207498801";
	
	/** 私有数据:原样返回的数据 一般设置为商品代码 attach */
	private String 	privateData	= "MultiPayService-SunMedicalNet-ChinaPay";
	
	/** 财付通外部订单号 交易流水号 transaction_id */
	private String tradeNo;
	
	/** MD5 加密KEY 接口文档中没有 */
	private String key			="bd15ee9d3d741643aca88fa1f4183db6";	
	
	/**签名后的数据 实际就是一个加密的URL地址 sign */
	private String signRsult;
	
	/** 实际支付类型 */
	private Integer payTypeCode = new Integer(Constants.TEN_PAY);

	/**
	 * 数据验证成员
	 */
	private Validate 	validater;						/** 验证类成员 */
	private String 		validateInfo = "Init";;			/** 验证结果信息*/
	
	/**
	 * 验证标志位
	 */
	private Boolean initFlag 	= Boolean.FALSE;	/** Init()方法是否已调用标识	*/
	private Boolean	signFlag	= Boolean.FALSE;	/** Sign()方法是否已调用标识	*/
	
	/**
	 * 返回数据验证参数
	 */
	/** 交易返回结果的状态 */
	private String  OrderStatus;
	
	/** 货币类型 */
	private String curyId = "1";
	
	/** 支付结果信息，支付成功时为空 */
	private String payInfo;
	
	/** 支付总金额信息 */
	private Long totalFee;
	
	/**
	 * BaseRequest 接口方法
	 */
	public void Init(NetPayData req) {
		// TODO Auto-generated method stub
		setOutTradeNo(req.getID());
		setTradeNo(NetPayFormatTools.TenPayOrderID(req.getID(), Constants.TEN_PAY_MERD_ID));
		setPrivateData(req.getWaresCode());
		setDate(NetPayFormatTools.getTenPayDateFromTradeNo(getTradeNo()));
		if(!getPayTypeCode().equals(req.getPayType()))
		{
			setValidateInfo(new StringBuilder("实际支付类型和请求类型不同异常! 详细：实际类型")
				.append(getPayTypeCode()).append("调用传入的类型：").append(req.getPayType()).toString());
			writeLog(getValidateInfo());
		}
		else
		{
			initFlag = Boolean.TRUE;
		}
	}
	
	/** 接数据成功后用源数据对象进行赋值 方便下面调用 verifyReceieData*/
	public void preValidate(TenPayQueryResult dtoResult) {
		// TODO Auto-generated method stub
		setPrivateData(dtoResult.getAttach());
		setPartner(dtoResult.getBargainorID());
		setService(dtoResult.getCmdNo());
		setDate(dtoResult.getDate());
		setCuryId(dtoResult.getFeeType());
		setPayInfo(dtoResult.getPayInfo());
		setOrderStatus(dtoResult.getPayResult());
		setOutTradeNo(dtoResult.getSpBillNo());
		setTotalFee(dtoResult.getTotalFee());
		setTradeNo(dtoResult.getTransactionId());
	}
	
	/**
	 * BaseRequest 接口方法 加密方法
	 */
	public void sign() {
		try
		{
			/** 生成签名字段			*/
			StringBuilder preSign = new StringBuilder("attach=").append(getPrivateData())
				.append("&bargainor_id=").append(getPartner()).append("&charset=").append(getInputCharset())
				.append("&cmdno=").append(getService()).append("&date=").append(getDate()).append("&output_xml=1")
				.append("&sp_billno=").append(getOutTradeNo()).append("&transaction_id=").append(getTradeNo())
				.append("&key=").append(getKey());
			setSign(TenPayMD5Util.MD5Encode(preSign.toString(), getInputCharset()));
			if(getValidater().isString(getSign()))
			{
				setSignFlag(Boolean.TRUE);
				setValidateInfo(new StringBuilder("生成签名非空验证成功! 值：").append(getSign()).toString());
				writeLog(getValidateInfo());
			}
			else
			{
				setValidateInfo(new StringBuilder("多支付流水号:").append(getOutTradeNo())
						.append("财付通数字签名生成失败!").toString());
				setSignFlag(Boolean.FALSE);
				writeLog(getValidateInfo());
			}
			createUrl();
		}
		catch(Exception e)
		{
			writeLog(new StringBuilder().append(e.getStackTrace()).append(e.getCause()).append(e.getClass()));
			writeLog("XXXXXXXXXXXX SIGN EXCEPTION XXXXXXXXXXXXXXX");
		}
	}
	
	/** 产生加密的URL地址地址
	 * @throws UnsupportedEncodingException 
	 */
	private void createUrl() throws UnsupportedEncodingException
	{
		/** show_url 参数在直接到帐接口是没有或者可选参数 只有在担保到帐接口才 必填 */
		StringBuilder url = new StringBuilder(getPayGateway()).append("?")
			.append("cmdno=").append(URLEncoder.encode(getService(), getInputCharset()))
			.append("&date=").append(URLEncoder.encode(getDate(), getInputCharset()))
			.append("&bargainor_id=").append(URLEncoder.encode(getPartner(), getInputCharset()))
			.append("&transaction_id=").append(URLEncoder.encode(getTradeNo(), getInputCharset()))
			.append("&sp_billno=").append(URLEncoder.encode(getOutTradeNo(), getInputCharset()))
			.append("&attach=").append(URLEncoder.encode(getPrivateData(), getInputCharset()))
			.append("&output_xml=").append(URLEncoder.encode("1", getInputCharset()))
			.append("&charset=").append(URLEncoder.encode(getInputCharset(), getInputCharset()))
			.append("&sign=").append(URLEncoder.encode(getSign(), getInputCharset()));
		setSignRsult(url.toString());
		url = null;
	}
	
	/** 产生查询POST参数组 */
	public Hashtable<String, String> createPOSTParams()
	{
		Hashtable<String, String> params = new Hashtable<String, String>();
		params.put("ActionUrl", getPayGateway());
		params.put("cmdno", getService());
		params.put("date", getDate());
		params.put("bargainor_id", getPartner());
		params.put("transaction_id", getTradeNo());
		params.put("sp_billno", getOutTradeNo());
		params.put("attach",getPrivateData());
		params.put("output_xml","1");
		params.put("charset", getInputCharset());
		params.put("sign", getSign());
		return params;
	}
	
	/**
	 * BaseRequest 接口方法 加密验证方法
	 */
	public boolean verifyReceieData() 
	{
		try
		{
			/** 生成签名字段			*/
			StringBuilder resultSign = new StringBuilder("attach=").append(getPrivateData())
				.append("&bargainor_id=").append(getPartner()).append("&cmdno=").append(getService())
				.append("&date=").append(getDate()).append("&fee_type=").append(getCuryId())
				.append("&pay_info=").append(getPayInfo()).append("&pay_result=").append(getOrderStatus())
				.append("&sp_billno=").append(getOutTradeNo()).append("&total_fee=").append(getTotalFee())
				.append("&transaction_id=").append(getTradeNo()).append("&key=").append(getKey());
				
			return getSign().equals(TenPayMD5Util.MD5Encode(resultSign.toString(), getInputCharset()));
		}
		catch(Exception e)
		{
			writeLog(new StringBuilder(e.getMessage()).append(e.getStackTrace()).append(e.getCause()).append(e.getClass()));
			return Boolean.FALSE;
		}
	}
	
	/**
	 * 验证接口方法
	 */
	public boolean validate() {
		if(getValidater() == null )							{setValidateInfo("验证类加载失败"); 					return Boolean.FALSE;}
		if(!getValidater().isString(getPayGateway())) 		{setValidateInfo("财付通网关地址URL验证失败"); 		return Boolean.FALSE;}
		if(!getValidater().isString(getService())) 			{setValidateInfo("财付通网关服务类型参数验证失败"); 	return Boolean.FALSE;}
		if(!getValidater().isString(getInputCharset())) 	{setValidateInfo("参数默认字符集验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isString(getPartner())) 			{setValidateInfo("商户ID验证失败"); 					return Boolean.FALSE;}
		if(!getValidater().isString(getKey())) 				{setValidateInfo("加密KEY验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isObject(getPayTypeCode())) 		{setValidateInfo("实际支付类型验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isString(getCuryId())) 			{setValidateInfo("货币类型验证失败"); 				return Boolean.FALSE;}
		
		/**	判定初始化完成标识位,没调用init()之前不再向下验证	*/	
		if(!getInitFlag()) 									{validateInfo = "未调用初始化方法！"; 				return Boolean.FALSE;}
		/** 主业务流程是Init后马上 Set ID进来 再运行签名方法 AfterInfo方法是 Init 和 set 后的验证验证信息 */
		if(!getValidater().isString(getOutTradeNo())) 		{setValidateInfo("外部订单号验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getPrivateData())) 		{setValidateInfo("私有数据商品代码验证失败"); 		return Boolean.FALSE;}
		if(!getValidater().isNum(getDate()))				{setValidateInfo("财付通订单日期格式验证失败"); 		return Boolean.FALSE;}
		if(!getValidater().isNum(getTradeNo()))				{setValidateInfo("财付通订单号格式验证失败"); 		return Boolean.FALSE;}
		
		/**	判定签名生成标识位,没调用sign()之前不再向下验证	*/	
		if(!getSignFlag()) 									{setValidateInfo("未调用签名方法！"); 				return Boolean.FALSE;}	
		if(!getValidater().isString(getSign())) 			{setValidateInfo("签名结果验证失败"); 				return Boolean.FALSE;}
		writeLog("XXXXXXXXXXXXXXXXXXXXXXXXX VALIDATE SUCCEED XXXXXXXXXXXXXXXXXXXXX");
		setValidateInfo("SUCCEED");
		return Boolean.TRUE;
	}
	
	/**
	 * LogInfo 接口方法
	 */
	public String InitInfo() {
		StringBuilder log = new StringBuilder("InitInfo:");
		log.append(" 财付通订单日期:").append(getDate());
		log.append(" 财付通订单号:").append(getTradeNo());
		log.append(" 外部订单号[多支付流水号]:").append(getOutTradeNo());
		return log.toString();
	}
	
	/**
	 * LogInfo 接口方法
	 */
	public String LoadInfo() {
		// TODO Auto-generated method stub
		StringBuilder log = new StringBuilder("LoadInfo:");
		log.append("财付通查询地址:").append(getPayGateway());
		log.append(" 财付通网关服务类型:").append(getService());
		log.append(" 字符编码:").append(getInputCharset());
		log.append(" 合做商户签约ID:").append(getPartner());
		log.append(" 加密KEY:").append(getKey());
		log.append(" 实际支付类型:").append(getPayTypeCode());
		log.append(" 货币类型:").append(getCuryId());
		return log.toString();
	}
	
	/**
	 * LogInfo 接口方法
	 */
	public String SignInfo() {
		// TODO Auto-generated method stub
		StringBuilder log = new StringBuilder("SignInfo:");
		log.append(" 签名字段:").append(getSign());
		log.append(" 签名URL:").append(getSignRsult());
		return log.toString();
	}
	
	/** 私有LOG方法 用于记录 WS主业务类中不记录的私有信息 */
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
	
	/**
	 * 返回类名
	 * 用于加载同名XML文件
	 */
	@Override
	public String toString()
	{
		return "TenPayBaseQuery";
	}
	
	/**
	 * @return the payInfo
	 */
	public String getPayInfo() {
		return payInfo;
	}

	/**
	 * @param payInfo the payInfo to set
	 */
	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	/**
	 * @return the payGateway
	 */
	public String getPayGateway() {
		return payGateway;
	}
	/**
	 * @param payGateway the payGateway to set
	 */
	public void setPayGateway(String payGateway) {
		this.payGateway = payGateway;
	}
	/**
	 * @return the service
	 */
	public String getService() {
		return service;
	}
	/**
	 * @param service the service to set
	 */
	public void setService(String service) {
		this.service = service;
	}
	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}
	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**
	 * @return the outTradeNo
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}
	/**
	 * @param outTradeNo the outTradeNo to set
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	/**
	 * @return the inputCharset
	 */
	public String getInputCharset() {
		return inputCharset;
	}
	/**
	 * @param inputCharset the inputCharset to set
	 */
	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}
	/**
	 * @return the partner
	 */
	public String getPartner() {
		return partner;
	}
	/**
	 * @param partner the partner to set
	 */
	public void setPartner(String partner) {
		this.partner = partner;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the totalFee
	 */
	public Long getTotalFee() {
		return totalFee;
	}
	/**
	 * @param totalFee the totalFee to set
	 */
	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the signRsult
	 */
	public String getSignRsult() {
		return signRsult;
	}
	/**
	 * @param signRsult the signRsult to set
	 */
	public void setSignRsult(String signRsult) {
		this.signRsult = signRsult;
	}
	/**
	 * @return the payTypeCode
	 */
	public Integer getPayTypeCode() {
		return payTypeCode;
	}
	/**
	 * @param payTypeCode the payTypeCode to set
	 */
	public void setPayTypeCode(Integer payTypeCode) {
		this.payTypeCode = payTypeCode;
	}
	/**
	 * @return the validater
	 */
	public Validate getValidater() {
		return validater;
	}
	/**
	 * @param validater the validater to set
	 */
	public void setValidater(Validate validater) {
		this.validater = validater;
	}
	/**
	 * @return the validateInfo
	 */
	public String getValidateInfo() {
		return validateInfo;
	}
	/**
	 * @param validateInfo the validateInfo to set
	 */
	public void setValidateInfo(String validateInfo) {
		this.validateInfo = validateInfo;
	}
	/**
	 * @return the initFlag
	 */
	public Boolean getInitFlag() {
		return initFlag;
	}
	/**
	 * @param initFlag the initFlag to set
	 */
	public void setInitFlag(Boolean initFlag) {
		this.initFlag = initFlag;
	}
	/**
	 * @return the signFlag
	 */
	public Boolean getSignFlag() {
		return signFlag;
	}
	/**
	 * @param signFlag the signFlag to set
	 */
	public void setSignFlag(Boolean signFlag) {
		this.signFlag = signFlag;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the privateData
	 */
	public String getPrivateData() {
		return privateData;
	}
	/**
	 * @param privateData the privateData to set
	 */
	public void setPrivateData(String privateData) {
		this.privateData = privateData;
	}
	/**
	 * @return the tradeNo
	 */
	public String getTradeNo() {
		return tradeNo;
	}
	/**
	 * @param tradeNo the tradeNo to set
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	/**
	 * @return the curyId
	 */
	public String getCuryId() {
		return curyId;
	}
	/**
	 * @param curyId the curyId to set
	 */
	public void setCuryId(String curyId) {
		this.curyId = curyId;
	}
	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return OrderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}
}
