package utils.JUtils.chenbug.chineseren.base;

import java.util.*;   
import javax.servlet.http.*;   
import org.apache.torque.*;   
import org.apache.torque.util.*;   
import com.workingdogs.village.*;   
   
/**  
 * <p>Title: DBPages</p>  
 * <p>Description: Peer==>DBPages==>listData==>pageHTML</p>  
 * ��������: RunData("page_sql","page_index","page_size","page_url")  
 * �������: RunData("page_list","page_html")  
 * <p>Copyright: Copyright (c) 2003</p>  
 * <p>Company: pubinfo</p>  
 * @author chineseren  
 * @version 1.0  
 */   
   
public class DBPages_new {   
  //��ҳʱ������list��ŵ�rd�����õ���key   
  public static final String KEY_PAGE_SQL = "page_sql"; //��ѯ��sql   
  public static final String KEY_PAGE_INDEX = "page_index"; //��ʼ��ҳ��   
  public static final String KEY_PAGE_SIZE = "page_size"; //ҳ���С   
  public static final String KEY_PAGE_URL = "page_url"; //��ҳ����Handler����������,����:"/webapps/ActHandler"   
  public static final String KEY_PAGE_HTML = "page_html"; //���ɵ�html   
  public static final String KEY_PAGE_LIST = "page_list"; //��ǰҳ��list   
  public static final int PAGE_SIZE = 10; //Ĭ�ϵķ�ҳ��С   
  private RunData rd; //�����RunData   
  private List list;//��ѯ���ĵ�ǰҳ������rundatas   
  private String html;//���ɵ�ҳ��html   
  private int totalRecords; //��ѯsql���ܻ�õļ�¼����   
   
  public RunData getRd(){   
    return rd;   
  }   
  public List getList(){   
    return list;   
  }   
  public String getHtml(){   
    return html;   
  }   
   
  /**  
   * ���⿪�ŵķ�ҳ���  
   * @param rd RunData("page_sql","page_index","page_size","page_url")  
   * @return DBPages RunData("page_list","page_html")  
   */   
  public static DBPages doPage(RunData rd) {   
    DBPages pager = new DBPages(rd);   
    //��ѯ���� && ���ؽ��   
    rd.put(KEY_PAGE_LIST, pager.getPageList());   
    rd.put(KEY_PAGE_HTML, pager.getPageHtml());   
    return pager;   
  }   
   
  public DBPages(RunData rd) {   
    this.rd = rd;   
  }   
   
  public void setIndex(int index) {   
    rd.put(KEY_PAGE_INDEX,index);   
  }   
   
  public void setTotalRecords(int totalRecords) {   
    this.totalRecords = totalRecords;   
  }   
   
  public void setPageSize(int pageSize) {   
    rd.put(KEY_PAGE_SIZE,pageSize);   
  }   
   
  //��ǰҳ���С   
  public int getPageSize() {   
    return rd.getInt(KEY_PAGE_SIZE,PAGE_SIZE);   
  }   
   
  //ȡ�����м�¼������   
  public int getTotalRecords() {   
    return this.totalRecords;   
  }   
   
  //ȡ�����е�ҳ��   
  public int getTotalPages() {   
    return(totalRecords + getPageSize() - 1) / getPageSize();   
  }   
   
  //�õ���ǰҳ��   
  public int getIndex() {   
    return rd.getInt(KEY_PAGE_INDEX,1);   
  }   
   
  //�Ƿ�����һҳ   
  public boolean hasPreviousPage() {   
    if(1 >= this.getIndex()) {   
      return false;   
    } else {   
      return true;   
    }   
  }   
   
  //�Ƿ�����һҳ   
  public boolean hasNextPage() {   
    if(this.getTotalPages() = this.getIndex()) {   
      return false;   
    } else {   
      return true;   
    }   
  }   
   
  //ȡ����һҳ��   
  public int getPreviousIndex() {   
    if(this.hasPreviousPage()) {   
      return (this.getIndex() - 1);   
    } else {   
      return this.getIndex();   
    }   
  }   
   
  //ȡ����һҳ��   
  public int getNextIndex() {   
    if(this.hasNextPage()) {   
      return (this.getIndex() + 1);   
    } else {   
      return this.getIndex();   
    }   
  }   
   
  //ȡ����ҳ��   
  public int getFirstIndex() {   
    return 1;   
  }   
   
  //ȡ�����ҳ��   
  public int getLastIndex() {   
    return this.getTotalPages();   
  }   
   
