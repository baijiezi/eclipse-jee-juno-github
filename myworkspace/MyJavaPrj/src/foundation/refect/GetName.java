package foundation.refect;

public class GetName 
{
	public static void main(String[] args)
	{
		GetName getName = new GetName();
		getName.aaa();
	}
	
	public void aaa()
	{
		//获取当前方法的名字
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		//获取调用者的方法名
		System.out.println(new Exception().getStackTrace()[1].getMethodName());
	}
}
