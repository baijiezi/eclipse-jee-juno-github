package com.sun309.dto;

import org.xmappr.Element;
import org.xmappr.RootElement;

@RootElement
public class NetPayResponse 
{
	@Element(targetType = SimpleMessage.class)
	private SimpleMessage 	simplemessage;		/** 通知和结果信息 	*/
	@Element
	private String			payType;
	/**
	 * @return the simplemessage
	 */
	public SimpleMessage getSimplemessage() {
		return simplemessage;
	}
	/**
	 * @param simplemessage the simplemessage to set
	 */
	public void setSimplemessage(SimpleMessage simplemessage) {
		this.simplemessage = simplemessage;
	}
	/**
	 * @return the payType
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * @param payType the payType to set
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
}
