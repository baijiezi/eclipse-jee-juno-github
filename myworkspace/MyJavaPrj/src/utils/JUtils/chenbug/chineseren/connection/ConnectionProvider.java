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
 * ���� һ�������ṩ�ߣ�  
 * @param classDriver ����Դ  
 * @param url ���ݿ��JDBC URL  
 * @param user �û���  
 * @param password �û�����  
 * @param min ��С������  
 * @param max ���������  
 * @param addCount ÿ���´����ĸ�����  
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
 * �����ӳػ�ȡ��������.����ָ���ͻ������ܹ��ȴ����ʱ��  
 * �μ�ǰһ��getConnection()����.  
 *  
 * @param timeout �Ժ���Ƶĵȴ�ʱ������  
 */   
 public synchronized Connection getConnection(long timeout) {   
     long startTime = System.currentTimeMillis();   
     while ((con = getConnection()) == null) {   
       try {   
       wait(timeout);   
       }   
       catch (InterruptedException e) {}   
     if ((System.currentTimeMillis() - startTime) >= timeout) {   
     // wait()���ص�ԭ���ǳ�ʱ   
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



