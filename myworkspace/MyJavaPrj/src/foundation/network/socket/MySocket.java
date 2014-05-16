package foundation.network.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

//对于TCP/IP+BIO 方式，Java 提供了 Socket 和 ServerSocket 两个关键的类来实现，网络 IO 的操
//作则通过 java 中的流对象来进行，流对象通过 Socket 提供的 getInputStream 和getOutputStream来获取。

public class MySocket 
{
	public static void main(String[] args)
	{
		//InetAddress 类
		try 
		{
			InetAddress.getLocalHost();		//返回本地的IP地址
			InetAddress.getByName("");		//根据主机名获得IP地址
		} 
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		
		
		//ServerSocket 类，用于服务器端的 Socket 机制
		try 
		{
			ServerSocket serverSocket1 = new ServerSocket();
			int port = 8088;
			ServerSocket serverSocket2 = new ServerSocket(port);
			
			//阻塞式的等待客户端的连接，当有客户端的连接请求进入时，此方法才返回，返回的对象即为Socket对象
			serverSocket1.accept();		//挂起，等待客户端连接
			serverSocket1.close();		//关闭连接
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
		//Socket 类，用户客户端和服务端
		try
		{
			Socket socket1 = new Socket();		//建立一个空的Socket 类
			String host = "myHost";
			int port = 8088;
			Socket socket2 = new Socket(host, port);		//根据主机名和端口建立一个Socket 连接
			InetAddress inetAddr = null;
			Socket socket3 = new Socket(inetAddr, port);		//根据IP 和端口建立一个Socket 连接
			
			socket1.getInputStream();	//返回Socket 输入流
			socket1.getOutputStream();	//返回Socket 输出流
			socket1.close();				//关闭Socket
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
}
