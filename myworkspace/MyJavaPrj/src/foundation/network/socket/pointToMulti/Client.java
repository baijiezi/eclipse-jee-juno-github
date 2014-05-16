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
			
			//��ü���������
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			//�������������д���ݵ�������
			PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
			//��÷�������������
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			
			System.out.println("������ > ");
			boolean done = true;
			while(done)
			{
				String line = reader.readLine();
				//д��������
				out.println(line);
				
				if(line.equalsIgnoreCase("bye"))
				{
					done = false;
				}
				
				String info = in.readLine();
				System.out.println("��������Ϣ��" + info);
				
				if(done)
				{
					System.out.println("������ > ");
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
