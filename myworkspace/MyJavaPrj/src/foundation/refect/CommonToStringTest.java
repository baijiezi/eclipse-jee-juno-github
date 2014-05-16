package foundation.refect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * һ���ɹ�������ʹ�õ� toString ����������ʹ�� getDeclaredFields ��ȡ���е�������
 * Ȼ��ʹ�� setAccessible �����е�������Ϊ�ɷ��ʵġ�����ÿ���򣬵õ������ֺ�ֵ��
 * ���ݹ���� toString ��������ÿ��ֵת��Ϊ�ַ�����
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
 * ����������Ƽ��������������ƺ�ֵ
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
//		�����༰�丸�����
		do
		{
			result += "[";
			Field[] fields = c1.getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);
//			��ȡ������ֺ�ֵ
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






