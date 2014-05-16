package utils.JUtils.chenbug.chineseren;
   
import java.text.*;   
import java.util.*;   
   
/**  
 * <p>Title: DateUtils</p>  
 * <p>Description: DateUtils</p>  
 * <p>Copyright: Copyright (c) 2003</p>  
 * <p>Company: pubinfo</p>  
 * @author chineseren  
 * @version 1.0  
 */   
   
public class DateUtils {   
  private static java.util.Calendar calendar = java.util.Calendar.getInstance();   
  /**  
   * 根据日期得到年份字符串  
   */   
  public static final String getYear(java.util.Date date) {   
    String week;   
    String Tempstr;   
    int i;   
    SimpleDateFormat mydate = new SimpleDateFormat(" MMM dd H mm ss,yyyy");   
    Tempstr = "";   
    week = "";   
    mydate.applyPattern("yyyy");   
    i = Integer.parseInt(mydate.format(date));   
    Tempstr = Tempstr + String.valueOf(i);   
    return Tempstr;   
  }   
   
  /**  
   * 根据日期得到月份字符串  
   */   
  public static final String getMonth(java.util.Date date) {   
    String week;   
    String Tempstr;   
    int i;   
    SimpleDateFormat mydate = new SimpleDateFormat(" MM dd H mm ss,yyyy");   
    Tempstr = "";   
    week = "";   
    mydate.applyPattern("MM");   
    i = Integer.parseInt(mydate.format(date));   
    Tempstr = Tempstr + String.valueOf(i);   
    return Tempstr;   
  }   
   
  /**  
   * 根据日期得到日期字符串  
   */   
  public static final String getDay(java.util.Date date) {   
    String week;   
    String Tempstr;   
    int i;   
    SimpleDateFormat mydate = new SimpleDateFormat(" MM dd H mm ss,yyyy");   
    Tempstr = "";   
    week = "";   
    mydate.applyPattern("dd");   
    i = Integer.parseInt(mydate.format(date));   
    Tempstr = Tempstr + String.valueOf(i);   
    return Tempstr;   
  }   
   
  /**  
   * 根据日期得到小时字符串  
   */   
  public static final String getHour(java.util.Date date) {   
    String week;   
    String Tempstr;   
    int i;   
    SimpleDateFormat mydate = new SimpleDateFormat(" MM dd H mm ss,yyyy");   
    Tempstr = "";   
    week = "";   
    mydate.applyPattern("H");   
    i = Integer.parseInt(mydate.format(date));   
    Tempstr = Tempstr + String.valueOf(i);   
    return Tempstr;   
  }   
   
  /**  
   * 得到日期时间格式例如 2002-03-15 02:32:25  
   */   
  public static final String getFormatDateTime(java.util.Date date) {   
    return (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);   
  }   
  public static final String getFormatDateTime(long lngDate) {   
    if(0 >= lngDate){   
      return "";   
    }else{   
      return getFormatDateTime(new java.util.Date(lngDate));   
    }   
  }   
  public static final String getFormatDateTime(Long lngObj) {   
    return getFormatDateTime(lngObj.longValue());   
  }   
   
  /**  
   * 得到小时，分的格式例如  02:32  
   */   
  public static final String getFormatTime(java.util.Date date) {   
    String week;   
    String Tempstr;   
    int i;   
    SimpleDateFormat mydate = new SimpleDateFormat(" MMM dd H mm ss,yyyy");   
    Tempstr = "";   
   
    mydate.applyPattern("H");   
    i = Integer.parseInt(mydate.format(date));   
    if(i < 10) {   
      Tempstr = Tempstr + "0" + String.valueOf(i) + ":";   
    } else {   
      Tempstr = Tempstr + String.valueOf(i) + ":";   
    }   
   
    mydate.applyPattern("mm");   
    i = Integer.parseInt(mydate.format(date));   
    if(i < 10) {   
      Tempstr = Tempstr + "0" + String.valueOf(i);   
    } else {   
      Tempstr = Tempstr + String.valueOf(i);   
    }   
   
    return Tempstr;   
  }   
   
  /**  
   * 得到小时的格式例如  02  
   */   
  public static final String getFormatHour(java.util.Date date) {   
    String week;   
    String Tempstr;   
    int i;   
    SimpleDateFormat mydate = new SimpleDateFormat(" MMM dd H mm ss,yyyy");   
    Tempstr = "";   
   
    mydate.applyPattern("H");   
    i = Integer.parseInt(mydate.format(date));   
    Tempstr = Tempstr + String.valueOf(i);   
    return Tempstr;   
  }   
   
  /**  
   * 得到小时的格式例如  02  
   */   
  public static final String getFormatMinute(java.util.Date date) {   
    String week;   
    String Tempstr;   
    int i;   
    SimpleDateFormat mydate = new SimpleDateFormat(" MMM dd H mm ss,yyyy");   
    Tempstr = "";   
   
    mydate.applyPattern("mm");   
    i = Integer.parseInt(mydate.format(date));   
    Tempstr = Tempstr + String.valueOf(i);   
    return Tempstr;   
  }   
   
