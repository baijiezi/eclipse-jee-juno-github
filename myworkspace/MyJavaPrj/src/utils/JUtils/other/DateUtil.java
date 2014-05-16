package utils.JUtils.other;



//日期实用工具，我们在做统计功能的时候，经常要统计本周，本月，本季度，本年的数据，
//这就需要以某一天为基准，找出这些日期的范围；另外也有些方便的Date与String的转换函数。    
   
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
   
   
/**  
 * @author iori.ogami  
 * @version 1.0 / 2005-06-30  
 *   
 */   
public class DateUtil {   
   
 public static final int HOUR = 1129 + 1;   
   
 public static final int DATE = 1129 + 2;   
   
 public static final int WEEK = 1129 + 3;   
   
 public static final int MONTH = 1129 + 4;   
   
 public static final int SEASON = 1129 + 5;   
   
 public static final int YEAR = 1129 + 6;   
   
 public static final String BEGIN = "begin";   
   
 public static final String END = "end";   
   
 /**  
  * get the specified date range based on ref.  
  * the range may be today, this week, this month,   
  * this season or this year.  
  *   
  * @param ref:  
  *   the benchmark of the range.   
  * @param range:  
  *   the range rounding the ref.   
  *   DAY, WEEK, MONTH, SEASON or YEAR.  
  *     
  * @return  
  *   a map with two entries, keyed BEGIN and END,  
  *   whose values are of Date type and indicate   
  *   the begin date and the end date of the range.   
  */   
 public static Map getDateRange(Date ref, int range) {   
  Map m = new HashMap();   
  Calendar cal = Calendar.getInstance();   
  cal.setTime(ref);   
     
  // this week   
  if ( range == WEEK ) {    
   int cnt = 0;   
   for (; cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY; cnt++) {   
    cal.add(Calendar.DATE, -1);   
   }   
   m.put( BEGIN, cal.getTime() );   
   cal.add(Calendar.DATE, cnt);   
   while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {   
    cal.add(Calendar.DATE, 1);   
   }   
   m.put( END, cal.getTime() );   
     
  // this month   
  } else if ( range == MONTH ) {   
   cal.set(Calendar.DATE, 1);   
   m.put(BEGIN, cal.getTime());   
      
   int month = cal.get(Calendar.MONTH)+1;   
   switch (month) {   
   case 1:   
   case 3:   
   case 5:   
   case 7:   
   case 8:   
   case 10:   
   case 12:   
    cal.set(Calendar.DATE, 31);   
    break;   
   case 4:   
   case 6:   
   case 9:   
   case 11:   
    cal.set(Calendar.DATE, 30);   
    break;   
   case 2:   
    int year = cal.get(Calendar.YEAR);   
    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {   
     cal.set(Calendar.DATE, 29);   
    } else {   
     cal.set(Calendar.DATE, 28);   
    }   
    break;   
   }   
   m.put(END, cal.getTime());   
      
  //this season    
  } else if ( range == SEASON ) {   
   int year = cal.get(Calendar.YEAR);   
   int month = cal.get(Calendar.MONTH);   
   switch (month / 3) {   
   case 0: //01-01 to 03-31   
    cal.set(Calendar.MONTH,0);   
    cal.set(Calendar.DATE,1);   
    m.put(BEGIN, cal.getTime());   
    cal.set(Calendar.MONTH,2);   
    cal.set(Calendar.DATE,31);   
    m.put(END, cal.getTime());   
    break;   
   case 1: //04-01 to 06-30   
    cal.set(Calendar.MONTH,3);   
    cal.set(Calendar.DATE,1);   
    m.put(BEGIN, cal.getTime());   
    cal.set(Calendar.MONTH,5);   
    cal.set(Calendar.DATE,30);   
    m.put(END, cal.getTime());   
   
    break;   
   case 2: //07-01 to 09-30   
    cal.set(Calendar.MONTH,6);   
    cal.set(Calendar.DATE,1);   
    m.put(BEGIN, cal.getTime());   
    cal.set(Calendar.MONTH,8);   
    cal.set(Calendar.DATE,30);   
    m.put(END, cal.getTime());   
    break;   
   case 3: // 10-01 to 12-31   
    cal.set(Calendar.MONTH,9);   
    cal.set(Calendar.DATE,1);   
    m.put(BEGIN, cal.getTime());   
    cal.set(Calendar.MONTH,11);   
    cal.set(Calendar.DATE,31);   
    m.put(END, cal.getTime());   
    break;   
   }   
      
  // this year   
  } else if ( range == YEAR ) {   
   cal.set(Calendar.MONTH,0);   
   cal.set(Calendar.DATE,1);   
   m.put(BEGIN, cal.getTime());   
   cal.set(Calendar.MONTH,11);   
   cal.set(Calendar.DATE,31);   
   m.put(END, cal.getTime());   
     
  // default is DAY   
  } else {   
   m.put(BEGIN,cal.getTime());   
   m.put(END,cal.getTime());   
  }   
  setTime(m); //optional   
  return m;   
 }   
   
    
 /**  
  * get the specified date range based on ref.  
  * the range may be this week, this month,   
  * this season or this year.  
  *   
  * @param ref:  
  *   the benchmark of the range. a String of "yyyy-mm-dd" format.  
  * @param range:  
  *   the range rounding the ref.   
  *   WEEK, MONTH, SEASON or YEAR.  
  *     
  * @return  
  *   a map with two entries, keyed BEGIN and END,  
  *   whose values are of Date type and indicate   
  *   the begin date and the end date of the range.   
  *   if the input string dosen't match "yyyy-M[M]-d[d]" format,  
  *   null will be returned.  
  */   
 public static Map getDateRange(String ref, int range){   
  if( !ref.matches("\\d{4}-\\d{1,2}-\\d{1,2}") )   
   return null;   
  Calendar cal = Calendar.getInstance();   
  cal.set(Calendar.YEAR, Integer.parseInt( ref.split("-")[0]) );   
  cal.set(Calendar.MONTH, Integer.parseInt( ref.split("-")[1])-1 );   
  cal.set(Calendar.DATE, Integer.parseInt( ref.split("-")[2]) );   
  Date d = cal.getTime();   
  return getDateRange(d, range);   
 }   
    
