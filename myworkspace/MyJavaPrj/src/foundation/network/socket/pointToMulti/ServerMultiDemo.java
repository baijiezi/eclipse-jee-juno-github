package foundation.network.socket.pointToMulti;

import java.util.Scanner;

/**
 * �����ͨѶʾ��
 *
 */
public class ServerMultiDemo 
{
	public static void main(String[] args)
	{
		try
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("������˿ںţ�");
			int port = scanner.nextInt();
			
			ServerMulti serverMulti = new ServerMulti(port);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
