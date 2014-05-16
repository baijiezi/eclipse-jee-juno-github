package utils.JUtils.chenbug.chineseren;
   
/**  
 * <p>Title: string utility</p>  
 * <p>Description: �ַ���������</p>  
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
     * ��������(�Ժ�ת�Ƶ�utils��������)��String charSetת��  
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
   
  //�ж��ַ����Ƿ�null����""   
  public static boolean isBlank(String src){   
    if(null == src || "".equals(src.trim())){   
      return true;   
    }   
    return false;   
  }   
   
  //��û��ʵ�����Ĵ�ת��Ϊ�մ�   
  public static String convertNull(String p_in) {   
    if(p_in == null) {   
      return "";   
    } else {   
      return p_in;   
    }   
  }   
   
  //����ҳ����ʾ����,dataStrΪ���Ƴ��ȵ��ַ��� lengthΪ�ֽڵĳ���   
  //�ַ������ֽڳ���С����ʾ���ȣ���ԭ�������������XXX...����ʽ���   
  public static String controlLength(String dataStr, int length) {   
    if(dataStr == null) {   
      return "";   
    }   
    //ת���ɸ�ʽ����Html   
    dataStr = StringUtils.escape(dataStr);   
   
    StringBuffer sb = new StringBuffer();   
    char datach;   
    int lengthi = 0; //�ַ������ֽڳ���   
    int strj = 0; //�ַ�����ʵ�ʳ���   
    int lengthi3 = 0; //�ֽڳ���-4   
    //�õ��ַ������ֽڳ���   
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
    //�õ��ַ������ֽڳ���-4   
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
   * ���ַ���ת����HTML�ַ���  
   * @param  str      --��Ҫת�����ַ���  
   * @return String   --���ذ������ַ�ת���˵��ַ���  
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
   
  //ȫ�����ж�,�����ַ���strRef������:"0123456789","23546"��"0123"�ȵ�   
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
   * �ַ����滻,������org.apache.commons.lang.StringUtils.replace()  
   * @param strObj Ŀ���ַ���  
   * @param str1 ���滻���ַ���  
   * @param str2 �滻�ɵ��ַ���  
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
   * �ַ����и�,����"#sd#kf##dkf##Ej#"����"#"�и�֮��length=8,��"#"���ֵĴ�����һ  
   * @param strObj Ŀ���ַ���  
   * @param str1 �����и���ַ���  
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
        str1) + 1; //����org.apache.commons   
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
   
   
  //����ת�嵥б�������ַ�   
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
   * ��reader���е����ݱ��String  
   * @param is reader��  
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


