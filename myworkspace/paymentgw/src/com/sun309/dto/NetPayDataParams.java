/**
 * 
 */
package com.sun309.dto;

/**
 * @author Wormwood OPC_GROUP
 *
 */
public class NetPayDataParams
{
	/** 多支付流水ID 	*/
	private String ID;	
	
	/** 入口参数 			*/
	private String inputParams;	
	
	/** 出口参数			*/
	private String outputParams;	
	
	public String getID()
	{
		return ID;
	}
	public void setID(String iD)
	{
		ID = iD;
	}
	public String getInputParams()
	{
		return inputParams;
	}
	public void setInputParams(String inputParams)
	{
		this.inputParams = inputParams;
	}
	public String getOutputParams()
	{
		return outputParams;
	}
	public void setOutputParams(String outputParams)
	{
		this.outputParams = outputParams;
	}
	
	
}
