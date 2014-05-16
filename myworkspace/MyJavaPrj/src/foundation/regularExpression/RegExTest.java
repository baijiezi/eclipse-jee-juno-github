package foundation.regularExpression;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

//���û�����ģʽ��������Ҫƥ����ַ���������ӡ��������ַ����Ƿ��ģʽƥ�䡣
//���ƥ�䣬����ģʽ�а����飬��ó���Ὣ��߽��ӡ��Բ���ţ�����ģʽ "a(b*)c" ���ַ���"abbc"

public class RegExTest 
{
	public static void main(String[] args)
	{
		Scanner scannner = new Scanner(System.in);
		System.out.println("������ģʽ��");
		String patternString = scannner.nextLine();
		
		Pattern pattern = null;
		try
		{
			pattern = Pattern.compile(patternString);
		}catch (PatternSyntaxException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		while(true)
		{
			System.out.println("����Ҫƥ����ַ�����");
			String input = scannner.nextLine();
			if(input==null || input.equals(""))
				return;
			Matcher matcher = pattern.matcher(input);
			
//			matcher.find()�ַ������Ƿ���ڣ����ڲ�һ����ƥ��ɹ������磬ģʽ"a" ���ַ��� "javaj" 
			if(matcher.find())
				System.out.println("matcher.find()���ҳɹ���");
			else 
				System.out.println("matcher.find()����ʧ�ܣ�");
			
//			matcher.matches()�ַ����Ƿ�ƥ��ɹ��� 
			if(matcher.matches())
			{
				System.out.println("matcher.matches()ģʽƥ��ɹ���");
				int groups = matcher.groupCount();
				if(groups > 0)
				{
					for(int i=0; i<input.length(); i++)
					{
						for(int j=1; j<=groups; j++)
							if(i == matcher.start(j))
								System.out.print("(");
						System.out.print(input.charAt(i));
						for(int j=1; j<=groups; j++)
							if(i + 1 == matcher.end(j))
								System.out.print(")");
					}
					System.out.println();
				}
			}
			else
				System.out.println("matcher.matches()ģʽƥ��ʧ�ܣ�");
		}
	}
}
