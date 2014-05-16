package com.sun309.dto;

/**
 * Fault entity. @author MyEclipse Persistence Tools
 */

public class Fault 
{

	// Fields

	private Integer id;

	private Long addTime;

	private String extOrderId;

	private String cardNo;

	private String payOrderId;

	private Integer transType;

	// Constructors

	/** default constructor */
	public Fault()
	{
	}

	/** full constructor */
	public Fault(Long addTime, String extOrderId, String cardNo, String payOrderId, Integer transType)
	{
		this.addTime = addTime;
		this.extOrderId = extOrderId;
		this.cardNo = cardNo;
		this.payOrderId = payOrderId;
		this.transType = transType;
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

	public Long getAddTime()
	{
		return this.addTime;
	}

	public void setAddTime(Long addTime)
	{
		this.addTime = addTime;
	}

	public String getExtOrderId()
	{
		return this.extOrderId;
	}

	public void setExtOrderId(String extOrderId)
	{
		this.extOrderId = extOrderId;
	}

	public String getCardNo()
	{
		return this.cardNo;
	}

	public void setCardNo(String cardNo)
	{
		this.cardNo = cardNo;
	}

	public String getPayOrderId()
	{
		return this.payOrderId;
	}

	public void setPayOrderId(String payOrderId)
	{
		this.payOrderId = payOrderId;
	}

	public Integer getTransType()
	{
		return this.transType;
	}

	public void setTransType(Integer transType)
	{
		this.transType = transType;
	}

}