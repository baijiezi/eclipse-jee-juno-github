package foundation.refect;

import java.lang.reflect.Array;

/**
 * goodArrayGrow() 是一个通用的拓展数组的方法，可以将任意类型的数组拓展 10% + 10 个元素
 *
 */
public class ArrayGrowTest 
{
	public static void main(String[] args)
	{
		int[] a = {1, 2, 3};
		a = (int[]) goodArrayGrow(a);
		arrayPrint(a);
		
		String[] b = {"Tom", "Dick", "Harry"};
		b = (String[]) goodArrayGrow(b);
		arrayPrint(b);
	}
	
	
	static Object goodArrayGrow(Object a)
	{
		Class c1 = a.getClass();
		if(!c1.isArray()) return null;
		Class componentType = c1.getComponentType();
		int length = Array.getLength(a);
		int newLength = length * 11 / 10 + 10;
		
		Object newArray = Array.newInstance(componentType, newLength);
		System.arraycopy(a, 0, newArray, 0, length);
		return newArray;
	}
	
	
	static void arrayPrint(Object a)
	{
		Class c1 = a.getClass();
		if(!c1.isArray())    return;
		Class componentType = c1.getComponentType();
		int length = Array.getLength(a);
		System.out.print(componentType.getName() + "[" + length + "] = {");
		for(int i=0; i<Array.getLength(a); i++)
			System.out.print(Array.get(a, i) + " ");
		System.out.println("}");
	}
}
