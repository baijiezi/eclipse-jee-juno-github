package foundation.exception;

//通过异常类的堆栈信息获取当前方法的名字
public class GetCurrentMethodName 
{
	public static void main(String[] args)
	{
		GetCurrentMethodName getMethodName = new GetCurrentMethodName();
		getMethodName.aaa();
	}
	
	public void aaa()
	{
		//获取当前方法的名字
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		//获取调用者的方法名
		System.out.println(new Exception().getStackTrace()[1].getMethodName());
	}
}
