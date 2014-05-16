package com.sun309.gateway.dto;


@SuppressWarnings("serial")
public class Messages implements java.io.Serializable
{
	private Integer messageId;

	private String mobile;

	private Long sendTime;

	private Integer sendLevel;

	private Integer type;

	private Long addTime;

	private Long receiveTime;

	private Integer isTest;

	private Integer status;

	private Integer sendCounter;

	private Integer needCallBack;

	private Long callBackTime;

	private Integer enable;

	private Integer isResend;

	private String rptCallBackUrl;

	private String moCallBackUrl;

	private String comeFrom;

	private String interfaceName;
	
	private MessagesContent content = new MessagesContent();
	
	private RptStatus rptStatus = new RptStatus();
	
	private String methed;
	
	private String operName;
	
	private String access;

	public Messages()
	{
	}

	public Messages(String mobile, Long sendTime, Integer sendLevel, Integer type, Long addTime, Long receiveTime, Integer isTest, Integer status, Integer sendCounter, Integer needCallBack, Long callBackTime, Integer enable, Integer isResend, String rptCallBackUrl, String moCallBackUrl, String comeFrom, String interfaceName, String operName, String access)
	{
		this.mobile = mobile;
		this.sendTime = sendTime;
		this.sendLevel = sendLevel;
		this.type = type;
		this.addTime = addTime;
		this.receiveTime = receiveTime;
		this.isTest = isTest;
		this.status = status;
		this.sendCounter = sendCounter;
		this.needCallBack = needCallBack;
		this.callBackTime = callBackTime;
		this.enable = enable;
		this.isResend = isResend;
		this.rptCallBackUrl = rptCallBackUrl;
		this.moCallBackUrl = moCallBackUrl;
		this.comeFrom = comeFrom;
		this.interfaceName = interfaceName;
		this.operName = operName;
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

	public Integer getSendLevel()
	{
		return this.sendLevel;
	}

	public void setSendLevel(Integer sendLevel)
	{
		this.sendLevel = sendLevel;
	}

	public Integer getType()
	{
		return this.type;
	}

	public void setType(Integer type)
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

	public Long getReceiveTime()
	{
		return this.receiveTime;
	}

	public void setReceiveTime(Long receiveTime)
	{
		this.receiveTime = receiveTime;
	}

	public Integer getIsTest()
	{
		return this.isTest;
	}

	public void setIsTest(Integer isTest)
	{
		this.isTest = isTest;
	}

	public Integer getStatus()
	{
		return this.status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Integer getSendCounter()
	{
		return this.sendCounter;
	}

	public void setSendCounter(Integer sendCounter)
	{
		this.sendCounter = sendCounter;
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

	public MessagesContent getContent()
	{
		return content;
	}

	public void setContent(MessagesContent content)
	{
		this.content = content;
	}

	public RptStatus getRptStatus()
	{
		return rptStatus;
	}

	public void setRptStatus(RptStatus rptStatus)
	{
		this.rptStatus = rptStatus;
	}

	public String getMethed()
	{
		return methed;
	}

	public void setMethed(String methed)
	{
		this.methed = methed;
	}

	public String getOperName()
	{
		return operName;
	}

	public void setOperName(String operName)
	{
		this.operName = operName;
	}

	public String getAccess()
	{
		return access;
	}

	public void setAccess(String access)
	{
		this.access = access;
	}
}