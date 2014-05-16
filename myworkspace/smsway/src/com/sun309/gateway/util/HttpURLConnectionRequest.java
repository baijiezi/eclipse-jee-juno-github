package com.sun309.gateway.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class HttpURLConnectionRequest
{
	private static Logger log = Logger.getLogger(HttpURLConnectionRequest.class);

	private int httpStatus;

	/**
	 * POST方式发送数据
	 * 
	 * @param postUrl
	 * @param params
	 * @return
	 */
	public String doSendPostRequest(String postUrl, Hashtable<String, String> params)
	{
		StringBuffer buffer = new StringBuffer();
		HttpURLConnection httpUrl = null;
		try
		{
			log.debug("start send data");
			// Post请求的url，与get不同的是不需要带参数
			URL url = new URL(postUrl);
			// 打开连接
			httpUrl = (HttpURLConnection) url.openConnection();
			// 设置是否向connection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true
			httpUrl.setDoOutput(true);
			// Read from the connection. Default is true.
			httpUrl.setDoInput(true);
			// Set the post method. Default is GET
			httpUrl.setRequestMethod("POST");
			// Post 请求不能使用缓存
			httpUrl.setUseCaches(false);
			// URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
			httpUrl.setInstanceFollowRedirects(true);
			// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
			// 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode进行编码
			httpUrl.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpUrl.setRequestProperty("Accept-Charset", "utf-8");

			// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
			// 要注意的是connection.getOutputStream会隐含的进行connect。
			httpUrl.connect();
			DataOutputStream out = new DataOutputStream(httpUrl.getOutputStream());
			// The URL-encoded contend
			// 正文，正文内容其实跟get的URL中'?'后的参数字符串一致
			// 遍历Hashtable,中的参数
			StringBuffer paramsContent = new StringBuffer();
			if (params != null)
			{
				for (String node : params.keySet())
				{
					String nodeValue = params.get(node);
					paramsContent.append(node);
					paramsContent.append("=");
					paramsContent.append(URLEncoder.encode(nodeValue, "utf-8"));
					paramsContent.append("&");
				}
			}
			String content = paramsContent.toString();
			content = content.substring(0, content.length() - 1);
			// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
			out.writeBytes(content);
			out.flush();
			out.close(); // flush and close

			String header = new String();
			for (int i = 0; true; i++)
			{
				header = httpUrl.getHeaderField(i);
				if (header == null)
					break;
				System.out.println("header:" + header);
			}

			int res = 0;
			res = httpUrl.getResponseCode();
			System.out.println("res=" + res);
			this.setHttpStatus(res);
			if (res == 200)
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(httpUrl.getInputStream()));
				String line;
				System.out.println("=============================");
				System.out.println("Contents of post request");
				System.out.println("=============================");
				while ((line = reader.readLine()) != null)
				{
					buffer.append(line);
				}
				System.out.println("=============================");
				System.out.println("Contents of post request ends");
				System.out.println("=============================");
				reader.close();
				log.debug("end send data");
			}
			else
			{
				System.out.println("------接收出错了-------");
			}
		}
		catch (Exception e)
		{
			log.debug(e);
		}
		finally
		{
			httpUrl.disconnect();
		}
		return buffer.toString();
	}

	public String doSendPostRequest(String postUrl, String strXml)
	{
		StringBuffer buffer = null;
		HttpURLConnection c = null;
		try
		{
			URL url = new URL(postUrl);
			c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("POST");
			c.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			c.setRequestProperty("Accept-Charset", "utf-8");
			c.setDoOutput(true);
			c.setDoInput(true);
			// c.setConnectTimeout(30000);//设置连接主机超时（单位：毫秒）
			// c.setReadTimeout(30000);//设置从主机读取数据超时（单位：毫秒）
			c.connect();
			PrintWriter out = new PrintWriter(c.getOutputStream());// 发送数据
			strXml = URLEncoder.encode(strXml, "utf-8");
			out.print(strXml);
			out.flush();
			out.close();
			String header;
			for (int i = 0; true; i++)
			{
				header = c.getHeaderField(i);
				if (header == null)
					break;
				System.out.println("header:" + header);
			}
			int res = 0;
			res = c.getResponseCode();
			System.out.println("res=" + res);
			this.setHttpStatus(res);
			if (res == 200)
			{
				InputStream u = c.getInputStream();// 获取servlet返回值，接收
				BufferedReader in = new BufferedReader(new InputStreamReader(u));
				buffer = new StringBuffer();
				String line = "";
				while ((line = in.readLine()) != null)
				{
					buffer.append(line);
				}
			}
			else
			{
				System.out.println("------接收出错了-------");
			}
			c.disconnect();
		}
		catch (Exception e)
		{
			System.out.println("异常！");
			System.out.println(e.toString());
		}
		return buffer.toString();
	}

	public String doSendGetRequest(String _url)
	{
		HttpURLConnection httpUrl = null;
		URL url = null;
		try
		{
			url = new URL(_url);
			httpUrl = (HttpURLConnection) url.openConnection();
			if (httpUrl.getResponseCode() == 200)
			{
				InputStream u = httpUrl.getInputStream();// 获取servlet返回值，接收
				BufferedReader in = new BufferedReader(new InputStreamReader(u));
				StringBuffer buffer = new StringBuffer();
				String line = "";
				while ((line = in.readLine()) != null)
				{
					buffer.append(line);
				}
				return buffer.toString();
			}
			else
			{
				System.out.println("信息提交失败：" + httpUrl.getResponseCode());
				return "";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public String getHttpUrlConnectionPostData(HttpServletRequest request) throws Exception
	{
		char[] readerBuffer = new char[request.getContentLength()];
		BufferedReader bufferedReader = request.getReader();
		int portion = bufferedReader.read(readerBuffer);
		int amount = portion;
		while (amount < readerBuffer.length)
		{
			portion = bufferedReader.read(readerBuffer, amount, readerBuffer.length - amount);
			amount = amount + portion;
		}
		StringBuffer stringBuffer = new StringBuffer((int) (readerBuffer.length * 1.5));
		for (int index = 0; index < readerBuffer.length; index++)
		{
			char c = readerBuffer[index];
			stringBuffer.append(c);
		}
		String xml = urlDecode(stringBuffer.toString());
		return xml;
	}

	public String urlDecode(String value)
	{
		try
		{
			return java.net.URLDecoder.decode(value, "utf-8");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return value;
	}

	public int getHttpStatus()
	{
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus)
	{
		this.httpStatus = httpStatus;
	}

}
