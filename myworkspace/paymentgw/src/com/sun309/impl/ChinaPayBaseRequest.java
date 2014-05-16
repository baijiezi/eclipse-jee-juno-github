/**
 * 银联网银请求基本信息
 * 实现BaseRequest接口
 */
package com.sun309.impl;

import java.net.URLDecoder;

import org.apache.log4j.Logger;

import chinapay.PrivateKey;
import chinapay.SecureLink;

import com.sun309.dto.ActionForm;
import com.sun309.dto.NetPayData;
import com.sun309.factory.Factory;
import com.sun309.service.BaseRequest;
import com.sun309.service.LogService;
import com.sun309.service.Request;
import com.sun309.util.Constants;
import com.sun309.util.DateUtils;
import com.sun309.util.NetPayFormatTools;
import com.sun309.util.Validate;

/**
 * @author Wormwood OPC_GROUP
 *
 */
public class ChinaPayBaseRequest implements BaseRequest
{
	private Logger 		syslog 	= 	Logger.getLogger(ChinaPayBaseRequest.class);
	private LogService 	logger 	= 	(LogService)Factory.create(NetPayLogServiceImpl.class);
	 
	/** 多支付服务唯一ID号 */
	private String ID;
	
	/** 商户号 */
	private String  merId		= "808080450202901";
	 
	/** 订单号：外部订单号 16个字节	*/
	private String  outTradeNo; 
	
	/** 银联用 订单号：订单号，长度为16个字节的数字串，由商户系统生成，第5位到第9位必须是商户号的后5位：02901*/
	private String orderId;
	
	/** 交易金额：以分为单位 12个字节 */
	private Long  totalFee; 
	
	/** 货币类型：156指人民 */
	private String  curyId		= "156";
	
	/** 交易日期 格式：YYYYMMDD */
	private String  transDate	= null ;
	
	/** 银联交易类型：0001-消费,0002退款 */
	private String  transType	= "0001" ;
	
	/** 私有数据：银联原样返回的数据 一般设置为商品代码*/
	private String 	privateData	= "MultiPayService-SunMedicalNet-ChinaPay";
	
	/** 私有密匙 */
	private PrivateKey privateKey = null;
	
	/** 公有密匙 */
	private PrivateKey publicKey = null;
	
	/** 私有密匙路径 */
	private String privateKeyPath;
	
	/** 公有密匙路径 */
	private String publicKeyPath;
	
	/** 签名验证值 */
	private String checkValue;
	
	/** 网银程序版本号 */
	private String version = "20070129";
	
	/** 网关号：指默认支付的银行 0001：中国银联 */
	private String  GateId	= "8607";
	
	/** 交易返回结果的状态 */
	private String  OrderStatus;
	
	/** 通知地址 默认：网站首页，一般要替换为实际通知地址 	*/
	private String returnUrl	="http://www.sun309.com";
	
	/** 数据接收地址:多支付服务 用来接收对方返回数据的地址 */
	private String notifyUrl	="http://124.172.245.98:8080/paymentgw/servlet/ChinaPayReceive";
	
	/** 网银表单的提交地址 */
	private String actionUrl	="https://payment.ChinaPay.com/pay/TransGet";
	
	/** 表单提交方法 */
	private String formMethod = "POST";
	
	/** 商品代码 **/
	private String 		waresCode;
	
	/** 返回XML|HTML数据部分 **/
	private String 		response;
	
	/** 请求设置的超时时间 Long 毫秒 default:3600*1000 */
	private Long 		expireTime	= new Long(3600000);
	
	/** 请求设置的返回结果类型 [HTML|XML] default:HTML*/
	private String 		resultType;
	
	/** 客户端 实际IP */
	private String clientIP;
	
	/**
	 * 生成XML结果的配置参数列表
	 */
	/**[actionUrl|actionForm]签名结果类型*/
	private String 		payTypeResultCode	="actionForm";
	
	/**[mapping.conf.php payType]实际支付类型*/
	private Integer 	payTypeCode			= new Integer(Constants.CHINA_PAY);
	
