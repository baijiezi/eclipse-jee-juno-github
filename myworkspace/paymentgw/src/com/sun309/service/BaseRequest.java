package com.sun309.service;

import com.sun309.dto.NetPayData;
import com.sun309.util.Validate;
import com.sun309.util.Validation;

public interface BaseRequest extends Validation
{
	public void 	sign();								/** 支付签名方法	   */
	public void		Init(Request req);  				/** XML参数赋值方法 */
	public String 	getHTMLPayType();					/** HTML结果生成方法*/
	public String 	LoadInfo(); 						/** LogInfo 方法 类加载后*/
	public String 	InitInfo();							/** LogInfo 方法 初始化类*/
	public String 	SignInfo();							/** LogInfo 方法 签名生成*/
	public String 	ResultInfo();						/** LogInfo 方法 结果生成*/
	public void 	setValidater(Validate validater); 	/** 类成员数据格式验证类SET方法 */
	public String 	getValidateInfo();				  	/** 类成员数据格式验证结果方法 */
	public NetPayData getData();						/** 数据库记录数据类生成 */
	public boolean	verifyReceieData();					/** 数据签名验证方法 */
	public String getID();								/** 多支付流水ID */
	public void setID(String ID);						/** 多支付流水ID */
}
