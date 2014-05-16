package com.sun309.gateway.api;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.params.HttpClientParams;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;

import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;

public class SendMsgZjht
{

	private static LogService log = LogFactory.create(LogFactory.SEND_DATA, SendMsgZjht.class);
	private static String wsUrl = "http://esb.expresspay.cn/mas/service/sun309?wsdl";

	/**  【中经汇通】短信发送接口  ***/
	public static int send(String mobile, String context, int messid)
	{
		String method = "sendSM";
		Client client = null;
		try
		{
			client = new Client(new URL(wsUrl));
			HttpClientParams params = new HttpClientParams();
			params.setParameter(HttpClientParams.USE_EXPECT_CONTINUE,Boolean.FALSE);
			//设置ws连接超时时间
			params.setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT, (long)15000); 
			client.setProperty(CommonsHttpMessageSender.HTTP_CLIENT_PARAMS,params);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Object object[] = new Object[]{mobile,context,messid};
		log.debug("params mobile->"+mobile+",content->"+context+",msssid->"+messid, "SendMsgZjht");
		Object[] results = null;
		try
		{
			results = client.invoke(method, object);
			if(results!= null && results.length >0){
				return Integer.parseInt(results[0].toString());
			}else{
				return -1;
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e);
			return -1;
		}
		finally
		{
			log.write();
			client.close();
		}
	}
	
	/***  【中经汇通】短信回执取回接口　  ***/
	public static List rpt(int count)
	{
		String method = "receiveStringRPT";
		Client client = null;
		try
		{
			client = new Client(new URL(wsUrl));
			HttpClientParams params = new HttpClientParams();
			params.setParameter(HttpClientParams.USE_EXPECT_CONTINUE,Boolean.FALSE);
			//设置ws连接超时时间
			params.setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT, (long)15000); 
			client.setProperty(CommonsHttpMessageSender.HTTP_CLIENT_PARAMS,params);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Object[] results = null;
		try
		{
			results = client.invoke(method, new Object[]{-1l,count});
			if(results!= null && results.length >0 && results[0].toString().length() >0){
				return str2List(results[0].toString());
			}else{
				return null;
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}
		finally
		{
			log.write();
			client.close();
		}
	}
	
	/***  【中经汇通回执数据格式整理】 ***/
	public static List<Map> str2List(String tagstr){
		if(tagstr == null || tagstr.equals("") || tagstr.trim().length() <=0){
			return null;
		}
		List <Map> rptlist = new ArrayList();
		String [] objstr = tagstr.split(";");
		for (int i = 0; i < objstr.length; i++)
		{
			System.out.println(objstr[i]);
			String [] elementstr = objstr[i].split(":");
			Map rptmsg = new HashMap();
			for (int j = 0; j < elementstr.length; j++)
			{
				rptmsg.put("obj"+j, elementstr[j]);
			}
			rptlist.add(rptmsg);
		}
		return rptlist;
	}
	
	public static void main(String args [] ){
//		System.out.println(sendMsg("13800138000","sdasd",1201));
//		System.out.println("5a90fcec33aaf7ef0133ab24ac23002d".length());
		str2List("18933913689");
	}

}
