package com.sun309.dto;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ActionForm 
{
    private Map<String,String> fields 		= new HashMap<String,String>();				/** 字段列表 */
    private Map<String,String> fieldsType	= new HashMap<String,String>();			/** 字段类型列表 */
    private String 	formMethod		=	"POST";			/** 表单提交方法 默认：POST */
    private String 	usClass 		=	"actionForm";	/** 表单样式名称 默认：actionForm */
    private String 	buttionClass	= 	"actionFormButton";	/** 按钮样式 默认：actionFormButton*/
    private String 	actionUrl;							/** ActionUrl 表单提交地址 */
    private String 	payTypeName;						/** 支付的中文名称 */
    private Integer	payTypeCode;						/** 多支付服务对应的支付类型 */
    private String 	resultType;							/** 多支付服务对应的返回类型 */
    /** 支付的LOG图标路径 */
    private String 	logIcon	=	"http://124.172.245.98:8080/paymentgw/image/";						

	/**
	 * @return the field
	 */
	public void setField(String fieldName,String fieldValue,String type) 
	{
		if(type.equals("") || type == null)
			type = "hidden";
		this.fields.put(fieldName, fieldValue);
		this.fieldsType.put(fieldName, type);
	}
	
	public String toXML()
	{
		StringBuilder xml = new StringBuilder()
		.append("<payTypeCode>").append(getPayTypeCode()).append("</payTypeCode>")
		.append("<payTypeName>").append(getPayTypeName()).append("</payTypeName>")
		.append("<resultType>").append(getResultType()).append("</resultType>")
		.append("<actionForm>");
		Set<String> keys= fields.keySet();
		Iterator<String> iterator = keys.iterator();
		while(iterator.hasNext())
		{
			String key = iterator.next();
			xml.append("<field><name>").append(key).append("</name><type>")
				.append(fieldsType.get(key)).append("</type><value>");
			if(fields.get(key).contains("&"))
				xml.append("<![CDATA[").append(fields.get(key)).append("]]></value></field>");
			else
				xml.append(fields.get(key)).append("</value></field>");
		}
		keys 		= 	null;
		iterator	=	null;
		xml.append("<field><name>actionUrl</name><type>action</type><value><![CDATA[").append(getActionUrl()).append("]]></value></field>")
			.append("<field><name>actionMethod</name><type>method</type><value>").append(getFormMethod()).append("</value></field>")
			.append("<field><name>logImage</name><type>image</type><value>").append(getLogIcon()).append("</value></field>")
			.append("<field><name>").append(getPayTypeCode()).append("</name><type>submit</type><value>").append(getPayTypeName())
			.append("</value></field></actionForm>");
		return xml.toString();
	}
	public String toHTML()
	{
		StringBuilder html = new StringBuilder("<![CDATA[")
			.append("<form action=\"").append(getActionUrl()).append("\" method=\"")
			.append(getFormMethod()).append("\" class=\"").append(getUsClass()).append("\">");
		Set<String> keys= fields.keySet();
		Iterator<String> iterator = keys.iterator();
		while(iterator.hasNext())
		{
			String key = iterator.next();
			html.append("<input type =\"").append(fieldsType.get(key)).append("\"")
				.append(" name = \"").append(key).append("\" ")
				.append("value = \"").append(fields.get(key)).append("\" />");
		}
		keys 		= 	null;
		iterator	=	null;
		html.append("<input type=\"submit\" value=\"").append(getPayTypeName()).append("\"")
			.append(" class = \"").append(getButtionClass()).append("\" /></form>]]>");
		return html.toString();
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
	 * @return the usClass
	 */
	public String getUsClass() {
		return usClass;
	}

	/**
	 * @param usClass the usClass to set
	 */
	public void setUsClass(String usClass) {
		this.usClass = usClass;
	}

	/**
	 * @return the fields
	 */
	public Map<String, String> getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}

	/**
	 * @return the fieldsType
	 */
	public Map<String, String> getfieldsType() {
		return fieldsType;
	}

	/**
	 * @param fieldsType the fieldsType to set
	 */
	public void setfieldsType(Map<String, String> fieldsType) {
		this.fieldsType = fieldsType;
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
	 * @return the buttionClass
	 */
	public String getButtionClass() {
		return buttionClass;
	}

	/**
	 * @param buttionClass the buttionClass to set
	 */
	public void setButtionClass(String buttionClass) {
		this.buttionClass = buttionClass;
	}
	
}
