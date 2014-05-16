package com.sun309.oldNetPay;

import java.util.Map;

import org.apache.log4j.Logger;

import chinapay.PrivateKey;
import chinapay.SecureLink;

public class ChinaPay extends NetPayService {
	
	private Logger log = Logger.getLogger(ChinaPay.class);
	
	@Override
	public String sign(String param) {
		Map<String, Object> p = super.parseXml(param);
		
		String merId	= (String) p.get("merId");		//商户号
		String orderNo	= (String) p.get("orderNo");	//订单号	16个字节
		String amount	= (String) p.get("amount");		//金额	12个字节，最小单位为分
		String curyId	= (String) p.get("curyId");		//币种	156指人民币
		String transDate= (String) p.get("transDate");	//交易日期	YYYYMMDD
		String transType= (String) p.get("transType");	//交易类型	0001消费		0002退款
		String priv1	= (String) p.get("priv1");		//私有数据，ChinaPay会原样返回

		PrivateKey privateKey = new PrivateKey();
		String path = this.getClass().getClassLoader().getResource("/MerPrK_808080450202901_20100527111119.key").getPath();
		boolean flag = privateKey.buildKey(merId, 0, path);
		
		if (flag == false) {
			log.info("数字签名："+param+"，结果：失败");
		    return "0";
		}
		
		SecureLink t = new SecureLink (privateKey);
		String checkValue = t.Sign(merId+orderNo+amount+curyId+transDate+transType+priv1);
		
		log.info("数字签名："+param+"，结果："+checkValue);
		
		p = null;
		
		return checkValue;
	}
	
	/**
	 * 验证ChinaPay返回的通知
	 */
	@Override
	public String verify(String param) {

		Map<String, Object> p = super.parseXml(param);
		String merId 		= (String) p.get("merId");
		String orderNo		= (String) p.get("orderNo");
		String transDate	= (String) p.get("transDate");
		String amount		= (String) p.get("amount");
		String currencyCode	= (String) p.get("currencyCode");
		String transType	= (String) p.get("transType");
		String status		= (String) p.get("status");
		String checkValue	= (String) p.get("checkValue");
		
		PrivateKey publicKey = new PrivateKey();
		String path = this.getClass().getClassLoader().getResource("/PgPubk.key").getPath();
		boolean flag = publicKey.buildKey("999999999999999", 0, path);
		
		if (flag == false) {
			log.info("验证签名："+param+"，结果：获取KEY失败");
		    return "-1";
		}
		
		SecureLink t = new SecureLink(publicKey);
		boolean res = t.verifyAuthToken(merId, orderNo, amount, currencyCode, transDate, transType, status, checkValue);
		
		log.info("验证签名："+param+"，结果："+res);
		
		p = null;
		
		return res == true ? "1" : "0";
	}
	
	public static void main(String[] args)
	{
		ChinaPay pay = new ChinaPay();
		pay.sign("");
	}
}
