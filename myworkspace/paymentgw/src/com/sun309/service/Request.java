package com.sun309.service;

import com.sun309.util.Validate;
import com.sun309.util.Validation;

public interface Request extends Validation 
{
	public String 	getOutTtradeNo();					/** 业务系统外部订单号 	*/
	public String 	getWaresCode();						/** 多支付服务商品代码 	*/
	public Long	 	getTotalFee();						/** 支付总金额			*/
	public String	getReturnUrl();						/** 通知地址				*/
	public String	getClientIP();						/** 实际支付请求发起客户机IP	*/
	public Integer	getPayType();						/** 多支付服务支付类型	*/
	public String	getResultType();					/** 返回结果类型			*/
	public Long 	getExpireTime();					/** 支付请求超时间		*/
	public void 	setValidater(Validate validater);	/** 设置类验证工具类		*/
	public String 	getValidateInfo();					/** 获取验证结果信息		*/
	public String 	getNPGWID();						/** 取得多支付流水ID		*/
	public Boolean 	getInitTag();						/** 取得异步生成标识		*/
}
