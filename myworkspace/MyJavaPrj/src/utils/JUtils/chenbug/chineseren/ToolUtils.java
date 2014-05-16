package utils.JUtils.chenbug.chineseren;
   
/**  
 * <p>Title: ToolUtils</p>  
 * <p>Description: 其它的一些经常要用到的工具方法</p>  
 * <p>Copyright: Copyright (c) 2004</p>  
 * <p>Company: pubinfo</p>  
 * @author chineseren  
 * @version 1.0  
 */   
import java.io.*;   
import java.util.*;   
import java.net.*;   
import java.sql.*;   
import java.math.BigDecimal;   
   
import org.apache.commons.beanutils.*;   
import org.apache.commons.lang.*;   
   
public class ToolUtils {   
  //将其它的Collection转化成Vector   
  public static Vector convertToVector(Object[] anArray){   
    if(null == anArray){   
      return null;   
    }   
    Vector vc = new Vector();   
    for (int i = 0; i  anArray.length; i++) {   
      vc.add(i,anArray[i]);   
    }   
    return vc;   
  }   
  public static Vector convertToVector(Object[][] anArray){   
    if(null == anArray){   
      return null;   
    }   
    Vector vc = new Vector();   
    for (int i = 0; i  anArray.length; i++) {   
      //Vector中存放Vector   
      vc.add(i,convertToVector(anArray[i]));   
    }   
    return vc;   
  }   
  public static Vector convertToVector(List anList){   
    if(null == anList){   
      return null;   
    }   
    return convertToVector(anList.toArray());   
  }   
   
  //判断Collection是否为null || isEmpty   
  public static boolean isBlank(Collection collection){   
    if(null == collection || collection.isEmpty()){   
      return true;   
    }else   
      return false;   
  }   
   
  /**  
   * 将map的values转化成list  
   * @param map 要转化的map  
   * @return list  
   */   
  public static List mapToList(Map map) {   
    if(null == map) {   
      return null;   
    }   
    return Arrays.asList(map.values().toArray());   
  }   
   
  /**  
   * 将传入的字符串数组用tag字符串连接，并返回  
   * @param strAry - 待连接字符串的数组  
   * @param tag - 连接标记  
   * @return String 连接好的String  
   */   
  public static String complexString(String[] strAry, String tag) {   
    if(strAry == null || strAry.length == 0) {   
      return "";   
    }   
    tag = (tag == null ? "" : tag);   
    StringBuffer retBuffer = new StringBuffer();   
    int i;   
    for(i = 0; i  strAry.length - 1; i++) {   
      retBuffer.append(strAry[i]);   
      retBuffer.append(tag);   
    }   
    retBuffer.append(strAry[i]);   
    return retBuffer.toString();   
  }   
   
  /**  
   * 将传入的HashMap中所有的键值对用tag连接，返回  
   * @param hashMap HashMap  
   * @param tag String  
   * @return String[] String[] {complexKeys, complexValues}  
   */   
  public static String[] complexString(Map hashMap, String tag) {   
   
    if(hashMap == null || hashMap.size() == 0) {   
      return new String[] {   
          "", ""};   
    }   
    tag = (tag == null ? "" : tag);   
    String[] keys = new String[hashMap.size()];   
    String[] values = new String[hashMap.size()];   
    Iterator ite = hashMap.keySet().iterator();   
    int index = 0;   
    while(ite.hasNext()) {   
      String key = ite.next().toString();   
      keys[index] = key;   
      values[index++] = hashMap.get(key).toString();   
    }   
    String complexKeys = complexString(keys, tag);   
    String complexValues = complexString(values, tag);   
    return new String[] {   
        complexKeys, complexValues};   
  }   
   
  /**  
   * 将传入的HashMap中的键值,根据vKeys的键，用tag连接，返回  
   * @param hashMap HashMap  
   * @param vKeys 要查询进行连接的keys  
   * @param tag String  
   * @return String[] String[] {complexKeys, complexValues}  
   */   
  public static String[] complexString(Vector vKeys, Map hashMap,   
      String tag) {   
    if(vKeys == null || vKeys.size() == 0 || hashMap == null ||   
        hashMap.size() == 0) {   
      return new String[] {   
          "", ""};   
    }   
    tag = (tag == null ? "" : tag);   
    StringBuffer keysBuf = new StringBuffer();   
    StringBuffer valuesBuf = new StringBuffer();   
    for(int i = 0; i  vKeys.size(); i++) {   
      String key = vKeys.get(i).toString();   
      if(!hashMap.containsKey(key)) {   
        continue;   
      }   
      keysBuf.append(key);   
      keysBuf.append(tag);   
      valuesBuf.append(hashMap.get(key).toString());   
      valuesBuf.append(tag);   
    }   
    return new String[] {   
        keysBuf.toString(), valuesBuf.toString()};   
  }   
   
