package foundation.refect.methodPointer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import foundation.refect.Student;

/**
 * 利用放射机制获取一个实体对象的所有域和值
 *
 */
public class PrintBeanInfoTest 
{
	public static void main(String[] args)
	{
		PrintBeanInfoTest p = new PrintBeanInfoTest();
		Student student = new Student("ShaoGuocui", 22);
		p.printBeanInfo(student);
	}
	
	
	/**
	 * 通过 get 方法获取域名和值，能取到属性为私有但get方法为公有的域的值
	 * @param obj
	 */
	public void printBeanInfo(Object obj)
	{
		Method[] methods = obj.getClass().getMethods();
		StringBuffer result = new StringBuffer(obj.getClass().getName());
		result.append("[");
		int i = 0;
		for(Method method : methods)
		{
			try
			{
				String methodName = method.getName();
				if(methodName.startsWith("get") && !methodName.equals("getClass"))
				{
					String fieldName = methodName.substring(3, 4).toUpperCase() + methodName.substring(4, methodName.length());
					String value = method.invoke(obj, null).toString();
					if(i > 0)
						result.append(", ");
					result.append(fieldName).append("=").append(value);
					i++;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
		}
		result.append("]");
		System.out.println(result);
	}
	
	
	/**
	 * 通过域名来构造出get方法名，缺点是不能取得私有域的值
	 * @param obj
	 */
	public void _printBeanInfo(Object obj)
	{
		Field[] fields = obj.getClass().getFields();
		StringBuffer result = new StringBuffer(obj.getClass().getName());
		result.append("[");
		for(int i=0; i<fields.length; i++)
		{
			try
			{
				Field field = fields[i];
				String str = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1, field.getName().length());
				String methodName = "get" + str;
				Method method = obj.getClass().getMethod(methodName, null);
				String value = method.invoke(obj, null).toString();
				if(i > 0)
					result.append(", ");
				result.append(field.getName()).append("=").append(value);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
		}
		result.append("]");
		System.out.println(result);
	}
}
