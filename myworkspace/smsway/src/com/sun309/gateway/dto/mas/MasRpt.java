package com.sun309.gateway.dto.mas;

import java.sql.Timestamp;

public class MasRpt
{
	private Long autoSn;

	private Long smId;

	private String mobile;

	private Short rptCode;

	private String rptDesc;

	private Timestamp rptTime;

	public Long getAutoSn()
	{
		return autoSn;
	}

	public void setAutoSn(Long autoSn)
	{
		this.autoSn = autoSn;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public Short getRptCode()
	{
		return rptCode;
	}

	public void setRptCode(Short rptCode)
	{
		this.rptCode = rptCode;
	}

	public String getRptDesc()
	{
		return rptDesc;
	}

	public void setRptDesc(String rptDesc)
	{
		this.rptDesc = rptDesc;
	}

	public Timestamp getRptTime()
	{
		return rptTime;
	}

	public void setRptTime(Timestamp rptTime)
	{
		this.rptTime = rptTime;
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
