package com.sun309.service;

public interface BaseReceive 
{
	public String 	getOutTradeNo();		/** 多支付服务唯一ID号 	*/
	public Long 	getTotalFee();			/** 支付总金额 			*/
	public String	getTradeNo();			/** 实际支付流水号		*/
	public Integer 	getRealPayType();		/** 多支付实际支付类型	*/
	public String 	getInitInfo();			/** 数据接收初始化日志	*/
	public boolean 	validateTradeStatus();	/** 返回交易状态验证		*/
}
