package com.sun309.dto;

import com.sun309.service.BaseReceive;
import com.sun309.util.Constants;
import com.sun309.util.Validate;
import com.sun309.util.Validation;

public class TenPayPackageResult implements Validation,BaseReceive 
{
	private String bankType;			/** 默认银行			*/
	private String tradeMode;			/** 交易模式			*/
	private String discount;			/** 折扣				*/
	private String inputCharset;		/** 字符集			*/
	private Long   totalFee;			/** 交易总金额｜分	*/
	private Long   transportFee;		/** 手序费			*/
	private String notifyId;			/** 通知序号			*/
	private String transactionId;		/** 财付通交易流水号	*/
	private String sign;				/** 加密字符			*/
	private String partner;				/** 合作商户号SPID	*/
	private Long   productFee;			/** 商品价格			*/
	private String timeEnd;				/** 实际支付完成时间	*/
	private String outTradeNo; 			/** 外部订单号 实际上是多支付网关 流水号 	*/
	private String tradeState;			/** 交易状态			*/
	private String signType;			/** 支付状态说明							*/
	private String feeType;				/** 货币类型			*/
	
	/**
	 * 数据验证成员
	 */
	private Validate 	validater = null;		/** 验证类成员 */
	private String 		validateInfo = "Init";;	/** 验证结果信息*/
	
	/** 多支付实际支付类型    默认值方式初始化[为了增加异常处理] */
	private Integer	realPayType = new Integer(Constants.TENPAYPACKAGE_PAY);

