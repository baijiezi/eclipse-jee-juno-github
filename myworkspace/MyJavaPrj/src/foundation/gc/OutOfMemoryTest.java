package foundation.gc;

import java.util.ArrayList;
import java.util.List;

/**
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space

Heap size ����
JVM�ѵ�������ָjava�������й�����JVM���Ե���ʹ�õ��ڴ�ռ������.JVM��������ʱ����Զ�����Heap size��ֵ��
���ʼ�ռ�(��-Xms)�������ڴ��1/64�����ռ�(-Xmx)�������ڴ��1/4����������JVM�ṩ��-Xmn -Xms -Xmx��ѡ���
�������ã�һ�����ռ����ò�Ӧ���������ڴ�80%.
 
 -Xms  JVM��ʼ���ѵĴ�С
 -Xmx  JVM�ѵ����ֵ
 
��������jar��ʱ�������ã� 
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
			//ÿ��ѭ�����¶���list���Զ������ڴ治�ᱨ�쳣OutOfMemoryError
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