  //���µ��ǻ�ȡ��ǰ��ҳ��Ϣ,����Ϊpublic��Ϊ���ⲿʵ�ֲ�ͬ�ķ�ҳ���   
  public List getPageList() {   
    String sql = null;   
    String strCount = null;   
    List ls = null;   
    Record tc = null;   
    int index = 1; //ҳ���1��ʼ,��¼�Ŵ�0��ʼ   
    int size = 10; //ҳ���С   
   
    sql = rd.getString(KEY_PAGE_SQL);   
    Debug.debug("DBPages.getPageList() input SQL is:\n" + sql);   
    sql = sql.trim();   
    String tempSql = sql.toUpperCase();   
    strCount = "SELECT COUNT(*) as C FROM "   
        + sql.substring(tempSql.indexOf(" FROM ") + 6, sql.length());   
    tempSql = null;   
    Debug.debug("DBPages.getPageList() Count(*) SQL is:\n" + strCount);   
   
    //ȡ�÷�ҳ��¼��,������null   
    try {   
      ls = BasePeer.executeQuery(strCount);   
      tc = (Record)ls.get(0);   
      this.setTotalRecords(tc.getValue("C").asInt()); //���м�¼��   
      Debug.debug("DBPages.getPageList() totalRecord is: " + this.totalRecords);   
    } catch(Exception ex) {   
      Debug.error("DBPages.getPageList() get COUNT ERROR!", ex);   
      return null;   
    }   
    try {   
      index = rd.getInt(KEY_PAGE_INDEX, 1);   
      index = (1 > index) ? 1 : index;   
      this.setIndex(index);   
      size = rd.getInt(KEY_PAGE_SIZE, PAGE_SIZE);   
      size = (1 > size) ? PAGE_SIZE : size;   
      this.setPageSize(size);   
      this.list = BasePeer.executeQuery(sql, (index - 1) * size, size,   
          Torque.getDefaultDB(), false);   
      this.list = RunData.recordListToRunDataList(this.list);   
      int count = (null == list) ? 0 : this.list.size();   
      Debug.debug("DBPages.getPageList() LIST is: " + count);   
    } catch(Exception ex) {   
      Debug.error("DBPages.getPageList() get LIST ERROR!", ex);   
      return null;   
    }   
    return list;   
  }   
   
  //����RunData�еĲ����õ���ҳ���������url����ȥ��index����   
  public String getPageUrl() {   
    StringBuffer url = new StringBuffer(rd.getString(KEY_PAGE_URL));   
    url.append("?"); //��ʼȡrequest�еĲ���   
    HttpServletRequest request = rd.getRequest();   
    Enumeration keys = request.getParameterNames();   
    while(keys.hasMoreElements()) {   
      String key = (String)keys.nextElement();   
      String value = request.getParameter(key);   
      if(value != null && !key.equals(KEY_PAGE_INDEX)) {   
        url.append(key);   
        url.append("=");   
        url.append(java.net.URLEncoder.encode(value));   
        url.append("&");   
      }   
    }   
    //�ȴ�����index����   
    url.append(KEY_PAGE_INDEX);   
    url.append("=");   
    return url.toString().substring(0, url.length());   
  }   
   
  //��ȡpage_html: "��25����¼ ��2/3ҳ(ÿҳ10��)  <  1 2 3 > >>_go"   
  public String getPageHtml() {   
    //����   
    String url = this.getPageUrl();   
    StringBuffer sb = new StringBuffer();   
    //��25����¼ ��2/3ҳ(ÿҳ10��)   
    sb.append("��").append(this.getTotalRecords()).append("����¼ ��")   
        .append(this.getIndex()).append("/").append(this.getTotalPages())   
        .append("ҳ(ÿҳ").append(this.getPageSize()).append("��)");   
    //<    
    if(this.hasPreviousPage()) {   
      sb.append("<a href='").append(url).append(this.getFirstIndex())   
          .append("'> << </a>");   
      sb.append("<a href='").append(url).append(this.getPreviousIndex())   
          .append("'> < </a>");   
    } else {   
      sb.append(" << ");   
      sb.append(" < ");   
    }   
    //1 2 3   
    int count = this.getTotalPages();   
    for (int i = 1; i = count; i++) {   
      sb.append("<a href='").append(url).append(i)   
          .append("'> ").append(i).append(" </a>");   
    }   
    //> >>   
    if(this.hasNextPage()) {   
      sb.append("<a href='").append(url).append(this.getNextIndex())   
          .append("'> > </a>");   
      sb.append("<a href='").append(url).append(this.getLastIndex())   
          .append("'> >> </a>");   
    } else {   
      sb.append(" > ");   
      sb.append(" >> ");   
    }   
    //_go   
    sb.append("<input type='text' size='2' maxLength='2' name='goIndex' value='");   
    sb.append(this.getNextIndex());   
    sb.append("'><input type='button' value='Go' onClick=javascript:window.location='");   
    sb.append(url);   
    sb.append("'+");   
    sb.append("document.all.goIndex.value;>");   
   
    this.html = sb.toString();   
    return this.html;   
  }   
}  


