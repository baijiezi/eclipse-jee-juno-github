package utils.JUtils.chenbug.chineseren.connection;

import java.sql.*;   
import java.io.*;   
import java.util.*;   
public class ConnectionManager {   
   
 //   private static ConnectionProvider connectionProvider;   
    private static ConnectionPool connectionPool;   
    private static String driver ="COM.ibm.db2.jdbc.net.DB2Driver";   
    //private static String url ="jdbc:db2://202.101.166.148:6789/CIXI";   
  //  private static String url ="jdbc:db2://192.168.2.13:6789/WUYI";   
    private static String url ="jdbc:db2://192.168.2.154:6789/eshop";   
    //private static String url ="jdbc:db2://192.168.2.59:6789/lishui";   
   // private static String url ="jdbc:db2://202.107.248.2:6789/lishui";   
    private static String user="ecomuser";   
    private static String password="ecomuser";   
    //private static String user="db2admin";   
   // private static String password="|+_)(*&^%$#@!";   
   // private static String password="Mcfd16214&yN";   
 //   private static int min=20;   
 //   private static int max=20;   
 //   private static int addCount=1;   
      private static final int maxConnections =20;   
      private static final int minConnections =3;   
      private static final int expiryTime =60*30;  // 30 minutes   
      private static final int connectionWaitTimeout=10;  // 10 seconds   
      private static final int threadWaitTime=3*60;  // 10 seconds   
      /**  
       * 得到连接  
       * @return  
       * @throws SQLException  
       */   
    public static Connection getConnection() throws SQLException {   
       Connection con = null;   
      if(connectionPool==null){   
         try{   
          System.out.println("To here: connection pool is empty ,create a pool and 3 new connection!");   
          connectionPool = new ConnectionPool(driver,url,user,password,maxConnections,expiryTime,connectionWaitTimeout,minConnections,threadWaitTime);;   
         }catch(Exception e){   
           throw new  SQLException();   
          }   
       }   
        System.out.println("To here: get a connection!!!!!!!!!!!!!");   
        con = connectionPool.getConnection(user,password);   
       if(con == null){   
        throw new SQLException();   
       }   
        return con;   
    }   
    /**  
     * 释放连接  
     * @return  
     * @throws SQLException  
     */   
   
    public static void free(Connection con){   
      connectionPool.releaseConnection(con);   
    }   
   
   public static void  destroy() throws Throwable{   
       connectionPool.finalize();   
   }   
}   
   
/**  
 *  
//JNDI连接代码  
  
  
  import java.io.PrintStream;  
  import java.sql.Connection;  
  import java.sql.SQLException;  
  import javax.naming.*;  
  import javax.sql.DataSource;  
  public class ConnectionManager{  
    private static Context initCtx;  
    private static DataSource instance;  
    private static String jndiName = "jdbc/lishui";  
  
    public ConnectionManager(String jndiName)  
        throws NamingException  
    {  
        this.initCtx = new InitialContext();  
        if(initCtx == null)  
        {  
            throw new NamingException("Boom - No Context");  
        } else  
        {  
            this.jndiName = jndiName;  
            this.instance = lookup(jndiName);  
            return;  
        }  
    }  
  
    public static Connection getConnection()  
    {  
        Connection conn = null;  
        try  
        {  
            if(instance == null)  
                new ConnectionManager(jndiName);  
            conn = instance.getConnection();  
        }  
        catch(SQLException e)  
        {  
            System.out.println("\u6570\u636E\u5E93\u8FDE\u63A5\u51FA\u9519\uFF01");  
            e.printStackTrace();  
        }  
        catch(NamingException e)  
        {  
            System.out.println("\u547D\u540D\u8BBF\u95EE\u9519\u8BEF\uFF01");  
            e.printStackTrace();  
        }  
        return conn;  
  
    }  
  
    public DataSource lookup(String jndiName)  
        throws NamingException  
    {  
        DataSource ds = (DataSource)initCtx.lookup(jndiName);  
        return ds;  
    }  
  
    public static void free(Connection con){  
       if(con != null){  
         try {  
          con.close();  
  
       } catch (SQLException e) {  
             e.printStackTrace();  
         }  
       }  
    }  
  
   public static void  destroy(){  
  
   }  
}  
*/   
   
   
   



