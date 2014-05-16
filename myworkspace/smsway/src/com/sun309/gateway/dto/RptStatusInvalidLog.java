package com.sun309.gateway.dto;


public class RptStatusInvalidLog
{
	private Integer messageId;

	private Integer getRptCounter;

	private Long readTime;

	private Short status;

	private Long mtId;

	private Long moId;

	private Long rptId;

	private Integer enable;

	private Integer isResend;

	private String interfaceName;

	private String rptCallBackUrl;

	public RptStatusInvalidLog()
	{
	}

	public RptStatusInvalidLog(Integer getRptCounter, Long readTime, Short status, Long mtId, Long moId, Long rptId, Integer enable, Integer isResend, String interfaceName, String rptCallBackUrl)
	{
		this.getRptCounter = getRptCounter;
		this.readTime = readTime;
		this.status = status;
		this.mtId = mtId;
		this.moId = moId;
		this.rptId = rptId;
		this.enable = enable;
		this.isResend = isResend;
		this.interfaceName = interfaceName;
		this.rptCallBackUrl = rptCallBackUrl;
	}

	public Integer getMessageId()
	{
		return this.messageId;
	}

	public void setMessageId(Integer messageId)
	{
		this.messageId = messageId;
	}

	public Integer getGetRptCounter()
	{
		return this.getRptCounter;
	}

	public void setGetRptCounter(Integer getRptCounter)
	{
		this.getRptCounter = getRptCounter;
	}

	public Long getReadTime()
	{
		return this.readTime;
	}

	public void setReadTime(Long readTime)
	{
		this.readTime = readTime;
	}

	public Short getStatus()
	{
		return this.status;
	}

	public void setStatus(Short status)
	{
		this.status = status;
	}

	public Long getMtId()
	{
		return this.mtId;
	}

	public void setMtId(Long mtId)
	{
		this.mtId = mtId;
	}

	public Long getMoId()
	{
		return this.moId;
	}

	public void setMoId(Long moId)
	{
		this.moId = moId;
	}

	public Long getRptId()
	{
		return this.rptId;
	}

	public void setRptId(Long rptId)
	{
		this.rptId = rptId;
	}

	public Integer getEnable()
	{
		return this.enable;
	}

	public void setEnable(Integer enable)
	{
		this.enable = enable;
	}

	public Integer getIsResend()
	{
		return this.isResend;
	}

	public void setIsResend(Integer isResend)
	{
		this.isResend = isResend;
	}

	public String getInterfaceName()
	{
		return this.interfaceName;
	}

	public void setInterfaceName(String interfaceName)
	{
		this.interfaceName = interfaceName;
	}

	public String getRptCallBackUrl()
	{
		return this.rptCallBackUrl;
	}

	public void setRptCallBackUrl(String rptCallBackUrl)
	{
		this.rptCallBackUrl = rptCallBackUrl;
	}

}