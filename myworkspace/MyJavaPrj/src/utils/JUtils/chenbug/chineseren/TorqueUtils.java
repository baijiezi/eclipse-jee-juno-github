package utils.JUtils.chenbug.chineseren;

www.pudn.com > javautil.rar > TorqueUtils.java, change:2005-07-30,size:10511b


package com.reason.util;   
   
/**  
 * <p>Title: TorqueUtils</p>  
 * <p>Description: ʹ��Torqueʱ����Ҫ�õ��Ĺ��߷���</p>  
 * <p>Copyright: Copyright (c) 2004</p>  
 * <p>Company: pubinfo</p>  
 * @author chineseren  
 * @version 1.0  
 */   
import java.util.*;   
import java.sql.*;   
   
import org.apache.torque.*;   
import org.apache.torque.util.*;   
import com.workingdogs.village.*;   
   
public class TorqueUtils {   
  //��ʼ��Torque   
  public static void init() throws TorqueException {   
    String configFile = "config/Torque.properties";   
    Debug.debug("TorqueConfigFile is : " + configFile);   
    try {   
      Torque.init(configFile);   
      Debug.debug("==================");   
      Debug.debug("Torque has inited!");   
      Debug.debug("==================");   
    } catch(TorqueException e) {   
      Debug.fatal("I am failed to init Torque!!\nPlease check & restart application!!");   
      throw e;   
    }   
  }   
  //����Torque��Դ   
  public static void destroy() {   
    Torque.shutdown();   
  }   
   
  /**  
   * ����ֵvalueת����Object����  
   * @param value ��ֵ  
   * @return Object ��ֵ(Integer,Long,Boolean,Double,Float,Date,String,Object...)  
   * @throws DataSetException  
   */   
  public static Object valueToObject(Value value) throws DataSetException{   
    if(null == value){   
      return "";   
    }   
   
    if(value.isBigDecimal()){   
      return value.asBigDecimal();   
    } else if(value.isBoolean()){   
      return value.asBooleanObj();   
    } else if(value.isByte()){   
      return value.asByteObj();   
    } else if(value.isDate()){   
      return value.asDate();   
    } else if(value.isDouble()){   
      return value.asDoubleObj();   
    } else if(value.isFloat()){   
      return value.asFloatObj();   
    } else if(value.isInt()){   
      return value.asIntegerObj();   
    } else if(value.isLong()){   
      return value.asLongObj();   
    } else if(value.isNull()){   
      return "";   
    } else if(value.isShort()){   
      return value.asShortObj();   
    } else if(value.isString()){   
      return value.asString();   
    } else if(value.isTime()){   
      return value.asTime();   
    } else if(value.isTimestamp()){   
      return value.asTimestamp();   
    } else if(value.isUtilDate()){   
      return value.asUtilDate();   
    } else{   
      return "";   
    }   
  }   
   
  /**  
   * ��Record�е�Valueת����Object[]  
   * @param record һ����¼����record  
   * @return Object[] ������ֵ  
   * @throws DataSetException  
   */   
  public static Object[] recordToObjArray(Record record) throws DataSetException {   
    int count = record.size();   
    Object[] objArray = new Object[count];   
    for (int i = 1; i = count; i++) {//Values are 1 based!   
      Value value = record.getValue(i);//throws DataSetException   
      Object object = TorqueUtils.valueToObject(value);   
      objArray[i-1] = object;   
    }   
   
    return objArray;   
  }   
  public static List recordToObjList(Record record) throws DataSetException {   
    return Arrays.asList(TorqueUtils.recordToObjArray(record));   
  }   
  public static Vector recordToObjVector(Record record) throws DataSetException {   
    return ToolUtils.convertToVector(TorqueUtils.recordToObjArray(record));   
  }   
   
  /**  
   * ��recordListת����Object[(Object1[],Object2[],Object3[],...)]  
   * @param recordList recordList  
   * @return Object[]  
   * @throws DataSetException  
   */   
  public static Object[] recordListToObjArray(List recordList) throws DataSetException {   
    int count = recordList.size();   
    Object[] objArray = new Object[count];   
    for (int i = 0; i  count; i++) {   
      Record record = (Record)recordList.get(i);   
      Object[] objects = TorqueUtils.recordToObjArray(record);//throws DataSetException   
      objArray[i] = objects;   
    }   
   
    return objArray;   
  }   
   
