package foundation.network.socket.pointToMulti;

import java.util.Scanner;

/**
 * 点对面通讯示例
 *
 */
public class ServerMultiDemo 
{
	public static void main(String[] args)
	{
		try
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入端口号：");
			int port = scanner.nextInt();
			
			ServerMulti serverMulti = new ServerMulti(port);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
