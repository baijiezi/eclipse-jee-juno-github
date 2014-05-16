package com.sun309.dto;

import com.sun309.service.BaseResult;

public class ActionUrl implements BaseResult
{
	/** 支付的LOG图标路径 */
	private	String	logIcon	=	"http://124.172.245.98:8080/paymentgw/image/";			
	private Integer	payTypeCode;			/** 多支付服务对应的支付类型 */
	private String 	payTypeName;			/** 支付的中文名称 */
	private String 	resultType;				/** 多支付服务对应的返回类型 */
	private String 	actionUrl;				/** ActionUrl 已生成的URL地址 */
	private String 	usclass		= "alipHref";		/** 链接样式名称 默认：alipHref */
	
	public String toHTML() {
		// TODO Auto-generated method stub
		StringBuilder html = new StringBuilder("<![CDATA[")
			.append("<a target='_blank' class = '").append(getUsclass()).append("' href='")
			.append(getActionUrl()).append("'>").append(getPayTypeName()).append("</a>").append("]]>");
		return html.toString();
	}
	public String toXML() {
		StringBuilder xml = new StringBuilder()
			.append("<payTypeCode>").append(getPayTypeCode()).append("</payTypeCode>")
			.append("<payTypeName>").append(getPayTypeName()).append("</payTypeName>")
			.append("<resultType>").append(getResultType()).append("</resultType>")
			.append("<actionUrl><![CDATA[").append(getActionUrl()).append("]]></actionUrl>");
		return xml.toString();
	}
	
	/**
	 * @return the usclass
	 */
	public String getUsclass() {
		return usclass;
	}
	/**
	 * @param usclass the usclass to set
	 */
	public void setUsclass(String usclass) {
		this.usclass = usclass;
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
	 * @return the logIcon
	 */
	public String getLogIcon() {
		return logIcon;
	}
	/**
	 * @param logIcon the logIcon to set
	 */
	public void setLogIcon(String logIcon) {
		StringBuilder builder = new StringBuilder(this.logIcon);
		builder.append(logIcon);
		this.logIcon = builder.toString();
		builder = null;
	}
}
