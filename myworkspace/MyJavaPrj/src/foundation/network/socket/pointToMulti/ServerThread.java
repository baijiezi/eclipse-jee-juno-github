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
		
		out.println("���ã����������ӳɹ�!");
		out.println("����BUY�˿������������");
		
		start();
	}
	
	public void run()
	{
		try
		{
			String line = in.readLine();
			System.out.println("�ӿͻ��˽�����Ϣ��" + line);
			while(!line.equalsIgnoreCase("bye"))
			{
				String message = process(line);
				out.println(message);
				
				line = in.readLine();
				System.out.println("�ӿͻ��˽�����Ϣ��" + line);
			}
			out.println("�ӷ������˷��أ�" + line);
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
