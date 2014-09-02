package foundation.refect.methodPointer;

import java.lang.reflect.Method;

/**
 * Java û���ṩ����ָ�룬���������ƿ���ʵ����Ӧ���ܣ�����һ�������Ĵ洢��ַ��������һ��������
 * �Ա�ڶ��������ܹ�������
 *
 */
public class MethodPointerTest 
{
	public static void main(String[] args) 
	{
		  try
		  {
			  Names names = new Names("Zhang", "San");
				 
//			     ͨ�������ȡ getFullName()����
			  Method method1 = names.getClass().getMethod("getFullName");
//			     ��һ������Ϊ����������Method Ϊ��̬����ʱ����һ����������Ϊ null
			  Object obj1 = method1.invoke(names, null);
			  System.out.println(obj1);
			  
			  
//			    ͨ��������ȡ getFullName(String firstName,String lastName)����
//			    �ò�������ͬ������
			  Method method2 = Names.class.getMethod("getFullName", String.class, String.class);
//			  invoke(���е��û��������Ķ���,���ڷ������õĲ���)
			  Object obj2 = method2.invoke(names, "Shao", "Guocui");
			  System.out.println(obj2);
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		
		  
	}
}
