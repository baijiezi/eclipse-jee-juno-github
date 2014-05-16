/**
 * 
 */
package com.sun309.dto;

/**
 * @author Wormwood OPC_GROUP
 * 数据异常DTO类
 */
public class NetPayDataException
{
	private	String		ID;					/** 多支付系统流水ID	*/
	private String		tranLineDetail;		/**	实际支付流水号ID	*/
	private	Long		totalFee;			/** 交易总金额:分	*/
	private	Integer		payStatus;			/**	交易状态			*/
	private	Long		distributeTime;		/**	分发时间			*/
	private Integer		distributeStatus;	/**	分发状态			*/
	private Long		addTime;			/** 添加时间			*/
	private Integer		payType;			/** 多支付实际交易类型	*/
	
	/**
	 * @return the iD
	 */
	public String getID()
	{
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD)
	{
		ID = iD;
	}
	/**
	 * @return the tranLineDetail
	 */
	public String getTranLineDetail()
	{
		return tranLineDetail;
	}
	/**
	 * @param tranLineDetail the tranLineDetail to set
	 */
	public void setTranLineDetail(String tranLineDetail)
	{
		this.tranLineDetail = tranLineDetail;
	}
	/**
	 * @return the totalFee
	 */
	public Long getTotalFee()
	{
		return totalFee;
	}
	/**
	 * @param totalFee the totalFee to set
	 */
	public void setTotalFee(Long totalFee)
	{
		this.totalFee = totalFee;
	}
	/**
	 * @return the payStatus
	 */
	public Integer getPayStatus()
	{
		return payStatus;
	}
	/**
	 * @param payStatus the payStatus to set
	 */
	public void setPayStatus(Integer payStatus)
	{
		this.payStatus = payStatus;
	}
	/**
	 * @return the distributeTime
	 */
	public Long getDistributeTime()
	{
		return distributeTime;
	}
	/**
	 * @param distributeTime the distributeTime to set
	 */
	public void setDistributeTime(Long distributeTime)
	{
		this.distributeTime = distributeTime;
	}
	/**
	 * @return the distributeStatus
	 */
	public Integer getDistributeStatus()
	{
		return distributeStatus;
	}
	/**
	 * @param distributeStatus the distributeStatus to set
	 */
	public void setDistributeStatus(Integer distributeStatus)
	{
		this.distributeStatus = distributeStatus;
	}
	/**
	 * @return the addTime
	 */
	public Long getAddTime()
	{
		return addTime;
	}
	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Long addTime)
	{
		this.addTime = addTime;
	}
	/**
	 * @return the payType
	 */
	public Integer getPayType()
	{
		return payType;
	}
	/**
	 * @param payType the payType to set
	 */
	public void setPayType(Integer payType)
	{
		this.payType = payType;
	}
	
	
}
