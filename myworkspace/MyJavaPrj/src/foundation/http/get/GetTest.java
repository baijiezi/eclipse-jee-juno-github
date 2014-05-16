package foundation.http.get;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class GetTest 
{
	public static String url = "http://dict.youdao.com/search?le=eng&q=add&tab=&keyfrom=dict.top";
//	public static String url = "http://dict.youdao.com/search?q=add&ue=utf8&keyfrom=dict.index#q%3Dadd%26ue%3Dutf8%26keyfrom%3Ddict.index";
	public static void main(String[] args)
	{
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		
		//设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler()); 
		
		//执行getMethod
		try 
		{
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) 
			{
			  System.err.println("Method failed: " + getMethod.getStatusLine());
			}
		}
		catch (HttpException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		byte[] responseBody = null;
		try 
		{
			//取得目标地址的内容有三种方法：第一种，getResponseBody，该方法返回的是目标的二进制的byte流；
			//第二种，getResponseBodyAsString，这个方法返回的是String类型，值得注意的是该方法返回的String的编码是根据系统默认的编码方式，
			//所以返回的String值可能编码类型有误，在本文的"字符编码"部分中将对此做详细介绍；
			//第三种，getResponseBodyAsStream，这个方法对于目标地址中有大量数据需要传输是最佳的。
			responseBody = getMethod.getResponseBody();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		//释放连接。无论执行方法是否成功，都必须释放连接。
		getMethod.releaseConnection();

		try 
		{
			//本项目编码是 GBK，访问的HTML 代码为 UTF-8，需进行转换
			System.out.println(new String(responseBody, "UTF-8"));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		
		

	}
}
