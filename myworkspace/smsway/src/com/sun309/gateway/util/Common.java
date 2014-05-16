package com.sun309.gateway.util;

import java.io.StringReader;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
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
	 * 取得服务器的当前时间(时间)
	 * 
	 * @return
	 */
	public static long getNowTime()
	{
		return System.currentTimeMillis();
	}

	/**
	 * 取得客户端的IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIP(HttpServletRequest request)
	{
		log.debug(request);
		return request.getRemoteHost();
	}

	/**
	 * 对象转换成数组
	 * 
	 * @param myobject
	 * @return
	 */
	public ArrayList objectToArray(Object[] myobject)
	{
		ArrayList<Object> al = new ArrayList<Object>();
		for (int i = 0; i < myobject.length; i++)
		{
			al.add(myobject[i]);
		}
		return al;
	}

	public List objectToList(Object[] ob)
	{
		List<Object> list = null;
		for (int i = 0; i < ob.length; i++)
		{
			list.add(ob[i]);
		}
		return list;
	}

	/**
	 * 集合转换成对象
	 * 
	 * @param list
	 * @return
	 */
	public Object[] listToObject(List list)
	{
		if (list != null)
		{
			Object[] o = new Object[list.size()];
			for (int i = 0; i < list.size(); i++)
			{
				o[i] = (Object) list.get(i);
			}
			return o;
		}
		else
		{
			return null;
		}
	}

	/**
	 * 将时间戳变成(0000-00-00 00:00:00)形式
	 * 
	 * @param time
	 * @return
	 */
	public String getDate(String time)
	{
		if (!"".equals(time) && time != null && !"0".equals(time))
		{
			String date = new Timestamp(Long.parseLong(time)).toString();
			date = date.split("\\x2E")[0];
			return date;
		}
		else
		{
			return "";
		}
	}

	/**
	 * 将时间戳变成(0000-00-00)形式
	 * 
	 * @param time
	 * @return
	 */
	public String getSimpleDate(String time)
	{
		log.debug("time >>>>>>> " + time);
		if (!"".equals(time) && time != null && !"0".equals(time))
		{
			String date = new Timestamp(Long.parseLong(time)).toString();
			date = date.split("\\x2E")[0];
			log.debug(" date >>> " + date);
			return date.substring(0, 10);
		}
		else
		{
			return "";
		}
	}

	/**
	 * 00:00:00 => long
	 * 
	 * @param time
	 * @return
	 */
	public long timeToLong(String time)
	{
		long result = 0;
		if (time != null && !StringUtils.isEmpty(time))
		{
			String[] s = time.split(":");
			Integer t = Integer.parseInt(s[0]) * 60 * 60;
			Integer t1 = Integer.parseInt(s[1]) * 60;
			Integer t2 = Integer.parseInt(s[2]) * 1;
			result = t.intValue() + t1.intValue() + t2.intValue();
		}
		log.debug("result ---- " + result);
		return result;
	}

	public String longToTime(String l)
	{
		long ll = Long.parseLong(l);
		int t = ((int) ll / 60) % 60;
		int result = 0;
		result = ((int) ll / 60) / 60;
		System.out.println("----" + result);
		System.out.println("----" + t);
		String r = new String();
		if (result < 10)
			r = "0" + new Integer(result).toString();
		if (t == 0)
		{
			r = r + ":00";
		}
		else
		{
			r = r + ":" + new Integer(t).toString();
		}
		return r;
	}

	/**
	 * 时间字符0000-00-00 转成 long
	 * 
	 * @param date
	 * @return
	 */
	public Long stringDataToLongTime(String date)
	{
		if (date == null || StringUtils.isEmpty("date"))
			return 0l;
		long l = 0;
		Date d = new Date();
		try
		{
			String _date[] = date.split(" ");
			String temp[] = _date[0].split("/");
			if (temp.length > 1)
			{
				date = new StringBuffer(temp[2]).append("-").append(temp[1]).append("-").append(temp[0]).toString();
			}
			if (date.trim().length() <= 10)
			{
				date = date + " 00:00:00";
			}
			Format format = null;
			try
			{
				format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
			catch(Exception e)
			{
				try
				{
					format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				}
				catch(Exception ex)
				{
					try
					{
						format = new SimpleDateFormat("yyyy-MM-dd HH");
					}
					catch(Exception ex1)
					{
						format = new SimpleDateFormat("yyyy-MM-dd");
					}
				}
			}
			
			try
			{
				d = (Date) format.parseObject(date);
			}
			catch(Exception ex)
			{
				
			}
			_date = null;
			temp = null;
			format = null;
		}
		catch (Exception e)
		{
			e.printStackTrace(); 
		}
		l = d.getTime();
		d = null;
		return l;
	}

	public Long stringDataToLongTime(String date, String hour, String minute)
	{
		Long l = new Long(0);
		Date d = new Date();
		try
		{
			String _date[] = date.split(" ");
			String temp[] = _date[0].split("/");
			if (temp.length > 1)
			{
				date = temp[2] + "-" + temp[1] + "-" + temp[0];
			}
			String temp1 = date + " " + hour + ":" + minute + ":00";
			Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			d = (Date) format.parseObject(temp1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		l = d.getTime();
		log.debug("l  stringDataToLongTime --------------- " + l);
		return l;
	}

	/**
	 * 处理绝对路径(�? '\' 变成 '/')
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
		String cmcc = "^[1]{1}(([3]{1}[4-9]{1})|([5]{1}[01789]{1})|([8]{1}[278]{1}))[0-9]{8}$";
		String cucc = "^[1]{1}(([3]{1}[0-2]{1})|([5]{1}[356]{1})|([8]{1}[0569]{1}))[0-9]{8}$";
		String cnc = "^[1]{1}(([3]{1}[3]{1})|([5]{1}[3]{1})|([8]{1}[9]{1}))[0-9]{8}$";
		
		// like '130%' or '131%' or '132%' or '133%' or '153%' or '155%' or '156%' or '185%' or '186%'
		// like '189%'

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

	public String[] getNowHourMinuteTime()
	{
		String time[] = new String[3];
		String data = getDate(new Long(Common.getNowTime()).toString());
		String d[] = data.split(" ");
		time = d[1].split(":");
		return time;
	}

	public static String html2Text(String inputString)
	{
		return inputString.replace("'", "’");
	}
	
	public String checkNumber(String a, String b)
	{
		log.debug(Integer.parseInt(a) + " < " + Integer.parseInt(b));
		if(Integer.parseInt(a) < Integer.parseInt(b)) return "1";
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
		Common c = new Common();
		// long t = c.stringDataToLongTime("2009-09-08 08:00:09");
		//
		// System.out.println(t);
		System.out.println((c.getDate("586969200000")));
		//			
		//System.out.println(new Long(Common.getNowTime()+10000).toString());

		//System.out.println(c.stringDataToLongTime(c.getSimpleDate(new Long(Common.getNowTime()).toString()), "18", "00"));
	}
}
