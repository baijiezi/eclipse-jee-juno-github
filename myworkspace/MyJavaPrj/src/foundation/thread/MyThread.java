package foundation.thread;

import java.util.Calendar;

/**
* ���Լ̳� Thread ��ʵ�ֵĶ��̳߳���
*
* @author leizhimin 2008-9-13 18:15:02
*/
public class MyThread extends Thread{
	
	private String threadName;
	private int sleepTime;
	
	public MyThread(String threadName, int sleepTime)
	{
		this.threadName = threadName;
		this.sleepTime = sleepTime;
	}
	
	public void run()
	{
		for(int i=0; i<10; i++)
		{
//			try {
				Calendar calendar = Calendar.getInstance();
				System.out.println(calendar.get(Calendar.SECOND) + " " + threadName);
//				Thread.sleep(sleepTime);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
	
	
	public static void main(String[] args)
	{
		MyThread thread1 = new MyThread("�߳�1", 1000);
		thread1.start();
		
		MyThread thread2 = new MyThread("�߳�2", 2000);
		thread2.start();
		
		for(int i=0; i<10; i++)
		{
			Calendar calendar = Calendar.getInstance();
			System.out.println(calendar.get(Calendar.SECOND) + " " + "���������߳�");
		}
	}
}
