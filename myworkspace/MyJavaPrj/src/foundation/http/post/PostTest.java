package foundation.http.post;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

//����HttpClient�е�PostMethod��GetMethod����
//������������У�ʡȥ����GetMethod��ͬ�Ĳ��裬ֻ˵�������治ͬ�ĵط�
//�Ե�¼�廪��ѧBBSΪ���ӽ���˵����

public class PostTest 
{
	public static void main(String[] args)
	{
		String url = "http://www.newsmth.net/bbslogin2.php";
		PostMethod postMethod = new PostMethod(url);
		HttpClient httpClient = new HttpClient();
		// ������������ֵ
		NameValuePair[] data = { new NameValuePair("id", "youUserName"), new NameValuePair("passwd", "yourPwd") };
		// ������ֵ����postMethod��
		postMethod.setRequestBody(data);
		// ִ��postMethod
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
			System.out.println("��ʼ������ؽ����");
			System.out.println(new String(response));
			System.out.println("����������ؽ����");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		// HttpClient����Ҫ����ܺ�̷����������POST��PUT�Ȳ����Զ�����ת��
		// 301����302
		if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
		{
		    // ��ͷ��ȡ��ת��ĵ�ַ
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
