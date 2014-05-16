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
	 * ʵ��Comparable �ӿڵķ�������������
	 */
	 public int compareTo(Object obj){

		//�����ж��´������Ĳ����ǲ���Student����
        if(obj instanceof Student){

        	//��ǿ������ת��
        	Student stu = (Student) obj;

        	//ת���������������������Ƚ�
        	//�õ�ǰ������ʹ��ݽ�����������бȽ�
        	//����
            return this.age - stu.getAge();
        }
        return 0;

   }
}