package utils.JUtils.chenbug;
   
import java.net.*;   
/**  
 * <p>Title: NetSpeedTester</p>  
 * <p>Description: 连接速度测试</p>  
 * <p>Copyright: Copyright (c) 2004</p>  
 * <p>Company: </p>  
 * @author chenbug  
 * @version 1.0  
 */   
   
public class NetSpeedTester {   
   
  public static final int DEFAULT_CONNECT_COUNT = 4;   
   
  /**  
   * 连接次数  
   */   
  private int connectCount = DEFAULT_CONNECT_COUNT;   
   
  /**  
   * 连接url  
   */   
  private String httpURL;   
   
  /**  
   * 构造函数  
   * @param httpURL String  
   */   
  public NetSpeedTester() {   
   
  }   
   
   
  /**  
   * 构造函数  
   * @param httpURL String  
   */   
  public NetSpeedTester(String httpURL) {   
    this.httpURL = httpURL;   
  }   
   
  /**  
   * 测试函数  
   * @throws MalformedURLException  
   * @return long  
   */   
  public long testConnection() throws NetSpeedTester.TestException{   
    if (Tools.isNullOrEmpty(httpURL)) {   
      throw new NetSpeedTester.TestException("httpURL没有初始化");   
    }   
    long iCount = 0;   
    URL url = null;   
    try {   
      url = new URL(httpURL);   
    } catch (java.net.MalformedURLException e) {   
      throw new NetSpeedTester.TestException(e.toString());   
    }   
    for (int i = 0; i<connectCount; i++) {   
      try {   
        iCount += testConnection(url);   
      } catch (java.io.IOException e) {   
        throw new NetSpeedTester.TestException(e.toString());   
      }   
    }   
    return iCount/connectCount;   
  }   
   
  private long testConnection(URL url) throws java.io.IOException{   
    long time_start = System.currentTimeMillis();   
    URLConnection con = url.openConnection();   
    con.connect();   
    long time_end = System.currentTimeMillis();   
    return time_end - time_start;   
  }   
   
  public int getConnectCount() {   
    return connectCount;   
  }   
  public void setConnectCount(int connectCount) {   
    this.connectCount = connectCount;   
  }   
  public String getHttpURL() {   
    return httpURL;   
  }   
  public void setHttpURL(String httpURL) {   
    this.httpURL = httpURL;   
  }   
   
  public class TestException extends Exception{   
   
    private String message ;   
   
    public TestException() {   
      this.message = "";   
    }   
   
    public TestException(String message) {   
      this.message = message;   
    }   
   
    public String toString() {   
      return "网速测试异常："+message;   
    }   
  }   
   
   
}   


