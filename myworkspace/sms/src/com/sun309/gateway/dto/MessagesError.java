package com.sun309.gateway.dto;

@SuppressWarnings("serial")
public class MessagesError implements java.io.Serializable
{
	private Integer errorId;

	private Integer messageId;

	public MessagesError()
	{
	}

	public MessagesError(Integer messageId)
	{
		this.messageId = messageId;
	}

	public Integer getErrorId()
	{
		return this.errorId;
	}

	public void setErrorId(Integer errorId)
	{
		this.errorId = errorId;
	}

	public Integer getMessageId()
	{
		return this.messageId;
	}

	public void setMessageId(Integer messageId)
	{
		this.messageId = messageId;
	}

}