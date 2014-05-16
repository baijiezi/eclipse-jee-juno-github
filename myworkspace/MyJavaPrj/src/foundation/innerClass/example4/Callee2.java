package foundation.innerClass.example4;

/**
 * ��Ҽ���һ������������Ҫ�̳�һ���࣬��Ҫʵ��һ���ӿڣ������㷢����̳е���ͽӿ�����������ͬ���ķ���
 * ��ô�죿����ô�������ǣ��������Ҫ���ǵ��ڲ����ˡ�
 *
 */
public class Callee2 extends MyIncrement
{
	private int i=0;
	private void incr()
	{
		i++;
		System.out.println(i);
	}

	Incrementable getCallbackReference()
	{
		return new Closure();
	}
	
	private class Closure implements Incrementable
	{
		public void increment()
		{
			System.out.println("�����ǽӿ��е�increment����");
			incr();
		}	
	}
}
