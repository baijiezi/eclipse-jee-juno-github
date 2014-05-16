package foundation.innerClass.example3;

/**
 * 3.���Լ��ʵ�ֶ��ؼ̳С�����ص�ǳ���Ҫ��������Ϊ�����ڲ�����ڵ��������֮һ��
 * ���������ֱ�ʵ���������ڲ��� test1,��test2 ��test1���ּ̳���Example1��test2�̳���Example2��
 * �������ǵ�����MainExample��ӵ����Example1��Example2�ķ��������ԣ�Ҳ�ͼ�ӵ�ʵ���˶�̳С�
 *
 */
public class MainExample 
{
	public static void main(String args[])
	{
		MainExample mi=new MainExample();
		System.out.println("����:"+mi.name());
		System.out.println("����:"+mi.age());
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