  /**  
   * ��recordListת����Object[(Object1[],Object2[],Object3[],...)]  
   * @param recordList recordList  
   * @return Object[]  
   * @throws DataSetException  
   */   
  public static Vector recordListToObjVector(List recordList) throws DataSetException {   
    int count = recordList.size();   
    Vector vc = new Vector();   
    for (int i = 0; i  count; i++) {   
      Record record = (Record)recordList.get(i);   
      Vector objects = TorqueUtils.recordToObjVector(record);//throws DataSetException   
      vc.add(i,objects);   
    }   
   
    return vc;   
  }   
  public static Object[] rundataListToObjArray(List rundataList) throws DataSetException {   
    return TorqueUtils.recordListToObjArray(RunData.runDataListToRecordList(rundataList));   
  }   
  public static Vector rundataListToObjVector(List rundataList) throws DataSetException {   
    return TorqueUtils.recordListToObjVector(RunData.runDataListToRecordList(rundataList));   
  }   
   
  /*===��������Torque��صĹ��߷���===*/   
  /**  
   * ��ȡ��ѯ��¼����ǰrows������,�ٶ���һ��  
   * @param sql ��ѯ����а�����"fetch first n rows only"  
   * @return List �����������RunDatas,���ǵ����䲻�˲�������û��ͨ��row2Object()ת������Ӧ��Object  
   * @throws TorqueException  
   */   
  public static List fetchFirstRows(String sql) throws TorqueException {   
    Debug.debug("TorqueUtils.fetchFirstRows() SQL is:\n" + sql);   
    List list = BasePeer.executeQuery(sql);   
    list = RunData.recordListToRunDataList(list);   
    Debug.debug("TorqueUtils.fetchFirstRows() List size is: " + list.size());   
    return list;   
  }   
   
  public static List fetchFirstRows(String sql,   
      int rows) throws TorqueException {   
    String fetchSql = " fetch first " + rows + " rows only";   
    sql += fetchSql;   
   
    return fetchFirstRows(sql);   
  }   
   
  public static List fetchFirstRows(Criteria crit,   
      int rows) throws TorqueException {   
    String sql = BasePeer.createQueryString(crit);   
    return fetchFirstRows(sql, rows);   
  }   
   
  /**  
   * ����sqlִ��BasePeer.doSelect(sql)  
   * @param sql ��ѯSQL  
   * @return List(rundatas)  
   * @throws TorqueException  
   */   
  public static List findRows(String  sql) throws TorqueException {   
      List list = BasePeer.executeQuery(sql);   
      list = RunData.recordListToRunDataList(list);   
      Debug.debug("ToolUtils.findRows(String  sql) List size is: " + list.size());   
      return list;   
  }   
   
  /**  
   * ����critִ��BasePeer.doSelect(crit)  
   * @param crit ��ѯ����Criteria  
   * @return List(rundatas)  
   * @throws TorqueException  
   */   
  public static List findRows(Criteria crit) throws TorqueException {   
      List list = BasePeer.doSelect(crit);   
      list = RunData.recordListToRunDataList(list);   
      Debug.debug("TorqueUtils.findRows(Criteria crit) List size is: " + list.size());   
      return list;   
  }   
   
  /**  
   * ����BasePeer�ķ�����ȡ��ѯ�������,��start������rows����¼,�ٶȿ�һ��  
   * @param sql ��ͨ�Ĳ�ѯSQL,������"fetch first n rows only"  
   * @param start ��ʼ���  
   * @param rows Ҫ���õļ�¼��  
   * @return List(rundatas)  
   * @throws TorqueException  
   */   
  public static List findRows(String sql, int start,   
      int rows) throws TorqueException {   
    Debug.debug("TorqueUtils.findRows() SQL is:\n" + sql);   
    List list = BasePeer.executeQuery(sql, start, rows, Torque.getDefaultDB(), false);   
    list = RunData.recordListToRunDataList(list);   
    Debug.debug("TorqueUtils.findRows() List size is: " + list.size());   
    return list;   
  }   
   
