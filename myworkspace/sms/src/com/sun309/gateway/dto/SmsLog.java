package com.sun309.gateway.dto;

public class SmsLog
{
	private Integer logId;

	private Integer messageId;

	private String xml;

	private String type;

	private Long addTime;
	
	private String ip;

	public Long getAddTime()
	{
		return addTime;
	}

	public void setAddTime(Long addTime)
	{
		this.addTime = addTime;
	}

	public Integer getLogId()
	{
		return logId;
	}

	public void setLogId(Integer logId)
	{
		this.logId = logId;
	}

	public Integer getMessageId()
	{
		return messageId;
	}

	public void setMessageId(Integer messageId)
	{
		this.messageId = messageId;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getXml()
	{
		return xml;
	}

	public void setXml(String xml)
	{
		this.xml = xml;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}
	
}
