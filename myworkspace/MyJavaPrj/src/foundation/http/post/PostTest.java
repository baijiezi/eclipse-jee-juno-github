package foundation.http.post;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

//调用HttpClient中的PostMethod与GetMethod类似
//在下面的例子中，省去了与GetMethod相同的步骤，只说明与上面不同的地方
//以登录清华大学BBS为例子进行说明。

public class PostTest 
{
	public static void main(String[] args)
	{
		String url = "http://www.newsmth.net/bbslogin2.php";
		PostMethod postMethod = new PostMethod(url);
		HttpClient httpClient = new HttpClient();
		// 填入各个表单域的值
		NameValuePair[] data = { new NameValuePair("id", "youUserName"), new NameValuePair("passwd", "yourPwd") };
		// 将表单的值放入postMethod中
		postMethod.setRequestBody(data);
		// 执行postMethod
		int statusCode = 0;
		try 
		{
			statusCode = httpClient.executeMethod(postMethod);
		} 
		catch (HttpException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		try {
			byte[] response = postMethod.getResponseBody();
			System.out.println("开始输出返回结果：");
			System.out.println(new String(response));
			System.out.println("结束输出返回结果：");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
		// 301或者302
		if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
		{
		    // 从头中取出转向的地址
		    Header locationHeader = postMethod.getResponseHeader("location");
		    String location = null;
		    if (locationHeader != null) 
		    {
			    location = locationHeader.getValue();
			    System.out.println("The page was redirected to:" + location);
		    } 
		    else 
		    {
		     System.err.println("Location field value is null.");
		    }
		}

	}
}
