package utils.JUtils.chenbug.chineseren;

import javax.servlet.http.*;   
import java.util.*;   
import java.text.*;   
import java.lang.reflect.*;   
import java.io.*;   
import com.workingdogs.village.Record;   
import com.workingdogs.village.*;   
   
/**  
 * <p>Title: </p>  
 * <p>Description: 如果出现 key 相同的情况，用 rp(request.Parameters),ra(request.attribute),sa(session.attribute)来区分</p>  
 * 遵循规则:<p>  
 * 1 :对bean的拷贝，其属性区分大小写<p>  
 * <p>Copyright: Copyright (c) 2004</p>  
 * <p>Company: pubinfo</p>  
 * @author 蒋江伟  
 * @version 1.0  
 */   
   
public class RunData {   
  /**  
   * 保存所有的数据  
   */   
  private Map data = null;   
  private Record rcd = null;   
  public static final String RUNDATA_ATTR = "rundata_text";   
   
  /**  
   * HttpServletRequest  
   */   
  HttpServletRequest request = null;   
  /**  
   * HttpServletResponse  
   */   
  HttpServletResponse response = null;   
  public RunData() {   
    data = new HashMap();   
  }   
  //使用record生成rundata   
  public RunData(Record rcd) {   
    data = new HashMap();   
    this.rcd = rcd;   
  }   
  public Record getRecord(){   
    return this.rcd;   
  }   
  public void setRecord(Record record){   
    this.rcd = record;   
  }   
   
  /**  
   * 直接初始化页面里的所有数据到data里  
   * @param request HttpServletRequest  
   */   
  public RunData(HttpServletRequest request, HttpServletResponse response) {   
    data = new HashMap();   
    this.request = request;   
    this.response = response;   
    initData(request);   
  }   
   
  /**  
   * 直接初始化页面里的所有数据到data里  
   * @param request HttpServletRequest  
   */   
  public RunData(HttpServletRequest request) {   
    data = new HashMap();   
    this.request = request;   
    initData(request);   
  }   
   
  public HttpServletRequest getRequest() {   
    return request;   
  }   
   
  public HttpSession getSession() {   
    return request.getSession();   
  }   
   
  public HttpServletResponse getResponse() {   
    return this.response;   
  }   
   
  public void flush() {   
    if(request != null)   
      request.setAttribute(RUNDATA_ATTR, this);   
    else   
      Debug.error("the request is null,runData can't be set to the request,plz initialize the request first!");   
  }   
   
  /**  
   * 将data里属性名称一样的数据拷贝到目标bean里  
   * @param target Object  
   */   
  public void copyToBean(Object target) {   
    String className = target.getClass().getName();   
    Class clzz = target.getClass();   
    while(!className.equals("java.lang.Object")) {   
      Method[] m = clzz.getDeclaredMethods();   
   
      //赋值   
      for(int i = 0; i  m.length; i++) {   
        String name = m[i].getName();   
        if(name.startsWith("set") && !name.equals("set")) {   
          String p = name.substring(3);   
          if(p.length() > 1)   
            p = p.substring(0, 1).toLowerCase() + p.substring(1);   
          else   
            p = p.substring(0, 1).toLowerCase();   
            //System.out.println("p: " + p);   
          try {   
            Object o = data.get(p);   
            //System.out.println("o: " + o);   
            if(o != null)   
              org.apache.commons.beanutils.BeanUtils.setProperty(target, p, o);   
   
          } catch(Exception ex) {   
            //ex.printStackTrace();   
          }   
   
        }   
   
      }   
      // 最后初始化   
      clzz = clzz.getSuperclass();   
      className = clzz.getName();   
    }   
   
  }   
   
