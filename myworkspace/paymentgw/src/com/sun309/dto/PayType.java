package com.sun309.dto;

import org.xmappr.Element;
import org.xmappr.RootElement;

import com.sun309.util.Validate;
import com.sun309.util.Validation;

@RootElement
public class PayType implements Validation
{
	@Element
	private Integer 	payTypeCode;
	@Element
	private String 		payTypeName;
	@Element
	private	String		resultType;
	@Element
	private String 		actionUrl;
	@Element
	private ActionForm 	actionForm;
		
	/**
	 * 数据验证成员
	 */
	private Validate 	validater 		= null;				/** 验证类成员 */
	private String 		validateInfo 	= "Init";			/** 验证结果信息*/
	/**
	 * 验证标志位
	 */
	private Boolean		vailidateCode = Boolean.FALSE;	/** 验证ActionUrl还是ActionForm 	*/
	
	public boolean validate() {
		// TODO Auto-generated method stub
		if(!validater.isString(resultType)) 		{validateInfo="返回数据类型验证失败"; return false;}
		if(!validater.isChinese(payTypeName)) 		{validateInfo="支付类型中文名称验证失败"; return false;}
		if(!validater.isNum(payTypeCode.toString()))		{validateInfo="支付类型验证失败"; return false;}
		if(!vailidateCode&&!validater.isUrl(actionUrl))		{validateInfo="ActioinUrl[签名数据结果]验证失败"; return false;}
		if(vailidateCode&&!validater.isObject(actionForm))	{validateInfo="ActioinForml[签名数据结果]验证失败"; return false;}
		validateInfo = "SUCCEED";
		return true;
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
	 * @return the actionForm
	 */
	public ActionForm getActionForm() {
		return actionForm;
	}
	/**
	 * @param actionForm the actionForm to set
	 */
	public void setActionForm(ActionForm actionForm) {
		this.actionForm = actionForm;
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
	 * @return the vailidateCode
	 */
	public Boolean getVailidateCode() {
		return vailidateCode;
	}
	/**
	 * @param vailidateCode the vailidateCode to set
	 */
	public void setVailidateCode(Boolean vailidateCode) {
		this.vailidateCode = vailidateCode;
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
}
