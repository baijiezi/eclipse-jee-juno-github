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
		
		//���ó���Ĭ�ϵĻָ����ԣ��ڷ����쳣ʱ���Զ�����3�Σ���������Ҳ�������ó��Զ���Ļָ�����
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler()); 
		
		//ִ��getMethod
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
			//ȡ��Ŀ���ַ�����������ַ�������һ�֣�getResponseBody���÷������ص���Ŀ��Ķ����Ƶ�byte����
			//�ڶ��֣�getResponseBodyAsString������������ص���String���ͣ�ֵ��ע����Ǹ÷������ص�String�ı����Ǹ���ϵͳĬ�ϵı��뷽ʽ��
			//���Է��ص�Stringֵ���ܱ������������ڱ��ĵ�"�ַ�����"�����н��Դ�����ϸ���ܣ�
			//�����֣�getResponseBodyAsStream�������������Ŀ���ַ���д���������Ҫ��������ѵġ�
			responseBody = getMethod.getResponseBody();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		//�ͷ����ӡ�����ִ�з����Ƿ�ɹ����������ͷ����ӡ�
		getMethod.releaseConnection();

		try 
		{
			//����Ŀ������ GBK�����ʵ�HTML ����Ϊ UTF-8�������ת��
			System.out.println(new String(responseBody, "UTF-8"));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		
		

	}
}