  public static List findRows(String sql, int rows) throws TorqueException {   
    return findRows(sql, 0, rows);   
  }   
   
  public static List findRows(Criteria crit, int start,   
      int rows) throws TorqueException {   
    String sql = BasePeer.createQueryString(crit);   
    return findRows(sql, start, rows);   
  }   
   
  public static List findRows(Criteria crit, int rows) throws TorqueException {   
    return findRows(crit, 0, rows);   
  }   
   
  /**  
   * �򻯵ķ�ҳ����  
   * @param sql ��ѯ��SQL  
   * @param rd RunData("index","url")  
   * @return List(records) RunData("pageList","pageHtml")  
   */   
  public static List getDBPagesResult(String sql,RunData rd){   
    return TorqueUtils.getDBPagesResult(sql,rd,AppSystem.PAGESIZE);   
  }   
   
  public static List getDBPagesResult(String sql, RunData rd, int pageSize) {   
    int index;   
    String url;   
    List list;   
    //��ȡ��������   
    index = rd.getInt(AppSystem.KEY_PAGE_INDEX, 1);   
    url = rd.getString(AppSystem.KEY_PAGE_URL);   
    //��ѯ����   
    DBPages dbpages = new DBPages(sql, index, pageSize);   
    list = RunData.recordListToRunDataList(dbpages.getPage());   
    PageInfo pageInfo = dbpages.getPageInfo();   
    String pageHtml = pageInfo.getPageHtml(url, rd);   
    //���ؽ��   
    rd.put(AppSystem.KEY_PAGE_LIST, list);   
    rd.put(AppSystem.KEY_PAGE_HTML, pageHtml);   
   
    Debug.debug("TorqueUtils.getDBPagesResult size is: " + list.size());   
    return list;   
  }   
   
  /**  
   * ͨ��crit����SQL,������order by,������Ĭ��orderbyֵ  
   * @param crit �����Ѿ������select cols  
   * @param orderby ex: orderby = "id desc ";  ex: orderby ="id desc,time asc";  
   * @param defaultOrder Ĭ��orderbyֵ  
   * @return String SQL  
   * @throws TorqueException  
   */   
  public static String createSQL(Criteria crit,String orderby,String defaultOrder) throws TorqueException {   
    String sql = BasePeer.createQueryString(crit);   
    if(!StringUtils.isBlank(orderby)) {   
      sql += " ORDER BY " + orderby;   
    }else{   
      if(!StringUtils.isBlank(defaultOrder)){   
        sql += " ORDER BY " + defaultOrder;   
      }   
    }   
    Debug.debug("TorqueUtils.createSQL() SQL is:\n" + sql);   
    return sql;   
  }   
  public static String createSQL(Criteria crit,String orderby) throws TorqueException {   
    return createSQL(crit,orderby,null);   
  }   
  public static String createSQL(Criteria crit) throws TorqueException {   
    return createSQL(crit,null,null);   
  }   
   
  /**  
   * ����SQL��ѯ����Vectors����JTable��ʾ  
   * @param sql ������SQL  
   * @return Vectors(Vector1(),Vector2(),Vector3(),...)  
   * @throws TorqueException  
   */   
  public static Vector findVectors(String sql) throws TorqueException {   
    List list = BasePeer.executeQuery(sql);   
    Debug.debug("TorqueUtils.findVectors() Vectors size is: " + list.size());   
    try {   
      return TorqueUtils.recordListToObjVector(list);   
    } catch(DataSetException ex) {   
      throw new TorqueException(ex);   
    }   
  }   
  public static Vector findVectors(Criteria crit,String orderby) throws TorqueException {   
    String sql = TorqueUtils.createSQL(crit,orderby);   
    return TorqueUtils.findVectors(sql);   
  }   
  public static Vector findVectors(Criteria crit) throws TorqueException {   
    return TorqueUtils.findVectors(crit);   
  }   
}  



