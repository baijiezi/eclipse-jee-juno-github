package foundation.network.socket.pointToPoint;

import java.util.Scanner;

import foundation.network.socket.pointToMulti.Client;

public class ClientDemo 
{
	public static void main(String[] args)
	{
		try
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("��������������ƣ�");
			String host = scanner.next();
			System.out.println("������˿ںţ�");
			int port = scanner.nextInt();
			
			Client client = new Client(host, port);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
