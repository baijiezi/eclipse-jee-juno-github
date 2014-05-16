package foundation.network.socket.pointToMulti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMulti extends ServerSocket
{
	private int port;
	
	public ServerMulti(int port) throws IOException
	{
		super(port);
		this.port = port;
		
		System.out.println("服务器启动完成，监听端口在：" + port);
		System.out.println("正在等待客户连接");
		
		try
		{
			while(true)
			{
				//挂起，直到有客户连接
				Socket socket = this.accept();
				//建立服务线程
				new ServerThread(socket, port);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close();
		}
	}
}
