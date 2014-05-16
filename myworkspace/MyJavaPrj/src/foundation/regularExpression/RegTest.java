package foundation.regularExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//下面简单的说下正则表达式的4种常用功能

public class RegTest 
{
	public static void main(String[] args)
	{
		
//		匹配验证    string.match(reg)   或者    Pattern.matches(reg, mobile)   
		String mobile = "12112412";
		String reg = "[0-9]{8}||[0-9]{6}";
		
		if(!mobile.matches(reg))
		{
			System.out.println("手机号码格式不正确");
		} 
		
		if(!Pattern.matches(reg, mobile))
		{
			System.out.println("手机号码格式不正确");
		}
		
		
		
		
//		查询：
//		如果str中有regEx，那么rs为true，否则为flase。如果想在查找时忽略大小写，
//		则可以写成Pattern p=Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);
		System.out.println("查询：");
		String str1="abc efg ABC";
		String regEx1="a|f"; //表示a或f
		Pattern p1 = Pattern.compile(regEx1);
		Matcher m1 = p1.matcher(str1);
		boolean rs1 = m1.find();
		System.out.println(rs1);

		
		
//		提取：
//		执行结果为name.txt，提取的字符串储存在m.group(i)中，其中i最大值为m.groupCount();
		System.out.println("提取：");
//		String regEx2=".+\(.+)$";
//		String str2="c:\dir1\dir2\name.txt";
//		Pattern p2 = Pattern.compile(regEx2);
//		Matcher m2 = p2.matcher(str2);
//		boolean rs2 = m2.find();
//		for(int i=1;i<=m2.groupCount();i++)
//		{
//			System.out.println(m2.group(i));
//		}
		
		
		
//		分割：
//		执行后，r就是{"xd","abc","cde"}，
		System.out.println("分割：");
		String regEx3 = "::";
		Pattern p3 = Pattern.compile(regEx3);
		String[] r31 = p3.split("xd::abc::cde");
		for(int i=0; i<r31.length; i++)
		{
			System.out.print(r31[i] + ", ");
		}
		System.out.println();
		
//		其实分割时还有跟简单的方法：
		String str3 = "xd::abc::cde";
		String[] r32 = str3.split("::");
		for(int i=0; i<r32.length; i++)
		{
			System.out.print(r32[i] + ", ");
		}
		System.out.println();

		
		
//		替换（删除）：
		System.out.println("替换：");
		String regEx4 = "a+"; //表示一个或多个a
		Pattern p4 = Pattern.compile(regEx4);
		Matcher m4 = p4.matcher("aaabbced a ccdeaa");
		String s41 = m4.replaceAll("A");
		System.out.println(s41);
//		结果为"Abbced A ccdeA"
//		如果写成空串，既可达到删除的功能，比如
		
		String s42 = m4.replaceAll("");
		System.out.println(s42);
//		结果为"bbced  ccde"
	}
}
