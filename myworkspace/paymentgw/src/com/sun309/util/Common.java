package com.sun309.util;

import java.io.StringReader;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class Common
{
	private static Logger log = Logger.getLogger(Common.class);

	/**
	 * 处理绝对路径('\' 变成 '/')
	 * 
	 * @param path
	 * @return
	 */
	public static String processAbsolutePath(String path)
	{
		String[] pathArray = path.split("\\\\");
		String newPath = "";
		for (int i = 0; i < pathArray.length; i++)
		{
			if (!"".equals(pathArray[i]))
			{
				newPath += pathArray[i] + "/";
			}
		}
		newPath = newPath.substring(0, newPath.length() - 1);
		return newPath;
	}

	public static String splitString(String str, int len, String elide)
	{
		if (str == null)
		{
			return "";
		}
		byte[] strByte = str.getBytes();
		int strLen = strByte.length;
		int elideLen = (elide.trim().length() == 0) ? 0 : elide.getBytes().length;
		if (len >= strLen || len < 1)
		{
			return str;
		}
		if (len - elideLen > 0)
		{
			len = len - elideLen;
		}
		int count = 0;
		for (int i = 0; i < len; i++)
		{
			int value = (int) strByte[i];
			if (value < 0)
			{
				count++;
			}
		}
		if (count % 2 != 0)
		{
			len = (len == 1) ? len + 1 : len - 1;
		}
		return new String(strByte, 0, len) + elide.trim();
	}

	public boolean isSimpleDate(String date)
	{
		Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
		return p.matcher(date).matches();
	}

	public String getMessageAttrValue(Integer[] attrOptionId, String[] attrOptionValue, int value)
	{
		if (attrOptionId != null && attrOptionId.length > 0)
		{
			for (int i = 0; i < attrOptionId.length; i++)
			{
				if (attrOptionId[i] == value)
				{
					return attrOptionValue[i];
				}
			}
		}
		return "";
	}

	/**
	 * 将字符串转为Document
	 * 
	 * @return
	 * @param s
	 *            xml格式的字符串
	 */
	public Document string2Document(String s)
	{
		Document document = null;
		try
		{
			DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			document = parser.parse(new InputSource(new StringReader(s)));
		}
		catch (Exception e)
		{
			log.debug(e);
			return null;
		}
		return document;
	}

	/**
	 * 验证手机的正确性
	 * 
	 * @param mobile
	 * @return
	 */
	public boolean isMobile(String mobile)
	{
		String cmcc = "^[1]{1}(([3]{1}[4-9]{1})|([5]{1}[01789]{1})|([8]{1}[78]{1}))[0-9]{8}$";
		String cucc = "^[1]{1}(([3]{1}[0-3]{1})|([5]{1}[356]{1})|([8]{1}[56]{1}))[0-9]{8}$";
		String cnc = "^[1]{1}(([5]{1}[3]{1})|([8]{1}[9]{1}))[0-9]{8}$";

		boolean flag;// 存储匹配结果
		// 判断手机号码是否是11位
		if (mobile.length() == 11)
		{
			// 判断手机号码是否符合中国移动的号码规则
			if (mobile.matches(cmcc))
			{
				flag = true;
			}
			// 判断手机号码是否符合中国联通的号码规则
			else if (mobile.matches(cucc))
			{
				flag = true;
			}
			// 判断手机号码是否符合中国网通的号码规则
			else if (mobile.matches(cnc))
			{
				flag = true;
			}
			// 都不合适
			else
			{
				flag = false;
			}
		}
		// 不是11位
		else
		{
			flag = false;
		}
		return flag;

	}

	public static boolean isUnicomMobile(String mobile)
	{
		String cucc = "^[1]{1}(([3]{1}[0-3]{1})|([5]{1}[356]{1})|([8]{1}[0569]{1}))[0-9]{8}$";
		boolean flag = false;// 存储匹配结果
		// 判断手机号码是否是11位
		if (mobile.length() == 11)
		{
			// 判断手机号码是否符合中国联通的号码规则
			if (mobile.matches(cucc))
			{
				flag = true;
			}
		}
		// 不是11位
		else
		{
			flag = false;
		}
		return flag;

	}

	public static String html2Text(String inputString)
	{
		return inputString.replace("'", "’");
	}

	public String checkNumber(String a, String b)
	{
		log.debug(Integer.parseInt(a) + " < " + Integer.parseInt(b));
		if (Integer.parseInt(a) < Integer.parseInt(b))
			return "1";
		return "0";
	}

	public static String htmlEscaping(String html)
	{
		if (!StringUtils.isEmpty(html))
		{
			String _html = "";
			_html = html.replaceAll("\"", "\\\\\"");
			_html = html.replaceAll("\'", "\\\\\'");
			_html = html.replace("\'", ""); 
			return _html;
		}
		else
		{
			return html;
		}
	}

	public static void main(String[] args)
	{
		System.out.println(Common.htmlEscaping("aaaa''i''bbb"));
	}

}