  /**  
   * 得到时间格式例如 2002-03-15  
   */   
  public static final String getFormatDate(long dateLong) {   
    return getFormatDate(new java.util.Date(dateLong));   
  }   
  public static final String getFormatDate(java.util.Date date) {   
    String week;   
    String Tempstr;   
    int i;   
    SimpleDateFormat mydate = new SimpleDateFormat(" MMM dd H mm ss,yyyy");   
    Tempstr = "";   
    week = "";   
    mydate.applyPattern("yyyy");   
    i = Integer.parseInt(mydate.format(date));   
    Tempstr = Tempstr + String.valueOf(i) + "-";   
    mydate.applyPattern("MM");   
    i = Integer.parseInt(mydate.format(date));   
    if(i < 10) {   
      Tempstr = Tempstr + "0" + String.valueOf(i) + "-";   
    } else {   
      Tempstr = Tempstr + String.valueOf(i) + "-";   
    }   
    mydate.applyPattern("dd");   
    i = Integer.parseInt(mydate.format(date));   
    if(i < 10) {   
      Tempstr = Tempstr + "0" + String.valueOf(i);   
    } else {   
      Tempstr = Tempstr + String.valueOf(i);   
    }   
    return Tempstr;   
  }   
   
  /**  
   * 用于只输入年月日生成long型的时间格式  
   **/   
  public static final long getTimeLong(int yy, int mm, int dd) {   
    calendar.clear();   
    calendar.set(yy, mm - 1, dd, 0, 0, 0);   
    return calendar.getTimeInMillis();   
   
  }   
   
  /**  
   * 用于输入年月日小时分生成long型的时间格式  
   **/   
  public static final long getTimeLong(int yy, int mm, int dd, int h, int m) {   
    calendar.clear();   
    calendar.set(yy, mm - 1, dd, h, m, 0);   
    return calendar.getTimeInMillis();   
  }   
   
  /**  
   * 用于只输入年，月生成long型的时间格式  
   **/   
  public static final long getTimeLong(int yy, int mm) {   
    calendar.clear();   
    calendar.set(yy, mm - 1, 0, 0, 0, 0);   
    return calendar.getTimeInMillis();   
  }   
   
  /**  
   * 根据输入的初始日期和新增的月份到新增月份后的总时间  
   **/   
  public static final long getTotalTime(java.util.Date startTime, long month) {   
    calendar.setTime(startTime);   
    calendar.add(calendar.MONTH, (int)month);   
    return calendar.getTimeInMillis();   
  }   
   
  /**  
   * 用于输入年月日小时分秒生成long型的时间格式  
   **/   
  public static final long getTimeLong(int yy, int mm, int dd, int h, int m,   
      int sec) {   
    calendar.clear();   
    calendar.set(yy, mm - 1, dd, h, m, sec);   
    return calendar.getTimeInMillis();   
  }   
   
  /**  
   * 返回相应格式的时间字符串，iFlag == 0，返回年月日时分秒  
   * iFlag == 1，返回年－月－日 时：分：秒  
   * iFlag == 2，返回年月日  
   * iFlag == 3，返回年－月－日  
   * iFlag == 4，返回年－月  
   * @param date 要转化的时间  
   * @param iFlag 要转化的时间格式  
   * @return 相应格式的时间字符串  
   */   
  public static String getFormatDate(Date date, int iFlag) {   
    SimpleDateFormat simpleDateFormat;   
    if(iFlag == 0) {   
      simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");   
    } else if(iFlag == 1) {   
      simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
    } else if(iFlag == 2) {   
      simpleDateFormat = new SimpleDateFormat("yyyyMMdd");   
    } else if(iFlag == 3) {   
      simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");   
    } else if(iFlag == 4) {   
      simpleDateFormat = new SimpleDateFormat("yyyy-MM");   
    } else if(iFlag == 5) {   
      simpleDateFormat = new SimpleDateFormat("yyyy-MM HH");   
    } else {   
      simpleDateFormat = new SimpleDateFormat("yyyy-MM HH:mm");   
    }   
   
    return simpleDateFormat.format(date);   
  }   
   
  public static String getFormatDate(long lngDate, int iFlag) {   
    return getFormatDate(new Date(lngDate), iFlag);   
  }   
   
  //转化当前的时间   
  public static String getFormatDate(int iFlag) {   
    return getFormatDate(new Date(), iFlag);   
  }   
   
  /**  
   * 根据输入一个时间得到和现在的时间差  
   **/   
  public static final String getLeaveTime(long tagTime) {   
    long nowTime = System.currentTimeMillis();   
    long leaveTime = 0;   
    if(nowTime > tagTime)   
      leaveTime = (nowTime - tagTime) / 1000;   
    else   
      leaveTime = (tagTime - nowTime) / 1000;   
    long date = 0;   
    long hour = 0;   
    long minute = 0;   
//    int second = 0;   
   
   
    long dateTime = 0;   
    long hourTime = 0;   
 //   long minuteTime = 0;   
   
   
   
    String strDate = "";   
    String strHour = "";   
    String strMinute = "";   
    //   String strSecond = "";   
   
    date = leaveTime / 86400;   
    dateTime = date*86400;   
    hour = (leaveTime - dateTime)/3600;   
    hourTime = hour*3600;   
    minute = (leaveTime - dateTime - hourTime)/60;   
  //  minuteTime = minute*60;   
   
    //  second = leaveTime - dateTime - hourTime-minuteTime;   
   
    if(date > 0)   
      strDate = date + "天";   
    if(hour > 0||(minute > 0&&date>0))   
      strHour = hour + "小时";   
    if(minute > 0)   
      strMinute = minute + "分";   
      //   if(second>0)   
      //     strSecond = second+"秒";   
   
    return strDate + strHour + strMinute;   
  }   
   
  public static void main(String[] args) throws Exception {   
    long tagTime = getTimeLong(2004, 11, 29,11,31);   
   
    System.out.print("leavetime:"+getLeaveTime(tagTime));   
   
  }   
}  