  /**  
   * 将传入的Vector中所有的键值对用tag连接，返回  
   * @param v Vector  
   * @param tag 连接符  
   * @return 连接好的String  
   */   
  public static String complexString(Vector v, String tag) {   
    if(v == null || v.size() == 0) {   
      return "";   
    }   
    tag = (tag == null ? "" : tag);   
    StringBuffer retBuffer = new StringBuffer();   
    int i;   
    for(i = 0; i  v.size() - 1; i++) {   
      retBuffer.append(v.get(i).toString());   
      retBuffer.append(",");   
    }   
    retBuffer.append(v.get(i).toString());   
    return retBuffer.toString();   
  }   
   
  /**  
   * source HashMap减去subMap HashMap  
   * @param source 被减HashMap  
   * @param subMap 减去HashMap  
   * @return 结果HashMap  
   */   
  public static Map subHashMap(Map source, Map subMap) {   
    if(null == source || null == subMap) {   
      return source;   
    }   
   
    Iterator keys = subMap.keySet().iterator();   
    while(keys.hasNext()) {   
      source.remove(keys.next());   
    }   
    return source;   
  }   
   
  /**  
   * 判断str是否在src String[]中  
   * @param src 源String[]  
   * @param str 要判断的String  
   * @return boolean  
   */   
  public static boolean isContain(String[] src, String str) {   
    if(null == src) {   
      return false;   
    }   
   
    for(int i = 0; i  src.length; i++) {   
      if(str.equalsIgnoreCase(src[i])) {   
        return true;   
      }   
    }   
    return false;   
  }   
   
  /**  
   * 将Vector转化成String[]  
   * @param v Vector  
   * @return String[]  
   */   
  public static String[] vecotrToStrArray(Vector v) {   
    if(v == null) {   
      return null;   
    }   
    String[] ret = new String[v.size()];   
    for(int i = 0; i  v.size(); i++) {   
      ret[i] = v.get(i).toString();   
    }   
    return ret;   
  }   
   
  /**  
   * 将java.sql.Blob转化成byte[]  
   * @param blob java.sql.Blob  
   * @return byte[]  
   */   
  public static byte[] blobToByte(Blob blob) {   
    ByteArrayOutputStream bos = new ByteArrayOutputStream();   
    InputStream is = null;   
    try {   
      is = blob.getBinaryStream();   
      byte[] bRead = new byte[1024];   
      int iRead;   
      while((iRead = is.read(bRead, 0, 1024)) != -1) {   
        bos.write(bRead, 0, iRead);   
      }   
      return bos.toByteArray();   
    } catch(Exception e) {   
      Debug.error("转换java.sql.Blob到BYTE异常!", e);   
      return bos.toByteArray();   
    } finally {   
      try {   
        bos.close();   
      } catch(IOException e) {}   
      try {   
        is.close();   
      } catch(IOException e) {}   
    }   
  }   
   
  /**  
   * 将java.sql.Clob转化成String  
   * @param clob java.sql.Clob  
   * @return String  
   * @throws Exception  
   */   
  public static String clob2String(Clob clob) throws Exception {   
    StringWriter writer = new StringWriter();   
    Reader reader = null;   
    try {   
      reader = clob.getCharacterStream();   
      char[] cRead = new char[1024];   
      int iRead;   
      while((iRead = reader.read(cRead, 0, 1024)) != -1) {   
        writer.write(cRead, 0, iRead);   
      }   
      return writer.getBuffer().toString();   
    } catch(Exception e) {   
      Debug.error(e, e);   
      throw new Exception("转换java.sql.Clob到String异常!");   
    } finally {   
      try {   
        writer.close();   
      } catch(IOException e) {}   
      try {   
        reader.close();   
      } catch(IOException e) {}   
    }   
  }   
   
  /**  
   * 将String转化成boolean  
   * @param str String  
   * @return boolean  
   */   
  public static boolean booleanValue(String str) {   
    return Boolean.valueOf(str).booleanValue();   
  }   
   
