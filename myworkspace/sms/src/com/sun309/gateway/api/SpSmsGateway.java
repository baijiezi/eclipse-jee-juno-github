package com.sun309.gateway.api;

import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;

import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;
import com.sun309.gateway.util.DateUtils;
import com.sun309.gateway.util.ProcessPassword;

public class SpSmsGateway
{
	private static LogService log = LogFactory.create(LogFactory.SEND_DATA, SpSmsGateway.class);
	
	private static final String URL = "http://61.145.168.234:90/Interface.asmx?WSDL";
	private static final String USER_NAME = "sun309";//"kangjianyiliao";
	private static final String PWD = "123456";//"kangjian008";
	private static SpMessage m;
	
	
	public static Hashtable<String, String> rpt(int count)
	{
		String method = "GetReport2";
		Client client = null;
		try
		{
			client = new Client(new URL(URL));
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
		
		ProcessPassword pp = new ProcessPassword();
		StringBuilder result = new StringBuilder("");
		ArrayList param = new ArrayList();
		param.add(USER_NAME);
		param.add(pp.createMD5String(PWD));
		param.add(0);
		param.add(count);
		param.add(0);
		
		log.debug(param);
		
		Object[] results = null;
		try
		{
			results = client.invoke(method, param.toArray());
			String msg = CompressUtil.DeCompress(results[0].toString());
			msg = msg.replace("<MessagesReport>", "</MessagesReport>");
			SpRpt s = new SpRpt(msg);
			if(s.getHt()!=null) 
				log.debug(msg, "rpt_sp1");
			return s.getHt();
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}
		finally
		{
			log.write();
		}
	}
	
	public static int send(String mobile, String content, String sendTime)
	{
		String method = "Sms_SendEx";
		Client client = null;
		try
		{
			client = new Client(new URL(URL));
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
		
		ProcessPassword pp = new ProcessPassword();
		
		
		StringBuffer xml = new StringBuffer();
		xml.append("<Ms c=\"1\">")
		   .append("<m>")
		   .append("<FD>").append(mobile).append("</FD>")
		   .append("<FM>").append(content).append("</FM>")
		   //.append("<FT>").append(sendTime).append("</FT>")
		   .append("</m>")
		   .append("</Ms>");
		
		log.debug(xml.toString(), mobile);
		
		StringBuilder result = new StringBuilder("");
		ArrayList param = new ArrayList();
		param.add(new String(""));
		param.add(USER_NAME);
		param.add(pp.createMD5String(PWD));
		param.add(CompressUtil.Compress(xml.toString()));
		param.add(0);
		param.add(result);
		
		log.debug(param.toString(), mobile);
		
		Object[] results = null;
		try
		{
			results = client.invoke(method, param.toArray());
			String msg = CompressUtil.DeCompress(results[1].toString());
			log.debug(msg, mobile);
			msg = msg.replace("<MR>", "</MR>");
			log.debug(msg, mobile);
			SpMessage s = new SpMessage(msg);
			setM(s);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return -1;
		}
		finally
		{
			log.write();
			client = null;
		}
		try
		{
			return Integer.parseInt(results[0].toString());
		}
		catch(Exception e)
		{
			System.out.println(e);
			return -1;
		}
	}
 

	public static SpMessage getM()
	{
		return m;
	}


	public static void setM(SpMessage m)
	{
		SpSmsGateway.m = m;
	}

	public static void main(String[] args)
	{
		DateUtils du = new DateUtils();
		SpSmsGateway.send("13794401024","你好", null);
	}
}
