package utils.JUtils.chenbug;

import java.util.*;   
import org.apache.commons.lang.*;   
   
/**  
 *  
 * <p>Title: JSTools</p>  
 * <p>Description: JavaScript工具类</p>  
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
   * 显示一个alert框，在用户点击确定按钮后，如果url不为空，则页面转向url  
   *  
   * @param message - alert所显示的信息  
   * @param url - 用户点击确定按钮后，转向的新页面URL  
   * 如果不需要转向，则设置为null  
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
   * 将java的键值对转换成JS的Select的Options  
   *  
   * @param options  
   *  - 待Option的数据，键值对  
   * @param defaultValue - 默认选中值  
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
   * 将java数组转换为js数组  
   *  
   * @param values - java数组  
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