  /**  
   * 打印出List中所有的元素,用于调试  
   * @param list List  
   */   
  public static void printList(List list) {   
    if(null == list) {   
      return;   
    }   
    ToolUtils.printArray(list.toArray());   
  }   
   
  /**  
   * 打印出Array中所有的元素,用于调试  
   * @param array Array  
   */   
  public static void printArray(Object[] array) {   
    if(null == array) {   
      return;   
    }   
    Debug.debug("Bengin ToolUtils.printArray!");   
   
    Object obj;   
    for(int i = 0; i  array.length; i++) {   
      try {   
        obj = array[i];   
        Debug.debug(obj.toString());   
      } catch(Exception ex) {   
        Debug.debug("ToolUtils.printArray ERROR in: " + i, ex);   
      }   
    }   
   
    Debug.debug("End ToolUtils.printArray!");   
  }   
   
  /**  
   * 根据strUrl,连接获得返回的结果HTML String  
   * @param strUrl String 目标URL  
   * @return HTML String  
   */   
  public static String getHtmlByURL(String strUrl) {   
    StringBuffer html = new StringBuffer();   
    int HttpResult = -1;   
    URLConnection urlconn = null;   
    try {   
      URL url = new URL(strUrl);   
      urlconn = url.openConnection();   
      urlconn.connect();   
      HttpURLConnection httpconn = (HttpURLConnection)urlconn;   
      HttpResult = httpconn.getResponseCode();   
    } catch(IOException ex) {   
      Debug.debug("IOException when connecting to URL: " + strUrl);   
    }   
   
    if(HttpResult != HttpURLConnection.HTTP_OK) {   
      Debug.info("Sorry! Can not connect Url " + strUrl);   
    } else {   
      try {   
        BufferedReader reader = new BufferedReader(new InputStreamReader(   
            urlconn.getInputStream()));   
        String line = null;   
        while((line = reader.readLine()) != null)   
          html.append(line + "\n");   
        reader.close();   
      } catch(MalformedURLException e) {   
        Debug.debug("Unable to connect to URL: " + strUrl);   
      } catch(IOException e) {   
        Debug.debug("IOException when connecting to URL: " + strUrl);   
      }   
    }   
   
    return html.toString();   
  }   
   
  /**  
   * 开发者：王亚伟 的笨方法:(  
   * 最后修改日期：(2001-8-3 15:26:01)  
   * 功能：把一个单引号换算为sql语句认识的符号"''"  
   * @param p_in SQL String  
   * @return String SQL  
   */   
  public static String formatSql(String p_in) {   
    if(p_in == null) {   
      return null;   
    }   
   
    int i;   
    StringBuffer buffer = new StringBuffer("");   
    int intBegin, intPosition;   
    intBegin = 0;   
   
    intPosition = p_in.indexOf("'", intBegin);   
    while(intPosition > -1) {   
      buffer.append(p_in.substring(intBegin, intPosition + 1));   
      buffer.append("'");   
      intBegin = intPosition + 1;   
      intPosition = p_in.indexOf("'", intBegin);   
    }   
    if(p_in.length() > intBegin)   
      buffer.append(p_in.substring(intBegin, p_in.length()));   
    return buffer.toString();   
  }   
   
  //double数据类型取小数点位数   
  public static String  getDoubleByScale(double doubleData,int scale){   
    //BigDecimal bigData=new BigDecimal(String.valueOf(doubleData));   
    //bigData = bigData.divide(new BigDecimal("1"),scale, BigDecimal.ROUND_HALF_UP);   
    //return bigData.doubleValue();   
    String strFormator = "#." + org.apache.commons.lang.StringUtils.repeat("#",scale);   
    java.text.DecimalFormat formater = new java.text.DecimalFormat(strFormator);   
    String newData = formater.format(doubleData);   
    return newData;   
  }   
     
  //正则表达式判断(忽略大小写)   
  public static boolean isMatch(String target,String regex) {   
    PatternCompiler compiler = new Perl5Compiler();   
    Pattern pattern = null;   
    try {   
      pattern = compiler.compile(regex, Perl5Compiler.CASE_INSENSITIVE_MASK);   
    } catch (Exception e) {   
      Debug.warn("正则表达式验证异常!!",e);   
      return false;   
    }   
    PatternMatcher patternMatcher = new Perl5Matcher();   
    return patternMatcher.matches(target, pattern);   
  }   
}  


