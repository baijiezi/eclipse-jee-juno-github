package foundation.innerClass.example3;

/**
 * 3.可以间接实现多重继承。这个特点非常重要，个人认为它是内部类存在的最大理由之一。
 * 这个类里面分别实现了两个内部类 test1,和test2 ，test1类又继承了Example1，test2继承了Example2，
 * 这样我们的类三MainExample就拥有了Example1和Example2的方法和属性，也就间接地实现了多继承。
 *
 */
public class MainExample 
{
	public static void main(String args[])
	{
		MainExample mi=new MainExample();
		System.out.println("姓名:"+mi.name());
		System.out.println("年龄:"+mi.age());
	}	

	public String name()
	{
		return new test1().name();
	}

	public int age()
	{
		return new test2().age();
	}	
	
	private class test1 extends Example1
    {
        public String name()
        {
          return super.name();
        }
    }

	private class test2 extends Example2
    {
		public int age()
		{
			return super.age();
		}
    }



}
