package com.sun309.gateway.dto;

@SuppressWarnings("serial")
public class MoStatus implements java.io.Serializable
{
	private Integer messageId;

	private Integer getMoCounter;

	private Long callBackTime;

	private Long sendTime;

	private String mobile;

	private Long mtId;

	private Long moId;

	private Long rptId;

	private Integer isOvertime;
	
	private String interfaceName;
	
	private String moCallBackUrl;

	public MoStatus()
	{
	}

	public MoStatus(Integer getMoCounter, Long callBackTime, Long sendTime, String mobile, Long mtId, Long moId, Long rptId, Integer isOvertime)
	{
		this.getMoCounter = getMoCounter;
		this.callBackTime = callBackTime;
		this.sendTime = sendTime;
		this.mobile = mobile;
		this.mtId = mtId;
		this.moId = moId;
		this.rptId = rptId;
		this.isOvertime = isOvertime;
	}

	public Integer getMessageId()
	{
		return this.messageId;
	}

	public void setMessageId(Integer messageId)
	{
		this.messageId = messageId;
	}

	public Integer getGetMoCounter()
	{
		return this.getMoCounter;
	}

	public void setGetMoCounter(Integer getMoCounter)
	{
		this.getMoCounter = getMoCounter;
	}

	public Long getCallBackTime()
	{
		return this.callBackTime;
	}

	public void setCallBackTime(Long callBackTime)
	{
		this.callBackTime = callBackTime;
	}

	public Long getSendTime()
	{
		return this.sendTime;
	}

	public void setSendTime(Long sendTime)
	{
		this.sendTime = sendTime;
	}

	public String getMobile()
	{
		return this.mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
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

	public Integer getIsOvertime()
	{
		return this.isOvertime;
	}

	public void setIsOvertime(Integer isOvertime)
	{
		this.isOvertime = isOvertime;
	}

	public String getInterfaceName()
	{
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName)
	{
		this.interfaceName = interfaceName;
	}

	public String getMoCallBackUrl()
	{
		return moCallBackUrl;
	}

	public void setMoCallBackUrl(String moCallBackUrl)
	{
		this.moCallBackUrl = moCallBackUrl;
	}

}