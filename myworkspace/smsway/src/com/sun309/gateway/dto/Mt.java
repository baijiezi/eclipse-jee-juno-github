package com.sun309.gateway.dto;

@SuppressWarnings("serial")
public class Mt implements java.io.Serializable
{
	private Long id;

	private Long smId;

	private Long srcId;

	public Mt()
	{
	}

	public Mt(Long smId, Long srcId)
	{
		this.smId = smId;
		this.srcId = srcId;
	}

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getSmId()
	{
		return this.smId;
	}

	public void setSmId(Long smId)
	{
		this.smId = smId;
	}

	public Long getSrcId()
	{
		return this.srcId;
	}

	public void setSrcId(Long srcId)
	{
		this.srcId = srcId;
	}

}