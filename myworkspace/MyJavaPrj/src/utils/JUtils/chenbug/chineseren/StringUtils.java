package utils.JUtils.chenbug.chineseren;
   
/**  
 * <p>Title: string utility</p>  
 * <p>Description: 字符串工具类</p>  
 * <p>Copyright: Copyright (c) 2003</p>  
 * <p>Company: www.pubinfo.com.cn</p>  
 * @author wjren  
 * @version 1.0  
 */   
import java.security.MessageDigest;   
import java.security.NoSuchAlgorithmException;   
import java.text.DateFormat;   
import java.text.ParseException;   
import java.text.SimpleDateFormat;   
import java.util.*;   
import java.io.*;   
import java.math.BigDecimal;   
public class StringUtils {   
    /**  
     * 辅助方法(以后转移到utils工具类里)，String charSet转化  
     * @return  
     */   
    public static String toChinese(String str) {   
        if (str == null || str.length() == 0) {   
            return "";   
        }   
        try {   
            return new String(str.getBytes("ISO8859_1"), "GBK");   
        } catch (UnsupportedEncodingException ex) {   
            return str;   
        }   
    }   
   
  //判断字符串是否null或者""   
  public static boolean isBlank(String src){   
    if(null == src || "".equals(src.trim())){   
      return true;   
    }   
    return false;   
  }   
   
  //把没有实例化的串转化为空串   
  public static String convertNull(String p_in) {   
    if(p_in == null) {   
      return "";   
    } else {   
      return p_in;   
    }   
  }   
   
  //控制页面显示长度,dataStr为控制长度的字符串 length为字节的长度   
  //字符串的字节长度小于显示长度，则原样输出，否则以XXX...的形式输出   
  public static String controlLength(String dataStr, int length) {   
    if(dataStr == null) {   
      return "";   
    }   
    //转化成格式化的Html   
    dataStr = StringUtils.escape(dataStr);   
   
    StringBuffer sb = new StringBuffer();   
    char datach;   
    int lengthi = 0; //字符串的字节长度   
    int strj = 0; //字符串的实际长度   
    int lengthi3 = 0; //字节长度-4   
    //得到字符串的字节长度   
    while(strj  dataStr.length()) {   
      datach = dataStr.charAt(strj++);   
      if(((datach = 'z') && (datach >= 'a'))   
          || ((datach = 'Z') && (datach >= 'A'))   
          || ((datach = '9') && (datach >= '0'))) {   
        lengthi++;   
      } else {   
        lengthi = lengthi + 2;   
      }   
    }   
    strj = 0;   
    //得到字符串的字节长度-4   
    while((lengthi3  length - 4) && (strj  dataStr.length())) {   
      datach = dataStr.charAt(strj++);   
      sb.append(datach);   
      if(((datach = 'z') && (datach >= 'a'))   
          || ((datach = 'Z') && (datach >= 'A'))   
          || ((datach = '9') && (datach >= '0'))) {   
        lengthi3++;   
      } else {   
        lengthi3 = lengthi3 + 2;   
      }   
    }   
    if(lengthi = length) {   
      return dataStr;   
    } else {   
      sb.append("...");   
    }   
    return sb.toString();   
  }   
   
