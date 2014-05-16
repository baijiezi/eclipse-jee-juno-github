package com.sun309.test;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.sun309.util.HttpURLConnectionRequest;

public class Test {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Logger log = Logger.getLogger(Test.class);
		try
		{  
			HttpURLConnectionRequest http = new HttpURLConnectionRequest();
			String postUrl = "http://127.0.0.1:8080/paymentgw/servlet/AliPayReceive";
			Hashtable<String,String> params = new Hashtable<String,String>();
			params.put("notify_id", "2152-141552-25241");
			params.put("trade_no", "1111111111111111");
			
			params.put("out_trade_no", "OPCN20100812000000121");
			params.put("trade_status", "WAIT_SELLER_SEND_GOODS");
			params.put("total_fee", "7");
			String result = http.doSendPostRequest(postUrl, params);
			log.debug(new StringBuilder("POST 结果数据到：").append(postUrl)
					.append("返回结果为：").append(result)
					.append("数据为：notify_id=").append(params.get("notify_id"))
					.append(" trade_no=").append(params.get("trade_status"))
					.append(" out_trade_no=").append(params.get("trade_no"))
					.append(" trade_status=").append(params.get("trade_status"))
					.append(" total_fee=").append(params.get("total_fee")));
			if(result.equals("SUCCEED"))
			{
				log.debug("Send OK...............");
			}
			else
			{
				log.debug("Send Fail............");
			}
		}
		catch (Exception e) 
		{
			log.debug(new StringBuilder("expction:").append(e.getMessage()).append("cause:")
					.append(e.getCause()).append("stack:").append(e.getStackTrace()).append("class:").append(e.getClass()));
		}
	}

}
