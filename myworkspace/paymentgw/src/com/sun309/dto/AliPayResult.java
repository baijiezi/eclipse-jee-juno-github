package com.sun309.dto;
/**
 * @author WormwoodLeaf OPC_GROUP
 * 支付宝POST数据接收对象
 */

import com.sun309.service.BaseReceive;
import com.sun309.util.Constants;
import com.sun309.util.Validate;
import com.sun309.util.Validation;

public class AliPayResult implements Validation,BaseReceive
{
	private String 	notifyType;			/** 通知类型				*/
	private String 	notifyId;			/** 通知ID 用于验证		*/
	private String	notifyTime;			/** 通知时间				*/
	private String 	sign;				/** 签名					*/
	private String	signType;			/** 签名方式				*/
	private String 	tradeNo;			/** 交易流水号			*/
	private String 	OutTradeNo;			/** 多支付系统流水ID号 实际支付的外部订单号	*/
	private String	subject;			/** 商品名称				*/
	private String	price;				/** 商品单价				*/
	private String	quatity;			/** 商品数量				*/
	private String	useCoupon;			/** 是否使用红包	T｜F		*/
	private String 	isTotalFeeAjust;	/** 总价调整标志	T｜F		*/
	private String	tradeStatus;		/** 交易状态				*/
	private String 	sellerEmail;		/** 卖方Email			*/
	private String	sellerId;			/** 卖方支付宝ID			*/
	private String	buyerEmail;			/** 买方Email			*/
	private String	buyerId;			/**	买方支付宝ID			*/
	private String 	body;				/** 商品描述	NULL		*/
	private String	gmtCreateTime;		/** 交易创建时间 NULL	*/
	private String	gmtPaymentTime;		/** 交易支付时间 NULL	*/
	private String	gmtClose;			/** 交易结束时间 NULL	*/
	private String 	discount;			/** 商品折扣 NULL		*/
	private String	paymentType;		/**	支付宝支付类型 NULL	*/
	private Long	totalFee;			/** 交易总金额			*/
	
	/** 多支付实际支付类型    默认值方式初始化[为了增加异常处理] */
	private Integer	realPayType = new Integer(Constants.ALI_PAY);
	
	//private	String	gmtSendGoodsTime;	/** 发货时间 NULL	 实际返回参数中没有	*/
	//private String	gmtRefund;			/** 退款时间 NULL	  实际返回参数中没有	*/
	
	private Validate	validator;		/** 验证工具类			*/
	private String		validateInfo;	/** 验证结果信息			*/
	
	/**
	 * Validation方法
	 */
	public boolean validate() {
		setValidateInfo("Init");
		if(getValidator() == null) 							{setValidateInfo("验证类加载失败");			return Boolean.FALSE;}
		if(!getValidator().isString(getNotifyId())) 		{setValidateInfo("通知ID为空验证失败");		return Boolean.FALSE;}
		if(!getValidator().isString(getTradeNo())) 			{setValidateInfo("交易流水号为空验证失败");	return Boolean.FALSE;}
		if(!getValidator().isString(getTradeStatus())) 		{setValidateInfo("交易状态为空验证失败");		return Boolean.FALSE;}
		if(!getValidator().isString(getOutTradeNo()))		{setValidateInfo("外部订单号为空验证失败");	return Boolean.FALSE;}
		if(!getValidator().isNum(getTotalFee().toString()))	{setValidateInfo("交易总金额数字验证失败");	return Boolean.FALSE;}
		setValidateInfo("Not Null Validate SUCCEED!");
		return Boolean.TRUE;
	}
	
	/** 
	 * @return TradeStatus
	 * BaseReceive 方法 交易状态验证方法 
	 */
	public boolean validateTradeStatus()
	{
		if(getTradeStatus().equals("TRADE_FINISHED") || getTradeStatus().equals("WAIT_SELLER_SEND_GOODS") || getTradeStatus().equals("WAIT_BUYER_CONFIRM_GOODS"))
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
			.append("NotifyID:").append(getNotifyId())
			.append(" TradeNo:").append(getTradeNo())
			.append(" TradeStatus:").append(getTradeStatus())
			.append(" OutTradeNo:").append(getOutTradeNo())
			.append(" TotalFee:").append(getTotalFee());
		return builder.toString();
	}
	
	/**
	 * @return the totalFee
	 * BaseReceive 方法 获得交易总金额
	 */
	public Long getTotalFee() {
		return totalFee;
	}
	
