package foundation.innerClass.example4;

/**
 * 大家假想一下如果，你的类要继承一个类，还要实现一个接口，可是你发觉你继承的类和接口里面有两个同名的方法
 * 怎么办？你怎么区分它们？？这就需要我们的内部类了。
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
			System.out.println("这里是接口中的increment方法");
			incr();
		}	
	}
}
