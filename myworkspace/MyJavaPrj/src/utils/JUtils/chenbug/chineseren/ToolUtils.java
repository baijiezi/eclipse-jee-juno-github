package utils.JUtils.chenbug.chineseren;
   
/**  
 * <p>Title: ToolUtils</p>  
 * <p>Description: ������һЩ����Ҫ�õ��Ĺ��߷���</p>  
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
  //��������Collectionת����Vector   
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
      //Vector�д��Vector   
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
   
  //�ж�Collection�Ƿ�Ϊnull || isEmpty   
  public static boolean isBlank(Collection collection){   
    if(null == collection || collection.isEmpty()){   
      return true;   
    }else   
      return false;   
  }   
   
  /**  
   * ��map��valuesת����list  
   * @param map Ҫת����map  
   * @return list  
   */   
  public static List mapToList(Map map) {   
    if(null == map) {   
      return null;   
    }   
    return Arrays.asList(map.values().toArray());   
  }   
   
  /**  
   * ��������ַ���������tag�ַ������ӣ�������  
   * @param strAry - �������ַ���������  
   * @param tag - ���ӱ��  
   * @return String ���Ӻõ�String  
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
   * �������HashMap�����еļ�ֵ����tag���ӣ�����  
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
   * �������HashMap�еļ�ֵ,����vKeys�ļ�����tag���ӣ�����  
   * @param hashMap HashMap  
   * @param vKeys Ҫ��ѯ�������ӵ�keys  
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
   * �������Vector�����еļ�ֵ����tag���ӣ�����  
   * @param v Vector  
   * @param tag ���ӷ�  
   * @return ���Ӻõ�String  
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
   * source HashMap��ȥsubMap HashMap  
   * @param source ����HashMap  
   * @param subMap ��ȥHashMap  
   * @return ���HashMap  
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
   * �ж�str�Ƿ���src String[]��  
   * @param src ԴString[]  
   * @param str Ҫ�жϵ�String  
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
   * ��Vectorת����String[]  
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
   * ��java.sql.Blobת����byte[]  
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
      Debug.error("ת��java.sql.Blob��BYTE�쳣!", e);   
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
   * ��java.sql.Clobת����String  
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
      throw new Exception("ת��java.sql.Clob��String�쳣!");   
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
   * ��Stringת����boolean  
   * @param str String  
   * @return boolean  
   */   
  public static boolean booleanValue(String str) {   
    return Boolean.valueOf(str).booleanValue();   
  }   
   
  /**  
   * ��ӡ��List�����е�Ԫ��,���ڵ���  
   * @param list List  
   */   
  public static void printList(List list) {   
    if(null == list) {   
      return;   
    }   
    ToolUtils.printArray(list.toArray());   
  }   
   
  /**  
   * ��ӡ��Array�����е�Ԫ��,���ڵ���  
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
   * ����strUrl,���ӻ�÷��صĽ��HTML String  
   * @param strUrl String Ŀ��URL  
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
   * �����ߣ�����ΰ �ı�����:(  
   * ����޸����ڣ�(2001-8-3 15:26:01)  
   * ���ܣ���һ�������Ż���Ϊsql�����ʶ�ķ���"''"  
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
   
  //double��������ȡС����λ��   
  public static String  getDoubleByScale(double doubleData,int scale){   
    //BigDecimal bigData=new BigDecimal(String.valueOf(doubleData));   
    //bigData = bigData.divide(new BigDecimal("1"),scale, BigDecimal.ROUND_HALF_UP);   
    //return bigData.doubleValue();   
    String strFormator = "#." + org.apache.commons.lang.StringUtils.repeat("#",scale);   
    java.text.DecimalFormat formater = new java.text.DecimalFormat(strFormator);   
    String newData = formater.format(doubleData);   
    return newData;   
  }   
     
  //������ʽ�ж�(���Դ�Сд)   
  public static boolean isMatch(String target,String regex) {   
    PatternCompiler compiler = new Perl5Compiler();   
    Pattern pattern = null;   
    try {   
      pattern = compiler.compile(regex, Perl5Compiler.CASE_INSENSITIVE_MASK);   
    } catch (Exception e) {   
      Debug.warn("������ʽ��֤�쳣!!",e);   
      return false;   
    }   
    PatternMatcher patternMatcher = new Perl5Matcher();   
    return patternMatcher.matches(target, pattern);   
  }   
}  


