package com.sun309.dto;

public class NetPayData
{
	private String 		ID;				/** NETPAY 交易记录ID 	*/
	private Integer 	payType;		/** 实际支付类型			*/
	private String		tranLineDetail;	/** 返回的交易流水号		*/
	private String 		outOrderNo;		/**	外部交易订单号		*/
	private Long		totalFee;		/** 订单交易金额 单位：分	*/
	private String		waresCode;		/** 商品代码				*/
	private String		checkKey;		/**	加密KEY				*/
	private String		checkValue;		/** 加密验证值			*/
	private String		clientIP;		/** 客户端IP				*/
	private String		returnURL;		/** 通知地址				*/
	private Integer		payStatus;		/**	交易状态				*/
	private Long		initTime;		/** 请求交易时间			*/
	private Long		expireTime;		/** 请求设置的超时时间	*/
	private Long		receiveTime;	/** 成功接收数据时间		*/
	private Long		distributeTime;	/**	分发成功时间			*/
	private Integer		distributeStatus;	/**	分发状态			*/
	private Integer		receiveStatus;		/** 接收状态			*/
	private Integer		validateStatus;		/** 校验证状态		*/
	private	Integer		TryTimes;		/**	最大失败后分发次数	*/
	
	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
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
	/**
	 * @param id the iD to set
	 */
	public void setID(String id) {
		ID = id;
	}
	/**
	 * @return the payType
	 */
	public Integer getPayType() {
		return payType;
	}
	/**
	 * @param payType the payType to set
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	/**
	 * @return the tranLineDetail
	 */
	public String getTranLineDetail() {
		return tranLineDetail;
	}
	/**
	 * @param tranLineDetail the tranLineDetail to set
	 */
	public void setTranLineDetail(String tranLineDetail) {
		this.tranLineDetail = tranLineDetail;
	}
	/**
	 * @return the outOrderNo
	 */
	public String getOutOrderNo() {
		return outOrderNo;
	}
	/**
	 * @param outOrderNo the outOrderNo to set
	 */
	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
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
	 * @return the checkKey
	 */
	public String getCheckKey() {
		return checkKey;
	}
	/**
	 * @param checkKey the checkKey to set
	 */
	public void setCheckKey(String checkKey) {
		this.checkKey = checkKey;
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
	 * @return the payStatus
	 */
	public Integer getPayStatus() {
		return payStatus;
	}
	/**
	 * @param payStatus the payStatus to set
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	/**
	 * @return the initTime
	 */
	public Long getInitTime() {
		return initTime;
	}
	/**
	 * @param initTime the initTime to set
	 */
	public void setInitTime(Long initTime) {
		this.initTime = initTime;
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
	 * @return the receiveTime
	 */
	public Long getReceiveTime() {
		return receiveTime;
	}
	/**
	 * @param receiveTime the receiveTime to set
	 */
	public void setReceiveTime(Long receiveTime) {
		this.receiveTime = receiveTime;
	}
	/**
	 * @return the distributeTime
	 */
	public Long getDistributeTime() {
		return distributeTime;
	}
	/**
	 * @param distributeTime the distributeTime to set
	 */
	public void setDistributeTime(Long distributeTime) {
		this.distributeTime = distributeTime;
	}
	/**
	 * @return the distributeStatus
	 */
	public Integer getDistributeStatus() {
		return distributeStatus;
	}
	/**
	 * @param distributeStatus the distributeStatus to set
	 */
	public void setDistributeStatus(Integer distributeStatus) {
		this.distributeStatus = distributeStatus;
	}
	/**
	 * @return the tryTimes
	 */
	public Integer getTryTimes() {
		return TryTimes;
	}
	/**
	 * @param tryTimes the tryTimes to set
	 */
	public void setTryTimes(Integer tryTimes) {
		TryTimes = tryTimes;
	}
	/**
	 * @return the receiveStatus
	 */
	public Integer getReceiveStatus() {
		return receiveStatus;
	}
	/**
	 * @param receiveStatus the receiveStatus to set
	 */
	public void setReceiveStatus(Integer receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	/**
	 * @return the validateStatus
	 */
	public Integer getValidateStatus() {
		return validateStatus;
	}
	/**
	 * @param validateStatus the validateStatus to set
	 */
	public void setValidateStatus(Integer validateStatus) {
		this.validateStatus = validateStatus;
	}
	
}
