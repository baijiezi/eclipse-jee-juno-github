package foundation.innerClass.example1;

/**
 * �ڲ�����Ժܺõ�ʵ������
 * һ��ķ��ڲ��࣬�ǲ������� private ��protectedȨ�޵ģ����ڲ������
 *
 */
public class Example
{
	public InterfaceTest getIn()
    {
        return new InsideClass();
    }
	
    private class InsideClass implements InterfaceTest
    {
         public void test()
         {
             System.out.println("����һ������");
         }
    }
}
