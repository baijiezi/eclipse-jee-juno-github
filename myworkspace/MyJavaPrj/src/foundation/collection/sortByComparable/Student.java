package foundation.collection.sortByComparable;
 
public class Student implements Comparable
{
	private String name;
	private int age;

	public String getName() 
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getAge() 
	{
		return age;
	}
	public void setAge(int age) 
	{
		this.age = age;
	}

	public Student(String name,int age)
	{
		this.name=name;
		this.age=age;
	}

	public Student(){}

	public String toString()
	{
		return "name="+name+" age="+age;
	}
	
	/**
	 * 实现Comparable 接口的方法，用于排序
	 */
	 public int compareTo(Object obj){

		//首先判断下传进来的参数是不是Student类型
        if(obj instanceof Student){

        	//做强制类型转换
        	Student stu = (Student) obj;

        	//转换完后进行排序，用年龄来比较
        	//用当前的年龄和传递进来的年龄进行比较
        	//升序
            return this.age - stu.getAge();
        }
        return 0;

   }
}