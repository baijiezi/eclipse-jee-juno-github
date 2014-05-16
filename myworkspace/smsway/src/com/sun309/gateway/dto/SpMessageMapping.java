package com.sun309.gateway.dto;

public class SpMessageMapping 
{
	private Integer messageId;

	private String spMessageId;

	private Long addTime;

	private String access;

	public SpMessageMapping()
	{
	}

	public SpMessageMapping(String spMessageId, Long addTime, String access)
	{
		this.spMessageId = spMessageId;
		this.addTime = addTime;
		this.access = access;
	}

	public Integer getMessageId()
	{
		return this.messageId;
	}

	public void setMessageId(Integer messageId)
	{
		this.messageId = messageId;
	}

	public String getSpMessageId()
	{
		return this.spMessageId;
	}

	public void setSpMessageId(String spMessageId)
	{
		this.spMessageId = spMessageId;
	}

	public Long getAddTime()
	{
		return this.addTime;
	}

	public void setAddTime(Long addTime)
	{
		this.addTime = addTime;
	}

	public String getAccess()
	{
		return this.access;
	}

	public void setAccess(String access)
	{
		this.access = access;
	}

}