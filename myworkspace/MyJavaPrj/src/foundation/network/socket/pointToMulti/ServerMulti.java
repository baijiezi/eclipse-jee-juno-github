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
		
		System.out.println("������������ɣ������˿��ڣ�" + port);
		System.out.println("���ڵȴ��ͻ�����");
		
		try
		{
			while(true)
			{
				//����ֱ���пͻ�����
				Socket socket = this.accept();
				//���������߳�
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
