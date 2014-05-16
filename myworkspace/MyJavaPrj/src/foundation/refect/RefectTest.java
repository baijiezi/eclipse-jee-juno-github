package foundation.refect;

/**
 * 在程序运行期间，Java运行时系统始终为所有的对象维护一个被称为运行时的类型标识。这个信息保存着
 * 每个对象所属的类足迹。虚拟机利用运行时信息选择相应的方法执行。
 * 然后，可以通过专门的Java类访问这些信息，保存这些信息的类被称为 Class 类。
 */
public class RefectTest
{
	public static void main(String[] args)
	{
//		Object 类的 getClass() 方法会返回一个 Class 类型的实例。
		Student student = new Student();
		Class c1 = student.getClass();
		
		
//		还可以利用静态方法 forName() 获得类名对应的 Class 对象。
		try 
		{
			String className = "foundation.refect.Student";
			Class c2 = Class.forName(className);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		
//		获取Class 类对象的第三种方法非常简单，如果T 是任意的Java类型，那么 T.class 将代表匹配的类对象。
//		Class 对象实际上代表的是一个类型，而这个类型未必一定是一种类。
		Class c31 = Student.class;
		Class c32 = int.class;
		Class c33 = Double[].class;
		
		
//		最常用的Class 方法是 getName ，这个方法将返回类的名字。
		System.out.println( student.getClass().getName() );
		
		
//		虚拟机为每个类型管理一个Class 对象。因此，可以利用 == 运算符实现两个 Class 对象比较的操作
		if(student.getClass() == Student.class)
			System.out.println("student对象属于 Student类");
		
		
//		将forName 和 newInstance 配合起来使用，可以根据存储在字符串中的类名创建一个对象
		try 
		{
			String str = "foundation.refect.Student";
			Object stud = Class.forName(str).newInstance();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
