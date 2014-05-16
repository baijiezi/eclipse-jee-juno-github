package utils.JUtils.chenbug.chineseren;
   
/**  
 *  封装Log4j的使用:info,debug和error();  
 *  开发者：chineseren  
 *  最后修改时间：2004-7-6  
 *  功能：实现对debug的管理  
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
  //实现Log4j使用方法的封装   
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



