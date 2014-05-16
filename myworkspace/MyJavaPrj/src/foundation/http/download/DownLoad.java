package foundation.http.download;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//��Internet�ϣ�����Ҫ����ĳ����Դ�����ǻ���һ��URL������һ����������Դ��λ�����������صĹ������岽�����£�
//����1���ͻ��˷�����������һ��URL
//����2������������URL������ָ������Դ����һ�����������ͻ���
//����3���ͻ��˽����������������е�����д���ļ�
public class DownLoad 
{
	public static void main(String[] args)
	{
		System.out.println("��ʼ����");
		long start = System.currentTimeMillis();
		FileOutputStream fos = null;
		HttpURLConnection conn = null;
		BufferedInputStream bfis = null;
		
		try 
		{
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
			String fileName = format.format(new Date());
			fileName = new StringBuffer("E:\\����\\ԤԼ����\\log\\").append("catalina.out.").append(fileName).append(".log").toString();
			fos = new FileOutputStream(new File(fileName));
			
			URL url = new URL("http://124.172.245.98:28080/tlogs/catalina.out");
			conn = (HttpURLConnection)url.openConnection();
			conn.connect();
			bfis = new BufferedInputStream(conn.getInputStream());
			byte[] buf = new byte[2 * 1024 * 1024];
			int size = 0;
			while((size = bfis.read(buf)) != -1)
			{
				fos.write(buf, 0, size);
			}
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fos.close();
				bfis.close();
				conn.disconnect();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		System.out.println("���ؽ���������ʱ�䣺");
		System.out.println(System.currentTimeMillis() - start);
		
	}
}
