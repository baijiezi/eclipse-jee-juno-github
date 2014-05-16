package foundation.network.socket.pointToMulti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client 
{
	private String host;
	private int port;
	
	public Client(String host, int port)
	{
		this.host = host;
		this.port = port;
		connect();
	}
	
	private void connect()
	{
		try
		{
			Socket connection;
			if(host.equals("localhost"))
			{
				connection = new Socket(InetAddress.getLocalHost(), port);
			}
			else 
			{
				connection = new Socket(InetAddress.getByName(host), port);
			}
			
			//获得键盘输入流
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			//获得往服务器端写内容的数据流
			PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
			//获得服务器的输入流
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			
			System.out.println("请输入 > ");
			boolean done = true;
			while(done)
			{
				String line = reader.readLine();
				//写往服务器
				out.println(line);
				
				if(line.equalsIgnoreCase("bye"))
				{
					done = false;
				}
				
				String info = in.readLine();
				System.out.println("服务器信息：" + info);
				
				if(done)
				{
					System.out.println("请输入 > ");
				}
				connection.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
