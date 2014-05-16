package foundation.network.socket.pointToPoint;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
	private int port;
	
	public Server(int port)
	{
		this.port = port;
		this.start();
	}
	
	private String process(String line)
	{
		return line.toUpperCase();
	}
	
	private void start()
	{
		try
		{
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("服务器启动完成，监听端口在：" + port);
			System.out.println("正在等待客户连接...");
			//挂起等待客户的连接请求
			Socket connection = serverSocket.accept();
			//获取读取客户端内容的数据流
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			//获取写往客户端内容的数据流，true 表示自动更新
			PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
			out.println("你好，服务器连接成功...");
			out.println("输入BYE 断开与服务器的连接");
			boolean done = true;
			while(done)
			{
				String line = reader.readLine();
				if(line == null)
				{
					done = false;
				}
				else
				{
					System.out.println("从客户端来的内容：" + line);
					String message = process(line);
					out.println("从服务器端口8000发出的内容：" +  message);
					if(line.trim().equals("BYE"))
						done = false;
				}
			}
			
			connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
