package foundation.regularExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//����򵥵�˵��������ʽ��4�ֳ��ù���

public class RegTest 
{
	public static void main(String[] args)
	{
		
//		ƥ����֤    string.match(reg)   ����    Pattern.matches(reg, mobile)   
		String mobile = "12112412";
		String reg = "[0-9]{8}||[0-9]{6}";
		
		if(!mobile.matches(reg))
		{
			System.out.println("�ֻ������ʽ����ȷ");
		} 
		
		if(!Pattern.matches(reg, mobile))
		{
			System.out.println("�ֻ������ʽ����ȷ");
		}
		
		
		
		
//		��ѯ��
//		���str����regEx����ôrsΪtrue������Ϊflase��������ڲ���ʱ���Դ�Сд��
//		�����д��Pattern p=Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);
		System.out.println("��ѯ��");
		String str1="abc efg ABC";
		String regEx1="a|f"; //��ʾa��f
		Pattern p1 = Pattern.compile(regEx1);
		Matcher m1 = p1.matcher(str1);
		boolean rs1 = m1.find();
		System.out.println(rs1);

		
		
//		��ȡ��
//		ִ�н��Ϊname.txt����ȡ���ַ���������m.group(i)�У�����i���ֵΪm.groupCount();
		System.out.println("��ȡ��");
//		String regEx2=".+\(.+)$";
//		String str2="c:\dir1\dir2\name.txt";
//		Pattern p2 = Pattern.compile(regEx2);
//		Matcher m2 = p2.matcher(str2);
//		boolean rs2 = m2.find();
//		for(int i=1;i<=m2.groupCount();i++)
//		{
//			System.out.println(m2.group(i));
//		}
		
		
		
//		�ָ
//		ִ�к�r����{"xd","abc","cde"}��
		System.out.println("�ָ");
		String regEx3 = "::";
		Pattern p3 = Pattern.compile(regEx3);
		String[] r31 = p3.split("xd::abc::cde");
		for(int i=0; i<r31.length; i++)
		{
			System.out.print(r31[i] + ", ");
		}
		System.out.println();
		
//		��ʵ�ָ�ʱ���и��򵥵ķ�����
		String str3 = "xd::abc::cde";
		String[] r32 = str3.split("::");
		for(int i=0; i<r32.length; i++)
		{
			System.out.print(r32[i] + ", ");
		}
		System.out.println();

		
		
//		�滻��ɾ������
		System.out.println("�滻��");
		String regEx4 = "a+"; //��ʾһ������a
		Pattern p4 = Pattern.compile(regEx4);
		Matcher m4 = p4.matcher("aaabbced a ccdeaa");
		String s41 = m4.replaceAll("A");
		System.out.println(s41);
//		���Ϊ"Abbced A ccdeA"
//		���д�ɿմ����ȿɴﵽɾ���Ĺ��ܣ�����
		
		String s42 = m4.replaceAll("");
		System.out.println(s42);
//		���Ϊ"bbced  ccde"
	}
}
