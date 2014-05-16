package foundation.regularExpression;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

//让用户输入模式后再输入要匹配的字符串，它打印出输入的字符串是否和模式匹配。
//如果匹配，并且模式中包含组，则该程序会将组边界打印成圆括号，例如模式 "a(b*)c" ，字符串"abbc"

public class RegExTest 
{
	public static void main(String[] args)
	{
		Scanner scannner = new Scanner(System.in);
		System.out.println("请输入模式：");
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
			System.out.println("输入要匹配的字符串：");
			String input = scannner.nextLine();
			if(input==null || input.equals(""))
				return;
			Matcher matcher = pattern.matcher(input);
			
//			matcher.find()字符串中是否存在，存在不一定能匹配成功，例如，模式"a" 与字符串 "javaj" 
			if(matcher.find())
				System.out.println("matcher.find()查找成功！");
			else 
				System.out.println("matcher.find()查找失败！");
			
//			matcher.matches()字符串是否匹配成功； 
			if(matcher.matches())
			{
				System.out.println("matcher.matches()模式匹配成功！");
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
				System.out.println("matcher.matches()模式匹配失败！");
		}
	}
}
