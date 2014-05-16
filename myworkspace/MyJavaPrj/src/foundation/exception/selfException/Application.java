package foundation.exception.selfException;

 /**
 *自定义异常有两种，分别是继承Exception和Throwable 类,因为Throwable是exception的父类，所以，
 *继承Exception和继承Throwable效果差不多，下面是继承Exception的自定义的类的实现和用法。
 *
 */
public class Application 
{
	private static CaculateFunction CaculateFunction = null;
   	public static void main(String[] args) 
   	{
   		CaculateFunction = new CaculateFunction();
   		try 
   		{
   			CaculateFunction.add(12, 12);
   		} 
   		catch (ObjAlreadyExistException e) 
   		{
   			e.printStackTrace();
   		}
   	}
}