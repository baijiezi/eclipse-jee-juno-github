package utils.sun;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils
{
	public static String changeDateFormat(String scrDateString, String srcDateFormat, String destDateFormat) throws Exception
	{
		if(scrDateString==null || scrDateString.trim().equals("")) return "";
		SimpleDateFormat sdf = new SimpleDateFormat(srcDateFormat);
		try
		{
			Date date = sdf.parse(scrDateString);
			SimpleDateFormat bartDateFormat = new SimpleDateFormat(destDateFormat);
			return bartDateFormat.format(date);
		} 
		catch (ParseException e)
		{
			throw new Exception("【" + scrDateString + "】 转换失败：" + e.getMessage()); 
		}
	}
	// 返回当前年份
	public int getYear()
	{
		Date date = new Date();
		String year = new SimpleDateFormat("yyyy").format(date);
		return Integer.parseInt(year);
	}

	// 返回当前月份
	public int getMonth()
	{
		Date date = new Date();
		String month = new SimpleDateFormat("MM").format(date);
		return Integer.parseInt(month);
	}

	// 返回当前天
	public int getDay()
	{
		Date date = new Date();
		String dd = new SimpleDateFormat("dd").format(date);
		return Integer.parseInt(dd);
	}

	public String getDate()
	{
		Date date = new Date();
		StringBuffer result = new StringBuffer();
		result.append(new SimpleDateFormat("yyyy").format(date));
		result.append("-");
		result.append(new SimpleDateFormat("MM").format(date));
		result.append("-");
		result.append(new SimpleDateFormat("dd").format(date));
		result.append(" ");
		result.append(new SimpleDateFormat("HH").format(date));
		result.append(":");
		result.append(new SimpleDateFormat("mm").format(date));
		result.append(":");
		result.append(new SimpleDateFormat("ss").format(date));
		return result.toString();
	}

	public String getSimpleDate()
	{
		Date date = new Date();
		StringBuffer result = new StringBuffer();
		result.append(new SimpleDateFormat("yyyy").format(date));
		result.append("-");
		result.append(new SimpleDateFormat("MM").format(date));
		result.append("-");
		result.append(new SimpleDateFormat("dd").format(date));
		return result.toString();
	}

	// 判断闰年
	public boolean isLeap(int year)
	{
		if (((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0))
			return true;
		else
			return false;
	}

	// 返回当月天数
	public int getDays(int year, int month)
	{
		int days;
		int FebDay = 28;
		if (isLeap(year))
			FebDay = 29;
		switch (month)
		{
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			days = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		case 2:
			days = FebDay;
			break;
		default:
			days = 0;
			break;
		}
		return days;
	}

	// 返回当月星期天数
	public int getSundays(int year, int month)
	{
		int sundays = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		Calendar setDate = Calendar.getInstance();
		// 从第一天开始
		int day;
		for (day = 1; day <= getDays(year, month); day++)
		{
			setDate.set(Calendar.DATE, day);
			String str = sdf.format(setDate.getTime());
			if (str.equals("星期日"))
			{
				sundays++;
			}
		}
		return sundays;
	}

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
		if (!"".equals(time) && time != null && !"0".equals(time))
		{
			String date = new Timestamp(Long.parseLong(time)).toString();
			date = date.split("\\x2E")[0];
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
		if (time != null && !time.trim().equals(""))
		{
			String[] s = time.split(":");
			Integer t = Integer.parseInt(s[0]) * 60 * 60;
			Integer t1 = Integer.parseInt(s[1]) * 60;
			Integer t2 = Integer.parseInt(s[2]) * 1;
			result = t.intValue() + t1.intValue() + t2.intValue();
		}
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
	 * 时间字符0000-00-00 00:00:00 转成 long
	 * 
	 * @param date
	 * @return
	 */
	public Long stringDataToLongTime(String date)
	{
		if (date == null || date.trim().equals(""))
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
				date = new StringBuffer(temp[2]).append("-").append(temp[1]).append("-").append(temp[0]).toString();
			}
			String temp1 = new StringBuffer(date).append(" ").append(hour).append(":").append(minute).append(":00").toString();
			Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			d = (Date) format.parseObject(temp1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		l = d.getTime();
		d = null;
		return l;
	}

	/**
	 * 缺省的DateFormat对象，可以将一个java.util.Date格式化成yyyy-mm-dd输出
	 */
	private static DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);

	/**
	 * <p>
	 * 返回一个当前的时间，并按格式转换为字符串
	 * </p>
	 * 例：17:27:03
	 * 
	 * @return String
	 */
	public static String getTime()
	{
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);
		return df.format(dNow);
	}

	/**
	 * <p>
	 * 返回一个当前日期，并按格式转换为字符串
	 * </p>
	 * 例：2004-4-30
	 * 
	 * @return String
	 */
	public static String getNowDate()
	{
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);
		return df.format(dNow);
	}

	/**
	 * <p>
	 * 返回一个当前日期和时间，并按格式转换为字符串
	 * </p>
	 * 例：2004-4-30 17:27:03
	 * 
	 * @return String
	 */
	public static String getNowDateTime()
	{
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);
		return df.format(dNow);
	}

	/**
	 * <p>
	 * 返回当前年
	 * </p>
	 * 
	 * @return int
	 */
	public static int getThisYear()
	{
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.YEAR);
	}

	/**
	 * 返回本月
	 * 
	 * @return int
	 */
	public static int getThisMonth()
	{
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.MONTH) + 1;
	}

	/**
	 * 返回今天是本月的第几天
	 * 
	 * @return int 从1开始
	 */
	public static int getToDayOfMonth()
	{
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.DAY_OF_MONTH);
	}

	public static int getWeekDayOfDate(String date)
	{
		try
		{
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

			Date d = f.parse(date);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d);
			return calendar.get(Calendar.DAY_OF_WEEK) - 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 返回当前的小时
	 * 
	 * @return int
	 */
	public static int getHour()
	{
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.HOUR);
	}

	/**
	 * 返回当前的分钟
	 * 
	 * @return int 返回当前的分钟
	 */
	public static int getMinute()
	{
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.MINUTE);
	}

	/**
	 * 返回当前的秒数
	 * 
	 * @return int 第几秒
	 */
	public static int getSecond()
	{
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.SECOND);
	}

	/**
	 * 返回今天是本年的第几周
	 * 
	 * @return int 从1开始
	 */

	public static int getToWeekOfYear()
	{
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.WEEK_OF_YEAR);
	}

	/**
	 * 返回一格式化的日期
	 * 
	 * @param time
	 *            long
	 * @return String yyyy-mm-dd 格式
	 */
	public static String formatDate(java.util.Date date)
	{
		if (date == null)
			return "";
		else
			return df.format(date);
	}

	/**
	 * 返回一格式化的日期
	 * 
	 * @param time
	 *            long
	 * @return String 2005-6-17 格式
	 */
	public static String formatSDate(java.util.Date date)
	{
		if (date == null)
			return "";
		else
		{
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return bartDateFormat.format(date);
		}
	}

	/**
	 * 返回已添加指定时间间隔的日期
	 * 
	 * @param interval
	 *            表示要添加的时间间隔("y":年;"d":天;"m":月;如有必要可以自行增加)
	 * @param number
	 *            表示要添加的时间间隔的个数
	 * @param date
	 *            java.util.Date()
	 * @return String 2005-5-12格式的日期字串
	 */
	public static String dateAdd(String interval, int number, java.util.Date date)
	{
		String strTmp = "";
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		// 加若干年
		if (interval.equals("y"))
		{
			int currYear = gc.get(Calendar.YEAR);
			gc.set(Calendar.YEAR, currYear + number);
		}
		// 加若干月
		else if (interval.equals("m"))
		{
			int currMonth = gc.get(Calendar.MONTH);
			gc.set(Calendar.MONTH, currMonth + number);
		}
		// 加若干天
		else if (interval.equals("d"))
		{
			int currDay = gc.get(Calendar.DATE);
			gc.set(Calendar.DATE, currDay + number);
		}
		// 加若小时
		else if (interval.equals("h"))
		{
			int currDay = gc.get(Calendar.HOUR);
			gc.set(Calendar.HOUR, currDay + number);
		}
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
		strTmp = bartDateFormat.format(gc.getTime());
		return strTmp;
	}

	/**
	 * <p>
	 * 返回两个日期之间的单位间隔数
	 * </p>
	 * 
	 * @param a
	 *            java.util.Date
	 * @param b
	 *            java.util.Date
	 * @return int 间隔数
	 */
	public static int dateDiff(java.util.Date a, java.util.Date b)
	{
		int tempDifference = 0;
		int difference = 0;
		Calendar earlier = Calendar.getInstance();
		Calendar later = Calendar.getInstance();

		if (a.compareTo(b) < 0)
		{
			earlier.setTime(a);
			later.setTime(b);
		}
		else
		{
			earlier.setTime(b);
			later.setTime(a);
		}

		while (earlier.get(Calendar.YEAR) != later.get(Calendar.YEAR))
		{
			tempDifference = 365 * (later.get(Calendar.YEAR) - earlier.get(Calendar.YEAR));
			difference += tempDifference;

			earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
		}

		if (earlier.get(Calendar.DAY_OF_YEAR) != later.get(Calendar.DAY_OF_YEAR))
		{
			tempDifference = later.get(Calendar.DAY_OF_YEAR) - earlier.get(Calendar.DAY_OF_YEAR);
			difference += tempDifference;

			earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
		}

		return difference;
	}

	/**
	 * <p>
	 * 该方法是获得到每月1号星期一的数据
	 * </p>
	 * 
	 * @return -返回一个数字
	 */
	/**
	 * <p>
	 * 该方法是获得到每月1号星期一的数据
	 * </p>
	 * 
	 * @return -返回一个数字
	 */
	public static int getDate(int curYear, int curMonth, int curDate)
	{
		int day1 = 0;
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(curYear, curMonth - 1, curDate);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println("curDate=" + curDate + "   dayOfWeek   " + dayOfWeek);
		switch (dayOfWeek)
		{
		case 1: // 星期天
			day1 = 0;
			break;
		case 2: // 星期一
			day1 = 1;
			break;
		case 3: // 星期二
			day1 = 2;
			break;
		case 4: // 星期三
			day1 = 3;
			break;
		case 5: // 星期四
			day1 = 4;
			break;
		case 6: // 星期五
			day1 = 5;
			break;
		case 7: // 星期六
			day1 = 6;
			break;
		}
		return day1;
	}

	public static String checkTime(int id)
	{
		String bol = "";
		Calendar tt = Calendar.getInstance();
		String currDate = getNowDate();
		System.out.println("currDate=" + currDate);
		int result = tt.get(Calendar.DAY_OF_WEEK);

		int shour = tt.get(Calendar.HOUR_OF_DAY);

		if (id == 3)
		{
			switch (result)
			{
			case 1:
				break;
			case 7:
				if ((shour >= 8) && (shour < 12))
				{
					bol = "disabled";
					break;
				}
			default:
				if ((shour >= 8) && (shour < 12))
				{
					bol = "disabled";
					break;
				}
				else if ((shour >= 14) && (shour < 17))
				{
					bol = "disabled";
					break;
				}
			}
		}
		return bol;
	}

	/**
	 * <p>
	 * 该方法是将字符型转变成日期型
	 * </p>
	 * 
	 * @param strX
	 *            -传入字符类型
	 * @return -返回日期类型
	 */
	public static Date getStrDate(String strX)
	{
		Date date1 = new Date();
		if (!strX.equals(""))
		{
			try
			{
				date1 = (DateFormat.getDateInstance()).parse(strX);
			}
			catch (Exception ex)
			{
				System.out.println(ex.toString());
			}
		}
		else
		{
			GregorianCalendar gcNow = new GregorianCalendar();
			date1 = gcNow.getTime();
		}

		return date1;
	}

	/**
	 * <p>
	 * 比较两日期字符串的大小
	 * </p>
	 * 
	 * @param d1
	 * @param d2
	 * @return (d1>d2)?2:(d1=d2)?1:0
	 */
	public static int compareDate(String d1, String d2)
	{
		short vl = 1;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(getStrDate(d1));
		int year = gc.get(GregorianCalendar.YEAR);
		int month = gc.get(GregorianCalendar.MONTH);
		int day = gc.get(GregorianCalendar.DAY_OF_MONTH);
		gc.setTime(getStrDate(d2));
		int tempYear = gc.get(GregorianCalendar.YEAR);
		int tempMonth = gc.get(GregorianCalendar.MONTH);
		int tempDay = gc.get(GregorianCalendar.DAY_OF_MONTH);
		if (year != tempYear)
		{
			if (year > tempYear)
				vl = 2;
			else
				vl = 0;
		}
		else
		{
			if (month != tempMonth)
			{
				if (month > tempMonth)
					vl = 2;
				else
					vl = 0;
			}
			else
			{
				if (day != tempDay)
				{
					if (day > tempDay)
						vl = 2;
					else
						vl = 0;
				}
			}
		}
		return vl;
	}
	
	//周六、周日、周一的前一个工作日期是周五
	public static String getPreWorkDate(String date)
	{
		int weekOfDay = DateUtils.getWeekDayOfDate(date);
		String d = new String();
		DateUtils du = new DateUtils();
		switch(weekOfDay)
		{
			case 0 :  //周日
			{
				d = du.getSimpleDate(new Long(du.stringDataToLongTime(date) - (2l*24l*60l*60l*1000l)).toString());
				break;
			}
			case 1 :  //周一
			{
				d = du.getSimpleDate(new Long(du.stringDataToLongTime(date) - (3l*24l*60l*60l*1000l)).toString());
				break;
			}
			case 6 :  //周六
			{
				d = du.getSimpleDate(new Long(du.stringDataToLongTime(date) - (1l*24l*60l*60l*1000l)).toString());
				break;
			}
			default : 
			{
				d = du.getSimpleDate(new Long(du.stringDataToLongTime(date) - (1l*24l*60l*60l*1000l)).toString());
				break;
			}
		}
		return d;
	}
	
	//2011年国家法定节假日
	public static final int[][] COUNTRY_VACATION_DAY_OF_2011={
			/**劳动节**/{2011,5,1},{2011,5,2},{2011,5,3},
			/**国庆节**/{2011,10,1},{2011,10,2},{2011,10,3},{2011,10,4},{2011,10,5},{2011,10,6},{2011,10,7}
	};
	
	/**
	 * 是否2011年的国家节假日
	 * @param timeMillis
	 * @return
	 */
	public static boolean isCountryVacation(long timeMillis)
	{
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(timeMillis);
		int date=calendar.get(Calendar.DATE);
		int month=calendar.get(Calendar.MONTH)+1;
		int year=calendar.get(Calendar.YEAR);
		System.out.println(month);
		for(int vacationDay[]:COUNTRY_VACATION_DAY_OF_2011)
		{
			if(vacationDay[0]==year && vacationDay[1]==month && vacationDay[2]==date)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 是否周末
	 * @param timeMillis
	 * @return
	 */
	public static boolean isWeekEnd(long timeMillis)
	{
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(timeMillis);
		int dayOfWeek=calendar.get(Calendar.DAY_OF_WEEK);
		
		if(dayOfWeek==Calendar.SATURDAY||dayOfWeek==Calendar.SUNDAY)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 获取某天0点开始，23点结束
	 * date : 0000-00-00
	 * @param date
	 * @return
	 */
	public static long[] getDateStartAndEnd(String date)
	{
		DateUtils du = new DateUtils();
		String start = new StringBuilder(date).append(" 00:00:00").toString();
		String end = new StringBuilder(date).append(" 23:59:59").toString();
		long startTime = du.stringDataToLongTime(start);
		long endTime = du.stringDataToLongTime(end);
		return new long[] { startTime, endTime };
	}
}
