package foundation.innerClass.example2;

/**
 * 2.�����������ط�����Χ�������Ԫ��
 * name�����������TagBean���涨���˽�б���������������ڲ����п����������ط���System.out.println(name);
 *
 */
public class TagBean 
{
	private String name = "�۹���";
	
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
