/**
 * 支付宝请求基本信息
 * 实现BaseRequest接口
 */
package com.sun309.impl;

import org.apache.log4j.Logger;

import com.alipay.util.CheckURL;
import com.alipay.util.Payment;
import com.sun309.dto.ActionUrl;
import com.sun309.dto.NetPayData;
import com.sun309.factory.Factory;
import com.sun309.service.BaseRequest;
import com.sun309.service.LogService;
import com.sun309.service.Request;
import com.sun309.util.Constants;
import com.sun309.util.NetPayFormatTools;
import com.sun309.util.Validate;

/**
 * @author Wormwood OPC_GROUP
 *
 */
public class AliPayBaseRequest implements BaseRequest
{
	private Logger 		syslog 	= 	Logger.getLogger(AliPayBaseRequest.class);
	private LogService 	logger 	= 	(LogService)Factory.create(NetPayLogServiceImpl.class);
	
	/** 多支付服务唯一ID号 */
	private String ID;
	
	/** 外部业务系统交易订单号 API_NOT_NULL */
	private String outTradeNo;	
	
	/** 交易总金额 API_NOT_NULL */
	private Long   totalFee;	
	
	/** 商品名称 API_NOT_NULL */
	private String subject;		
	
	/** 通知地址 API */
	private String returnUrl	="http://www.sun309.com";		
	
	/** 商品代码 **/
	private String 		waresCode;
	
	/** 返回XML数据部分 **/
	private String 		response;	
	
	/** 请求设置的超时时间 Long 毫秒 default:3600*1000 */
	private Long 		expireTime	= new Long(3600000);
	
	/** 请求设置的返回结果类型 [HTML|XML] default:HTML*/
	private String 		resultType;	
	
	/**
	 * 生成签名参数列表
	 */
	/** 支付宝服务地址 API_NOT_NULL */
	private String payGateway	="https://www.alipay.com/cooperate/gateway.do?";
	
	/** 支付发起的方式 API_NOT_NULL */
	private String service		="create_direct_pay_by_user";
	
	/** 签名方式 API_NOT_NULL */
	private String signType		="MD5";	
	
	/** 字符编码：用于签名 生成的加密URL API_NOT_NULL */
	private String inputCharset	="utf-8";
	
	/** 合做商户签约ID API_NOT_NULL*/
	private String partner		="2088102681649780";
	
	/** MD5 加密KEY 接口文档中没有 */
	private String key			="30ruggr3udodi58jn27kucd5fka7ixky";	
	
	/** 产品详细信息地址 API */
	private String showUrl		="http://www.sun309.com";
	
	/** 商品描述 API 默认值：详情登录阳光医疗网  Init之后更新为：外部订单号[OPCN] */
	private String body			="详情登录阳光医疗网";
	
	/** 支付宝要求的支付类型参数 default:1 API_NOT_NULL*/
	private String paymentType	="1";	
	
	/** 商户Email地址 用于支付宝商家认证 API_NOT_NULL*/
	private String sellerEmail	="13538788208";
	
	/**	交易结果数据接收地址 API_NOT_NULL*/
	private String notifyUrl	="http://124.172.245.98:8080/paymentgw/servlet/AliPayReceive";
	
	/**支付方法 接口文档中没有*/
	private String payMethod	="bankPay";
	
	/** 默认银行 接口文档中没有*/
	private String defaultBank	="ICBCB2C";		
	/**private String sign;			API_NOT_NULL 原Alipy没有使用这个参数可能接口文档不是最新。*/
	
	/** 客户端 实际IP */
	private String clientIP;
	
	/**
	 * 签名结果
	 */
	private String signRsult;		/**签名后的数据 实际就是一个加密的URL地址*/
	
	/**
	 * 生成XML结果的配置参数列表
	 */
	/**[actionUrl|actionForm]签名结果类型*/
	private String 		payTypeResultCode	="actionUrl"; 
	
	/**[mapping.conf.php payType]实际支付类型*/
	private Integer 	payTypeCode			= new Integer(Constants.ALI_PAY);	
	
	/** 支付方式的中文名称 */
	private String 		payTypeName			="支付宝"; 
	
	
	/**
	 * 数据验证成员
	 */
	private Validate 	validater = null;		/** 验证类成员 */
	private String 		validateInfo = "Init";;	/** 验证结果信息*/
	
	/**
	 * 验证标志位
	 */
	private Boolean initFlag 	= Boolean.FALSE;	/** Init()方法是否已调用标识	*/
	private Boolean asyncFlag 	= Boolean.FALSE;	/** 是否异步方法	默认同步生成	*/
	private Boolean	signFlag	= Boolean.FALSE;	/** Sign()方法是否已调用标识	*/
	
