package utils.JUtils.chenbug;

import java.util.*;   
import org.apache.commons.lang.*;   
   
/**  
 *  
 * <p>Title: JSTools</p>  
 * <p>Description: JavaScript������</p>  
 * <p>Copyright: Copyright (c) 2003</p>  
 * <p>Company: </p>  
 * @author not attributable  
 * @version 1.0  
 */   
public class JSTools {   
   
  /**  
   * @roseuid 3FBE423D00B5  
   */   
  public JSTools() {   
   
  }   
   
  /**  
   * ��ʾһ��alert�����û����ȷ����ť�����url��Ϊ�գ���ҳ��ת��url  
   *  
   * @param message - alert����ʾ����Ϣ  
   * @param url - �û����ȷ����ť��ת�����ҳ��URL  
   * �������Ҫת��������Ϊnull  
   * @return String  
   * @roseuid 3FBE2EB0004A  
   */   
  public static String genAlert(String message, String address, boolean isUrl) {   
    StringBuffer retBuffer = new StringBuffer();   
    retBuffer.append("<script language=javascript>");   
    retBuffer.append("alert('");   
    retBuffer.append(message);   
    retBuffer.append("');");   
    if (isUrl && address != null) {   
      retBuffer.append("window.location='");   
      retBuffer.append(address);   
      retBuffer.append("';");   
    }   
    else {   
      retBuffer.append(" " + address + ";");   
    }   
    retBuffer.append("</script>");   
    return retBuffer.toString();   
  }   
   
  public static String genAlert2(String message, String address, boolean isUrl) {   
    StringBuffer retBuffer = new StringBuffer();   
    retBuffer.append("<script language=javascript>");   
    retBuffer.append("alert('");   
    retBuffer.append(message);   
    retBuffer.append("');");   
    if (isUrl && address != null) {   
      retBuffer.append("window.parent.location='");   
      retBuffer.append(address);   
      retBuffer.append("';");   
    }   
    else {   
      retBuffer.append(" " + address + ";");   
    }   
    retBuffer.append("</script>");   
    return retBuffer.toString();   
  }   
   
  public static String genClose(String message) {   
    StringBuffer retBuffer = new StringBuffer();   
    retBuffer.append("<script language=javascript>");   
    if (message != null) {   
      retBuffer.append("alert('");   
      retBuffer.append(message);   
      retBuffer.append("');");   
    }   
    retBuffer.append("window.close();");   
    retBuffer.append("</script>");   
    return retBuffer.toString();   
  }   
   
  public static String genClose2(String exec, String message) {   
    StringBuffer retBuffer = new StringBuffer();   
    retBuffer.append("<script language=javascript>");   
    if (message != null) {   
      retBuffer.append("alert('");   
      retBuffer.append(message);   
      retBuffer.append("');");   
    }   
    retBuffer.append(exec);   
    retBuffer.append(";window.close();");   
    retBuffer.append("</script>");   
    return retBuffer.toString();   
   
  }   
   
  public static String genBack() {   
    StringBuffer retBuffer = new StringBuffer();   
    retBuffer.append("<script language=javascript>");   
    retBuffer.append("history.go(-1);");   
    retBuffer.append("</script>");   
    return retBuffer.toString();   
  }   
   
  public static String genBack(String message) {   
    StringBuffer retBuffer = new StringBuffer();   
    retBuffer.append("<script language=javascript>");   
    if (message != null) {   
      retBuffer.append("alert('" + StringUtils.replace(message, "'", "\\\'") +   
                       "');");   
    }   
    retBuffer.append("history.go(-1);");   
    retBuffer.append("</script>");   
    return retBuffer.toString();   
  }   
   
  /**  
   * ��java�ļ�ֵ��ת����JS��Select��Options  
   *  
   * @param options  
   *  - ��Option�����ݣ���ֵ��  
   * @param defaultValue - Ĭ��ѡ��ֵ  
   * @return String  
   * @roseuid 3FBE2EBA0120  
   */   
  public static String genOptions(Map options, String defaultValue) {   
   
    if (options == null || options.size() == 0) {   
      return "";   
    }   
   
    Iterator keys = options.keySet().iterator();   
    String curKey;   
    StringBuffer retBuffer = new StringBuffer();   
    while (keys.hasNext()) {   
      curKey = (String) keys.next();   
      retBuffer.append("<option value=\"");   
      retBuffer.append(curKey);   
      retBuffer.append("\"");   
      if (defaultValue != null && defaultValue.equals(curKey)) {   
        retBuffer.append(" selected ");   
      }   
      retBuffer.append(">");   
      retBuffer.append(options.get(curKey));   
      retBuffer.append("</option>");   
    }   
   
    return retBuffer.toString();   
  }   
   
  public static String genOptions(Vector options, String defaultValue) {   
   
    if (options == null || options.size() == 0) {   
      return "";   
    }   
   
    StringBuffer retBuffer = new StringBuffer();   
    for (int i = 0; i<options.size(); i++) {   
      retBuffer.append("<option value=\"");   
      retBuffer.append(options.get(i).toString());   
      retBuffer.append("\"");   
      if (defaultValue != null && defaultValue.equals(options.get(i).toString())) {   
        retBuffer.append(" selected ");   
      }   
      retBuffer.append(">");   
      retBuffer.append(options.get(i).toString());   
      retBuffer.append("</option>");   
    }   
    return retBuffer.toString();   
  }   
   
  /**  
   * ��java����ת��Ϊjs����  
   *  
   * @param values - java����  
   * @return String  
   * @roseuid 3FBE2EC50112  
   */   
  public static String genArray(String aryName, String[] values) {   
   
    if (values == null || values.length == 0) {   
      return "";   
    }   
   
    StringBuffer retBuffer = new StringBuffer();   
    retBuffer.append("var ");   
    retBuffer.append(aryName);   
    retBuffer.append("=[");   
    for (int i = 0; i<values.length - 1; i++) {   
      retBuffer.append("'");   
      retBuffer.append(values[i]);   
      retBuffer.append("',");   
    }   
    retBuffer.append("'");   
    retBuffer.append(values[values.length - 1]);   
    retBuffer.append("'];");   
   
    return retBuffer.toString();   
  }   
   
}   



