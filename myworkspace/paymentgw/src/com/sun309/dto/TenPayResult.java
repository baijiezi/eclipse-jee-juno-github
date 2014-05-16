package com.sun309.dto;

import com.sun309.service.BaseReceive;
import com.sun309.util.Constants;
import com.sun309.util.Validate;
import com.sun309.util.Validation;

/**
 * 财付通GET数据实体类
 * @author Wormwood OPC_GROUP
 */
public class TenPayResult implements Validation,BaseReceive 
{
	private String 		attach;				/** attach 商家数据包，原样返回 		*/
	private String 		bargainorID;		/** bargainor_id 卖方账号(商户spid) 	*/
	private String 		cmdNo;				/** cmdno 任务代码					*/
	private String 		date;				/** date 商户日期 yyyyMMdd 			*/
	private String 		feeType;			/** fee_type 货币类型 				*/
	private String 		payResult;			/** 交易结果状态 pay_result 			*/
	private String 		sign;				/** 加密验证字段 sign 				*/ 
	private Long 		TotalFee;			/** 支付总金额 TotalFee 单位：分		*/
	private String 		transactionId;		/** 财付通订单号[流水号] transactionId 对帐	*/
	private String 		spBillNo;			/** 财付通外部订单号 sp_billno [多支付流水号] */
	
	private Validate	validator;			/** 验证工具类						*/
	private String		validateInfo;		/** 验证结果信息						*/
	
	/** 多支付实际支付类型    默认值方式初始化[为了增加异常处理] */
	private Integer	realPayType = new Integer(Constants.TEN_PAY);
	
	/**
	 * Validation方法
	 */
	public boolean validate() {
		setValidateInfo("Init");
		if(getValidator() == null) 							{setValidateInfo("验证类加载失败");					return Boolean.FALSE;}
		if(!getValidator().isString(getCmdNo())) 			{setValidateInfo("财付通网关服务类型参数验证失败");	return Boolean.FALSE;}
		if(!getValidator().isString(getBargainorID())) 		{setValidateInfo("商户ID验证失败");					return Boolean.FALSE;}
		if(!getValidator().isString(getDate())) 			{setValidateInfo("外部订单为空验证失败");				return Boolean.FALSE;}
		if(!getValidator().isString(getFeeType()))			{setValidateInfo("交易类型号为空验证失败");			return Boolean.FALSE;}
		if(!getValidator().isNum(getTotalFee().toString()))	{setValidateInfo("交易总金额数字验证失败");			return Boolean.FALSE;}
		if(!getValidator().isString(getPayResult())) 		{setValidateInfo("货币类型为空验证失败");				return Boolean.FALSE;}
		if(!getValidator().isString(getSign())) 			{setValidateInfo("签名结果为空验证失败");				return Boolean.FALSE;}
		if(!getValidator().isString(getSpBillNo()))			{setValidateInfo("订单状态号为空验证失败");			return Boolean.FALSE;}
		setValidateInfo("Not Null Validate SUCCEED!");
		return Boolean.TRUE;
	}
	
	/** 
	 * @return TradeStatus
	 * BaseReceive 方法 交易状态验证方法 
	 */
	public boolean validateTradeStatus()
	{
		if(getPayResult().equals("0"))
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
			.append(" CmdNo:").append(getCmdNo())
			.append(" BargainorID:").append(getBargainorID())
			.append(" Date:").append(getDate())
			.append(" FeeType:").append(getFeeType())
			.append(" TotalFee:").append(getTotalFee())
			.append(" PayResult:").append(getPayResult())
			.append(" Sign:").append(getSign())
			.append(" SpBillNo:").append(getSpBillNo());
		return builder.toString();
	}
	
	/**
	 * @return the totalFee
	 * BaseReceive 方法 获得交易总金额
	 */
	public Long getTotalFee() {
		// TODO Auto-generated method stub
		return this.TotalFee;
	}
	
	/**
	 * @return the tradeNo
	 * BaseReceive 方法 获得交易流水号
	 */
	public String getTradeNo() {
		// TODO Auto-generated method stub
		return getTransactionId();
	}
	
	/**
	 * @return the outTradeNo
	 * BaseReceive 方法 获得外部订单号[多支付流水ID号]
	 */
	public String getOutTradeNo() {
		return getSpBillNo();
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
	 * @return the attach
	 */
	public String getAttach() {
		return attach;
	}

	/**
	 * @param attach the attach to set
	 */
	public void setAttach(String attach) {
		this.attach = attach;
	}

	/**
	 * @return the bargainorID
	 */
	public String getBargainorID() {
		return bargainorID;
	}

	/**
	 * @param bargainorID the bargainorID to set
	 */
	public void setBargainorID(String bargainorID) {
		this.bargainorID = bargainorID;
	}

	/**
	 * @return the cmdNo
	 */
	public String getCmdNo() {
		return cmdNo;
	}

	/**
	 * @param cmdNo the cmdNo to set
	 */
	public void setCmdNo(String cmdNo) {
		this.cmdNo = cmdNo;
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
	 * @return the feeType
	 */
	public String getFeeType() {
		return feeType;
	}

	/**
	 * @param feeType the feeType to set
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	/**
	 * @return the payResult
	 */
	public String getPayResult() {
		return payResult;
	}

	/**
	 * @param payResult the payResult to set
	 */
	public void setPayResult(String payResult) {
		this.payResult = payResult;
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
	 * @param TotalFee the TotalFee to set
	 */
	public void setTotalFee(Long TotalFee) {
		this.TotalFee = TotalFee;
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
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the spBillNo
	 */
	public String getSpBillNo() {
		return spBillNo;
	}

	/**
	 * @param spBillNo the spBillNo to set
	 */
	public void setSpBillNo(String spBillNo) {
		this.spBillNo = spBillNo;
	}

	/**
	 * @param realPayType the realPayType to set
	 */
	public void setRealPayType(Integer realPayType)
	{
		this.realPayType = realPayType;
	}
	
}
