package foundation.refect.methodPointer;

import java.lang.reflect.Method;

/**
 * Java 没有提供方法指针，但其放射机制可以实现相应功能，即将一个方法的存储地址传给另外一个方法，
 * 以便第二个方法能够调用它
 *
 */
public class MethodPointerTest 
{
	public static void main(String[] args) 
	{
		  try
		  {
			  Names names = new Names("Zhang", "San");
				 
//			     通过对象获取 getFullName()方法
			  Method method1 = names.getClass().getMethod("getFullName");
//			     第一个参数为对象名，当Method 为静态方法时，第一个参数可以为 null
			  Object obj1 = method1.invoke(names, null);
			  System.out.println(obj1);
			  
			  
//			    通过类名获取 getFullName(String firstName,String lastName)方法
//			    用参数区分同名方法
			  Method method2 = Names.class.getMethod("getFullName", String.class, String.class);
//			  invoke(从中调用基础方法的对象,用于方法调用的参数)
			  Object obj2 = method2.invoke(names, "Shao", "Guocui");
			  System.out.println(obj2);
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		
		  
	}
}
