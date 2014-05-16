package foundation.network.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

//����TCP/IP+BIO ��ʽ��Java �ṩ�� Socket �� ServerSocket �����ؼ�������ʵ�֣����� IO �Ĳ�
//����ͨ�� java �е������������У�������ͨ�� Socket �ṩ�� getInputStream ��getOutputStream����ȡ��

public class MySocket 
{
	public static void main(String[] args)
	{
		//InetAddress ��
		try 
		{
			InetAddress.getLocalHost();		//���ر��ص�IP��ַ
			InetAddress.getByName("");		//�������������IP��ַ
		} 
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		
		
		//ServerSocket �࣬���ڷ������˵� Socket ����
		try 
		{
			ServerSocket serverSocket1 = new ServerSocket();
			int port = 8088;
			ServerSocket serverSocket2 = new ServerSocket(port);
			
			//����ʽ�ĵȴ��ͻ��˵����ӣ����пͻ��˵������������ʱ���˷����ŷ��أ����صĶ���ΪSocket����
			serverSocket1.accept();		//���𣬵ȴ��ͻ�������
			serverSocket1.close();		//�ر�����
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
		//Socket �࣬�û��ͻ��˺ͷ����
		try
		{
			Socket socket1 = new Socket();		//����һ���յ�Socket ��
			String host = "myHost";
			int port = 8088;
			Socket socket2 = new Socket(host, port);		//�����������Ͷ˿ڽ���һ��Socket ����
			InetAddress inetAddr = null;
			Socket socket3 = new Socket(inetAddr, port);		//����IP �Ͷ˿ڽ���һ��Socket ����
			
			socket1.getInputStream();	//����Socket ������
			socket1.getOutputStream();	//����Socket �����
			socket1.close();				//�ر�Socket
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
}
