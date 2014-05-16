package com.sun309.dto;

import com.sun309.service.BaseReceive;
import com.sun309.util.Constants;
import com.sun309.util.Validate;
import com.sun309.util.Validation;

public class ChinaPayResult implements Validation,BaseReceive
{
	private String	orderId;			/** 银联用订单号：前9位是商户号的后9位 后7位是外部订单号的后7位 	*/
	private String 	outTradeNo;			/** 多支付系统流水ID号 实际支付的外部订单号*/
	private String	transDate;			/** 交易日期	yyyyMMdd	*/
	private String	merId;				/** 商户ID				*/
	private String	transType;			/** 银联交易类型			*/
	private Long	transAmt;			/** 交易总金额			*/
	private	String	curyId;				/** 货币类型				*/
	private String	chkValue;			/** 签名结果				*/
	private String	Priv1;				/** 私有数据 用于标识商品*/
	private String	orderStatus;		/** 订单状态				*/
	private String	gateId;				/** 默认支付银行			*/
	
	private Validate	validator;		/** 验证工具类			*/
	private String		validateInfo;	/** 验证结果信息			*/
	
	/** 多支付实际支付类型    默认值方式初始化[为了增加异常处理] */
	private Integer	realPayType = new Integer(Constants.CHINA_PAY);
	
	/**
	 * Validation方法
	 */
	public boolean validate() {
		setValidateInfo("Init");
		if(getValidator() == null) 							{setValidateInfo("验证类加载失败");			return Boolean.FALSE;}
		if(!getValidator().isString(getTransDate())) 		{setValidateInfo("交易日期为空验证失败");		return Boolean.FALSE;}
		if(!getValidator().isString(getMerId())) 			{setValidateInfo("商户号为空验证失败");		return Boolean.FALSE;}
		if(!getValidator().isString(getOutTradeNo())) 		{setValidateInfo("外部订单为空验证失败");		return Boolean.FALSE;}
		if(!getValidator().isString(getTransType()))		{setValidateInfo("交易类型号为空验证失败");	return Boolean.FALSE;}
		if(!getValidator().isNum(getTransAmt().toString()))	{setValidateInfo("交易总金额数字验证失败");	return Boolean.FALSE;}
		if(!getValidator().isString(getCuryId())) 			{setValidateInfo("货币类型为空验证失败");		return Boolean.FALSE;}
		if(!getValidator().isString(getChkValue())) 		{setValidateInfo("签名结果为空验证失败");		return Boolean.FALSE;}
		if(!getValidator().isString(getOrderStatus()))		{setValidateInfo("订单状态号为空验证失败");	return Boolean.FALSE;}
		setValidateInfo("Not Null Validate SUCCEED!");
		return Boolean.TRUE;
	}
	
	/** 
	 * @return TradeStatus
	 * BaseReceive 方法 交易状态验证方法 
	 */
	public boolean validateTradeStatus()
	{
		if(getOrderStatus().equals("1001") || getOrderStatus().equals("1003"))
		{
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	/**
	 * @return InitInfo 
	 * BaseReceive 方法 数据接收初始化日志
	 */
	public String getInitInfo()
	{
		StringBuilder builder = new StringBuilder("Init Info :")
			.append(" TransDate:").append(getTransDate())
			.append(" MerId:").append(getMerId())
			.append(" OutTradeNo:").append(getOutTradeNo())
			.append(" TransType:").append(getTransType())
			.append(" TransAmt:").append(getTransAmt())
			.append(" CuryId:").append(getCuryId())
			.append(" ChkValue:").append(getChkValue())
			.append(" OrderStatus:").append(getOrderStatus());
		return builder.toString();
	}
	
	/**
	 * @return the totalFee
	 * BaseReceive 方法 获得交易总金额
	 */
	public Long getTotalFee() {
		// TODO Auto-generated method stub
		return getTransAmt();
	}
	/**
	 * @return the tradeNo
	 * BaseReceive 方法 获得交易流水号
	 */
	public String getTradeNo() {
		// TODO Auto-generated method stub
		return getOrderId();
	}
	/**
	 * @return the outTradeNo
	 * BaseReceive 方法 获得外部订单号[多支付流水ID号]
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}
	
	/**
	 * @return the realPayType
	 * BaseReceive 方法 获得多支付实际支付类型
	 */
	public Integer getRealPayType()
	{
		return realPayType;
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
	 * @param outTradeNo the outTradeNo to set
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
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
	 * @return the transAmt
	 */
	public Long getTransAmt() {
		return transAmt;
	}

	/**
	 * @param transAmt the transAmt to set
	 */
	public void setTransAmt(Long transAmt) {
		this.transAmt = transAmt;
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
	 * @return the chkValue
	 */
	public String getChkValue() {
		return chkValue;
	}

	/**
	 * @param chkValue the chkValue to set
	 */
	public void setChkValue(String chkValue) {
		this.chkValue = chkValue;
	}

	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * @return the gateId
	 */
	public String getGateId() {
		return gateId;
	}

	/**
	 * @param gateId the gateId to set
	 */
	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	/**
	 * @return the validator
	 */
	public Validate getValidator() {
		return validator;
	}

	/**
	 * @param validator the validator to set
	 */
	public void setValidator(Validate validator) {
		this.validator = validator;
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
	 * @return the priv1
	 */
	public String getPriv1() {
		return Priv1;
	}

	/**
	 * @param priv1 the priv1 to set
	 */
	public void setPriv1(String priv1) {
		Priv1 = priv1;
	}

	public void setRealPayType(Integer realPayType)
	{
		this.realPayType = realPayType;
	}
}
