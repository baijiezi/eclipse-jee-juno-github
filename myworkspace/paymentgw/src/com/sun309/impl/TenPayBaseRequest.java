/**
 * 财付通请求基本信息
 * 实现BaseRequest接口
 */
package com.sun309.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.sun309.dto.ActionUrl;
import com.sun309.dto.NetPayData;
import com.sun309.factory.Factory;
import com.sun309.service.BaseRequest;
import com.sun309.service.LogService;
import com.sun309.service.Request;
import com.sun309.util.Constants;
import com.sun309.util.DateUtils;
import com.sun309.util.NetPayFormatTools;
import com.sun309.util.TenPayMD5Util;
import com.sun309.util.Validate;

/**
 * @author Wormwood OPC_GROUP
 *
 */
public class TenPayBaseRequest implements BaseRequest
{
	private Logger 		syslog 	= 	Logger.getLogger(TenPayBaseRequest.class);
	private LogService 	logger 	= 	(LogService)Factory.create(NetPayLogServiceImpl.class);
	
	/** 多支付服务唯一ID号 */
	private String ID;
	
	/** 外部业务系统交易订单号 API_NOT_NULL sp_billno */
	private String 	outTradeNo;	
	
	/** 交易总金额 API_NOT_NULL total_fee*/
	private Long   	totalFee;	
	
	/** 通知地址 API */
	private String 	returnUrl	="http://www.sun309.com";		
	
	/** 商品代码 **/
	private String 	waresCode;
	
	/** 返回XML数据部分 **/
	private String 	response;	
	
	/** 请求设置的超时时间 Long 毫秒 default:3600*1000 */
	private Long 	expireTime	= new Long(3600000);
	
	/** 交易日期 date 20051212*/
	private String 	date;	
	
	/** 请求设置的返回结果类型 [HTML|XML] default:HTML*/
	private String 	resultType;	
	
	/** 实际支付请求发起客户机IP	spbill_create_ip */
	private String 	ClientIP;
	
	/**
	 * 生成签名参数列表
	 */
	/** 财付通服务地址 API_NOT_NULL */
	private String 	payGateway	="http://service.tenpay.com/cgi-bin/v3.0/payservice.cgi";
	
	/** 支付服务接口类型 API_NOT_NULL cmdno*/
	private String 	service		="1";
	
	/** 签名方式 MD5结果 API_NOT_NULL */
	private String 	sign;	
	
	/** 用户网站编号 SPID 由财付通统计分配=120开头的商户号 */
	private String SPID = Constants.TEN_PAY_MERD_ID;
	
	/** 商品名称 API_NOT_NULL desc */
	private String 	subject;
	
	/** 字符编码：用于签名 生成的加密URL API_NOT_NULL */
	private String inputCharset	="gbk";
	
	/** 合做商户签约ID API_NOT_NULL bargainor_id */
	private String partner		= Constants.TEN_PAY_MERD_ID;
	
	/** 私有数据:原样返回的数据 一般设置为商品代码 attach */
	private String 	privateData	= "MultiPayService-SunMedicalNet-ChinaPay";
	
	/** 财付通外部订单号 交易流水号 transaction_id */
	private String tradeNo;
	
	/** MD5 加密KEY 接口文档中没有 */
	private String key			="bd15ee9d3d741643aca88fa1f4183db6";	
	
	/** 货币类型：只支持人民币 fee_type*/
	private String  curyId		= "1";
	
	/**	交易结果数据接收地址 API_NOT_NULL return_url */
	private String notifyUrl	="http://124.172.245.98:8080/paymentgw/servlet/TenPayReceive";
	
	/** 默认银行 接口文档中 bank_type */
	private String defaultBank	="0";		
	/**private String sign;			API_NOT_NULL 原Alipy没有使用这个参数可能接口文档不是最新。*/
	
	/** 签名结果 sign */
	private String signRsult;		/**签名后的数据 实际就是一个加密的URL地址 sign */
	
	/**
	 * 生成XML结果的配置参数列表
	 */
	/**[actionUrl|actionForm]签名结果类型*/
	private String 		payTypeResultCode	="actionUrl"; 
	
	/**[mapping.conf.php payType]实际支付类型*/
	private Integer 	payTypeCode			= new Integer(Constants.TEN_PAY);	
	
	/** 支付方式的中文名称 */
	private String 		payTypeName			="财付通"; 
	
	
	/**
	 * 数据验证成员
	 */
	private Validate 	validater = null;		/** 验证类成员 */
	private String 		validateInfo = "Init";	/** 验证结果信息*/
	
