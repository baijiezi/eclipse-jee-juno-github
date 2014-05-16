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
			System.out.println("������������ɣ������˿��ڣ�" + port);
			System.out.println("���ڵȴ��ͻ�����...");
			//����ȴ��ͻ�����������
			Socket connection = serverSocket.accept();
			//��ȡ��ȡ�ͻ������ݵ�������
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			//��ȡд���ͻ������ݵ���������true ��ʾ�Զ�����
			PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
			out.println("��ã����������ӳɹ�...");
			out.println("����BYE �Ͽ��������������");
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
					System.out.println("�ӿͻ����������ݣ�" + line);
					String message = process(line);
					out.println("�ӷ������˿�8000���������ݣ�" +  message);
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