 /**  
  * convert a Date type variable to String type,   
  * applying the specified format.  
  *   
  * @param d  
  * @param format  
  * @return  
  */   
 public static String dateToStr(Date d, String format){   
  SimpleDateFormat df = (SimpleDateFormat) SimpleDateFormat   
   .getDateInstance();   
  df.applyPattern(format);   
  return df.format(d);   
 }   
    
 /**  
  * convert a Date type variable to String,   
  * applying the "yyyy-MM-dd" format.  
  *   
  * @param d  
  * @return  
  */   
 public static String dateToStr(Date d){   
  return dateToStr(d, "yyyy-MM-dd");   
 }   
    
 /**  
  * convert a String type variable to Date type.   
  * this method only support the "yyyy-MM-dd" format,  
  * if dosen't match, null will be returned.  
  *   
  * @param strDate  
  * @return  
  */   
 public static Date strToDate(String strDate){   
  if( !strDate.matches("\\d{4}-\\d{1,2}-\\d{1,2}") )   
   return null;   
  Calendar cal = Calendar.getInstance();   
  cal.set(Calendar.YEAR, Integer.parseInt(strDate.split("-")[0]) );   
  cal.set(Calendar.MONTH, Integer.parseInt(strDate.split("-")[1])-1 );   
  cal.set(Calendar.DATE, Integer.parseInt(strDate.split("-")[2]) );   
  return cal.getTime();   
 }   
    
 /**  
  * set the begin time of the date range to 00:00:00,  
  * and the end time of the date range to 23:59:59.  
  * @param m:  
  *   the date range map.  
  */   
 private static void setTime(Map m){   
  Calendar cal = Calendar.getInstance();   
  cal.setTime( (Date)m.get(BEGIN) );   
  cal.set(Calendar.HOUR_OF_DAY,0);   
  cal.set(Calendar.MINUTE,0);   
  cal.set(Calendar.SECOND,0);   
  m.put(BEGIN,cal.getTime());   
     
  cal.setTime( (Date)m.get(END) );   
  cal.set(Calendar.HOUR_OF_DAY,23);   
  cal.set(Calendar.MINUTE,59);   
  cal.set(Calendar.SECOND,59);   
  m.put(END,cal.getTime());   
 }   
    
 public static Date stringToDate(String dateString, String dateFormat)
 {
	 DateFormat format = new SimpleDateFormat(dateFormat);
	 Date date = null;
	 try 
	 {
		date =  format.parse(dateString);
	 } 
	 catch (ParseException e) 
	 {
		e.printStackTrace();
	 }
	 return date;
 }
 
 
 public static void main(String args[]){   
  System.out.println( getDateRange(new Date(), WEEK) );   
 }   
}   
   


