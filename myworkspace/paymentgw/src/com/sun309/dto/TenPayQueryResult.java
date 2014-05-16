package com.sun309.dto;

import java.util.Map;

import com.sun309.util.Validate;
import com.sun309.util.Validation;

/**
 * 财付通GET数据实体类
 * @author Wormwood OPC_GROUP
 */
public class TenPayQueryResult implements Validation 
{
	private String 		attach;				/** attach 商家数据包，原样返回 		*/
	private String 		bargainorID;		/** bargainor_id 卖方账号(商户spid) 	*/
	private String 		cmdNo;				/** cmdno 任务代码					*/
	private String 		date;				/** date 商户日期 yyyyMMdd 			*/
	private String 		feeType;			/** fee_type 货币类型 				*/
	private String 		payResult;			/** 交易结果状态 pay_result 			*/
	private String 		sign;				/** 加密验证字段 sign 				*/ 
	private Long 		TotalFee;			/** 支付总金额 TotalFee 单位：分		*/
	private String		charset;			/** 字符集							*/
	private String		outputXml;			/** 结果类型 1:XML					*/
	private String		payInfo;			/** 支付结果信息						*/
	private String		retCode;			/** 交易结果代码						*/
	private String		retMsg;				/** 交易结果代码信息					*/
	private String		returnURL;			/** 数据接收地址						*/
	private String 		transactionId;		/** 财付通订单号[流水号] transactionId 对帐	*/
	private String 		spBillNo;			/** 财付通外部订单号 sp_billno [多支付流水号] */
	
	private Validate	validator;			/** 验证工具类						*/
	private String		validateInfo;		/** 验证结果信息						*/	
	
	/**
	 * Validation方法
	 */
	public boolean validate() {
		setValidateInfo("Init");
		if(getValidator() == null) 							{setValidateInfo("验证类加载失败");						return Boolean.FALSE;}
		if(!getValidator().isString(getCmdNo())) 			{setValidateInfo("财付通网关查询类型参数验证失败");		return Boolean.FALSE;}
		if(!getValidator().isString(getPayResult())) 		{setValidateInfo("交易结果状态为空验证失败");				return Boolean.FALSE;}
		if(!getValidator().isString(getDate())) 			{setValidateInfo("订单日期为空验证失败");					return Boolean.FALSE;}
		if(!getValidator().isString(getBargainorID())) 		{setValidateInfo("商户ID为空验证失败");					return Boolean.FALSE;}
		if(!getValidator().isString(getTransactionId())) 	{setValidateInfo("订单ID为空验证失败");					return Boolean.FALSE;}
		if(!getValidator().isString(getFeeType()))			{setValidateInfo("货币类型为空验证失败");					return Boolean.FALSE;}
		if(!getValidator().isNum(getTotalFee().toString()))	{setValidateInfo("交易总金额数字验证失败");				return Boolean.FALSE;}
		if(!getValidator().isString(getSign())) 			{setValidateInfo("签名结果为空验证失败");					return Boolean.FALSE;}
		if(!getValidator().isString(getSpBillNo()))			{setValidateInfo("外部订单号[支付流水号]为空验证失败");	return Boolean.FALSE;}
		if(!getValidator().isString(getAttach()))			{setValidateInfo("私有数据[商品代码]为空验证失败");		return Boolean.FALSE;}
		setValidateInfo("Not Null Validate SUCCEED!");
		return Boolean.TRUE;
		//if(!getValidator().isString(getPayInfo())) 		{setValidateInfo("交易结果信息为空验证失败");				return Boolean.FALSE;}
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
	public void Init(Map<String, Object> result)
	{
		setAttach(result.get("attach").toString());
		setBargainorID(result.get("bargainor_id").toString());
		setCmdNo(result.get("cmdno").toString());
		setDate(result.get("date").toString());
		setFeeType(result.get("fee_type").toString());
		setPayResult(result.get("pay_result").toString());
		setSign(result.get("sign").toString());
		setSpBillNo(result.get("sp_billno").toString());
		setTotalFee(Long.parseLong(result.get("total_fee").toString()));
		setTransactionId(result.get("transaction_id").toString());
		/** 以下参数可能为 NULL */
		if(result.containsKey("retcode"))
			setRetCode(result.get("retcode").toString());
		if(result.containsKey("retmsg"))
			setRetMsg(result.get("retmsg").toString());
		if(result.containsKey("return_url"))
			setReturnURL(result.get("return_url").toString());
		if(result.containsKey("charset"))
			setCharset(result.get("charset").toString());
		if(result.containsKey("output_xml"))
			setOutputXml(result.get("output_xml").toString());
		if(result.containsKey("pay_info"))
			setPayInfo(result.get("pay_info").toString());
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
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * @param charset the charset to set
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * @return the outputXml
	 */
	public String getOutputXml() {
		return outputXml;
	}

	/**
	 * @param outputXml the outputXml to set
	 */
	public void setOutputXml(String outputXml) {
		this.outputXml = outputXml;
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
	 * @return the retCode
	 */
	public String getRetCode() {
		return retCode;
	}

	/**
	 * @param retCode the retCode to set
	 */
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	/**
	 * @return the retMsg
	 */
	public String getRetMsg() {
		return retMsg;
	}

	/**
	 * @param retMsg the retMsg to set
	 */
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	/**
	 * @return the returnURL
	 */
	public String getReturnURL() {
		return returnURL;
	}

	/**
	 * @param returnURL the returnURL to set
	 */
	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}
}