	/**
	 * 验证标志位
	 */
	private Boolean initFlag 	= Boolean.FALSE;	/** Init()方法是否已调用标识	*/
	private Boolean asyncFlag 	= Boolean.FALSE;	/** 是否异步方法	默认同步生成	*/
	private Boolean	signFlag	= Boolean.FALSE;	/** Sign()方法是否已调用标识	*/
	
	/**
	 * 返回数据验证参数
	 */
	/** 交易返回结果的状态 */
	private String  OrderStatus;
	
	/**
	 * BaseRequest 接口方法
	 */
	public void Init(Request req) {
		// TODO Auto-generated method stub
		setOutTradeNo(req.getOutTtradeNo());
		setWaresCode(req.getWaresCode());
		setTotalFee(req.getTotalFee());
		setReturnUrl(req.getReturnUrl());
		setResultType(req.getResultType());
		setExpireTime(req.getExpireTime());
		setClientIP(req.getClientIP());
		setSubject(NetPayFormatTools.getNetPaySubject(getWaresCode()));
		setDate(DateUtils.getFormatDate("yyyyMMdd"));
		setPrivateData(getOutTradeNo());
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
	/**
	 * BaseRequest 接口方法
	 */
	public String getHTMLPayType()
	{
		// TODO Auto-generated method stub
		//log.debug(getResultType().equals("HTML"));
		if(getResultType().equals("HTML"))
		{
			ActionUrl url = new ActionUrl();
			url.setActionUrl(getSignRsult());
			url.setPayTypeName(getPayTypeName());
			url.setUsclass("alipHref");
			setResponse(url.toHTML());
			url	=	null;
			return getResponse();
		}
		else
		{
			ActionUrl url = new ActionUrl();
			url.setActionUrl(getSignRsult());
			url.setPayTypeName(getPayTypeName());
			url.setPayTypeCode(getPayTypeCode());
			url.setResultType(getResultType());
			setResponse(url.toXML());
			url = null;
			return getResponse();
		}
	}
	
	/**
	 * BaseRequest 接口方法 加密方法
	 */
	public void sign() {
		try
		{
			/** 生成财付通外部订单号 	*/
			setTradeNo(NetPayFormatTools.TenPayOrderID(getID(),getSPID()));
			/** 生成签名字段			*/
			StringBuilder preSign = new StringBuilder("cmdno=").append(getService())
				.append("&date=").append(getDate()).append("&bargainor_id=").append(getPartner())
				.append("&transaction_id=").append(getTradeNo()).append("&sp_billno=").append(getID())
				.append("&total_fee=").append(getTotalFee()).append("&fee_type=").append(getCuryId())
				.append("&return_url=").append(getNotifyUrl()).append("&attach=").append(getPrivateData())
				.append("&spbill_create_ip=").append(getClientIP()).append("&key=").append(getKey());
			setSign(TenPayMD5Util.MD5Encode(preSign.toString(), getInputCharset()));
			/** 生成有加密的URL 		*/
			createUrl();
			if(getValidater().isObject(getSignRsult()))
			{
				setSignFlag(Boolean.TRUE);
				setValidateInfo("生成签名非空验证成功!");
			}
			else
			{
				setValidateInfo(new StringBuilder("外部订单：").append(getOutTradeNo()).append("多支付流水号:").append(getID())
						.append("财付通数字签名生成失败!").toString());
				setSignFlag(Boolean.FALSE);
				writeLog(getValidateInfo());
			}
		}
		catch(UnsupportedEncodingException e)
		{
			writeLog("XXXXXXXXXXXX SIGN EXCEPTION <URL编码异常> XXXXXXXXXXXXXXX");
		}
		catch(Exception e)
		{
			writeLog("XXXXXXXXXXXX SIGN EXCEPTION XXXXXXXXXXXXXXX");
			e.printStackTrace();
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
			.append("&sp_billno=").append(URLEncoder.encode(getID(), getInputCharset()))
			.append("&total_fee=").append(URLEncoder.encode(getTotalFee().toString(), getInputCharset()))
			.append("&fee_type=").append(URLEncoder.encode(getCuryId(), getInputCharset()))
			.append("&return_url=").append(URLEncoder.encode(getNotifyUrl(), getInputCharset()))
			.append("&attach=").append(URLEncoder.encode(getPrivateData(), getInputCharset()))
			.append("&spbill_create_ip=").append(URLEncoder.encode(getClientIP(), getInputCharset()))
			.append("&desc=").append(URLEncoder.encode(getSubject(), getInputCharset()))
			.append("&bank_type=").append(URLEncoder.encode(getDefaultBank(), getInputCharset()))
			.append("&cs=").append(URLEncoder.encode(getInputCharset(), getInputCharset()))
			.append("&show_url=").append(URLEncoder.encode(getReturnUrl(), getInputCharset()))
			.append("&purchaser_id=").append(URLEncoder.encode("", getInputCharset()))
			.append("&sign=").append(URLEncoder.encode(getSign(), getInputCharset()));
		setSignRsult(url.toString());
		url = null;
	}
	
	/** 产生保存到数据的记录信息 */
	public NetPayData getData() 
	{
		// TODO Auto-generated method stub
		NetPayData data = new NetPayData();
		data.setPayType(getPayTypeCode());
		data.setOutOrderNo(getOutTradeNo());
		data.setTotalFee(getTotalFee());
		data.setWaresCode(getWaresCode());
		data.setExpireTime(getExpireTime());
		data.setClientIP(getClientIP());
		data.setReturnURL(getReturnUrl());
		data.setCheckKey(getKey());
		data.setCheckValue((getValidater().isString(getSign()))?getSign():getID());
		return data;
	}
	
	/**
	 * BaseRequest 接口方法 加密验证方法
	 */
	public boolean verifyReceieData() 
	{
		try
		{
			StringBuilder apliPayNontifyURL = new StringBuilder("cmdno=").append(getService())
				.append("&pay_result=").append(getOrderStatus()).append("&date=").append(getDate())
				.append("&transaction_id=").append(getTradeNo()).append("&sp_billno=").append(getID())
				.append("&total_fee=").append(getTotalFee()).append("&fee_type=").append(getCuryId())
				.append("&attach=").append(getPrivateData()).append("&key=").append(getKey());
			return getSign().equals(TenPayMD5Util.MD5Encode(apliPayNontifyURL.toString(), getInputCharset()));
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
		if(!getValidater().isString(getDefaultBank())) 		{setValidateInfo("默认银行验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isObject(getPayTypeCode())) 		{setValidateInfo("实际支付类型验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isString(getPayTypeName())) 		{setValidateInfo("支付中文名验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getNotifyUrl())) 		{setValidateInfo("数据返回地址验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isString(getPayTypeResultCode())){setValidateInfo("签名结果类型验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isString(getCuryId())) 			{setValidateInfo("货币类型验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getSPID())) 			{setValidateInfo("网站SPID验证失败"); 				return Boolean.FALSE;}
		
		/**	判定初始化完成标识位,没调用init()之前不再向下验证	*/	
		if(!getInitFlag()) 									{validateInfo = "未调用初始化方法！"; 				return Boolean.FALSE;}
		/** 主业务流程是Init后马上 Set ID进来 再运行签名方法 AfterInfo方法是 Init 和 set 后的验证验证信息 */
		if(!getValidater().isString(getSubject())) 			{setValidateInfo("商品名称验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getWaresCode())) 		{setValidateInfo("商品代码验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isObject(getExpireTime())) 		{setValidateInfo("超时时间验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getOutTradeNo())) 		{setValidateInfo("外部订单号验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isNum(getDate()))				{setValidateInfo("财付通订单日期格式验证失败"); 		return Boolean.FALSE;}
		/** 异步生成时: 直接返成功不再向下验证 */
		if(!getAsyncFlag() && !getSignFlag()) 				{setValidateInfo("ASYNC SUCCEED"); 					return Boolean.TRUE;}
		if(!getValidater().isNetPayID(getID())) 			{setValidateInfo("多支付流水号格式验证失败");			return Boolean.FALSE;}
		if(!getValidater().isObject(getTotalFee()))			{setValidateInfo("支付金额验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getReturnUrl())) 		{setValidateInfo("通知地址验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getResultType())) 		{setValidateInfo("返回结果类型验证失败"); 			return Boolean.FALSE;}
		
		/**	判定签名生成标识位,没调用sign()之前不再向下验证	*/	
		if(!getSignFlag()) 									{setValidateInfo("未调用签名方法！"); 				return Boolean.FALSE;}	
		if(!getValidater().isNum(getTradeNo()))				{setValidateInfo("财付通订单号格式验证失败"); 		return Boolean.FALSE;}
		if(!getValidater().isString(getSign())) 			{setValidateInfo("签名结果验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getSignRsult()))		{setValidateInfo("生成签名URL字符串为空格式验证失败");return Boolean.FALSE;}
		writeLog("XXXXXXXXXXXXXXXXXXXXXXXXX VALIDATE SUCCEED XXXXXXXXXXXXXXXXXXXXX");
		setValidateInfo("SUCCEED");
		return Boolean.TRUE;
	}
	
	/**
	 * LogInfo 接口方法
	 */
	public String InitInfo() {
		StringBuilder log = new StringBuilder("InitInfo:");
		log.append("商品代码:").append(getWaresCode());
		log.append(" 商品名称:").append(getSubject());
		log.append(" 超时时间:").append(getExpireTime());
		log.append(" 财付通订单日期:").append(getDate());
		log.append(" 多支付流水号[实际支付系统外部订单号]：").append(getID());
		log.append(" 外部订单号:").append(getOutTradeNo());
		return log.toString();
	}
	
	/**
	 * LogInfo 接口方法
	 */
	public String LoadInfo() {
		// TODO Auto-generated method stub
		StringBuilder log = new StringBuilder("LoadInfo:");
		log.append("财付通服务地址:").append(getPayGateway());
		log.append(" 财付通网关服务类型:").append(getService());
		log.append(" 字符编码:").append(getInputCharset());
		log.append(" 合做商户签约ID:").append(getPartner());
		log.append(" 加密KEY:").append(getKey());
		log.append(" 网站SPID:").append(getSPID());
		log.append(" 默认银行:").append(getDefaultBank());
		log.append(" 签名结果类型:").append(getPayTypeResultCode());
		log.append(" 实际支付类型:").append(getPayTypeCode());
		log.append(" 货币类型:").append(getCuryId());
		log.append(" 支付方式的中文名称").append(getPayTypeName());
		return log.toString();
	}
	/**
	 * LogInfo 接口方法
	 */
	public String ResultInfo() {
		// TODO Auto-generated method stub
		if(getResponse() == null)
		{
			return getHTMLPayType();
		}
		return getResponse();
	}
	/**
	 * LogInfo 接口方法
	 */
	public String SignInfo() {
		// TODO Auto-generated method stub
		StringBuilder log = new StringBuilder("SignInfo:");
		log.append("支付金额:").append(getTotalFee());
		log.append(" 通知地址:").append(getReturnUrl());
		log.append(" 财付通订单号:").append(getTradeNo());
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
		return "TenPayBaseRequest";
	}
	
	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}
	/**
	 * @param id the iD to set
	 */
	public void setID(String id) {
		ID = id;
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
	 * @return the notifyUrl
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}
	/**
	 * @param notifyUrl the notifyUrl to set
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	/**
	 * @return the returnUrl
	 */
	public String getReturnUrl() {
		return returnUrl;
	}
	/**
	 * @param returnUrl the returnUrl to set
	 */
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
	/**
	 * @return the defaultBank
	 */
	public String getdefaultBank() {
		return defaultBank;
	}
	/**
	 * @param defaultBank the defaultBank to set
	 */
	public void setdefaultBank(String defaultBank) {
		this.defaultBank = defaultBank;
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
	 * @return the payTypeResultCode
	 */
	public String getPayTypeResultCode() {
		return payTypeResultCode;
	}
	/**
	 * @param payTypeResultCode the payTypeResultCode to set
	 */
	public void setPayTypeResultCode(String payTypeResultCode) {
		this.payTypeResultCode = payTypeResultCode;
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
	 * @return the payTypeName
	 */
	public String getPayTypeName() {
		return payTypeName;
	}
	/**
	 * @param payTypeName the payTypeName to set
	 */
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	/**
	 * @return the waresCode
	 */
	public String getWaresCode() {
		return waresCode;
	}
	/**
	 * @param waresCode the waresCode to set
	 */
	public void setWaresCode(String waresCode) {
		this.waresCode = waresCode;
	}
	/**
	 * @return the expireTime
	 */
	public Long getExpireTime() {
		return expireTime;
	}
	/**
	 * @param expireTime the expireTime to set
	 */
	public void setExpireTime(Long expireTime) {
			this.expireTime = expireTime;
	}
	/**
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}
	/**
	 * @param resultType the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
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
	 * @return the defaultBank
	 */
	public String getDefaultBank() {
		return defaultBank;
	}
	/**
	 * @param defaultBank the defaultBank to set
	 */
	public void setDefaultBank(String defaultBank) {
		this.defaultBank = defaultBank;
	}
	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}
	/**
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
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
	 * @return the clientIP
	 */
	public String getClientIP() {
		return ClientIP;
	}
	/**
	 * @param clientIP the clientIP to set
	 */
	public void setClientIP(String clientIP) {
		ClientIP = clientIP;
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
	 * @return the sPID
	 */
	public String getSPID() {
		return SPID;
	}
	/**
	 * @param spid the sPID to set
	 */
	public void setSPID(String spid) {
		SPID = spid;
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
	/**
	 * @return the asyncFlag
	 */
	public Boolean getAsyncFlag()
	{
		return asyncFlag;
	}
	/**
	 * @param asyncFlag the asyncFlag to set
	 */
	public void setAsyncFlag(Boolean asyncFlag)
	{
		this.asyncFlag = asyncFlag;
	}
	
}
