package foundation.gc;

import java.util.ArrayList;
import java.util.List;

//OutOfMemoryError   默认内存64M
public class OutOfMemoryTest 
{
	public static void main(String[] args)
	{
		int i=0;
		
		long lastTime = System.currentTimeMillis();
		long currentTime = 0;
		List list = new ArrayList<byte[]>();
		while(true)
		{
			//每次循环重新定义list，自动回收内存不会报异常OutOfMemoryError
			//List list = new ArrayList<byte[]>();
			i++;
			for(int j=0; j<1024; j++)
			{
				list.add(new byte[1024]);
			}
			System.out.print(i%1024 + " M");
			currentTime = System.currentTimeMillis();
			System.out.print("    " + (currentTime-lastTime) + " ms\n");
			lastTime = currentTime;
			
			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
		}
	}
}
