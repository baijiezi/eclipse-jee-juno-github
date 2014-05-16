package com.sun309.gateway.dto;

@SuppressWarnings("serial")
public class MessagesContent implements java.io.Serializable
{

	private Integer messageId;

	private String messageContent;

	public MessagesContent()
	{
	}

	public MessagesContent(String messageContent)
	{
		this.messageContent = messageContent;
	}

	public Integer getMessageId()
	{
		return this.messageId;
	}

	public void setMessageId(Integer messageId)
	{
		this.messageId = messageId;
	}

	public String getMessageContent()
	{
		return this.messageContent;
	}

	public void setMessageContent(String messageContent)
	{
		this.messageContent = messageContent;
	}

}