package utils.JUtils.chenbug;

import java.io.*;   
import java.util.*;   
import org.apache.commons.beanutils.*;   
import org.apache.commons.lang.*;   
import com.basicdb.*;   
   
/**  
 * <p>Title:公共方法的工具类 </p>  
 * <p>Description: 工具类</p>  
 * <p>Copyright: Copyright (c) 2003</p>  
 * <p>Company: wadin</p>  
 * @author chenbug  
 * @version 1.0  
 */   
public class Tools {   
   
  public static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(   
      Tools.class);   
   
  private static final int RESOURCE_RANDOM_LENGTH = 32;   
   
  /**  
   * 初始化操作日志  
  
     static {  
    PropertyConfigurator pf = new PropertyConfigurator();  
    pf.configure("c:/log4j.properties");  
     } */   
   
  /**  
   * 将obj转换成String  
   * @param  o  Object  
   * @param obj  
   * @return    String  
   * @roseuid 3FBE26DE022C  
   */   
  public static String objToString(Object obj) {   
    if (obj == null) {   
      return "";   
    }   
    else {   
      return obj.toString();   
    }   
  }   
   
  /**  
   * 获取文件的字节编码  
   * @param filePath 文件全路径  
   *  - 文件路径  
   * @return 文件内容的字节编码  
   * @roseuid 3FBE26DE027D  
   */   
  public static byte[] getFileAsBytes(String filePath) {   
   
    if (filePath == null) {   
      return null;   
    }   
   
    File tmpFile = new File(filePath);   
    if (tmpFile == null) {   
      return null;   
    }   
   
    byte[] retBuffer = new byte[ (int) tmpFile.length()];   
    FileInputStream fis = null;   
    try {   
      fis = new FileInputStream(filePath);   
      fis.read(retBuffer);   
      fis.close();   
      return retBuffer;   
    }   
    catch (IOException e) {   
      return null;   
    }   
  }   
   
  public static String getFileAsString(String filePath) {   
   
    if (filePath == null) {   
      return null;   
    }   
   
    File tmpFile = new File(filePath);   
    if (tmpFile == null) {   
      return null;   
    }   
   
    char[] buffer = new char[1024];   
    StringWriter writer = new StringWriter();   
    FileReader fr = null;   
    try {   
      fr = new FileReader(filePath);   
      int iRead = -1;   
      while ((iRead = fr.read(buffer, 0 , 1024)) !=-1) {   
        writer.write(buffer, 0, iRead);   
      }   
      fr.close();   
      writer.close();   
      return writer.toString();   
    }   
    catch (IOException e) {   
      return null;   
    }   
  }   
   
  public static void saveBytesAsFile(String fullFilePath, byte[] content) {   
   
    if (fullFilePath == null || content == null) {   
      return;   
    }   
   
    File f = new File(getDir(fullFilePath));   
    if (f == null || !f.exists()) {   
      f.mkdirs();   
   
    }   
    try {   
      FileOutputStream fos = new FileOutputStream(fullFilePath);   
      fos.write(content);   
      fos.close();   
    }   
    catch (Exception e) {   
      Tools.log.error("写入文件异常:" + e.toString());   
    }   
  }   
   
  public static void saveStringAsFile(String fullFilePath, String content) {   
   
    if (fullFilePath == null) {   
       Tools.log.warn("路径为空");   
      return;   
    }   
    if (content == null) {   
      Tools.log.warn("内容为空");   
       return;   
    }   
   
    File f = new File(getDir(fullFilePath));   
    if (f == null || !f.exists()) {   
      f.mkdirs();   
   
    }   
    try {   
      FileWriter fos = new FileWriter(fullFilePath);   
      fos.write(content);   
      fos.close();   
    }   
    catch (Exception e) {   
      Tools.log.error("写入文件异常:" + e.toString());   
    }   
  }   
   
  /**  
   * 根据传入的文件全路径，返回文件所在路径  
   * @param fullPath 文件全路径  
   * @return 文件所在路径  
   * @roseuid 3FBE26DE029A  
   */   
  public static String getDir(String fullPath) {   
    int iPos1 = fullPath.lastIndexOf("/");   
    int iPos2 = fullPath.lastIndexOf("\\");   
    iPos1 = (iPos1 > iPos2 ? iPos1 : iPos2);   
    return fullPath.substring(0, iPos1 + 1);   
  }   
   
  /**  
   * 根据传入的文件全路径，返回文件全名（包括后缀名）  
   * @param fullPath 文件全路径  
   * @return 文件全名（包括后缀名）  
   * @roseuid 3FBE26DE02AE  
   */   
  public static String getFileName(String fullPath) {   
    int iPos1 = fullPath.lastIndexOf("/");   
    int iPos2 = fullPath.lastIndexOf("\\");   
    iPos1 = (iPos1 > iPos2 ? iPos1 : iPos2);   
    return fullPath.substring(iPos1 + 1);   
  }   
   
