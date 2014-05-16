package com.sun309.gateway.dto;

@SuppressWarnings("serial")
public class Mo implements java.io.Serializable
{
	private Integer moId;

	private Integer messageId;

	private Long smId;

	private Long moTime;

	private Integer msgFmt;

	private String content;

	private Long addTime;

	private Long sendTime;

	private Long callBackTime;

	private String mobile;

	private Integer enable;
	
	private String interfaceName;

	public Mo()
	{
	}

	public Mo(Integer messageId, Long smId, Long moTime, Integer msgFmt, String content, Long addTime, Long sendTime, Long callBackTime, String mobile, Integer enable)
	{
		this.messageId = messageId;
		this.smId = smId;
		this.moTime = moTime;
		this.msgFmt = msgFmt;
		this.content = content;
		this.addTime = addTime;
		this.sendTime = sendTime;
		this.callBackTime = callBackTime;
		this.mobile = mobile;
		this.enable = enable;
	}

	public Integer getMoId()
	{
		return this.moId;
	}

	public void setMoId(Integer moId)
	{
		this.moId = moId;
	}

	public Integer getMessageId()
	{
		return this.messageId;
	}

	public void setMessageId(Integer messageId)
	{
		this.messageId = messageId;
	}

	public Long getSmId()
	{
		return this.smId;
	}

	public void setSmId(Long smId)
	{
		this.smId = smId;
	}

	public Long getMoTime()
	{
		return this.moTime;
	}

	public void setMoTime(Long moTime)
	{
		this.moTime = moTime;
	}

	public Integer getMsgFmt()
	{
		return this.msgFmt;
	}

	public void setMsgFmt(Integer msgFmt)
	{
		this.msgFmt = msgFmt;
	}

	public String getContent()
	{
		return this.content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public Long getAddTime()
	{
		return this.addTime;
	}

	public void setAddTime(Long addTime)
	{
		this.addTime = addTime;
	}

	public Long getSendTime()
	{
		return this.sendTime;
	}

	public void setSendTime(Long sendTime)
	{
		this.sendTime = sendTime;
	}

	public Long getCallBackTime()
	{
		return this.callBackTime;
	}

	public void setCallBackTime(Long callBackTime)
	{
		this.callBackTime = callBackTime;
	}

	public String getMobile()
	{
		return this.mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public Integer getEnable()
	{
		return this.enable;
	}

	public void setEnable(Integer enable)
	{
		this.enable = enable;
	}

	public String getInterfaceName()
	{
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName)
	{
		this.interfaceName = interfaceName;
	}

}