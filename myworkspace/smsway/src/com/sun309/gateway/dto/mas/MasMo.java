package com.sun309.gateway.dto.mas;

import java.sql.Timestamp;

public class MasMo
{
	private Long autoSn;

	private Long smId;

	private String mobile;

	private String content;

	private Timestamp moTime;

	private Integer msgFmt;

	public Long getAutoSn()
	{
		return autoSn;
	}

	public void setAutoSn(Long autoSn)
	{
		this.autoSn = autoSn;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public Timestamp getMoTime()
	{
		return moTime;
	}

	public void setMoTime(Timestamp moTime)
	{
		this.moTime = moTime;
	}

	public Integer getMsgFmt()
	{
		return msgFmt;
	}

	public void setMsgFmt(Integer msgFmt)
	{
		this.msgFmt = msgFmt;
	}

	public Long getSmId()
	{
		return smId;
	}

	public void setSmId(Long smId)
	{
		this.smId = smId;
	}

}
