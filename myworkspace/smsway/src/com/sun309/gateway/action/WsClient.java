package com.sun309.gateway.action;

import java.net.URL;
import java.util.Date;

import org.apache.commons.httpclient.params.HttpClientParams;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;

public class WsClient
{
	String url = "http://192.168.200.220:8080/smsway/services/smsService?wsdl";
	
//	String url = "http://192.168.0.206:8080/sms_gateway/services/smsService?wsdl";
	
//	String url = "http://192.168.200.220:8080/sms_gateway/services/smsService?wsdl";
	
//	String url = "http://localhost:8080/smsway/services/smsService?wsdl";
	public void send()
	{
		String input = "<?xml version='1.0' encoding='utf-8'?><root><message><oper_name>sms1001</oper_name><mobile>18933913689</mobile>" +
				"<send_time>2011-11-14 16:00:00</send_time><send_level>2</send_level><is_test>1</is_test><need_call_back>0</need_call_back>" +
				"<call_back_time>2009-09-01 00:00:00</call_back_time>" +
				"<rpt_call_back_url>http://124.172.245.98:8080/sms_gateway/test.do?act=rpt</rpt_call_back_url>" +
				"<mo_call_back_url>http://124.172.245.98:8080/sms_gateway/test.do?act=mo</mo_call_back_url>" +
				"<come_from>总平台预约挂号</come_from><interface_name>gateway</interface_name>" +
				"<message_content>this is comed!</message_content></message></root>";
		String method = "send";
		Client client = null;
		try
		{
			client = new Client(new URL(url));
			HttpClientParams params = new HttpClientParams();
			params.setParameter(HttpClientParams.USE_EXPECT_CONTINUE,Boolean.FALSE);
			//设置ws连接超时时间
			params.setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT, (long)60000); 
			client.setProperty(CommonsHttpMessageSender.HTTP_CLIENT_PARAMS,params);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		String[] r = new String[]{input};
		Object[] results = null;
		try
		{
			results = client.invoke(method, r);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println((results!=null && results.length > 0)?results[0]:"");
	}
	public static void main(String[] args)
	{
		WsClient wb=new WsClient();
		Date date = new Date();
		for (int i = 0; i < 1; i++)
		{
			wb.send();
		
		}
		System.out.println((new Date()).getTime()-date.getTime());
		
		
	}
}
