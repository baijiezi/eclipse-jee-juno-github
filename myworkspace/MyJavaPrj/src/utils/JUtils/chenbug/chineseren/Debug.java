package utils.JUtils.chenbug.chineseren;
   
/**  
 *  ��װLog4j��ʹ��:info,debug��error();  
 *  �����ߣ�chineseren  
 *  ����޸�ʱ�䣺2004-7-6  
 *  ���ܣ�ʵ�ֶ�debug�Ĺ���  
 */   
import org.apache.log4j.Logger;   
   
public final class Debug {   
  //Log4j singleton instance   
  private static Logger logger;   
  static {   
    if(Debug.logger == null) {   
      Debug.logger = Logger.getLogger("org.apache.torque");   
    }   
  }   
  //ʵ��Log4jʹ�÷����ķ�װ   
  public static void info(Object p0,Throwable p1){   
    Debug.logger.info(p0,p1);   
  }   
  public static void info(Object p0){   
    Debug.logger.info(p0);   
  }   
  public static void debug(Object p0,Throwable p1){   
    Debug.logger.debug(p0,p1);   
  }   
  public static void debug(Object p0){   
    Debug.logger.debug(p0);   
  }   
  public static void error(Object p0,Throwable p1){   
    Debug.logger.error(p0,p1);   
  }   
  public static void error(Object p0){   
    Debug.logger.error(p0);   
  }   
   
  public static final boolean debuggingOn = true;   
  public static void println(String msg) {   
    if(debuggingOn) {   
      Debug.debug(msg);   
    }   
  }   
   
}   