  /**  
   * @param fileName  
   * @return String  
   * @roseuid 3FBE26DE02D6  
   */   
  public static String getFileSuffix(String fileName) {   
    return objToString(fileName.substring(fileName.lastIndexOf(".") + 1,   
                                          fileName.length()));   
  }   
   
  /**  
   * 根据传入的文件全名（包括后缀名）或者文件全路径返回文件名（没有后缀名）  
   * @param fullPath 文件全名（包括后缀名）或者文件全路径  
   * @return 文件名（没有后缀名）  
   * @roseuid 3FBE26DE02F4  
   */   
  public static String getPureFileName(String fullPath) {   
    String fileFullName = getFileName(fullPath);   
    return fileFullName.substring(0, fileFullName.lastIndexOf("."));   
  }   
   
  /**  
   * 中文转换  
   * @param strT  
   * @return  
   * @roseuid 3FBE26DE0308  
   */   
  public static String toChinese(String strT) {   
    return toChinese(strT, Constant.ENCODDING);   
  }   
   
  /**  
   * 中文转换  
   * @param strT  
   * @return  
   * @roseuid 3FBE26DE0308  
   */   
  public static String toChinese(String strT, String encodering) {   
    try {   
      if (strT == null) {   
        return "";   
      }   
      else {   
        strT = new String(strT.getBytes("ISO8859_1"), encodering);   
        return strT;   
      }   
    }   
    catch (Exception e) {   
      return "";   
    }   
  }   
   
  /**  
   * @param string - 待判断的字符串是否为null或者空字符串  
   * @return boolean  
   * @roseuid 3FBE26DE0326  
   */   
  public static boolean isNullOrEmpty(String string) {   
    if (string == null || string.trim().equals("")) {   
      return true;   
    }   
    else {   
      return false;   
    }   
  }   
   
  /**  
   * 进行request参数的中文转换  
   * @param req  
   * @param paramName  
   * @param isChinese  
   * @return String  
   * @roseuid 3FBE26DE0380  
   */   
  public static String getParam(javax.servlet.http.HttpServletRequest req,   
                                String paramName, boolean isChinese) {   
    if (isChinese) {   
      return toChinese(req.getParameter(paramName));   
    }   
    else {   
      return toChinese(req.getParameter(paramName));   
    }   
  }   
   
  /**  
   * 将传入的字符串数组用tag字符串连接，并返回  
   *  
   * @param strAry - 待连接字符串的数组  
   * @param tag - 连接标记  
   * @return String  
   * @roseuid 3FBE26DF0024  
   */   
  public static String complexString(String[] strAry, String tag) {   
   
    if (strAry == null || strAry.length == 0) {   
      return "";   
    }   
   
    StringBuffer retBuffer = new StringBuffer();   
    int i;   
    for (i = 0; i  strAry.length - 1; i++) {   
      retBuffer.append(strAry[i]);   
      retBuffer.append(tag);   
    }   
    retBuffer.append(strAry[i]);   
    return retBuffer.toString();   
  }   
   
  /**  
   * 将传入的HashMap中的键值对用tag连接，返回  
   * @param hashMap HashMap  
   * @param tag String  
   * @return String[]  
   */   
  public static String[] complexString(HashMap hashMap, String tag) {   
   
    if (hashMap == null || hashMap.size() == 0) {   
      return new String[] {   
          "", ""};   
    }   
    String[] keys = new String[hashMap.size()];   
    String[] values = new String[hashMap.size()];   
    Iterator ite = hashMap.keySet().iterator();   
    int index = 0;   
    while (ite.hasNext()) {   
      String key = ite.next().toString();   
      keys[index] = key;   
      values[index++] = hashMap.get(key).toString();   
    }   
    String complexKeys = complexString(keys, "|");   
    String complexValues = complexString(values, "|");   
    return new String[] {   
        complexKeys, complexValues};   
   
  }   
   
  /**  
   * 将传入的HashMap中的键值,根据vKeys的键，用tag连接，返回  
   * @param hashMap HashMap  
   * @param tag String  
   * @return String[]  
   */   
   