  /**  
   * 将目标bean里的属性全部拷贝到data里  
   * 比如rd.copyFromBean(commendOld);有问题,很多名称问题  
   * @param target Object  
   */   
  public void copyFromBean(Object target) {   
    String className = target.getClass().getName();   
    Class clzz = target.getClass();   
   
   
    while(!className.equals("java.lang.Object")) {   
      Method[] m = clzz.getDeclaredMethods();   
      Field fields[] = clzz.getFields();   
      //赋值   
      for(int i = 0; i  m.length; i++) {   
        String name = m[i].getName();   
        if(name.startsWith("get") && !name.equals("get")) {   
          String p = name.substring(3);   
          if(p.length() > 1)   
            p = p.substring(0, 1).toLowerCase() + p.substring(1);   
          else   
            p = p.substring(0, 1).toLowerCase();   
          try {   
   
            Object o = org.apache.commons.beanutils.BeanUtils.getProperty(   
                target, p);   
   
            put(p, o);   
          } catch(Exception ex) {   
            //ex.printStackTrace();   
          }   
        }   
      }   
      // 最后初始化   
      clzz = clzz.getSuperclass();   
      className = clzz.getName();   
    }   
   
  }   
   
  /**  
   * get the value as String  
   * @param key String  
   * @return String  
   */   
  public String getString(String key) {   
    if(get(key) == null)   
      return null;   
    else   
      return get(key).toString();   
  }   
  public String getString(String key,String defaultValue) {   
    if (get(key) == null){   
      return defaultValue;   
    }   
    else   
      return get(key).toString();   
  }   
   
  /**  
   * get the value as limit length String  
   * @param key String  
   * @param len int  
   * @return String  
   */   
  public String getLmtString(String key, int len) {   
    String rs = getString(key);   
    if(rs == null || rs.length()  len)   
      return rs;   
    return rs.substring(0, len);   
  }   
   
  /**  
   * get the value as Object  
   * @param key String  
   * @return Object  
   */   
  public Object get(String key) {   
    Object o = data.get(key);   
    try {   
      if(o == null) {   
        if(rcd != null) {   
          o = rcd.getValue(key);   
        }   
        if(o == null)   
          Debug.debug("RunData key: " + key + " value is null");   
      }   
    } catch(DataSetException ex) {   
      Debug.error("RunData.get(String key) ERROR!",ex);   
    }   
    return o;   
  }   
   
  /**  
   * 返回如:2002-03-15,get the value as Date  
   * @param key String  
   * @return String  
   */   
  public String getDate(String key) {   
    String date = getString(key);   
    if(date == null)   
      return date;   
    return getFormatDate(Long.parseLong(date));   
  }   
   
  /**  
   * 返回如：2002-03-15 02:32  
   * @param key String  
   * @return String  
   */   
  public String getDateTime(String key) {   
    String date = getString(key);   
    if(date == null)   
      return date;   
    return getFormatDateTime(Long.parseLong(date));   
  }   
   
  /**  
   * String to long  
   * @param key String  
   * @return long  
   */   
  public long getLong(String key) {   
    return Long.parseLong(getString(key));   
  }   
   
  /**  
   * get the value as long  
   * @param key String  
   * @param defaultValue long  
   * @return long  
   */   
  public long getLong(String key, long defaultValue) {   
    if(get(key) == null)   
      return defaultValue;   
    else   
      return getLong(key);   
  }   
  /**  
   * get String[]  
   * @param key String  
   * @return String[]  
   */   
  public String[] getStringArray(String key){   
    return (String[])get(key);   
  }   
  /**  
   * get request.String[]  
   * @param key String  
   * @return String[]  
   */   
  public String[] getReqStringArray(String key){   
    return (String[])getRequest().getParameterValues(key);   
  }   
   
  /**  
   * String to int  
   * @param key String  
   * @return int  
   */   
  public int getInt(String key) {   
    return Integer.parseInt(getString(key));   
  }   
   
  /**  
   * String to int  
   * @param key String  
   * @param defaultValue int  
   * @return int  
   */   
  public int getInt(String key, int defaultValue) {   
    if(get(key) == null)   
      return defaultValue;   
    else   
      return getInt(key);   
  }   
   
  /**  
   * String to double  
   * @param key String  
   * @return double  
   */   
  public double getDouble(String key) {   
    return Double.parseDouble(getString(key));   
  }   
   
  /**  
   * String to double  
   * @param key String  
   * @param defaultValue double  
   * @return double  
   */   
  public double getDouble(String key, double defaultValue) {   
    if(get(key) == null)   
      return defaultValue;   
    else   
      return getDouble(key);   
  }   
   
