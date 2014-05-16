package foundation.others.clone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CloneTest
{
	public static void main(String[] args)
	{
		Student student = new Student("ÉÛ¹ú´Þ", 22, new Date(), "88", "99");
		Student cloned = student.clone();
		
		try 
		{
			student.setName("ShaoGuocui");
			student.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1986-05-12"));
			student.getScore().setChinese("100");
			student.getScore().setEnglish("100");
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println(student.toString());
		System.out.println(cloned.toString());
	}
}
