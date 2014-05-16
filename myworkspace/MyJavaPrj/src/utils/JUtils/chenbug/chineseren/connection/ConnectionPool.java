package utils.JUtils.chenbug.chineseren.connection;

import java.io.PrintWriter;   
import java.sql.SQLException;   
import java.util.HashMap;   
import java.util.Map;   
import java.util.Stack;   
import java.sql.*;   
import java.util.*;   
import java.io.*;   
   
/**  
   开发者：金诚  
   创建日期：2004-2-26  
   最后修改日期：2004-2-27  
   功能介绍：连接池控制程序  
 */   
public class ConnectionPool {   
   
  public ConnectionPool() {   
  }   
   
  private String driver;   
  /**  
   * 连接池  
   */   
  //  private Stack pool;   
  //   private Hashtable pool;   
  private Vector pool;   
  /**  
   * 数据库url  
   */   
  private String url;   
   
  /**  
   * 用户名  
   */   
  private String username;   
   
  /**  
   * 密码  
   */   
  private String password;   
   
  /**  
   * 当前总的连接数  
   */   
  //  private int totalConnections;   
   
  /**  
   * 最大连接数  
   */   
  private int maxConnections;   
  /**  
   * 最小连接数  
   */   
  private int minConnections;   
  /**  
   * 连接的存活时间  
   */   
  private int expiryTime; // 1 hour   
   
  /**  
   * 监测器  
   */   
  private Monitor monitor;   
  /**  
   * 等待连接的线程数量  
   */   
  private int waitCount = 0;   
  /**  
   * 连接超时时间  
   */   
  private long connectionWaitTimeout;   
   
  /**  
   * 连接存活时间表  
   */   
  private Map timeStamps;   
  /**  
   * 监控线程存活时间  
   */   
  private long threadWaitTime;   
   
  /**  
   * 初始化数据  
   */   
  ConnectionPool(String driver, String url, String username,   
                 String password, int maxConnections, int expiryTime,   
                 int connectionWaitTimeout, int minConnections,   
                 int threadWaitTime   
                 ) {   
    //     totalConnections = 0;   
    //      pool = new Stack();   
    pool = new Vector();   
    timeStamps = new HashMap();   
    Connection con = null;   
    this.driver = driver;   
    this.url = url;   
    this.username = username;   
    this.password = password;   
    if (maxConnections > 0) {   
      this.maxConnections = maxConnections;   
    }   
    else {   
      this.maxConnections = 1;   
    }   
    if (minConnections > 0) {   
      this.minConnections = minConnections;   
    }   
    else {   
      this.minConnections = 1;   
    }   
    if (expiryTime > 0) {   
      this.expiryTime = expiryTime * 1000;   
    }   
    else {   
      this.expiryTime = 3600 * 1000; // 1 hour   
    }   
    if (connectionWaitTimeout > 0) {   
      this.connectionWaitTimeout = connectionWaitTimeout * 1000;   
    }   
    else {   
      this.connectionWaitTimeout = 10 * 1000; // 10 seconds   
    }   
    if (threadWaitTime > 0) {   
      this.threadWaitTime = threadWaitTime;   
    }   
    else {   
      this.threadWaitTime = 3 * 60 * 1000;   
    }   
    for (int i = 0; i  this.minConnections; i++) {   
      try {   
        con = createNewConnection(this.driver, this.url, username, password);   
        this.releaseConnection(con);   
      }   
      catch (Exception e) {   
   
      }   
    }   
  //     monitor = new Monitor();   
  //      monitor.setDaemon(true);   
  //      monitor.start();   
   
  }   
   
  /**  
   * 取得连接  
   */   
  final synchronized Connection getConnection(String username, String password) throws   
      SQLException {   
    if (username != this.username || password != this.password) {   
      throw new SQLException("Username and password are invalid.");   
    }   
   
    Connection con = null;   
    //  if (pool.isEmpty() && pool.size()  maxConnections)   
    //如果连接池为空，建立一个新连接   
    System.out.println("pool size:" + pool.size());   
    System.out.println("Map size:"+timeStamps.size());   
    if (pool.isEmpty() && timeStamps.size()<maxConnections) {   
      con = createNewConnection(this.driver, this.url, username, password);   
    }   
    else { //否则从连接池取得一个空闲连接   
      try {   
        con = getInternalConnection();   
      }   
      catch (Exception e) {   
        throw new SQLException(e.getMessage());   
      }   
    }   
    return con;   
  }   
   
  /**  
   * 创建一个新的连接  
   */   
  private Connection createNewConnection(String driver, String url,   
                                         String username, String password) throws   
      SQLException {   
    Connection con = null;   
    String className = driver;   
    try {   
      Class.forName(className).newInstance();   
    }   
    catch (Exception e) {   
      throw new SQLException("not class");   
    }   
    try {   
      con = DriverManager.getConnection(url, username, password);   
      System.out.println("To here:Create a new Connection!!!!!!!!!!!!!!!!!");   
    }   
    catch (Exception e) {   
      throw new SQLException("Exception :Can not create a connection");   
    }   
   
    long currentTime = System.currentTimeMillis();   
    /**  
     * 引入ratio，防止连接池初始化的所有链接同时失效。  
     */   
    double ratio = (1.0 * pool.size()) / maxConnections;   
    currentTime -= expiryTime * 0.25 * (1.0 - ratio);   
   
    timeStamps.put(con, new Long(currentTime));   
   
    return con;   
   
  }   
   