  /**  
   * 把字符串转化成HTML字符串  
   * @param  str      --需要转换的字符串  
   * @return String   --返回把特殊字符转换了的字符串  
   */   
  public static String escape(String str) {   
    if(str == null) {   
      return "";   
    }   
   
    byte[] data = str.getBytes();   
    int len = data.length;   
    StringBuffer result = new StringBuffer(len * 2);   
   
    int begin = 0, count = 0, tt = 0;   
    for(int i = 0; i  len; i++) {   
      switch((char)data[i]) {   
        case '&':   
          result.append(new String(data, begin, count));   
          result.append("&");   
          begin = i + 1;   
          count = 0;   
          break;   
        case '\"':   
          result.append(new String(data, begin, count));   
          result.append(""");   
          begin = i + 1;   
          count = 0;   
          break;   
        case '<':   
          result.append(new String(data, begin, count));   
          result.append("<");   
          begin = i + 1;   
          count = 0;   
          break;   
        case '>':   
          result.append(new String(data, begin, count));   
          result.append(">");   
          begin = i + 1;   
          count = 0;   
          break;   
        case '\n':   
          result.append(new String(data, begin, count));   
          result.append("<br>");   
          begin = i + 1;   
          count = 0;   
          break;   
        case '$':   
          result.append(new String(data, begin, count));   
          result.append("$$");   
          begin = i + 1;   
          count = 0;   
          break;   
        case ' ':   
          result.append(new String(data, begin, count));   
          result.append(" ");   
          begin = i + 1;   
          count = 0;   
          break;   
        default:   
          count++;   
          break;   
      }   
    }   
    if(count > 0) {   
      result.append(new String(data, begin, count));   
    }   
    return result.toString();   
  }   
   
  //全数字判断,参照字符串strRef可以是:"0123456789","23546"或"0123"等等   
  public static boolean isNumberString(String strIn) {   
    return isNumberString(strIn, "0123456789");   
  }   
  public static boolean isNumberString(String strIn, String strRef) {   
    if(strIn == null || strIn.length() == 0)   
      return(false);   
    for(int i = 0; i  strIn.length(); i++) {   
      String strTmp = strIn.substring(i, i + 1);   
      if(strRef.indexOf(strTmp, 0) == -1)   
        return(false);   
    }   
    return(true);   
  }   
   
  /**  
   * 字符串替换,调用了org.apache.commons.lang.StringUtils.replace()  
   * @param strObj 目标字符串  
   * @param str1 被替换的字符串  
   * @param str2 替换成的字符串  
   * @return String  
   */   
  public static String replace(String strObj, String str1, String str2) {   
    /*if(strObj == null || str1 == null || str2 == null){  
      return null;  
        }*/   
    if("".equals(str1)) {   
      return strObj;   
    }   
   
    return org.apache.commons.lang.StringUtils.replace(strObj, str1, str2);   
  }   
   
  /**  
   * 字符串切割,比如"#sd#kf##dkf##Ej#"按照"#"切割之后length=8,即"#"出现的次数加一  
   * @param strObj 目标字符串  
   * @param str1 用于切割的字符串  
   * @return String[]  
   */   
  public static String[] split(String strObj, String str1) {   
    if(strObj == null) {   
      return null;   
    }   
    if("".equals(strObj) || "".equals(str1)) {   
      return new String[] {   
          strObj};   
    }   
   
    int count = org.apache.commons.lang.StringUtils.countMatches(strObj,   
        str1) + 1; //调用org.apache.commons   
    int length = str1.length();   
    String[] strs = new String[count];   
    int posPre = 0 - length;   
    int pos = 0;   
    for(int i = 0; i  count - 1; i++) {   
      pos = strObj.indexOf(str1, posPre + length);   
      strs[i] = strObj.substring(posPre + length, pos);   
      posPre = pos;   
    }   
    strs[count - 1] = strObj.substring(posPre + length);   
   
    return strs;   
  }   
   
   
  //用于转义单斜杠特殊字符   
  public static final String appendStr(String str) {   
    char c = ' ';   
    StringBuffer sb = new StringBuffer();   
    for(int i = 0; i  str.length(); i++) {   
      c = str.charAt(i);   
      if(c == '\\') {   
        sb.append("\\\\");   
      } else {   
        sb.append(c);   
      }   
    }   
   
    return sb.toString();   
   
  }   
   
  /**  
   * 将reader流中的数据变成String  
   * @param is reader流  
   * @return String  
   * @throws IOException  
   */   
  public static String readerToString(Reader is) throws IOException {   
    StringBuffer sb = new StringBuffer();   
    char[] b = new char[8192];   
    int n;   
   
    while((n = is.read(b)) > 0) {   
      sb.append(b, 0, n);   
    }   
   
    return sb.toString();   
  }   
   
  public static String inputStreamToString(InputStream is) throws IOException {   
    return readerToString(new InputStreamReader(is));   
  }   
   
  //test for StringUtil   
  public static void main(String[] args) {   
    //test   
  }   
}  


