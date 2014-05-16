package foundation.network.socket.pointToMulti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread 
{
	private int port;
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	
	public ServerThread(Socket socket, int port) throws IOException
	{
		this.port = port;
		this.client = client;
		in = new BufferedReader(new InputStreamReader(client.getInputStream(), "GB2312"));
		out = new PrintWriter(client.getOutputStream(), true);
		
		out.println("您好，服务器连接成功!");
		out.println("输入BUY端口与服务器连接");
		
		start();
	}
	
	public void run()
	{
		try
		{
			String line = in.readLine();
			System.out.println("从客户端接收信息：" + line);
			while(!line.equalsIgnoreCase("bye"))
			{
				String message = process(line);
				out.println(message);
				
				line = in.readLine();
				System.out.println("从客户端接收信息：" + line);
			}
			out.println("从服务器端返回：" + line);
			client.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private String process(String line)
	{
		return line.toUpperCase();
	}
}
