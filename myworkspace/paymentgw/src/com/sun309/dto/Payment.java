package com.sun309.dto;

/**
 * Payment entity. @author MyEclipse Persistence Tools
 */

public class Payment 
{

	// Fields

	private String id;

	private Integer transType;

	private String payOrderId;

	private String extOrderId;

	private String cardNo;

	private Integer flag;

	private String message;

	private Long addTime;

	private Integer status;

	// Constructors

	/** default constructor */
	public Payment()
	{
	}

	/** full constructor */
	public Payment(Integer transType, String payOrderId, String extOrderId, String cardNo, Integer flag, String message, Long addTime, Integer status)
	{
		this.transType = transType;
		this.payOrderId = payOrderId;
		this.extOrderId = extOrderId;
		this.cardNo = cardNo;
		this.flag = flag;
		this.message = message;
		this.addTime = addTime;
		this.status = status;
	}

	// Property accessors

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Integer getTransType()
	{
		return this.transType;
	}

	public void setTransType(Integer transType)
	{
		this.transType = transType;
	}

	public String getPayOrderId()
	{
		return this.payOrderId;
	}

	public void setPayOrderId(String payOrderId)
	{
		this.payOrderId = payOrderId;
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

	public Integer getFlag()
	{
		return this.flag;
	}

	public void setFlag(Integer flag)
	{
		this.flag = flag;
	}

	public String getMessage()
	{
		return this.message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Long getAddTime()
	{
		return this.addTime;
	}

	public void setAddTime(Long addTime)
	{
		this.addTime = addTime;
	}

	public Integer getStatus()
	{
		return this.status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

}