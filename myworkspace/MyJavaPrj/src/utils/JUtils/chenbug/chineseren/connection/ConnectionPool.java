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
   �����ߣ����  
   �������ڣ�2004-2-26  
   ����޸����ڣ�2004-2-27  
   ���ܽ��ܣ����ӳؿ��Ƴ���  
 */   
public class ConnectionPool {   
   
  public ConnectionPool() {   
  }   
   
  private String driver;   
  /**  
   * ���ӳ�  
   */   
  //  private Stack pool;   
  //   private Hashtable pool;   
  private Vector pool;   
  /**  
   * ���ݿ�url  
   */   
  private String url;   
   
  /**  
   * �û���  
   */   
  private String username;   
   
  /**  
   * ����  
   */   
  private String password;   
   
  /**  
   * ��ǰ�ܵ�������  
   */   
  //  private int totalConnections;   
   
  /**  
   * ���������  
   */   
  private int maxConnections;   
  /**  
   * ��С������  
   */   
  private int minConnections;   
  /**  
   * ���ӵĴ��ʱ��  
   */   
  private int expiryTime; // 1 hour   
   
  /**  
   * �����  
   */   
  private Monitor monitor;   
  /**  
   * �ȴ����ӵ��߳�����  
   */   
  private int waitCount = 0;   
  /**  
   * ���ӳ�ʱʱ��  
   */   
  private long connectionWaitTimeout;   
   
  /**  
   * ���Ӵ��ʱ���  
   */   
  private Map timeStamps;   
  /**  
   * ����̴߳��ʱ��  
   */   
  private long threadWaitTime;   
   
  /**  
   * ��ʼ������  
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
   * ȡ������  
   */   
  final synchronized Connection getConnection(String username, String password) throws   
      SQLException {   
    if (username != this.username || password != this.password) {   
      throw new SQLException("Username and password are invalid.");   
    }   
   
    Connection con = null;   
    //  if (pool.isEmpty() && pool.size()  maxConnections)   
    //������ӳ�Ϊ�գ�����һ��������   
    System.out.println("pool size:" + pool.size());   
    System.out.println("Map size:"+timeStamps.size());   
    if (pool.isEmpty() && timeStamps.size()<maxConnections) {   
      con = createNewConnection(this.driver, this.url, username, password);   
    }   
    else { //��������ӳ�ȡ��һ����������   
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
   * ����һ���µ�����  
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
     * ����ratio����ֹ���ӳس�ʼ������������ͬʱʧЧ��  
     */   
    double ratio = (1.0 * pool.size()) / maxConnections;   
    currentTime -= expiryTime * 0.25 * (1.0 - ratio);   
   
    timeStamps.put(con, new Long(currentTime));   
   
    return con;   
   
  }   
   
  /**  
   * �跨�����ӳ�ȡ��һ������  
   */   
  private synchronized Connection getInternalConnection() throws Exception {   
   
    // ����������ȴ��̻߳����ӳ�Ϊ�գ��ȴ���   
    if (waitCount > 0 || pool.isEmpty()) {   
      System.out.println("�ж����ӳز�Ϊ�գ������ߵ����ͬ�����������ӳ��Ѿ��ջ��еȴ��̣߳�");   
      System.out.println("waitCount:" + waitCount);   
      System.out.println("pool size:" + pool.size());   
      try {   
        waitCount++;   
        wait(connectionWaitTimeout); //�ȴ�   
      }   
      catch (InterruptedException ignored) {   
      }   
      //���ѵȴ������ж����ӳ��Ƿ�Ϊ�գ�����գ��׳�ʱ�쳣��   
      finally {   
        waitCount--;   
      }   
      if (pool.isEmpty()) {   
        throw new Exception();   
      }   
    } //��Ϊ�գ��跨�����ӳ�ȡ������   
   
    return popConnection();   
   
  }   
   
  /**  
   *�����ӳ�ȡ��һ��������  
   */   
  private Connection popConnection() throws Exception {   
    for (int i = 0; i  pool.size(); i++) {   
      Connection con = (Connection) pool.elementAt(i); //????????????������ܳ�����   
      // ȡ��һ��Ч���ӣ��������С�ڹ涨ʱ���   
      if (isValid(con)) {   
        pool.remove(i);   
        return con;   
      }   
      else {   
        // �����ѹ�����ڣ�ɱ����   
        try {   
          con.close();   
        }   
        catch (Exception e) {   
        }   
        timeStamps.remove(con);   
        pool.remove(i);   
        // ����������Ӷ��ѳ�ʱ�������µ�����   
        if (pool.isEmpty()) {   
          return createNewConnection(this.driver, this.url, username, password);   
        }   
      }   
    }   
    // �����߼��жϺ���Ȼ�޷�ȡ�����ӣ����쳣   
    throw new Exception("Assertion failure: Attempted to pop "   
                        + "connection from empty pool!");   
   
  }   
   
  /**  
   * �Ƿ񳬹������  
   */   
  private boolean isExpired(Connection connection) {   
    if (timeStamps.get(connection) == null)   
      return true;   
    return (expiryTime    
            (System.currentTimeMillis() -   
             ( (Long) timeStamps.get(connection)).longValue()));   
  }   
   
  /**  
   *�Ƿ���Ч����û�г��������  
   */   
  private boolean isValid(Connection connection) {   
    return!isExpired(connection);   
  }   
   
  /**  
   * ע��  
   */   
  protected void finalize() throws Throwable {   
    shutdown();   
  }   
   
  /**  
   * �ر��������ӳ�  
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
    //ֹͣ����߳�   
       monitor.shutdown();   
  }   
   
  /**  
   *���ӳ�������һ  
   */   
  /*  void decrementConnections()  
    {  
   //     totalConnections--;  
        notify();  
    }*/   
   
  /**  
   *�ر����ӣ����û�г�ʱ���������ӳأ�����ɱ��  
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
   *ɱ������  
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
   *�������򣬼��pool�е����ӳ��Ƿ��ʱ  
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
            closeConnection(con); //�ر����ӡ�   
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


