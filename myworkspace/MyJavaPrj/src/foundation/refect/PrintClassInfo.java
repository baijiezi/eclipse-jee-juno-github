package foundation.refect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * 打印一个类的全部信息，包括域名、构造器、方法，这个程序会提示用户输入要打印的类名
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
			System.out.println("请输入类名，例如foundation.refect.Student：");
			className = scanner.next();
		}
		
		try
		{
//			打印类名和超类名称
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
	 * 打印所有的域
	 * c1.getFields() 返回该类及其父类的公有域
	 * c1.getDeclaredFields() 返回该类的公有域
	 */
	public static void printFields(Class c1)
	{
		System.out.println("打印该类的域：");
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
	 * 打印构造器
	 * c1.getConstructors() 返回该类的所有构造器(不含父类的)
	 * c1.getDeclaredConstructors() 返回该类的公有构造器(不含父类的)
	 */
	public static void printConstructors(Class c1)
	{
		System.out.println("打印该类的构造器：");
		Constructor[] constructors = c1.getConstructors();
		for(Constructor constructor : constructors)
		{
			String name = constructor.getName();
			System.out.print("  " + Modifier.toString(constructor.getModifiers()));
			System.out.print(" " + name + "(");
			
//			打印参数类型
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
	 * 打印所有的方法
	 * c1.getMethods() 返回该类及其父类的公有方法
	 * c1.getDeclaredMethods() 返回该类的公有方法
	 */
	public static void printMethods(Class c1)
	{
		System.out.println("打印该类的方法：");
		Method[] methods = c1.getMethods();
		for(Method method : methods)
		{
			Class returnType = method.getReturnType();
			String name = method.getName();
			
//			打印修饰符、返回类型和方法名
			System.out.print("  " + Modifier.toString(method.getModifiers()));
			System.out.print(" " + returnType.getName() + " " + name + "(");
			
//			打印参数类型
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
