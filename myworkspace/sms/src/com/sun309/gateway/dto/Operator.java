package com.sun309.gateway.dto;
 
public class Operator 
{
	private Integer operId;

	private String operName;

	private Long addTime;

	private String appDesc;

	private String access;

	public Operator()
	{
	}

	/** full constructor */
	public Operator(String operName, Long addTime, String appDesc, String access)
	{
		this.operName = operName;
		this.addTime = addTime;
		this.appDesc = appDesc;
		this.access = access;
	}

	// Property accessors

	public Integer getOperId()
	{
		return this.operId;
	}

	public void setOperId(Integer operId)
	{
		this.operId = operId;
	}

	public String getOperName()
	{
		return this.operName;
	}

	public void setOperName(String operName)
	{
		this.operName = operName;
	}

	public Long getAddTime()
	{
		return this.addTime;
	}

	public void setAddTime(Long addTime)
	{
		this.addTime = addTime;
	}

	public String getAppDesc()
	{
		return this.appDesc;
	}

	public void setAppDesc(String appDesc)
	{
		this.appDesc = appDesc;
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