package foundation.http.download;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//在Internet上，我们要下载某个资源，我们会获得一个URL，它是一个服务器资源定位的描述，下载的过程总体步骤如下：
//步骤1：客户端发起连接请求一个URL
//步骤2：服务器解析URL，并将指定的资源返回一个输入流给客户端
//步骤3：客户端接收输入流，将流中的内容写入文件
public class DownLoad 
{
	public static void main(String[] args)
	{
		System.out.println("开始下载");
		long start = System.currentTimeMillis();
		FileOutputStream fos = null;
		HttpURLConnection conn = null;
		BufferedInputStream bfis = null;
		
		try 
		{
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
			String fileName = format.format(new Date());
			fileName = new StringBuffer("E:\\阳光\\预约网关\\log\\").append("catalina.out.").append(fileName).append(".log").toString();
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
		
		System.out.println("下载结束，花费时间：");
		System.out.println(System.currentTimeMillis() - start);
		
	}
}
