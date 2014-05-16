package com.sun309.dto;

import org.xmappr.Element;
import org.xmappr.RootElement;

@RootElement
public class SystemInfo 
{
	@Element
	private String notifyUrl;  	/** 多支付网服务数据接收地址 	**/
	@Element
	private String subject;		/** 统计设置的商品名称      	**/
	/**
	 * @return the notifyUrl
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}
	/**
	 * @param notifyUrl the notifyUrl to set
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
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
}
