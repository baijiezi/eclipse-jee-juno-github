package com.sun309.dto;

/**
 * Log entity. @author MyEclipse Persistence Tools
 */

public class Log  
{

	// Fields

	private Integer logId;

	private String logContent;

	private Long addTime;

	// Constructors

	/** default constructor */
	public Log()
	{
	}

	/** full constructor */
	public Log(String logContent, Long addTime)
	{
		this.logContent = logContent;
		this.addTime = addTime;
	}

	// Property accessors

	public Integer getLogId()
	{
		return this.logId;
	}

	public void setLogId(Integer logId)
	{
		this.logId = logId;
	}

	public String getLogContent()
	{
		return this.logContent;
	}

	public void setLogContent(String logContent)
	{
		this.logContent = logContent;
	}

	public Long getAddTime()
	{
		return this.addTime;
	}

	public void setAddTime(Long addTime)
	{
		this.addTime = addTime;
	}

}