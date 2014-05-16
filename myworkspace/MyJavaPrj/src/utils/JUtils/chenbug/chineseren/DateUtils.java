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
   * �������ڵõ�����ַ���  
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
   * �������ڵõ��·��ַ���  
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
   * �������ڵõ������ַ���  
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
   * �������ڵõ�Сʱ�ַ���  
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
   * �õ�����ʱ���ʽ���� 2002-03-15 02:32:25  
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
   * �õ�Сʱ���ֵĸ�ʽ����  02:32  
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
   * �õ�Сʱ�ĸ�ʽ����  02  
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
   * �õ�Сʱ�ĸ�ʽ����  02  
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
   * �õ�ʱ���ʽ���� 2002-03-15  
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
   * ����ֻ��������������long�͵�ʱ���ʽ  
   **/   
  public static final long getTimeLong(int yy, int mm, int dd) {   
    calendar.clear();   
    calendar.set(yy, mm - 1, dd, 0, 0, 0);   
    return calendar.getTimeInMillis();   
   
  }   
   
  /**  
   * ��������������Сʱ������long�͵�ʱ���ʽ  
   **/   
  public static final long getTimeLong(int yy, int mm, int dd, int h, int m) {   
    calendar.clear();   
    calendar.set(yy, mm - 1, dd, h, m, 0);   
    return calendar.getTimeInMillis();   
  }   
   
  /**  
   * ����ֻ�����꣬������long�͵�ʱ���ʽ  
   **/   
  public static final long getTimeLong(int yy, int mm) {   
    calendar.clear();   
    calendar.set(yy, mm - 1, 0, 0, 0, 0);   
    return calendar.getTimeInMillis();   
  }   
   
  /**  
   * ��������ĳ�ʼ���ں��������·ݵ������·ݺ����ʱ��  
   **/   
  public static final long getTotalTime(java.util.Date startTime, long month) {   
    calendar.setTime(startTime);   
    calendar.add(calendar.MONTH, (int)month);   
    return calendar.getTimeInMillis();   
  }   
   
  /**  
   * ��������������Сʱ��������long�͵�ʱ���ʽ  
   **/   
  public static final long getTimeLong(int yy, int mm, int dd, int h, int m,   
      int sec) {   
    calendar.clear();   
    calendar.set(yy, mm - 1, dd, h, m, sec);   
    return calendar.getTimeInMillis();   
  }   
   
  /**  
   * ������Ӧ��ʽ��ʱ���ַ�����iFlag == 0������������ʱ����  
   * iFlag == 1�������꣭�£��� ʱ���֣���  
   * iFlag == 2������������  
   * iFlag == 3�������꣭�£���  
   * iFlag == 4�������꣭��  
   * @param date Ҫת����ʱ��  
   * @param iFlag Ҫת����ʱ���ʽ  
   * @return ��Ӧ��ʽ��ʱ���ַ���  
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
   
  //ת����ǰ��ʱ��   
  public static String getFormatDate(int iFlag) {   
    return getFormatDate(new Date(), iFlag);   
  }   
   
  /**  
   * ��������һ��ʱ��õ������ڵ�ʱ���  
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
      strDate = date + "��";   
    if(hour > 0||(minute > 0&&date>0))   
      strHour = hour + "Сʱ";   
    if(minute > 0)   
      strMinute = minute + "��";   
      //   if(second>0)   
      //     strSecond = second+"��";   
   
    return strDate + strHour + strMinute;   
  }   
   
  public static void main(String[] args) throws Exception {   
    long tagTime = getTimeLong(2004, 11, 29,11,31);   
   
    System.out.print("leavetime:"+getLeaveTime(tagTime));   
   
  }   
}  



