package foundation.network.socket.pointToPoint;

import java.util.Scanner;

/**
 * 点对点通讯示例
 *
 */
public class ServerDemo 
{
	public static void main(String[] args)
	{
		try
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入端口号：");
			int port = Integer.parseInt(scanner.next());
			Server myServer = new Server(port);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