  /**  
   * String to boolean  
   * @param key String  
   * @return boolean  
   */   
  public boolean getBoolean(String key) {   
    return Boolean.valueOf(getString(key)).booleanValue();   
  }   
   
  /**  
   * String to boolean  
   * @param key String  
   * @param defaultValue boolean  
   * @return boolean  
   */   
  public boolean getBoolean(String key, boolean defaultValue) {   
    if(get(key) == null)   
      return defaultValue;   
    else   
      return getBoolean(key);   
  }   
   
   
  public void put(String key, Object value) {   
    //这里可以写对 字符串value的处理代码，如过滤，编码转换等   
    //value = filter(value);   
    //////////////////////////////////////////////////   
    if(key == null) {   
      System.out.println(   
          "the key is null,the key will be default as str 'null' ");   
      key = "null";   
    }   
    data.put(key, value);   
  }   
   
  /**  
   * long to String,save as String type  
   * @param key String  
   * @param value long  
   */   
  public void put(String key, long value) {   
   
    put(key, Long.toString(value));   
  }   
   
  public void put(String key, int value) {   
   
    put(key, Integer.toString(value));   
  }   
   
  private void initData(HttpServletRequest request) {   
    try {   
      request.setCharacterEncoding("GBK");   
    } catch(UnsupportedEncodingException ex) {   
      ex.printStackTrace();   
    }   
   
    //1.parameters   
    java.util.Enumeration e = request.getParameterNames();   
    while(e.hasMoreElements()) {   
      String key = (String)e.nextElement();   
      if(data.get(key) != null) {   
        put("rp_" + key, (request.getParameter(key)));   
      } else   
        put(key, (request.getParameter(key)));   
    }   
    //2.将request里的所有数据存入data   
    e = request.getAttributeNames();   
    while(e.hasMoreElements()) {   
      String key = (String)e.nextElement();   
      if(data.get(key) != null) {   
        put("ra_" + key, request.getAttribute(key));   
      } else   
        put(key, request.getAttribute(key));   
    }   
    //3. session里的所有数据存入data   
    e = request.getSession().getAttributeNames();   
    while(e.hasMoreElements()) {   
      String key = (String)e.nextElement();   
      if(data.get(key) != null) {   
        put("sa_" + key, request.getSession().getAttribute(key));   
      } else   
        put(key, request.getSession().getAttribute(key));   
    }   
  }   
   
  public String getPageUrl(){   
    return getPageUrl(null);   
  }   
  /**  
   * 得到url，并去掉filter的内容，默认去除index，act  
   * @param filter  
   * @return  
   */   
  public String getPageUrl(String filter){   
    String url = "";   
    java.util.Enumeration e = request.getParameterNames();   
    while(e.hasMoreElements()) {   
      String key = (String)e.nextElement();   
      if(filter!=null&&filter.length()!=0){   
        if(data.get(key) != null && !key.equals(AppSystem.KEY_ACT) &&   
            !key.equals(AppSystem.KEY_PAGE_INDEX) &&   
            !key.equals(filter)) {   
          url += "&" + key + "=" +   
              java.net.URLEncoder.encode(request.getParameter(key));   
        }   
      }else{   
        if(data.get(key) != null && !key.equals(AppSystem.KEY_ACT) &&   
            !key.equals(AppSystem.KEY_PAGE_INDEX)) {   
          url += "&" + key + "=" +   
              java.net.URLEncoder.encode(request.getParameter(key));   
        }   
      }   
    }   
    return url;   
  }   
   
   
  /**  
   * 取得request初始化完成的 RunData实例  
   * @param request HttpServletRequest  
   * @return RunData  
   */   
  public static RunData getInstance(HttpServletRequest request) {   
    return new RunData(request);   
  }   
   
  /**  
   *  
   * @param request HttpServletRequest  
   * @return RunData  
   */   
  public static RunData getInstance(HttpServletRequest request,   
      HttpServletResponse response) {   
    return new RunData(request, response);   
  }   
   
