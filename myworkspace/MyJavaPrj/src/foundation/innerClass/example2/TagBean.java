package foundation.innerClass.example2;

/**
 * 2.可以无条件地访问外围类的所有元素
 * name这个变量是在TagBean里面定义的私有变量。这个变量在内部类中可以无条件地访问System.out.println(name);
 *
 */
public class TagBean 
{
	private String name = "邵国崔";
	
	public static void main(String args[])
	{
		TagBean bb=new TagBean();
		bb.test();
	}
	
	public void test()
	{
		new InTest();
	}
	
	private class InTest
	{
		public InTest()
		{
			System.out.println(name);
		}
	}
}
