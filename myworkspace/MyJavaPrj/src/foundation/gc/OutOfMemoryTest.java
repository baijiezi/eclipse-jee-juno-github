package foundation.gc;

import java.util.ArrayList;
import java.util.List;

/**
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space

Heap size 设置
JVM堆的设置是指java程序运行过程中JVM可以调配使用的内存空间的设置.JVM在启动的时候会自动设置Heap size的值，
其初始空间(即-Xms)是物理内存的1/64，最大空间(-Xmx)是物理内存的1/4。可以利用JVM提供的-Xmn -Xms -Xmx等选项可
进行设置，一般最大空间设置不应大于物理内存80%.
 
 -Xms  JVM初始化堆的大小
 -Xmx  JVM堆的最大值
 
例如启动jar包时这样设置： 
java -jar -Xms64m -Xmx512m xxxx.jar
 
 **/
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
			System.out.print(i + " M");
			currentTime = System.currentTimeMillis();
			System.out.print("    " + (currentTime-lastTime) + " ms");
			lastTime = currentTime;
			
			System.out.print(Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory() + " " + Runtime.getRuntime().maxMemory());
			System.out.println();
			try 
			{
				Thread.sleep(10);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
		}
	}
}



