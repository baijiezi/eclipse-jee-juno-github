package foundation.refect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * 一个可供任意类使用的 toString 方法。其中使用 getDeclaredFields 获取所有的数据域，
 * 然后使用 setAccessible 将所有的域设置为可访问的。对于每个域，得到了名字和值，
 * 并递归调用 toString 方法，将每个值转换为字符串。
 *
 */
public class CommonToStringTest
{
	public static void main(String[] args)
	{
		ArrayList<Integer> squares = new ArrayList<Integer>();
		for(int i=1; i<=5; i++)
			squares.add(i*i);
		System.out.println(new CommonToString().toString(squares));
	}
}


/**
 * 返回类的名称及类的所有域的名称和值
 *
 */
class CommonToString
{
	public String toString(Object obj)
	{
		if(obj == null)    return "null";
		if(visited.contains(obj))	return "...";
		visited.add(obj);
		Class c1 = obj.getClass();
		if(c1 == String.class)    return (String)obj;
		if(c1.isArray())
		{
			String r = c1.getComponentType() + "[]{";
			for(int i=0; i<Array.getLength(obj); i++)
			{
				if(i > 0)	r += ",";
				Object val = Array.get(obj, i);
				if(c1.getComponentType().isPrimitive())    
					r += val;
				else 
					r += toString(val);
			}
			return r + "}";
		}
		
		String result = c1.getName();
//		检查该类及其父类的域
		do
		{
			result += "[";
			Field[] fields = c1.getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);
//			获取域的名字和值
			for(Field field : fields)
			{
				if(!Modifier.isStatic(field.getModifiers()))
				{
					if(!result.endsWith("["))    result += ",";
					result += field.getName() + "=";
					try
					{
						Class type = field.getType();
						Object val = field.get(obj);
						if(type.isPrimitive())
							result += val;
						else
							result += toString(val);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			result += "]";
			c1 = c1.getSuperclass();
			
		} while(c1 != null);
		
		return result;
	}
	private ArrayList<Object> visited = new ArrayList<Object>();
}






