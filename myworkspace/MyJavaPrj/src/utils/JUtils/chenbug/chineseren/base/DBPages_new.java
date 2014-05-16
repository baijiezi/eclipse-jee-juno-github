package utils.JUtils.chenbug.chineseren.base;

import java.util.*;   
import javax.servlet.http.*;   
import org.apache.torque.*;   
import org.apache.torque.util.*;   
import com.workingdogs.village.*;   
   
/**  
 * <p>Title: DBPages</p>  
 * <p>Description: Peer==>DBPages==>listData==>pageHTML</p>  
 * 输入数据: RunData("page_sql","page_index","page_size","page_url")  
 * 输出数据: RunData("page_list","page_html")  
 * <p>Copyright: Copyright (c) 2003</p>  
 * <p>Company: pubinfo</p>  
 * @author chineseren  
 * @version 1.0  
 */   
   
public class DBPages_new {   
  //分页时将数据list存放到rd中是用到的key   
  public static final String KEY_PAGE_SQL = "page_sql"; //查询的sql   
  public static final String KEY_PAGE_INDEX = "page_index"; //开始的页号   
  public static final String KEY_PAGE_SIZE = "page_size"; //页面大小   
  public static final String KEY_PAGE_URL = "page_url"; //分页动作Handler不包括参数,比如:"/webapps/ActHandler"   
  public static final String KEY_PAGE_HTML = "page_html"; //生成的html   
  public static final String KEY_PAGE_LIST = "page_list"; //当前页的list   
  public static final int PAGE_SIZE = 10; //默认的分页大小   
  private RunData rd; //传入的RunData   
  private List list;//查询到的当前页的数据rundatas   
  private String html;//生成的页面html   
  private int totalRecords; //查询sql所能获得的记录总数   
   
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
   * 对外开放的分页入口  
   * @param rd RunData("page_sql","page_index","page_size","page_url")  
   * @return DBPages RunData("page_list","page_html")  
   */   
  public static DBPages doPage(RunData rd) {   
    DBPages pager = new DBPages(rd);   
    //查询数据 && 返回结果   
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
   
  //当前页面大小   
  public int getPageSize() {   
    return rd.getInt(KEY_PAGE_SIZE,PAGE_SIZE);   
  }   
   
  //取得所有记录的条数   
  public int getTotalRecords() {   
    return this.totalRecords;   
  }   
   
  //取得所有的页数   
  public int getTotalPages() {   
    return(totalRecords + getPageSize() - 1) / getPageSize();   
  }   
   
  //得到当前页号   
  public int getIndex() {   
    return rd.getInt(KEY_PAGE_INDEX,1);   
  }   
   
  //是否有上一页   
  public boolean hasPreviousPage() {   
    if(1 >= this.getIndex()) {   
      return false;   
    } else {   
      return true;   
    }   
  }   
   
  //是否有下一页   
  public boolean hasNextPage() {   
    if(this.getTotalPages() = this.getIndex()) {   
      return false;   
    } else {   
      return true;   
    }   
  }   
   
  //取得上一页号   
  public int getPreviousIndex() {   
    if(this.hasPreviousPage()) {   
      return (this.getIndex() - 1);   
    } else {   
      return this.getIndex();   
    }   
  }   
   
  //取得下一页号   
  public int getNextIndex() {   
    if(this.hasNextPage()) {   
      return (this.getIndex() + 1);   
    } else {   
      return this.getIndex();   
    }   
  }   
   
  //取得首页号   
  public int getFirstIndex() {   
    return 1;   
  }   
   
  //取得最后页号   
  public int getLastIndex() {   
    return this.getTotalPages();   
  }   
   
  //以下的是获取当前分页信息,设置为public是为了外部实现不同的分页外观   
  public List getPageList() {   
    String sql = null;   
    String strCount = null;   
    List ls = null;   
    Record tc = null;   
    int index = 1; //页码从1开始,记录号从0开始   
    int size = 10; //页面大小   
   
    sql = rd.getString(KEY_PAGE_SQL);   
    Debug.debug("DBPages.getPageList() input SQL is:\n" + sql);   
    sql = sql.trim();   
    String tempSql = sql.toUpperCase();   
    strCount = "SELECT COUNT(*) as C FROM "   
        + sql.substring(tempSql.indexOf(" FROM ") + 6, sql.length());   
    tempSql = null;   
    Debug.debug("DBPages.getPageList() Count(*) SQL is:\n" + strCount);   
   
    //取得分页记录集,出错返回null   
    try {   
      ls = BasePeer.executeQuery(strCount);   
      tc = (Record)ls.get(0);   
      this.setTotalRecords(tc.getValue("C").asInt()); //所有记录数   
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
   
  //根据RunData中的参数得到分页后的完整的url，并去掉index参数   
  public String getPageUrl() {   
    StringBuffer url = new StringBuffer(rd.getString(KEY_PAGE_URL));   
    url.append("?"); //开始取request中的参数   
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
    //等待补充index参数   
    url.append(KEY_PAGE_INDEX);   
    url.append("=");   
    return url.toString().substring(0, url.length());   
  }   
   
  //获取page_html: "共25条记录 第2/3页(每页10条)  <  1 2 3 > >>_go"   
  public String getPageHtml() {   
    //参数   
    String url = this.getPageUrl();   
    StringBuffer sb = new StringBuffer();   
    //共25条记录 第2/3页(每页10条)   
    sb.append("共").append(this.getTotalRecords()).append("条记录 第")   
        .append(this.getIndex()).append("/").append(this.getTotalPages())   
        .append("页(每页").append(this.getPageSize()).append("条)");   
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


