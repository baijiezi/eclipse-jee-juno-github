package foundation.network.socket.pointToPoint;

import java.util.Scanner;

/**
 * ��Ե�ͨѶʾ��
 *
 */
public class ServerDemo 
{
	public static void main(String[] args)
	{
		try
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("������˿ںţ�");
			int port = Integer.parseInt(scanner.next());
			Server myServer = new Server(port);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
