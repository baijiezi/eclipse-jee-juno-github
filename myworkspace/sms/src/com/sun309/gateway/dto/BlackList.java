package com.sun309.gateway.dto;
 
public class BlackList 
{
	private Integer id;

	private String mobile;

	private Long addTime;


	public BlackList()
	{
	}

	/** full constructor */
	public BlackList(String mobile, Long addTime)
	{
		this.mobile = mobile;
		this.addTime = addTime;
	}

	// Property accessors

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getMobile()
	{
		return this.mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
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