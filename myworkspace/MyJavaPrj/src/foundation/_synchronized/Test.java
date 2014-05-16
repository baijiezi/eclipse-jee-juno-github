package foundation._synchronized;

public class Test 
{
	public static void main(String[] args)
	{
		Counter counter = new Counter();
		AddThread addThread = new AddThread(counter);
		ReduceThread reduceThread = new ReduceThread(counter);
		addThread.start();
		reduceThread.start();
	}
}

	class Counter
	{
		private static int i = 0;
		
		public void add()
		{
			System.out.print("��ǰֵ��" + i);
			i += 1;
			System.out.println("�� 1 ��" + i);
		}
		
		public void reduce()
		{
			System.out.print("��ǰֵ��" + i);
			i -= 1;
			System.out.println("�� 1 ��" + i);
		}
	}
	
	class ReduceThread extends Thread 
	{
		Counter counter;
		public ReduceThread(Counter counter)
		{
			this.counter = counter;
		}
		public void run() 
		{
			for(int i=0; i<20; i++)
			{
				counter.reduce();
			}
		}
		
	}
	
	class AddThread extends Thread 
	{
		Counter counter;
		public AddThread(Counter counter)
		{
			this.counter = counter;
		}
		public void run() 
		{
			for(int i=0; i<20; i++)
			{
				counter.add();
			}
		}
		
	}
