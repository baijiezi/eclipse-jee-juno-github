package com.sun309.gateway.dto;

public class ResponseResult
{
	private Integer id;

	private String xml;

	private Long addTime;

	private String type;

	public Long getAddTime()
	{
		return addTime;
	}

	public void setAddTime(Long addTime)
	{
		this.addTime = addTime;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getXml()
	{
		return xml;
	}

	public void setXml(String xml)
	{
		this.xml = xml;
	}

}
