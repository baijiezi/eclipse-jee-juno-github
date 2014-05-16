package foundation.innerClass.example1;

/**
 * 内部类可以很好的实现隐藏
 * 一般的非内部类，是不允许有 private 与protected权限的，但内部类可以
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
             System.out.println("这是一个测试");
         }
    }
}