	/**
	 * @return the tradeNo
	 * BaseReceive 方法 获得交易流水号
	 */
	public String getTradeNo() {
		return tradeNo;
	}
	
	/**
	 * @return the outTradeNo
	 * BaseReceive 方法 获得外部订单号[多支付流水ID号]
	 */
	public String getOutTradeNo() {
		return OutTradeNo;
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
	 * @return the notifyType
	 */
	public String getNotifyType() {
		return notifyType;
	}
	/**
	 * @param notifyType the notifyType to set
	 */
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
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
	 * @param tradeNo the tradeNo to set
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	/**
	 * @param outTradeNo the outTradeNo to set
	 */
	public void setOutTradeNo(String outTradeNo) {
		OutTradeNo = outTradeNo;
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
	 * @return the quatity
	 */
	public String getQuatity() {
		return quatity;
	}
	/**
	 * @param quatity the quatity to set
	 */
	public void setQuatity(String quatity) {
		this.quatity = quatity;
	}
	/**
	 * @return the useCoupon
	 */
	public String getUseCoupon() {
		return useCoupon;
	}
	/**
	 * @param useCoupon the useCoupon to set
	 */
	public void setUseCoupon(String useCoupon) {
		this.useCoupon = useCoupon;
	}
	/**
	 * @return the isTotalFeeAjust
	 */
	public String getIsTotalFeeAjust() {
		return isTotalFeeAjust;
	}
	/**
	 * @param isTotalFeeAjust the isTotalFeeAjust to set
	 */
	public void setIsTotalFeeAjust(String isTotalFeeAjust) {
		this.isTotalFeeAjust = isTotalFeeAjust;
	}
	/**
	 * @return the tradeStatus
	 */
	public String getTradeStatus() {
		return tradeStatus;
	}
	/**
	 * @param tradeStatus the tradeStatus to set
	 */
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
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
	 * @return the sellerId
	 */
	public String getSellerId() {
		return sellerId;
	}
	/**
	 * @param sellerId the sellerId to set
	 */
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	/**
	 * @return the buyerEmail
	 */
	public String getBuyerEmail() {
		return buyerEmail;
	}
	/**
	 * @param buyerEmail the buyerEmail to set
	 */
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	/**
	 * @return the buyerId
	 */
	public String getBuyerId() {
		return buyerId;
	}
	/**
	 * @param buyerId the buyerId to set
	 */
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	/**
	 * @return the gmtCreateTime
	 */
	public String getGmtCreateTime() {
		return gmtCreateTime;
	}
	/**
	 * @param gmtCreateTime the gmtCreateTime to set
	 */
	public void setGmtCreateTime(String gmtCreateTime) {
		this.gmtCreateTime = gmtCreateTime;
	}
	/**
	 * @return the gmtPaymentTime
	 */
	public String getGmtPaymentTime() {
		return gmtPaymentTime;
	}
	/**
	 * @param gmtPaymentTime the gmtPaymentTime to set
	 */
	public void setGmtPaymentTime(String gmtPaymentTime) {
		this.gmtPaymentTime = gmtPaymentTime;
	}
	/**
	 * @return the gmtSendGoodsTime
	 */
//	public String getGmtSendGoodsTime() {
//		return gmtSendGoodsTime;
//	}
	/**
	 * @param gmtSendGoodsTime the gmtSendGoodsTime to set
	 */
//	public void setGmtSendGoodsTime(String gmtSendGoodsTime) {
//		this.gmtSendGoodsTime = gmtSendGoodsTime;
//	}
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
	 * @return the notifyTime
	 */
	public String getNotifyTime() {
		return notifyTime;
	}
	/**
	 * @param notifyTime the notifyTime to set
	 */
	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the discount
	 */
	public String getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	/**
	 * @param totalFee the totalFee to set
	 */
	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
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
	 * @return the gmtRefund
	 */
//	public String getGmtRefund() {
//		return gmtRefund;
//	}

	/**
	 * @param gmtRefund the gmtRefund to set
	 */
//	public void setGmtRefund(String gmtRefund) {
//		this.gmtRefund = gmtRefund;
//	}

	/**
	 * @return the gmtClose
	 */
	public String getGmtClose() {
		return gmtClose;
	}

	/**
	 * @param gmtClose the gmtClose to set
	 */
	public void setGmtClose(String gmtClose) {
		this.gmtClose = gmtClose;
	}

	public void setRealPayType(Integer realPayType)
	{
		this.realPayType = realPayType;
	}
}