	/**
	 * 返回数据验证参数
	 */
	private String alipayNotifyURL	= "http://notify.alipay.com/trade/notify_query.do?";		/** 接收数据验证地址		*/
	private String notifyId;			/**	返回数据验证的ID		*/
	/**
	 * BaseRequest 接口方法
	 */
	public void Init(Request req) {
		// TODO Auto-generated method stub
		setOutTradeNo(req.getOutTtradeNo());
		setWaresCode(req.getWaresCode());
		/** 商品描述 Init之后更新为：外部订单号[OPCN] */
		setBody(req.getOutTtradeNo());
		setTotalFee(req.getTotalFee());
		setReturnUrl(req.getReturnUrl());
		setResultType(req.getResultType());
		setExpireTime(req.getExpireTime());
		setSubject(NetPayFormatTools.getNetPaySubject(getWaresCode()));
		setClientIP(req.getClientIP());
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
	 * BaseRequest 接口方法
	 */
	public void sign() {
		try
		{
			
			writeLog("AAAAAAAAAAAAAAAAAAAAAA"+getTotalFee()+"AAAAAAAAAAAAAAAAAAAAAA");
			setSignRsult(Payment.CreateUrl(getPayGateway(), getService(), getSignType(), getID(), 
					getInputCharset(), getPartner(), getKey(), getShowUrl(),getBody(), NetPayFormatTools.ToAliPayTotalFee(getTotalFee()).toString(),
					getPaymentType(), getSellerEmail(), getSubject(), getNotifyUrl(), getReturnUrl(), getPayMethod(), getDefaultBank()));
			if(getValidater().isObject(getSignRsult()))
			{
				setSignFlag(Boolean.TRUE);
				setValidateInfo("生成签名非空验证成功!");
			}
			else
			{
				setValidateInfo(new StringBuilder("外部订单：").append(getOutTradeNo()).append("多支付流水号:").append(getID())
						.append("支付宝数字签名生成失败!").toString());
				setSignFlag(Boolean.FALSE);
				writeLog(getValidateInfo());
			}
		}
		catch(Exception e)
		{
			writeLog("XXXXXXXXXXXX SIGN EXCEPTION XXXXXXXXXXXXXXX");
			e.printStackTrace();
		}
	}
	
	public NetPayData getData() 
	{
		// TODO Auto-generated method stub
		NetPayData data = new NetPayData();
		data.setPayType(getPayTypeCode());
		data.setOutOrderNo(getOutTradeNo());
		data.setTotalFee(getTotalFee());
		data.setWaresCode(getWaresCode());
		data.setExpireTime(getExpireTime());
		data.setCheckKey(getKey());
		data.setCheckValue(getID());
		data.setClientIP(getClientIP());
		data.setReturnURL(getReturnUrl());
		/** data.setCheckValue((getValidater().isString(getSignRsult()))?getSignRsult():getKey()); Data too long for column 'checkValue' */
		return data;
	}
	
	public boolean verifyReceieData() 
	{
		String res = "";
		try
		{
			StringBuilder apliPayNontifyURL = new StringBuilder(getAlipayNotifyURL())
			.append("partner=").append(getPartner())
			.append("&notify_id=").append(getNotifyId());
		
			res = CheckURL.check(apliPayNontifyURL.toString());
		}
		catch(Exception e)
		{
			writeLog(new StringBuilder(e.getMessage()).append(e.getStackTrace()).append(e.getCause()).append(e.getClass()));
		}
		if(res == null || res == "") 
			return Boolean.FALSE;
		return res.equals("true") ? Boolean.TRUE : Boolean.FALSE;
	}
	
	/**
	 * 验证接口方法
	 */
	public boolean validate() {
		if(getValidater() == null )							{setValidateInfo("验证类加载失败"); 					return Boolean.FALSE;}
		if(!getValidater().isString(getPayGateway())) 		{setValidateInfo("支付宝网关地址URL验证失败"); 		return Boolean.FALSE;}
		if(!getValidater().isString(getService())) 			{setValidateInfo("支付宝网关服务类型参数验证失败"); 	return Boolean.FALSE;}
		if(!getValidater().isString(getSignType())) 		{setValidateInfo("签名方式验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getInputCharset())) 	{setValidateInfo("参数默认字符集验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isNum(getPartner())) 			{setValidateInfo("商户ID验证失败"); 					return Boolean.FALSE;}
		if(!getValidater().isString(getKey())) 				{setValidateInfo("加密KEY验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getShowUrl())) 			{setValidateInfo("默认详细地址验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isString(getBody())) 			{setValidateInfo("默认商品介绍验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isString(getPaymentType())) 		{setValidateInfo("参数支付类型验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isMobile(getSellerEmail())) 		{setValidateInfo("商户Email(手机帐号)验证失败"); 		return Boolean.FALSE;}
		if(!getValidater().isString(getPayMethod())) 		{setValidateInfo("服务支付方法验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isString(getDefaultBank())) 		{setValidateInfo("默认银行验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isObject(getPayTypeCode())) 		{setValidateInfo("实际支付类型验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isString(getPayTypeName())) 		{setValidateInfo("支付中文名验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getNotifyUrl())) 		{setValidateInfo("数据返回地址验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isString(getSubject())) 			{setValidateInfo("商品名称验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getPayTypeResultCode())){setValidateInfo("签名结果类型验证失败"); 			return Boolean.FALSE;}
		/**	判定初始化完成标识位,没调用init()之前不再向下验证	*/	
		if(!getInitFlag()) 									{validateInfo = "未调用初始化方法！"; 				return Boolean.FALSE;}	
		/** 主业务流程是Init后马上 Set ID进来 再运行签名方法 AfterInfo方法是 Init 和 set 后的验证验证信息 */
		if(!getValidater().isString(getWaresCode())) 		{setValidateInfo("商品代码验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getOutTradeNo())) 		{setValidateInfo("外部订单号验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isObject(getExpireTime())) 		{setValidateInfo("超时时间验证失败"); 				return Boolean.FALSE;}
		/** 异步生成时: 直接返成功不再向下验证 */
		if(!getAsyncFlag() && !getSignFlag()) 				{setValidateInfo("ASYNC SUCCEED"); 					return Boolean.TRUE;}
		if(!getValidater().isNetPayID(getID())) 			{setValidateInfo("多支付流水号格式验证失败");			return Boolean.FALSE;}
		if(!getValidater().isObject(getTotalFee()))			{setValidateInfo("支付金额验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getReturnUrl())) 		{setValidateInfo("通知地址验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getResultType())) 		{setValidateInfo("返回结果类型验证失败"); 			return Boolean.FALSE;}
		/**	判定签名生成标识位,没调用sign()之前不再向下验证	*/	
		if(!getSignFlag()) 									{setValidateInfo("未调用签名方法！"); 				return Boolean.FALSE;}	
		if(!validater.isString(getSignRsult()))	{setValidateInfo("生成签名结果字符串为空格式验证失败"); 			return Boolean.FALSE;}
		writeLog("XXXXXXXXXXXXXXXXXXXXXXXXX VALIDATE SUCCEED XXXXXXXXXXXXXXXXXXXXX");
		setValidateInfo("SUCCEED");
		return Boolean.TRUE;
	}
	
	/**
	 * LogInfo 接口方法
	 */
	public String InitInfo() {
		StringBuilder log = new StringBuilder("InitInfo:");
		log.append(" 商品代码:").append(getWaresCode());
		log.append(" 商品名称:").append(getSubject());
		log.append(" 超时时间:").append(getExpireTime());
		log.append(" 客户端IP:").append(getClientIP());
		log.append(" 外部订单号:").append(getOutTradeNo());
		return log.toString();
	}
	
	/**
	 * LogInfo 接口方法
	 */
	public String LoadInfo() {
		// TODO Auto-generated method stub
		StringBuilder log = new StringBuilder("LoadInfo:");
		log.append("支付宝服务地址:").append(getPayGateway());
		log.append(" 支付发起的方式:").append(getService());
		log.append(" 签名方式:").append(getSignType());
		log.append(" 字符编码:").append(getInputCharset());
		log.append(" 合做商户签约ID:").append(getPartner());
		log.append(" 加密KEY:").append(getKey());
		log.append(" 产品详细信息地址:").append(getShowUrl());
		log.append(" 商品描述:").append(getBody());
		log.append(" 支付宝要求的支付类型参数:").append(getPaymentType());
		log.append(" 商户Email:").append(getSellerEmail());
		log.append(" 支付方法:").append(getPayMethod());
		log.append(" 默认银行:").append(getDefaultBank());
		log.append(" 签名结果类型:").append(getPayTypeResultCode());
		log.append(" 实际支付类型:").append(getPayTypeCode());
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
		log.append("生成签名结果:").append(getSignRsult());
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
		return "AliPayBaseRequest";
	}
	
	/**
	 * @return the clientIP
	 */
	public String getClientIP() {
		return clientIP;
	}
	/**
	 * @param clientIP the clientIP to set
	 */
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
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
	 * @return the signType
	 */
	public String getSignType() {
		return signType;
	}
	/**
	 * @param signType the signType to set
	 */
	public void setSignType(String signType) {
		this.signType = signType;
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
	 * @return the showUrl
	 */
	public String getShowUrl() {
		return showUrl;
	}
	/**
	 * @param showUrl the showUrl to set
	 */
	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
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
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * @return the sellerEmail
	 */
	public String getSellerEmail() {
		return sellerEmail;
	}
	/**
	 * @param sellerEmail the sellerEmail to set
	 */
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
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
	 * @return the payMethod
	 */
	public String getpayMethod() {
		return payMethod;
	}
	/**
	 * @param payMethod the payMethod to set
	 */
	public void setpayMethod(String payMethod) {
		this.payMethod = payMethod;
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
	 * @return the payMethod
	 */
	public String getPayMethod() {
		return payMethod;
	}
	/**
	 * @param payMethod the payMethod to set
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
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
	 * @return the alipayNotifyURL
	 */
	public String getAlipayNotifyURL() {
		return alipayNotifyURL;
	}
	/**
	 * @param alipayNotifyURL the alipayNotifyURL to set
	 */
	public void setAlipayNotifyURL(String alipayNotifyURL) {
		this.alipayNotifyURL = alipayNotifyURL;
	}
	/**
	 * @return the notifyId
	 */
	public String getNotifyId() {
		return notifyId;
	}
	/**
	 * @param notifyId the notifyId to set
	 */
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
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