  public static String[] complexString(Vector vKeys, HashMap hashMap,   
                                       String tag) {   
    if (vKeys == null || vKeys.size() == 0 || hashMap == null ||   
        hashMap.size() == 0) {   
      return new String[] {   
          "", ""};   
    }   
    StringBuffer keysBuf = new StringBuffer();   
    StringBuffer valuesBuf = new StringBuffer();   
    for (int i = 0; i  vKeys.size(); i++) {   
      String key = vKeys.get(i).toString();   
      if (!hashMap.containsKey(key)) {   
        continue;   
      }   
      keysBuf.append(key);   
      keysBuf.append("|");   
      valuesBuf.append(hashMap.get(key).toString());   
      valuesBuf.append("|");   
    }   
    return new String[] {   
        keysBuf.toString(), valuesBuf.toString()};   
  }   
   
  /**  
   * 转换文件路径中的\\为/  
   * @param filePath  
   * @return String  
   * @roseuid 3FBE26DF0074  
   */   
  public static String wrapFilePath(String filePath) {   
    filePath = objToString(filePath);   
    filePath.replace('\\', '/');   
    if (filePath.charAt(filePath.length() - 1) != '/') {   
      filePath += "/";   
    }   
    return filePath;   
  }   
   
  /**  
   * 获取本机URL地址  
   *  
   * @param req  
   * @return 本机URL地址  
   * @roseuid 3FBE26DF009C  
   */   
  public static String getLocalHost(javax.servlet.http.HttpServletRequest req) {   
    String strProtocol = req.getProtocol();   
    strProtocol = strProtocol.substring(0, strProtocol.indexOf("/"));   
    String strIPAddress = req.getHeader("host");   
    String strContextPath = req.getContextPath();   
    return strProtocol + "://" + strIPAddress + strContextPath;   
  }   
   
  /**  
   * 根据传入时间的返回年月日  
   * 传入yyyy-mm-dd hh:mm:ss  
   * 返回yyyy-mm-dd  
   *  
   * @param date - 待处理日期格式如：yyyy-mm-dd hh:mm:ss  
   * @return String  
   * @roseuid 3FBE2A83036E  
   */   
  public static String getDate(String date) {   
    return date.substring(0, 10);   
  }   
   
  public static int FILL_PREFIX = 1;   
  public static int FILL_SUFFIX = 2;   
  public static String fillCharacter(String source, String filler, int length,   
                                     int type) {   
    source = objToString(source);   
    filler = objToString(filler);   
    while (source.length()  length) {   
      if (type == FILL_PREFIX) {   
        source = filler + source;   
      }   
      else if (type == FILL_SUFFIX) {   
        source += filler;   
      }   
      else {   
        break;   
      }   
    }   
    return source;   
  }   
   
  /**  
   * 对传入的字符串，按长度降序排列  
   * @param source  
   * @return  
   */   
  public static String[] sortByLenDesc(String[] source) {   
    int i, j;   
    int length = source.length;   
    String tmpStr;   
    for (i = 0; i  length - 1; i++) {   
      for (j = i + 1; j  length; j++) {   
        if (source[i].length()  source[j].length()) {   
          tmpStr = source[i];   
          source[i] = source[j];   
          source[j] = tmpStr;   
        }   
      }   
    }   
    return source;   
  }   
   
  public static Object toChinese(Object obj, String[] props) {   
    for (int i = 0; i  props.length; i++) {   
      String propValue = null;   
      try {   
        propValue = (String) BeanUtils.getSimpleProperty(obj, props[i]);   
      }   
      catch (Exception e) {   
        Tools.log.error("获取转换字段异常:" + e.toString());   
        return obj;   
      }   
      propValue = toChinese(propValue);   
      try {   
        BeanUtils.setProperty(obj, props[i], propValue);   
      }   
      catch (Exception e) {   
        Tools.log.error("设置转换字段异常:" + e.toString());   
        return obj;   
      }   
    }   
    return obj;   
  }   
   
  public static HashMap subHashMap(HashMap source, HashMap subMap) {   
    Iterator keys = subMap.keySet().iterator();   
    while (keys.hasNext()) {   
      source.remove(keys.next());   
    }   
    return source;   
  }   
   
  public static Map subMap(Map source, Map subMap) {   
    Iterator keys = subMap.keySet().iterator();   
    while (keys.hasNext()) {   
      source.remove(keys.next());   
    }   
    return source;   
  }   
   
  public static String complexString(Vector v, String tag) {   
    if (v == null || v.size() == 0) {   
      return "";   
    }   
    tag = (tag == null ? "" : tag);   
    StringBuffer retBuffer = new StringBuffer();   
    int i;   
    for (i = 0; i  v.size() - 1; i++) {   
      retBuffer.append(v.get(i).toString());   
      retBuffer.append(",");   
    }   
    retBuffer.append(v.get(i).toString());   
    return retBuffer.toString();   
  }   
   
  public static boolean isContain(String[] src, String str) {   
    //检测是否已经存在列   
    for (int i = 0; i  src.length; i++) {   
      if (str.equalsIgnoreCase(src[i])) {   
        return true;   
      }   
    }   
    return false;   
  }   
   