  /**  
   * 默认实例对象  
   * @return RunData  
   */   
  public static RunData getInstance() {   
    return new RunData();   
  }   
   
  public static RunData getInstance(Record rcd) {   
    return new RunData(rcd);   
  }   
   
  /**  
   * 打印所有的数据，调试时使用  
   */   
  public void print() {   
    Set k = data.keySet();   
    Iterator i = k.iterator();   
    System.out.println("************************************");   
    System.out.println("*      RunData'values print start  *");   
    System.out.println("************************************");   
    while(i.hasNext()) {   
      String key = (String)i.next();   
   
      System.out.println(" " + key + " = " + data.get(key));   
    }   
    if(rcd != null) {   
      System.out.println("*************Record*****************");   
      int size = rcd.size();   
      for(int l = 0; l  size; l++) {   
        try {   
          System.out.println(" " + l + ": " + rcd.getValue(l+1));   
        } catch(DataSetException ex) {   
          ex.printStackTrace();   
        }   
      }   
    }   
    System.out.println("************************************");   
    System.out.println("*      RunData'values print end    *");   
    System.out.println("************************************");   
  }   
   
   
   
   
  /*****************************************************************  
   *                        下面的额外工具的函数  
   *****************************************************************/   
  /**  
   *  
   * @param date Date  
   * @param format String (yyyy-MM-dd H:mm:ss)  
   * @return String  
   */   
  public static final String baseFormatDateTime(Date date, String format) {   
    SimpleDateFormat mydate = new SimpleDateFormat(format);   
    return mydate.format(date);   
  }   
  /**  
   *  
   * @param millis long  
   * @param format String (yyyy-MM-dd H:mm:ss)  
   * @return String  
   */   
  public static final String baseFormatDateTime(long millis, String format) {   
    return baseFormatDateTime(new Date(millis), format);   
  }   
   
  /**  
   * 取得格式化日期时间的函数0000-00-00 00:00:00  
   * @param date Date  
   * @return String  
   */   
  public static final String getFormatDateTime(java.util.Date date) {   
    return baseFormatDateTime(date, "yyyy-MM-dd H:mm:ss");   
   
  }   
   
  public static final String getFormatDateTime(long millis) {   
    return getFormatDateTime(new Date(millis));   
  }   
   
  /**  
   * 取得格式化日期的函数  
   * @param date Date  
   * @return String  
   */   
  public static final String getFormatDate(java.util.Date date) {   
    return baseFormatDateTime(date, "yyyy-MM-dd");   
  }   
   
  public static final String getFormatDate(long millis) {   
    return getFormatDate(new Date(millis));   
  }   
   
  public static String ISO8859ToGBK(String s1) {   
    if(s1 == null)   
      return null;   
    String s = s1;   
    try {   
      byte[] b = s.getBytes("8859_1");   
      String s2 = new String(b, "GBK");   
   
      return(s2);   
    } catch(Exception e) {   
      Debug.error("RunData.ISO8859ToGBK(String s1) ERROR!",e);   
    }   
    return(s);   
  }   
   
  /**  
   * 直接将RecordList 转化成RunDataList  
   * @param recordList recordList  
   * @return List  
   */   
  public static List recordListToRunDataList(List recordList) {   
    List tmp = new ArrayList();   
    if(recordList != null) {   
      Iterator itr = recordList.iterator();   
      while(itr.hasNext()) {   
        com.workingdogs.village.Record rd = (com.workingdogs.village.Record)   
            itr.next();   
        RunData rund = new RunData(rd);   
        tmp.add(rund);   
      }   
    }   
    return tmp;   
  }   
   
  /**  
   * 直接将RunDataList转化成RecordList  
   * @param rundataList rundataList  
   * @return List  
   */   
  public static List runDataListToRecordList(List rundataList) {   
    List tmp = new ArrayList();   
    if(rundataList != null) {   
      Iterator itr = rundataList.iterator();   
      while(itr.hasNext()) {   
        RunData rd = (RunData)itr.next();   
        Record record = rd.getRecord();   
        tmp.add(record);   
      }   
    }   
    return tmp;   
  }   
}  



