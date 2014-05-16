package foundation.refect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * ��ӡһ�����ȫ����Ϣ������������������������������������ʾ�û�����Ҫ��ӡ������
 *
 */
public class PrintClassInfo
{
	public static void main(String[] args)
	{
		String className;
		if(args.length>0)
			className = args[0];
		else
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("����������������foundation.refect.Student��");
			className = scanner.next();
		}
		
		try
		{
//			��ӡ�����ͳ�������
			Class c1 = Class.forName(className);
			Class c1Super = c1.getSuperclass();
			System.out.print("class " + className);
			if(c1Super!=null && c1Super!=Object.class)
				System.out.print(" extends " + c1Super.getName());
			
			System.out.print("\n{\n");
			printFields(c1);
			System.out.println();
			printConstructors(c1);
			System.out.println();
			printMethods(c1);
			System.out.println("}");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	
	/**
	 * ��ӡ���е���
	 * c1.getFields() ���ظ��༰�丸��Ĺ�����
	 * c1.getDeclaredFields() ���ظ���Ĺ�����
	 */
	public static void printFields(Class c1)
	{
		System.out.println("��ӡ�������");
		Field[] fields = c1.getFields();
		for(Field field : fields)
		{
			Class fieldType = field.getType();
			String name = field.getName();
			System.out.print("  " + Modifier.toString(field.getModifiers()));
			System.out.println(" " + fieldType.getName() + " " + name + ";");
		}
	}
	
	
	/**
	 * ��ӡ������
	 * c1.getConstructors() ���ظ�������й�����(���������)
	 * c1.getDeclaredConstructors() ���ظ���Ĺ��й�����(���������)
	 */
	public static void printConstructors(Class c1)
	{
		System.out.println("��ӡ����Ĺ�������");
		Constructor[] constructors = c1.getConstructors();
		for(Constructor constructor : constructors)
		{
			String name = constructor.getName();
			System.out.print("  " + Modifier.toString(constructor.getModifiers()));
			System.out.print(" " + name + "(");
			
//			��ӡ��������
			Class[] paramTypes = constructor.getParameterTypes();
			for(int j=0; j<paramTypes.length; j++)
			{
				if(j > 0)	System.out.print(", ");
				System.out.print(paramTypes[j].getName());
			}
			System.out.println(");");
		}
	}
	
	
	/**
	 * ��ӡ���еķ���
	 * c1.getMethods() ���ظ��༰�丸��Ĺ��з���
	 * c1.getDeclaredMethods() ���ظ���Ĺ��з���
	 */
	public static void printMethods(Class c1)
	{
		System.out.println("��ӡ����ķ�����");
		Method[] methods = c1.getMethods();
		for(Method method : methods)
		{
			Class returnType = method.getReturnType();
			String name = method.getName();
			
//			��ӡ���η����������ͺͷ�����
			System.out.print("  " + Modifier.toString(method.getModifiers()));
			System.out.print(" " + returnType.getName() + " " + name + "(");
			
//			��ӡ��������
			Class[] paramTypes = method.getParameterTypes();
			for(int j=0; j<paramTypes.length; j++)
			{
				if(j > 0)	System.out.print(", ");
				System.out.print(paramTypes[j].getName());
			}
			System.out.println(")");
		}
	}
	
}