  public static String[] vecotrToString(Vector v) {   
    if (v == null) {   
      return null;   
    }   
    String[] ret = new String[v.size()];   
    for (int i = 0; i  v.size(); i++) {   
      ret[i] = v.get(i).toString();   
    }   
    return ret;   
  }   
   
  public static long[] vectorToLong(Vector v) {   
    if (v == null) {   
      return null;   
    }   
    long[] ret = new long[v.size()];   
    for (int i = 0; i  v.size(); i++) {   
      try {   
        ret[i] = Long.parseLong(v.get(i).toString());   
      }   
      catch (Exception e) {   
        Tools.log.warn(e.toString());   
        Tools.log.warn("第" + i + "个值格式化异常，设置为-1");   
        ret[i] = -1;   
        continue;   
      }   
    }   
    return ret;   
  }   
   
  public static String urlEncode(String str) {   
    return urlEncode(str, Constant.ENCODDING);   
  }   
   
  public static String urlEncode(String str, String encodding) {   
    try {   
      return java.net.URLEncoder.encode(str, encodding);   
    }   
    catch (Exception e) {   
      Tools.log.error(e.toString());   
      return null;   
    }   
  }   
   
  public static String urlDecode(String str) {   
    return urlDecode(str, Constant.ENCODDING);   
  }   
   
  public static String urlDecode(String str, String encodding) {   
    try {   
      return java.net.URLDecoder.decode(str, encodding);   
    }   
    catch (Exception e) {   
      Tools.log.error(e.toString());   
      return null;   
    }   
  }   
   
  public static void deleteDirs(String path) {   
    File rootFile = new File(path);   
    if (rootFile == null) {   
      return;   
    }   
    File[] files = rootFile.listFiles();   
    if (files == null) {   
      return;   
    }   
    for (int i = 0; i  files.length; i++) {   
      File file = files[i];   
      if (file.isDirectory()) {   
        deleteDirs(file.getPath());   
      }   
      else {   
        file.delete();   
      }   
    }   
    rootFile.delete();   
  }   
   
  public static boolean contains(String[] strAry, String str) {   
    if (strAry == null || str == null) {   
      return false;   
    }   
    for (int i = 0; i  strAry.length; i++) {   
      if (strAry[i].equals(str)) {   
        return true;   
      }   
    }   
    return false;   
  }   
   
  public static boolean copyProperties(Object dest, Object src) {   
    try {   
      BeanUtils.copyProperties(dest, src);   
      return true;   
    }   
    catch (Exception e) {   
      log.error(e.toString());   
      return false;   
    }   
  }   
   
  public static String genResourceId(String inId) {   
    if (isNullOrEmpty(inId)) {   
      inId = "";   
    }   
    if (inId.length() > 18) {   
      inId = inId.substring(0, 17);   
    }   
    return DateTools.getStringFullTime() + inId +   
        RandomStringUtils.   
        randomNumeric(RESOURCE_RANDOM_LENGTH - inId.length() - 14);   
  }   
   
  public static String wrapTableName(String tableName) {   
    if (tableName.startsWith(Constant.DB_OWNER + ".")) {   
      return tableName;   
    }   
    else {   
      return Constant.DB_OWNER + "." + tableName;   
    }   
  }   
   
  public static String wrapFileName(String fileName) {   
    if (fileName == null)   
      return null;   
    fileName = StringUtils.replace(fileName, "\\", "");   
    fileName = StringUtils.replace(fileName, "/", "");   
    fileName = StringUtils.replace(fileName, ":", "");   
    fileName = StringUtils.replace(fileName, "*", "");   
    fileName = StringUtils.replace(fileName, "?", "");   
    fileName = StringUtils.replace(fileName, "\"", "");   
    fileName = StringUtils.replace(fileName, "<", "");   
    fileName = StringUtils.replace(fileName, ">", "");   
    fileName = StringUtils.replace(fileName, "|", "");   
    return fileName;   
  }   
   
  public static String convertExcelColumnId(int index) {   
    if (index  26) {   
      return Character.toString( (char) (65 + index));   
    }   
    else {   
      int j = (int) index / 26 - 1;   
      index = index % 26;   
      return Character.toString( (char) (65 + j)) +   
          Character.toString( (char) (65 + index));   
    }   
   
  }   
   
  public static String getDownloadHeader(String fileName) {   
    try {   
      return "attachment; filename=\"" + new String(fileName.toString().getBytes("GBK"),"ISO8859-1")+"\"";   
    }   
    catch (UnsupportedEncodingException ex) {   
      Tools.log.error(ex.toString());   
      return "";   
    }   
   
  }   
}   


