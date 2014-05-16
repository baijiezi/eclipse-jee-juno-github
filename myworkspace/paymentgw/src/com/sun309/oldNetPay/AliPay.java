package com.sun309.oldNetPay;

import java.util.Map;

import org.apache.log4j.Logger;

import com.alipay.util.CheckURL;
import com.alipay.util.Payment;

public class AliPay extends NetPayService {
	
	protected String partner = "2088102681649780";
	protected String key = "30ruggr3udodi58jn27kucd5fka7ixky";
	protected String sellerEmail = "13538788208";
	
	private Logger log = Logger.getLogger(AliPay.class);

	@Override
	public String sign(String param) {
		
		Map<String, Object> p = super.parseXml(param);
		
		String paygateway	= "https://www.alipay.com/cooperate/gateway.do?";
		String service		= "create_direct_pay_by_user";
		String sign_type	= "MD5";
		String out_trade_no	= (String) p.get("out_trade_no");
		String input_charset= "utf-8";
		String partner		= this.partner;
		String key			= this.key;
		String show_url		= (String) p.get("show_url");
		String body			= (String) p.get("body");
		String total_fee	= (String) p.get("total_fee");
		String payment_type	= "1";
		String seller_email	= this.sellerEmail;
		String subject		= (String) p.get("subject");
		String notify_url	= (String) p.get("notify_url");
		String return_url	= (String) p.get("return_url");
		String paymethod	= "bankPay";
		String defaultbank	= "ICBCB2C";
		
		String url = Payment.CreateUrl(paygateway, service, sign_type, out_trade_no, input_charset, partner, key, show_url, body, total_fee, payment_type, seller_email, subject, notify_url, return_url, paymethod, defaultbank);
		log.info("数字签名："+param+"，结果："+url);
		
		p = null;
		
		return url;
	}

	@Override
	public String verify(String param) {
		Map<String, Object> p = super.parseXml(param);
		String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?"
			+ "partner="+this.partner
			+ "&notify_id="+p.get("notify_id");
		String res = CheckURL.check(alipayNotifyURL);
		
		log.info("验证签名："+param+"，结果："+res);
		p = null;
		
		return res.equals("true") ? "1" : "0";
	}

}
