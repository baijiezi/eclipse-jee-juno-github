package foundation.others.clone;

import java.util.Date;

/**
 * 要使一个类能够克隆自身，必须：
 * 1)实现 Cloneable 接口
 * 2)使用public 访问修饰符重新定义 clone 方法
 * 
 * 默认的克隆方法只是浅克隆，并没有克隆包含在对象中的内部对象
 * @author Asus
 *
 */
public class Student implements Cloneable
{
	private String name;
	private int age;
	private Date birthday;
	private Score score;
	
	public Student(){}
	
	public Student(String name, int age, Date birthday, String Chinese, String English)
	{
		this.name = name;
		this.age = age;
		this.birthday = birthday;
		this.score = new Score();
		this.score.setChinese(Chinese);
		this.score.setEnglish(English);
	}
	
	
	public Student clone()
	{
		Student cloned = null;
		try 
		{
			cloned = (Student)super.clone();
			
//			默认的克隆方法只是浅克隆，并没有克隆包含在对象中的内部对象，要想实现深克隆，必须克隆所有可变的实例域(包括list对象)
			cloned.score = (Score)score.clone();
		} 
		catch (CloneNotSupportedException e) 
		{
			e.printStackTrace();
		}
		return cloned;
	}
	
	
	
	public String toString()
	{
		return "name=" + name + ", age=" + age + ", birthday=" + birthday.toString() 
				+ ", Chinese=" + score.getChinese() + ", English=" + score.getEnglish();
	}
	
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
	public Date getBirthday()
	{
		return birthday;
	}
	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public Score getScore() 
	{
		return score;
	}
	public void setScore(Score score)
	{
		this.score = score;
	}
	
}