	/** 支付方式的中文名称 */
	private String 		payTypeName			="网付通(ChinaPay)";
	
	
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
	 * BaseRequest 接口方法
	 */
	public void Init(Request req) {
		// TODO Auto-generated method stub
		setOutTradeNo(req.getOutTtradeNo());
		setWaresCode(req.getWaresCode());
		setReturnUrl(req.getReturnUrl());
		setResultType(req.getResultType());
		setPayTypeCode(req.getPayType());
		setExpireTime(req.getExpireTime());
		setTotalFee(req.getTotalFee());
        setTransDate(DateUtils.getFormatDate("yyyyMMdd"));
        setPrivateData(req.getOutTtradeNo());
        setClientIP(req.getClientIP());
		if(!getPayTypeCode().equals(req.getPayType()))
		{
			setValidateInfo(new StringBuilder("实际支付类型和请求类型不同异常! 详细：实际类型")
				.append(getPayTypeCode()).append("调用传入的类型：").append(req.getPayType()).toString());
			writeLog(getValidateInfo());
		}
		else
		{
			setInitFlag(Boolean.TRUE);
		}
	}
	
	/**
	 * BaseRequest 接口方法
	 */
	public void sign() {
		// TODO Auto-generated method stub
		try
		{
			setOrderId(NetPayFormatTools.ChinaPayOrderID(getID(), getMerId()));
			setPrivateKey(new PrivateKey());
			String path = this.getClass().getClassLoader().getResource(Constants.CHINAPAY_PRIVATE_KEY_PATH).getPath();
			if(path.contains("%"))
				path = URLDecoder.decode(path, "utf-8");
			setPrivateKeyPath(path);
			path = null;
			boolean flag =getPrivateKey().buildKey(getMerId(), 0, getPrivateKeyPath());
			if (flag == false) {
				writeLog(new StringBuilder("外部订单：").append(getOutTradeNo()).append("网银数字签名私有密匙生成失败!"));
				setSignFlag(Boolean.FALSE);
				setValidateInfo(new StringBuilder("外部订单：").append(getOutTradeNo()).append("网银数字签名私有密匙生成失败!").toString());
				return;
			}
			SecureLink t = new SecureLink (getPrivateKey());
			setCheckValue(t.Sign(new StringBuilder(getMerId()).append(getOrderId()).append(NetPayFormatTools.ChinaPayTotalFee(getTotalFee()))
					.append(getCuryId()).append(getTransDate()).append(getTransType()).append(getPrivateData()).toString()));	
			setValidateInfo(new StringBuilder("外部订单：").append(getOutTradeNo())
					.append("网银数字签名生成成功!").toString());
			setSignFlag(Boolean.TRUE);
		}
		catch(Exception e)
		{
			writeLog("XXXXXXXXXXXX SIGN EXCEPTION XXXXXXXXXXXXXXX");
			e.printStackTrace();
		}
	}
	
