package com.sun309.ws;

import java.net.URL;

import org.apache.commons.httpclient.params.HttpClientParams;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;

import com.sun309.factory.Factory;
import com.sun309.util.NetPayXmlTools;

public class WsClient
{
	String url = "http://localhost:8080/paymentgw/services/PaymentGwService?wsdl";
	public void receive()
	{
		NetPayXmlTools tools = (NetPayXmlTools)Factory.create(NetPayXmlTools.class);
		
		String methodA = "getPayInfoAsync";
		String methodB = "getPayInfoResultAsync";
		String methodC = "getPayInfo";
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
		};
		String[] r = null;
		Object[] results = null;
		try
		{
			r = new String[]{tools.createAsyncTestXmlString().toString()};
			System.out.println("----------------- Request A----------------------");
			System.out.println(tools.createAsyncTestXmlString().toString());
			System.out.println("----------------- Request A----------------------");
			results = client.invoke(methodA, r);
			System.out.println("----------------- Result A----------------------");
			System.out.println(results[0]);
			System.out.println("----------------- Result A----------------------");
			
			r = new String[]{tools.createAsyncResultTestXmlString(tools.parseAsyncID(results[0].toString())).toString()};
			System.out.println("----------------- Request B----------------------");
			System.out.println(tools.createAsyncResultTestXmlString(tools.parseAsyncID(results[0].toString())).toString());
			System.out.println("----------------- Request B----------------------");
			Object[] resultss = client.invoke(methodB, r);
			System.out.println("----------------- Result B----------------------");
			System.out.println(resultss[0]);
			System.out.println("----------------- Result B----------------------");
			
			r = new String[]{tools.createAsyncResultTestXmlOnlyNPGWIDString(tools.parseAsyncID(results[0].toString())).toString()};
			System.out.println("----------------- Request C----------------------");
			System.out.println(tools.createAsyncResultTestXmlOnlyNPGWIDString(tools.parseAsyncID(results[0].toString())).toString());
			System.out.println("----------------- Request C----------------------");
			results = client.invoke(methodB, r);
			System.out.println("----------------- Result C----------------------");
			System.out.println(results[0]);
			System.out.println("----------------- Result C----------------------");
			
			r = new String[]{tools.createTestXmlString().toString()};
			System.out.println("----------------- Request D----------------------");
			System.out.println(tools.createTestXmlString().toString());
			System.out.println("----------------- Request D----------------------");
			results = client.invoke(methodC, r);
			System.out.println("----------------- Result D----------------------");
			System.out.println(results[0]);
			System.out.println("----------------- Result D----------------------");
			
			r = new String[]{tools.createAsyncResultTestXmlOnlyNPGWIDString(tools.parseAsyncID(results[0].toString())).toString()};
			System.out.println("----------------- Request E----------------------");
			System.out.println(tools.createAsyncResultTestXmlOnlyNPGWIDString(tools.parseAsyncID(results[0].toString())).toString());
			System.out.println("----------------- Request E----------------------");
			results = client.invoke(methodB, r);
			System.out.println("----------------- Result E----------------------");
			System.out.println(results[0]);
			System.out.println("----------------- Result E----------------------");
			
		}
		catch (Exception e)
		{
			System.out.println(e + "---");
		}
	}
	
	public static void main(String[] args)
	{
		WsClient ws = new WsClient();
		ws.receive();
	}
}