	/**
	 * Validation方法
	 */
	public boolean validate()
	{
		if(getValidater() == null )							{setValidateInfo("验证类加载失败"); 					return Boolean.FALSE;}
		if(!getValidater().isString(getOutTradeNo())) 		{setValidateInfo("外部订单号[多支付流水号]验证失败"); return Boolean.FALSE;}
		if(!getValidater().isString(getBankType())) 		{setValidateInfo("默认银行验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getTimeEnd())) 			{setValidateInfo("交易结束时间验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isString(getTransactionId()))	{setValidateInfo("财付通流水号验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isObject(getTotalFee())) 		{setValidateInfo("实际支付总金额验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isString(getTradeMode()))		{setValidateInfo("财付通交易模式验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isObject(getTransportFee())) 	{setValidateInfo("手序费验证失败"); 					return Boolean.FALSE;}
		if(!getValidater().isString(getFeeType())) 			{setValidateInfo("货币类型验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getPartner()))			{setValidateInfo("财付通商户号验证失败"); 			return Boolean.FALSE;}
		if(!getValidater().isObject(getProductFee())) 		{setValidateInfo("商品价格验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getTradeState())) 		{setValidateInfo("交易状态验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getDiscount())) 		{setValidateInfo("交易折扣验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getInputCharset())) 	{setValidateInfo("字符集验证失败"); 					return Boolean.FALSE;}
		if(!getValidater().isString(getNotifyId())) 		{setValidateInfo("通知序号验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getSignType())) 		{setValidateInfo("加密方法验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getSign())) 			{setValidateInfo("加密串验证失败"); 					return Boolean.FALSE;}
		setValidateInfo("Not Null Validate SUCCEED!");
		return Boolean.TRUE;
	}
	
	/** 
	 * @return TradeStatus
	 * BaseReceive 方法 交易状态验证方法 
	 */
	public boolean validateTradeStatus()
	{
		// TODO Auto-generated method stub
		return "0".equals(getTradeState());
	}

	/**
	 * @return InitInfo 
	 * BaseReceive 方法 数据接收初始化日志
	 */
	public String getInitInfo()
	{
		StringBuilder builder = new StringBuilder("Init Info :")
		.append("outTradeNo:").append(getOutTradeNo())
		.append(" transactionId:").append(getTransactionId())
		.append(" bankType:").append(getBankType())
		.append(" tradeMode:").append(getTradeMode())
		.append(" discount:").append(getDiscount())
		.append(" inputCharset:").append(getInputCharset())
		.append(" totalFee:").append(getTotalFee())
		.append(" transportFee:").append(getTransportFee())
		.append(" notifyId:").append(getNotifyId())
		.append(" sign:").append(getSign())
		.append(" partner:").append(getPartner())
		.append(" productFee:").append(getProductFee())
		.append(" timeEnd:").append(getTimeEnd())
		.append(" tradeState:").append(getTradeState())
		.append(" signType:").append(getSignType())
		.append(" feeType:").append(getFeeType());
		return builder.toString();
	}

	/**
	 * @return the totalFee
	 * BaseReceive 方法 获得交易总金额
	 */
	public Long getTotalFee()
	{
		// TODO Auto-generated method stub
		return totalFee;
	}
	
	/**
	 * @return the tradeNo
	 * BaseReceive 方法 获得交易流水号
	 */
	public String getTradeNo()
	{
		// TODO Auto-generated method stub
		return transactionId;
	}
	
	/**
	 * @return the outTradeNo
	 * BaseReceive 方法 获得外部订单号[多支付流水ID号]
	 */
	public String getOutTradeNo()
	{
		// TODO Auto-generated method stub
		return outTradeNo;
	}

	/**
	 * @return the realPayType
	 * BaseReceive 方法 获得多支付实际支付类型
	 */
	public Integer getRealPayType()
	{
		// TODO Auto-generated method stub
		return realPayType;
	}

	/**
	 * @return the transactionId
	 */
	public String getTransactionId()
	{
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
	}

	/**
	 * @return the timeEnd
	 */
	public String getTimeEnd()
	{
		return timeEnd;
	}

	/**
	 * @param timeEnd the timeEnd to set
	 */
	public void setTimeEnd(String timeEnd)
	{
		this.timeEnd = timeEnd;
	}

	/**
	 * @return the validater
	 */
	public Validate getValidater()
	{
		return validater;
	}

	/**
	 * @param validater the validater to set
	 */
	public void setValidater(Validate validater)
	{
		this.validater = validater;
	}

	/**
	 * @return the validateInfo
	 */
	public String getValidateInfo()
	{
		return validateInfo;
	}

	/**
	 * @param validateInfo the validateInfo to set
	 */
	public void setValidateInfo(String validateInfo)
	{
		this.validateInfo = validateInfo;
	}

	/**
	 * @param outTradeNo the outTradeNo to set
	 */
	public void setOutTradeNo(String outTradeNo)
	{
		this.outTradeNo = outTradeNo;
	}

	/**
	 * @param totalFee the totalFee to set
	 */
	public void setTotalFee(String totalFee)
	{
		this.totalFee = Long.parseLong(totalFee);
	}

	/**
	 * @return the bankType
	 */
	public String getBankType()
	{
		return bankType;
	}

	/**
	 * @param bankType the bankType to set
	 */
	public void setBankType(String bankType)
	{
		this.bankType = bankType;
	}

	/**
	 * @return the feeType
	 */
	public String getFeeType()
	{
		return feeType;
	}

	/**
	 * @param feeType the feeType to set
	 */
	public void setFeeType(String feeType)
	{
		this.feeType = feeType;
	}

	/**
	 * @return the inputCharset
	 */
	public String getInputCharset()
	{
		return inputCharset;
	}

	/**
	 * @param inputCharset the inputCharset to set
	 */
	public void setInputCharset(String inputCharset)
	{
		this.inputCharset = inputCharset;
	}

	/**
	 * @return the signType
	 */
	public String getSignType()
	{
		return signType;
	}

	/**
	 * @param signType the signType to set
	 */
	public void setSignType(String signType)
	{
		this.signType = signType;
	}

	/**
	 * @return the partner
	 */
	public String getPartner()
	{
		return partner;
	}

	/**
	 * @param partner the partner to set
	 */
	public void setPartner(String partner)
	{
		this.partner = partner;
	}

	/**
	 * @return the notifyId
	 */
	public String getNotifyId()
	{
		return notifyId;
	}

	/**
	 * @param notifyId the notifyId to set
	 */
	public void setNotifyId(String notifyId)
	{
		this.notifyId = notifyId;
	}

	/**
	 * @return the transportFee
	 */
	public Long getTransportFee()
	{
		return transportFee;
	}

	/**
	 * @param transportFee the transportFee to set
	 */
	public void setTransportFee(String transportFee)
	{
		this.transportFee = Long.parseLong(transportFee);
	}

	/**
	 * @return the sign
	 */
	public String getSign()
	{
		return sign;
	}

	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign)
	{
		this.sign = sign;
	}

	/**
	 * @return the tradeMode
	 */
	public String getTradeMode()
	{
		return tradeMode;
	}

	/**
	 * @param tradeMode the tradeMode to set
	 */
	public void setTradeMode(String tradeMode)
	{
		this.tradeMode = tradeMode;
	}

	/**
	 * @return the discount
	 */
	public String getDiscount()
	{
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(String discount)
	{
		this.discount = discount;
	}

	/**
	 * @return the productFee
	 */
	public Long getProductFee()
	{
		return productFee;
	}

	/**
	 * @param productFee the productFee to set
	 */
	public void setProductFee(Long productFee)
	{
		this.productFee = productFee;
	}
	
	/**
	 * @param productFee the productFee to set
	 */
	public void setProductFee(String productFee)
	{
		this.productFee = Long.parseLong(productFee);
	}

	/**
	 * @return the tradeState
	 */
	public String getTradeState()
	{
		return tradeState;
	}

	/**
	 * @param tradeState the tradeState to set
	 */
	public void setTradeState(String tradeState)
	{
		this.tradeState = tradeState;
	}

	/**
	 * @param totalFee the totalFee to set
	 */
	public void setTotalFee(Long totalFee)
	{
		this.totalFee = totalFee;
	}

	/**
	 * @param transportFee the transportFee to set
	 */
	public void setTransportFee(Long transportFee)
	{
		this.transportFee = transportFee;
	}
}