	/**
	 * BaseRequest 接口方法
	 */
	public String getHTMLPayType()
	{
		// TODO Auto-generated method stub
		ActionForm form = new ActionForm();
		form.setActionUrl(getActionUrl());
		form.setPayTypeName(getPayTypeName());
		form.setResultType(getResultType());
		form.setPayTypeCode(getPayTypeCode());
		form.setField("MerId", getMerId(), "");
		form.setField("OrdId", getOrderId(), "");
		form.setField("TransAmt", NetPayFormatTools.ChinaPayTotalFee(getTotalFee()),"");
		form.setField("CuryId", getCuryId(), "");
		form.setField("TransDate", getTransDate(), "");
		form.setField("TransType", getTransType(), "");
		form.setField("Version", getVersion(), "");
		form.setField("BgRetUrl", getNotifyUrl(), "");
		form.setField("PageRetUrl", getReturnUrl(), "");
		form.setField("GateId", "", "");					/** 实际生成表单时把GateId 置空,因为之前测试需要多种GateId */				
		form.setField("Priv1", getPrivateData(), "");
		form.setField("ChkValue", getCheckValue(), "");
		if(getResultType().equals("HTML"))
		{
			
			setResponse(form.toHTML());
			form	=	null;
			return getResponse();
		}
		else
		{
			setResponse(form.toXML());
			form = null;
			return getResponse();
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
		data.setCheckKey(getOutTradeNo());
		data.setClientIP(getClientIP());
		data.setReturnURL(getReturnUrl());
		data.setCheckValue((getValidater().isString(getCheckValue()))?getCheckValue():getID());
		return data;
	}
	
	public boolean verifyReceieData() {
		try
		{
			setPublicKey(new PrivateKey());
			String path = this.getClass().getClassLoader().getResource(Constants.CHINAPAY_PUBLIC_KEY_PATH).getPath();
			if(path.contains("%"))
				path = URLDecoder.decode(path, "utf-8");
			setPublicKeyPath(path);
			boolean flag = getPublicKey().buildKey("999999999999999", 0, getPublicKeyPath());
			path = null;
			
			if (flag == false) {
				writeLog(new StringBuilder("验证签名时生成公用密匙失败！外部订单号：").append(getOutTradeNo()));
				setValidateInfo(new StringBuilder("验证签名时生成公用密匙失败！外部订单号：").append(getOutTradeNo()).toString());
			    return Boolean.FALSE;
			}
			
			SecureLink t = new SecureLink(getPublicKey());
			boolean res = t.verifyAuthToken(getMerId(), getOrderId(), NetPayFormatTools.ChinaPayTotalFee(getTotalFee()), 
					getCuryId(), getTransDate(), getTransType(), getOrderStatus(), getCheckValue());
			setValidateInfo(new StringBuilder("数据真实性验证 结果：").append(res).toString());
			return res == Boolean.TRUE? Boolean.TRUE : Boolean.FALSE;
		}
		catch(Exception e)
		{
			writeLog("XXXXXXXXXXXX VERIFY EXCEPTION XXXXXXXXXXXXXXX");
			writeLog(new StringBuilder(e.getMessage()).append(e.getStackTrace()).append(e.getCause()).append(e.getClass()));
			return Boolean.FALSE;
		}
	}
	
	/**
	 * 验证接口方法
	 */
	public boolean validate() {
		if(getValidater() == null )							{setValidateInfo("验证类加载失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getMerId())) 			{setValidateInfo("银联交易商户号验证失败"); 		return Boolean.FALSE;}
		if(!getValidater().isString(getCuryId())) 			{setValidateInfo("货币类型验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isString(getTransType())) 		{setValidateInfo("银联交易类型信息验证失败"); 	return Boolean.FALSE;}
		if(!getValidater().isString(getPrivateData()))		{setValidateInfo("银联交易私有数据验证失败"); 	return Boolean.FALSE;}
		if(!getValidater().isString(getVersion())) 			{setValidateInfo("银联交易程序版本号验证失败"); 	return Boolean.FALSE;}
		if(!getValidater().isString(getGateId())) 			{setValidateInfo("银联交易默认支付银行验证失败");	return Boolean.FALSE;}
		if(!getValidater().isString(getFormMethod()))		{setValidateInfo("交易表单提交方式验证失败"); 	return Boolean.FALSE;}
		if(!getValidater().isString(getNotifyUrl())) 		{setValidateInfo("数据返回地址验证失败"); 		return Boolean.FALSE;}
		if(!getValidater().isString(getActionUrl())) 		{setValidateInfo("银联交易地址验证失败"); 		return Boolean.FALSE;}
		if(!getValidater().isString(getPayTypeName()))		{setValidateInfo("支付中文名验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isString(getPayTypeResultCode())){setValidateInfo("签名结果类型验证失败"); 		return Boolean.FALSE;}
		/**	判定初始化完成标识位,没调用init()之前不再向下验证	*/	
		if(!getInitFlag()) 									{setValidateInfo("未调用初始化方法！"); 			return Boolean.FALSE;}	
		/** 主业务流程是Init后马上 Set ID进来 再运行签名方法 AfterInfo方法是 Init 和 set 后的验证验证信息 */
		if(!getValidater().isString(getWaresCode()))		{setValidateInfo("商品代码验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isObject(getExpireTime())) 		{setValidateInfo("超时时间验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isString(getOutTradeNo())) 		{setValidateInfo("外部订单号验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isNum(getTransDate()))			{setValidateInfo("银联订单日期验证失败"); 		return Boolean.FALSE;}
		if(!getValidater().isObject(getPayTypeCode())) 		{setValidateInfo("实际支付类型验证失败"); 		return Boolean.FALSE;}
		/** 异步生成时: 直接返成功不再向下验证 */
		if(!getAsyncFlag() && !getSignFlag()) 				{setValidateInfo("ASYNC SUCCEED"); 				return Boolean.TRUE;}
		if(!getValidater().isString(getResultType()))		{setValidateInfo("返回结果类型验证失败"); 		return Boolean.FALSE;}
		if(!getValidater().isString(getReturnUrl())) 		{setValidateInfo("通知地址验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isObject(getTotalFee()))			{setValidateInfo("支付金额验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isNetPayID(getID())) 			{setValidateInfo("多支付流水号格式验证失败");		return Boolean.FALSE;}
		/**	判定签名生成标识位,没调用sign()之前不再向下验证	*/	
		if(!getSignFlag()) 									{setValidateInfo("未调用签名方法！"); 			return Boolean.FALSE;}	
		if(!getValidater().isNum(getOrderId()))				{setValidateInfo("银联订单号格式验证失败"); 		return Boolean.FALSE;}
		if(!getValidater().isString(getCheckValue()))		{setValidateInfo("生成签名结果验证失败"); 		return Boolean.FALSE;}
		writeLog("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX VALIDATE SUCCEED XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		setValidateInfo("SUCCEED");
		return Boolean.TRUE;
	}
	
	/**
	 * LogInfo 接口方法
	 */
	public String InitInfo() {
		StringBuilder log = new StringBuilder("InitInfo:");
		log.append("商品代码:").append(getWaresCode());
		log.append(" 超时时间:").append(getExpireTime());
		log.append(" 银联订单日期:").append(getTransDate());
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
		log.append("银联服务地址:").append(getActionUrl());
		log.append(" 签约商户号:").append(getMerId());
		log.append(" 货币类型:").append(getCuryId());
		log.append(" 交易类型:").append(getTransType());
		log.append(" 加密KEY:").append(getPrivateData());
		log.append(" 程序版本号:").append(getVersion());
		log.append(" 默认银行:").append(getGateId());
		log.append(" 签名结果类型:").append(getPayTypeResultCode());
		log.append(" 实际支付类型:").append(getPayTypeCode());
		log.append(" 支付方式的中文名称：").append(getPayTypeName());
		log.append(" 数据接收地址：").append(getNotifyUrl());
		log.append(" 数据提交地址：").append(getActionUrl());
		return log.toString();
	}
	/**
	 * LogInfo 接口方法
	 */
	public String ResultInfo() {
		// TODO Auto-generated method stub
		if(getResponse() == null || getResponse().equals(""))
		{
			setResponse(getHTMLPayType());
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
		log.append(" 银联订单号:").append(getOrderId());
		log.append(" 生成签名结果:").append(getCheckValue());
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
		return "ChinaPayBaseRequest";
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
	 * @return the merId
	 */
	public String getMerId() {
		return merId;
	}
	/**
	 * @param merId the merId to set
	 */
	public void setMerId(String merId) {
		this.merId = merId;
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
	 * @return the transDate
	 */
	public String getTransDate() {
		return transDate;
	}
	/**
	 * @param transDate the transDate to set
	 */
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	/**
	 * @return the transType
	 */
	public String getTransType() {
		return transType;
	}
	/**
	 * @param transType the transType to set
	 */
	public void setTransType(String transType) {
		this.transType = transType;
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
	 * @return the privateKey
	 */
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	/**
	 * @return the privateKeyPath
	 */
	public String getPrivateKeyPath() {
		return privateKeyPath;
	}
	/**
	 * @param privateKeyPath the privateKeyPath to set
	 */
	public void setPrivateKeyPath(String privateKeyPath) {
		this.privateKeyPath = privateKeyPath;
	}
	/**
	 * @return the checkValue
	 */
	public String getCheckValue() {
		return checkValue;
	}
	/**
	 * @param checkValue the checkValue to set
	 */
	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
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
	 * @return the gateId
	 */
	public String getGateId() {
		return GateId;
	}
	/**
	 * @param gateId the gateId to set
	 */
	public void setGateId(String gateId) {
		GateId = gateId;
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
		if(!payTypeCode.equals(this.payTypeCode))
			setValidateInfo("实际支付类型和类初始化得到的支付类型不同，而产生的异常！");
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
	 * @return the actionUrl
	 */
	public String getActionUrl() {
		return actionUrl;
	}

	/**
	 * @param actionUrl the actionUrl to set
	 */
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the formMethod
	 */
	public String getFormMethod() {
		return formMethod;
	}

	/**
	 * @param formMethod the formMethod to set
	 */
	public void setFormMethod(String formMethod) {
		this.formMethod = formMethod;
	}

	/**
	 * @return the publicKey
	 */
	public PrivateKey getPublicKey() {
		return publicKey;
	}

	/**
	 * @param publicKey the publicKey to set
	 */
	public void setPublicKey(PrivateKey publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * @return the publicKeyPath
	 */
	public String getPublicKeyPath() {
		return publicKeyPath;
	}

	/**
	 * @param publicKeyPath the publicKeyPath to set
	 */
	public void setPublicKeyPath(String publicKeyPath) {
		this.publicKeyPath = publicKeyPath;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
