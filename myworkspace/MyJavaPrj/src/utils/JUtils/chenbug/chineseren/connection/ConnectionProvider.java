package utils.JUtils.chenbug.chineseren.connection;

/**  
 * Title:  
 * Description:  
 * Copyright:    Copyright (c) 2002  
 * Company:  
 * @author  
 * @version 1.0  
 */   
import java.sql.*;   
import java.util.*;   
import java.io.*;   
   
public class ConnectionProvider {   
   private Connection con;   
   private Vector connectionPool = new Vector();   
   private int count;   
   private String classDriver,url,user,password;   
   private int min,max,addCount;   
/**  
 * 构造 一个连接提供者，  
 * @param classDriver 驱动源  
 * @param url 数据库的JDBC URL  
 * @param user 用户名  
 * @param password 用户口令  
 * @param min 最小连接数  
 * @param max 最大连接数  
 * @param addCount 每次新创建的个数。  
 */   
    ConnectionProvider(String classDriver,String url,String user,String password,int min,int addCount,int max)throws SQLException{   
       this.classDriver = classDriver;   
       this.url = url;   
       this.user = user;   
       this.password = password;   
       this.min = min;   
       this.max = max;   
       this.addCount = addCount;   
       Connection tempCon =null;   
       try{   
           for(int i=0;i<min;i++){   
             tempCon=this.createCon(classDriver,url,user,password);   
             connectionPool.addElement(tempCon);   
           }   
           count = min;   
      }catch(Exception e){   
           throw new SQLException();   
      }   
  }   
   
   /**  
 * 从连接池获取可用连接.可以指定客户程序能够等待的最长时间  
 * 参见前一个getConnection()方法.  
 *  
 * @param timeout 以毫秒计的等待时间限制  
 */   
 public synchronized Connection getConnection(long timeout) {   
     long startTime = System.currentTimeMillis();   
     while ((con = getConnection()) == null) {   
       try {   
       wait(timeout);   
       }   
       catch (InterruptedException e) {}   
     if ((System.currentTimeMillis() - startTime) >= timeout) {   
     // wait()返回的原因是超时   
       return null;   
       }   
     }   
     return con;   
 }   
   public synchronized Connection getConnection(){   
      if(connectionPool.size()>0){   
          con = (Connection)connectionPool.firstElement() ;   
          connectionPool.removeElementAt(0);   
          try{   
            if(con.isClosed()){   
              con = getConnection();   
            }   
          }catch(Exception e){e.printStackTrace();   
             return null ;   
          }   
          return con;   
      }else if(count  max){   
         try{   
             for(int i=0;i<addCount;i++){   
                con=this.createCon(classDriver,url,user,password);   
                connectionPool.addElement(con);   
             }   
              count+=addCount;   
         }catch(Exception e){   
            e.printStackTrace();   
         }   
         con = getConnection();   
          return con;   
   
      }   
      return null ;   
   }   
   public synchronized void freeConnection(Connection con){   
     connectionPool.addElement(con);   
   }   
   private Connection createCon(String driver,String url,String user,String pwd)throws SQLException{   
       Connection tempCon=null;   
       String className=driver;   
         try{   
          Class.forName(className).newInstance();   
         }   
         catch(Exception e)   
         {   
           throw new SQLException("not class");   
         }   
         try {   
   
           // setConnectionProvider((ConnectionProvider)conClass.newInstance());   
            tempCon=DriverManager.getConnection(url,user,pwd);   
   
            }   
          catch(Exception e) {   
               throw new SQLException("can not create a connection");   
   
          }   
      return tempCon;   
   }   
   public synchronized void destroy(){   
       Enumeration allConnections = connectionPool.elements();   
       while (allConnections.hasMoreElements()) {   
        con = (Connection) allConnections.nextElement();   
         try {   
            con.close();   
   
         } catch (SQLException e) {   
               e.printStackTrace();   
         }   
       }   
        connectionPool.removeAllElements();   
   }   
}   



