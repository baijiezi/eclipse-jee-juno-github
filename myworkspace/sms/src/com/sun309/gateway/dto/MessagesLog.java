package com.sun309.gateway.dto;

@SuppressWarnings("serial")
public class MessagesLog implements java.io.Serializable
{
	private Integer messageId;

	private String mobile;

	private Long sendTime;

	private Short type;

	private Long addTime;

	private Short status;

	private Long receiveTime;

	private Long mtId;

	private Long moId;

	private Long rptId;

	private Byte isTest;

	private Byte isCallBack;

	private Integer needCallBack;

	private Long callBackTime;

	private Integer isResend;

	private String rptCallBackUrl;

	private String moCallBackUrl;

	private String comeFrom;

	private String interfaceName;
	
	private MessagesContent messagesContent = new MessagesContent();
	
	private String methed;

	public MessagesLog()
	{
	}

	public MessagesLog(String mobile, Long sendTime, Short type, Long addTime, Short status, Long receiveTime, Long mtId, Long moId, Long rptId, Byte isTest, Byte isCallBack, Integer needCallBack, Long callBackTime, Integer isResend, String rptCallBackUrl, String moCallBackUrl, String comeFrom, String interfaceName)
	{
		this.mobile = mobile;
		this.sendTime = sendTime;
		this.type = type;
		this.addTime = addTime;
		this.status = status;
		this.receiveTime = receiveTime;
		this.mtId = mtId;
		this.moId = moId;
		this.rptId = rptId;
		this.isTest = isTest;
		this.isCallBack = isCallBack;
		this.needCallBack = needCallBack;
		this.callBackTime = callBackTime;
		this.isResend = isResend;
		this.rptCallBackUrl = rptCallBackUrl;
		this.moCallBackUrl = moCallBackUrl;
		this.comeFrom = comeFrom;
		this.interfaceName = interfaceName;
	}

	public Integer getMessageId()
	{
		return this.messageId;
	}

	public void setMessageId(Integer messageId)
	{
		this.messageId = messageId;
	}

	public String getMobile()
	{
		return this.mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public Long getSendTime()
	{
		return this.sendTime;
	}

	public void setSendTime(Long sendTime)
	{
		this.sendTime = sendTime;
	}

	public Short getType()
	{
		return this.type;
	}

	public void setType(Short type)
	{
		this.type = type;
	}

	public Long getAddTime()
	{
		return this.addTime;
	}

	public void setAddTime(Long addTime)
	{
		this.addTime = addTime;
	}

	public Short getStatus()
	{
		return this.status;
	}

	public void setStatus(Short status)
	{
		this.status = status;
	}

	public Long getReceiveTime()
	{
		return this.receiveTime;
	}

	public void setReceiveTime(Long receiveTime)
	{
		this.receiveTime = receiveTime;
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

	public Byte getIsTest()
	{
		return this.isTest;
	}

	public void setIsTest(Byte isTest)
	{
		this.isTest = isTest;
	}

	public Byte getIsCallBack()
	{
		return this.isCallBack;
	}

	public void setIsCallBack(Byte isCallBack)
	{
		this.isCallBack = isCallBack;
	}

	public Integer getNeedCallBack()
	{
		return this.needCallBack;
	}

	public void setNeedCallBack(Integer needCallBack)
	{
		this.needCallBack = needCallBack;
	}

	public Long getCallBackTime()
	{
		return this.callBackTime;
	}

	public void setCallBackTime(Long callBackTime)
	{
		this.callBackTime = callBackTime;
	}

	public Integer getIsResend()
	{
		return this.isResend;
	}

	public void setIsResend(Integer isResend)
	{
		this.isResend = isResend;
	}

	public String getRptCallBackUrl()
	{
		return this.rptCallBackUrl;
	}

	public void setRptCallBackUrl(String rptCallBackUrl)
	{
		this.rptCallBackUrl = rptCallBackUrl;
	}

	public String getMoCallBackUrl()
	{
		return this.moCallBackUrl;
	}

	public void setMoCallBackUrl(String moCallBackUrl)
	{
		this.moCallBackUrl = moCallBackUrl;
	}

	public String getComeFrom()
	{
		return this.comeFrom;
	}

	public void setComeFrom(String comeFrom)
	{
		this.comeFrom = comeFrom;
	}

	public String getInterfaceName()
	{
		return this.interfaceName;
	}

	public void setInterfaceName(String interfaceName)
	{
		this.interfaceName = interfaceName;
	}

	public MessagesContent getMessagesContent()
	{
		return messagesContent;
	}

	public void setMessagesContent(MessagesContent messagesContent)
	{
		this.messagesContent = messagesContent;
	}

	public String getMethed()
	{
		return methed;
	}

	public void setMethed(String methed)
	{
		this.methed = methed;
	}
	
}