package com.sun309.gateway.dto;

@SuppressWarnings("serial")
public class Interfaces implements java.io.Serializable
{
	private Integer interfaceId;

	private String interfaceName;

	public Interfaces()
	{
	}

	public Interfaces(String interfaceName)
	{
		this.interfaceName = interfaceName;
	}

	public Integer getInterfaceId()
	{
		return this.interfaceId;
	}

	public void setInterfaceId(Integer interfaceId)
	{
		this.interfaceId = interfaceId;
	}

	public String getInterfaceName()
	{
		return this.interfaceName;
	}

	public void setInterfaceName(String interfaceName)
	{
		this.interfaceName = interfaceName;
	}

}