  /**  
   * 设法从连接池取得一个连接  
   */   
  private synchronized Connection getInternalConnection() throws Exception {   
   
    // 如果有其他等待线程或连接池为空，等待。   
    if (waitCount > 0 || pool.isEmpty()) {   
      System.out.println("判断连接池不为空，程序走到这里，同步，发现连接池已经空或有等待线程！");   
      System.out.println("waitCount:" + waitCount);   
      System.out.println("pool size:" + pool.size());   
      try {   
        waitCount++;   
        wait(connectionWaitTimeout); //等待   
      }   
      catch (InterruptedException ignored) {   
      }   
      //唤醒等待，并判断连接池是否为空，如果空，抛超时异常。   
      finally {   
        waitCount--;   
      }   
      if (pool.isEmpty()) {   
        throw new Exception();   
      }   
    } //不为空，设法从连接池取得连接   
   
    return popConnection();   
   
  }   
   
  /**  
   *从连接池取得一空闲连接  
   */   
  private Connection popConnection() throws Exception {   
    for (int i = 0; i  pool.size(); i++) {   
      Connection con = (Connection) pool.elementAt(i); //????????????这里可能出问题   
      // 取得一有效连接，即存活期小于规定时间的   
      if (isValid(con)) {   
        pool.remove(i);   
        return con;   
      }   
      else {   
        // 连接已过存活期，杀死。   
        try {   
          con.close();   
        }   
        catch (Exception e) {   
        }   
        timeStamps.remove(con);   
        pool.remove(i);   
        // 如果所有连接都已超时，生成新的连接   
        if (pool.isEmpty()) {   
          return createNewConnection(this.driver, this.url, username, password);   
        }   
      }   
    }   
    // 所有逻辑判断后，仍然无法取得连接，抛异常   
    throw new Exception("Assertion failure: Attempted to pop "   
                        + "connection from empty pool!");   
   
  }   
   
  /**  
   * 是否超过存活期  
   */   
  private boolean isExpired(Connection connection) {   
    if (timeStamps.get(connection) == null)   
      return true;   
    return (expiryTime    
            (System.currentTimeMillis() -   
             ( (Long) timeStamps.get(connection)).longValue()));   
  }   
   
  /**  
   *是否有效，即没有超过存活期  
   */   
  private boolean isValid(Connection connection) {   
    return!isExpired(connection);   
  }   
   
  /**  
   * 注销  
   */   
  protected void finalize() throws Throwable {   
    shutdown();   
  }   
   
  /**  
   * 关闭所有连接池  
   */   
  void shutdown() {   
    if (pool != null) {   
      for (int i = 0; i  pool.size(); i++) {   
        try {   
          ( (Connection) pool.elementAt(i)).close();   
        }   
        catch (SQLException ignore) {   
        }   
        finally {   
          pool.remove(i);   
        }   
      }   
    }   
    //停止监控线程   
       monitor.shutdown();   
  }   
   
  /**  
   *连接池数量减一  
   */   
  /*  void decrementConnections()  
    {  
   //     totalConnections--;  
        notify();  
    }*/   
   
  /**  
   *关闭连接，如果没有超时，送入连接池，否则杀死  
   */   
  public synchronized void releaseConnection(Connection con) {   
    if (isValid(con)) {   
      pool.add(con);   
      notify();   
    }   
    else {   
      closeConnection(con);   
    }   
  }   
   
  /**  
   *杀死连接  
   */   
  private void closeConnection(Connection con) {   
    try {   
   
      con.close();   
    }   
    catch (Exception e) {   
    }   
    finally {   
      timeStamps.remove(con);   
    }   
  }   
   
  /**  
   *监听程序，监测pool中的连接池是否过时  
   */   
  protected class Monitor   
      extends Thread {   
    /** true if the monot is running */   
    private boolean isRun = true;   
   
    /**  
     * run method for the monitor thread  
     */   
    public void run() {   
      StringBuffer buf = new StringBuffer();   
      Connection con = null;   
      while (isRun) {   
        //              System.out.println(PubinfoDate.getFormatDateTime(new java.util.Date())+"monitor thread is running!");   
        for (int i = 0; i  pool.size(); i++) {   
          con = (Connection) pool.elementAt(i);   
          if (!isValid(con)) {   
            closeConnection(con); //关闭连接。   
            pool.remove(i);   
            try {   
              con = createNewConnection(driver, url, username, password);   
              releaseConnection(con);   
            }   
            catch (Exception e) {   
            }   
          }   
   
        }   
        try {   
          Thread.sleep(threadWaitTime / 2);   
        }   
        catch (InterruptedException ignored) {   
        }   
      }   
    }   
   
    /**  
     * Shut down the monitor  
     */   
    public void shutdown() {   
      isRun = false;   
    }   
  }   
   
}  


