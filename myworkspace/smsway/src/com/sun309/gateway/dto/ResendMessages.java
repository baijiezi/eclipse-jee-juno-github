package com.sun309.gateway.dto;


@SuppressWarnings("serial")
public class ResendMessages implements java.io.Serializable
{
	private Integer reSendId;

	private Integer oldMessagesId;

	private Long addTime;

	private Integer newMessagesId;


	public ResendMessages()
	{
	}

	public ResendMessages(Integer oldMessagesId, Long addTime, Integer newMessagesId)
	{
		this.oldMessagesId = oldMessagesId;
		this.addTime = addTime;
		this.newMessagesId = newMessagesId;
	}

	public Integer getReSendId()
	{
		return this.reSendId;
	}

	public void setReSendId(Integer reSendId)
	{
		this.reSendId = reSendId;
	}

	public Integer getOldMessagesId()
	{
		return this.oldMessagesId;
	}

	public void setOldMessagesId(Integer oldMessagesId)
	{
		this.oldMessagesId = oldMessagesId;
	}

	public Long getAddTime()
	{
		return this.addTime;
	}

	public void setAddTime(Long addTime)
	{
		this.addTime = addTime;
	}

	public Integer getNewMessagesId()
	{
		return this.newMessagesId;
	}

	public void setNewMessagesId(Integer newMessagesId)
	{
		this.newMessagesId = newMessagesId;
	}

}