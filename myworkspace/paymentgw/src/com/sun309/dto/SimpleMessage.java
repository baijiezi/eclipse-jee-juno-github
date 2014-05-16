package com.sun309.dto;

import org.xmappr.Element;
import org.xmappr.RootElement;
@RootElement
public class SimpleMessage 
{
	@Element
	private	int		error;		/** 信息代码：1：成功，0：失败*/
	@Element
	private String 	message;	/** 信息内容 XXXX 			*/
	
	public SimpleMessage(int i, String validateInfo) 
	{
		// TODO Auto-generated constructor stub
		this.error 		= i;
		this.message 	= validateInfo;
	}

	/**
	 * @return the error
	 */
	public int getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(int error) {
		this.error = error;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
