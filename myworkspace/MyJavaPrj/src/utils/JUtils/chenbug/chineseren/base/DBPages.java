package utils.JUtils.chenbug.chineseren.base;

import java.util.*;   
import org.apache.torque.util.BasePeer;   
import org.apache.torque.util.*;   
/**  
 * <p>Title: DBPages</p>  
 * <p>Description: Peer==>DBPages==>listData==>PageInfo==>pageHTML</p>  
 * <p>Copyright: Copyright (c) 2003</p>  
 * <p>Company: pubinfo</p>  
 * @author chineseren  
 * @version 1.0  
 */   
   
public class DBPages {   
  private String sql;   
  private Criteria  crit;   
  private int startIndex;   
  private List list;   
  private PageInfo pageInfo;   
  public boolean isSql;   
   
  //通过sql   
  public DBPages(String sql,int startIndex) {   
    this(sql,startIndex,AppSystem.PAGESIZE);   
  }   
  public DBPages(String sql,int startIndex,int pageSize) {   
    Debug.debug("The SQL put in DBPages is: " + sql);   
    this.sql = sql;   
    this.startIndex  = startIndex;   
    pageInfo = new PageInfo(startIndex);   
    pageInfo.setPageSize(pageSize);   
    this.isSql = true ;   
  }   
   
  //通过crit==>sql,其实fieldNames可以用'*'代替,可是king把'*'过滤了:(   
  public DBPages(Criteria crit,List fieldNames,int startIndex) {   
    this(crit,fieldNames,startIndex,AppSystem.PAGESIZE);   
  }   
  public DBPages(Criteria crit,List fieldNames,int startIndex,int pageSize) {   
    this(crit,null,fieldNames,startIndex,pageSize);   
  }   
  //添加了orderby,比如:"col1 desc,col2 asc"   
  public DBPages(Criteria crit,String orderby,List fieldNames,int startIndex,int pageSize) {   
    this.crit = crit;   
    this.startIndex  = startIndex;   
    pageInfo = new PageInfo(startIndex);   
    pageInfo.setPageSize(pageSize);   
    this.isSql = true ;   
    try{   
      this.sql = BasePeer.createQueryString(crit);   
      int pos1 = this.sql.indexOf("SELECT  FROM");   
      if(pos1 !=-1){   
        this.sql = this.sql.substring(pos1+12,this.sql.length());   
        String tempStr = "";   
        Iterator iterator = fieldNames.iterator();   
        while(iterator.hasNext()){   
          tempStr = tempStr+","+(String)iterator.next();   
        }   
         tempStr = tempStr.substring(1,tempStr.length());   
         this.sql ="SELECT "+tempStr+" FROM"+ this.sql;   
       }   
   
       //添加order by语句   
       if(null != orderby && !"".equals(orderby)){   
         this.sql += " ORDER BY " + orderby;   
       }   
   
       Debug.debug("The SQL BasePeer.createQueryString(crit) is: " + this.sql);   
    }catch(Exception ex){   
      Debug.error("BasePeer.createQueryString(crit) ERROR in DBPages!",ex);   
    }   
  }   
   
  public List getPage(){   
    String s=null;   
    String strSQL = null;   
    String strCount = null;   
    String strOrder = null;   
    List ls = null;   
    String colStr = "";   
    String tableStr = "";   
    String whereStr = "";   
    com.workingdogs.village.Record tc = null;   
    Debug.println(this.sql);   
    try{   
    if(isSql==true){//是普通的sql语句   
      this.sql = this.sql.trim();   
      String tempSql = this.sql.toUpperCase();   
      int a = this.sql.indexOf("*");   
      if(a !=-1 ){   
        throw new Exception("error sql!" );   
      }   
      int colpos = tempSql.indexOf(" FROM ");   
      a = tempSql.indexOf("WHERE");   
      if(a != -1){//有where   
        tableStr =this.sql.substring(colpos+6,a);   
        whereStr =  this.sql.substring(a,tempSql.length());   
      }else{//没有where   
        tableStr =this.sql.substring(colpos+5,tempSql.length());   
        int j=tableStr.toUpperCase().indexOf(" ORDER ");   
        if(j != -1){//说明有order by   
          tableStr = tableStr.substring(0,j);   
        }   
        whereStr = "";   
      }   
      int k=whereStr.toUpperCase().indexOf(" ORDER ");   
      if(k!= -1){//有order by语句   
          whereStr=whereStr.substring(0,k);   
      }   
   
    Debug.println(tableStr);   
    Debug.println(whereStr);   
      strCount = "select count(*) as C from " + tableStr + whereStr;   
   
    }else{//是torque语句   
   
    }   
    }catch(Exception ex){   
      Debug.error("DBPages.getPage() generate SQL ERROR!",ex);   
      return null;   
    }   
   
    //取得分页记录集   
    try{   
      Debug.println(strCount);   
      ls = BasePeer.executeQuery(strCount);   
      tc = (com.workingdogs.village.Record )ls.get(0);   
      pageInfo.setTotalRecord(tc.getValue("C").asInt());//所有记录数   
      Debug.debug("DBPages.getPage() list size is:" + pageInfo.getTotalRecords());   
    }catch(Exception exx){   
      Debug.error("DBPages.getPage() get count of row from db ERROR!",exx) ;   
      return null;   
    }   
    try{   
      list = BasePeer.executeQuery(this.sql, this.startIndex - 1,   
          pageInfo.getPageSize(), org.apache.torque.Torque.getDefaultDB(), false);   
    }catch(Exception ex){   
      Debug.error("DBPages.getPage() to get paging record from db ERROR!",ex);   
      Debug.debug("DBPages.getPage() strSQL is: " + strSQL) ;   
      return null;   
    }   
    return list;   
  }   
   
  public PageInfo getPageInfo() {   
    return this.pageInfo;   
  }   
}  


