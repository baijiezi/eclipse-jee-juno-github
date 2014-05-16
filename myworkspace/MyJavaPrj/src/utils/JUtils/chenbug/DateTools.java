package utils.JUtils.chenbug;

import java.text.*;   
import java.util.*;   
   
/**  
 * <p>Description:  
 * 时间处理工具类，传入的时间格式必须是yyyymmddhhmmss，即14位长度的字符串</p>  
 * <p>Company: wadin</p>  
 * <p>Copyright Copyright (c) 2003</p>  
 * <p>Createtime </p>  
 * @author chenbug  
 * @version 1.0  
 */   
public class DateTools {   
   
  /**  
   * 返回当前时间，iFlag == 0，返回年月日时分秒  
   * iFlag == 1，返回年－月－日 时：分：秒  
   * iFlag == 2，返回年月日  
   * iFlag == 3，返回年－月－日  
   * iFlag == 4，返回年－月  
   * @param iFlag  
   * @return  
   */   
  private static String getFullTime(int iFlag) {   
    Date date = new Date();   
    SimpleDateFormat simpleDateFormat;   
    if (iFlag == 0) {   
      simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");   
    }   
    else if (iFlag == 1) {   
      simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
    }   
    else if (iFlag == 2) {   
      simpleDateFormat = new SimpleDateFormat("yyyyMMdd");   
    }   
    else if (iFlag == 3) {   
      simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");   
    }   
    else if (iFlag == 4) {   
      simpleDateFormat = new SimpleDateFormat("yyyy-MM");   
    }   
    else if (iFlag == 5) {   
      simpleDateFormat = new SimpleDateFormat("yyyy-MM HH");   
    }   
    else {   
      simpleDateFormat = new SimpleDateFormat("yyyy-MM HH:mm");   
    }   
    return simpleDateFormat.format(date);   
   
  }   
   
  /**  
   * 解析字符串格式时间为标准格式 iFlag == 0 返回年－月－日 时：分：秒  
   * iFlag == 1 返回年－月－日  
   * @param sFullTime  
   * @param iFlag  
   * @return  
   */   
  private static String getFullTime(String sFullTime, int iFlag) {   
    if (sFullTime == null || sFullTime.trim().equals("")) {   
      return "";   
    }   
    StringBuffer sDateBuffer = new StringBuffer();   
    sDateBuffer.append(sFullTime.substring(0, 4) + "-");   
    sDateBuffer.append(sFullTime.substring(4, 6) + "-");   
    sDateBuffer.append(sFullTime.substring(6, 8) + " ");   
    if (iFlag == 1) {   
      return sDateBuffer.toString();   
    }   
   
    if (iFlag == 2) {   
      sDateBuffer.append(sFullTime.substring(8, 10));   
      sDateBuffer.append("时");   
      return sDateBuffer.toString();   
    }   
    else {   
      sDateBuffer.append(sFullTime.substring(8, 10) + ":");   
    }   
   
    if (iFlag == 3) {   
      sDateBuffer.append(sFullTime.substring(10, 12));   
      return sDateBuffer.toString();   
    }   
    else {   
      sDateBuffer.append(sFullTime.substring(10, 12) + ":");   
    }   
   
    sDateBuffer.append(sFullTime.substring(12, 14));   
    return sDateBuffer.toString();   
   
  }   
   
  /**  
   * 返回当前时间的标准格式： 年－月－日 时：分：秒  
   * @return  
   */   
  public static String getStandardFullTime() {   
    return getFullTime(1);   
  }   
   
  /**  
   * 解析字符串格式时间为标准格式 年月日时分秒 -> 年－月－日 时：分：秒  
   * @param sFullTime  
   * @return  
   */   
  public static String getStandardFullTime(String sFullTime) {   
    return getFullTime(sFullTime, 0);   
  }   
   
  /**  
   * 返回当前时间的字符串格式：年月日时分秒  
   * @return  
   */   
  public static String getStringFullTime() {   
    return getFullTime(0);   
  }   
   
  /**  
   * 返回当前时间的标准格式：年－月－日  
   * @return  
   */   
  public static String getStandardYearMonthDay() {   
    return getFullTime(3);   
  }   
   
  /**  
   * 返回当前时间的标准格式：年－月－日  
   * @return  
   */   
  public static String getStandardYearMonth() {   
    return getFullTime(4);   
  }   
   
  public static String getStandardYearMonthDayHour() {   
    return getFullTime(5);   
  }   
   
  public static String getStandardYearMonthDayHourMinute() {   
    return getFullTime(6);   
  }   
   
  /**  
   * 解析字符串格式时间为标准格式  年月日 -> 年－月－日  
   * @param sYearMonthDay  
   * @return  
   */   
  public static String getStandardYearMonthDay(String sYearMonthDay) {   
    return getFullTime(sYearMonthDay, 1);   
  }   
   
  public static String getStandardYearMonthDayHour(String sFullTime) {   
    return getFullTime(sFullTime, 2);   
  }   
   
  public static String getStandardYearMonthDayHourMinute(String sFullTime) {   
    return getFullTime(sFullTime, 3);   
  }   
   
  /**  
   * 返回当前时间的字符串格式:年月日  
   * @return  
   */   
  public static String getStringYearMonthDay() {   
    return getFullTime(2);   
  }   
   
  /**  
   * 转换年－月－日 到年月日  
   * @param date  
   * @return  
   */   
  public static String parseYearMonthDay(String date) {   
    try {   
      return date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10);   
    }   
    catch (Exception e) {   
      return null;   
    }   
   
  }   
}   


