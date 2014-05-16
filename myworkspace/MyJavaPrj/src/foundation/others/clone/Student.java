package foundation.others.clone;

import java.util.Date;

/**
 * Ҫʹһ�����ܹ���¡�������룺
 * 1)ʵ�� Cloneable �ӿ�
 * 2)ʹ��public �������η����¶��� clone ����
 * 
 * Ĭ�ϵĿ�¡����ֻ��ǳ��¡����û�п�¡�����ڶ����е��ڲ�����
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
			
//			Ĭ�ϵĿ�¡����ֻ��ǳ��¡����û�п�¡�����ڶ����е��ڲ�����Ҫ��ʵ�����¡�������¡���пɱ��ʵ����(����list����)
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
