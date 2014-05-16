package client;

import java.net.URL;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;

public class WsClient 
{
    String url = "http://127.0.0.1:8080/myWS/services/abc?wsdl" ;
	public static void main(String[] args) 
	{
		WsClient ct = new WsClient();
		long startTime = System.currentTimeMillis();
		String result = ct.getName();
		
		System.out.println("耗费时间" + (System.currentTimeMillis()- startTime) + "毫秒");
		
	}
	
	public String getName()
	{
		String name = "ShaoGuocui";
		String method = "getName";
		Client client = null;
		try
		{
			client = new Client(new URL(url));
			HttpClientParams params = new HttpClientParams();
			params.setParameter(HttpClientParams.USE_EXPECT_CONTINUE, Boolean.FALSE);
		    params.setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT, (long)60000);
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
	
	public String getAge()
	{
		String age = "ShaoGuocui";
		
		String method = "getAge";
		Client client = null;
		try
		{
			client = new Client(new URL(url));
			HttpClientParams params = new HttpClientParams();
			params.setParameter(HttpClientParams.USE_EXPECT_CONTINUE, Boolean.FALSE);
		    params.setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT, (long)60000);
			client.setProperty(CommonsHttpMessageSender.HTTP_CLIENT_PARAMS, params);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		String[]  r = new String[]{ age };
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
