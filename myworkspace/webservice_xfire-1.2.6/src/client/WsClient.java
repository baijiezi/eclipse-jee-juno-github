package client;

import java.net.URL;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;

public class WsClient 
{
    String url = "http://localhost:8080/webservice_xfire-1.2.6/services/HelloService?wsdl" ;
	public static void main(String[] args) 
	{
		WsClient ct = new WsClient();
		long startTime = System.currentTimeMillis();
		String result = ct.hello();
		
		System.out.println("耗费时间" + (System.currentTimeMillis()- startTime) + "毫秒");
		
	}
	
	public String hello()
	{
		String name = "ShaoGuocui";
		
		String method = "hello";
		Client client = null;
		try
		{
			client = new Client(new URL(url));
			HttpClientParams params = new HttpClientParams(); 
			params.setParameter(HttpClientParams.USE_EXPECT_CONTINUE, Boolean.FALSE);
//		    params.setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT, (long)60000);
			client.setProperty(CommonsHttpMessageSender.HTTP_CLIENT_PARAMS, params);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		String[]  r = new String[]{ name };
		Object[] results = null;
		try
		{
			results = client.invoke(method, r);
		}
		catch (Exception e)
		{
			System.out.println(e +"-----");
		}
		finally
		{
			client.close();
		}
		System.out.println((results != null && results.length > 0)? results[0].toString():"");
		String result = results[0].toString();
		return result;
	}
	
}
