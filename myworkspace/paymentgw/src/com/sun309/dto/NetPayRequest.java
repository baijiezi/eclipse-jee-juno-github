package com.sun309.dto;
/**
 * 多支付网关支付请求实体类
 * @author Wormwood OPC_GROUP
 */
import com.sun309.service.Request;
import com.sun309.util.Constants;
import com.sun309.util.Validate;

public class NetPayRequest implements Request
{
	/** 外部订单号	*/
	private 	String 		outTtradeNo;
	
	/** 商品代码		*/
	private 	String		waresCode;
	
	/** 支付总金额 	*/
	private 	Long		totalFee;
	
	/** 通知地址		*/
	private 	String		returnUrl;
	
	/** 请求支付类型 */
	private 	Integer		payType;
	
	/** 返回结果类型 */
	private		String		resultType;	
	
	/** 请求超时时间 */
	private 	Long 		expireTime;
	
	/** 实际支付请求发起客户机IP	*/
	private 	String 		ClientIP	= 	"0.0.0.0";
	
	/** 多支付流水ID */
	private		String		NPGWID;
	
	
	/**
	 * 数据验证成员
	 */
	private Validate 	validater 		= null;						/** 验证类成员 */
	private String 		validateInfo	= "Init Request";			/** 验证结果信息*/
	private Boolean		initTag			= Boolean.TRUE;				/** 是否异步方法	默认同步生成	*/			
	
	/**
	 * 数据验证方法
	 * 主要完成不为空验证
	 */
	public boolean validate() {
		// TODO Auto-generated method stub
		if(getValidater() == null )								{setValidateInfo("验证类加载失败"); 					return Boolean.FALSE;}
		if(!getValidater().isString(getOutTtradeNo()))			{setValidateInfo("支付宝网关服务类型参数验证失败"); 	return Boolean.FALSE;}
		if(!getValidater().isNum(getPayType().toString())) 		{setValidateInfo("支付类型验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isObject(getExpireTime())) 			{setValidateInfo("超时时间验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isNetPayWaresCode(getWaresCode())) 	{setValidateInfo("商品代码验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getClientIP())) 			{setValidateInfo("客户端IP验证失败"); 				return Boolean.FALSE;}
		if(getInitTag().equals(Boolean.FALSE)) 					{setValidateInfo("ASYNC SUCCEED"); 					return Boolean.TRUE;}
		if(!getValidater().isObject(getTotalFee())) 			{setValidateInfo("支付总金额验证失败"); 				return Boolean.FALSE;}
		if(getTotalFee()<=0)									{setValidateInfo("支付总金额小于等零");	 			return Boolean.FALSE;}
		if(!getValidater().isString(getReturnUrl())) 			{setValidateInfo("通知地址验证失败"); 				return Boolean.FALSE;}
		if(!getValidater().isString(getResultType())) 			{setValidateInfo("返回数据类型验证失败"); 			return Boolean.FALSE;}
		if(!getInitTag() && !getValidater().isNetPayID(getNPGWID())) {setValidateInfo("多支付NPGWID验证失败"); 			return Boolean.FALSE;}
		setValidateInfo("SUCCEED");
		return Boolean.TRUE;
	}
	
	public Long getExpireTime() {
		return expireTime;
	}
	/**
	 * @param expireTime the expireTime to set
	 */
	public void setExpireTime(Long expireTime) {
		if(expireTime <Constants.MIN_EXPIRE_TIME)
			this.expireTime = new Long(Constants.MIN_EXPIRE_TIME);
		else
			this.expireTime = expireTime;
	}
	/**
	 * @return the outTtradeNo
	 */
	public String getOutTtradeNo() {
		return outTtradeNo;
	}
	/**
	 * @param outTtradeNo the outTtradeNo to set
	 */
	public void setOutTtradeNo(String outTtradeNo) {
		this.outTtradeNo = outTtradeNo;
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
	 * @return the clientIP
	 */
	public String getClientIP() {
		return ClientIP;
	}

	/**
	 * @param clientIP the clientIP to set
	 */
	public void setClientIP(String clientIP) {
		if(clientIP.length() > 0)
			ClientIP = clientIP;
	}

	public Boolean getInitTag()
	{
		return initTag;
	}

	public void setInitTag(Boolean initTag)
	{
		this.initTag = initTag;
	}

	public String getNPGWID()
	{
		return NPGWID;
	}

	public void setNPGWID(String nPGWID)
	{
		NPGWID = nPGWID;
	}
	
}
