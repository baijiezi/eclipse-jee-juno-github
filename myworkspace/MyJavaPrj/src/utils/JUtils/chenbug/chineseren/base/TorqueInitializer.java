package utils.JUtils.chenbug.chineseren.base;
   
import java.util.*;   
import javax.servlet.*;   
import javax.servlet.http.*;   
   
import org.apache.torque.Torque;   
import org.apache.torque.TorqueException;   
   
/**  
 *  
 *  �����ߣ�����  
 *  ����޸�ʱ�䣺2004-2-11  
 *  ���ܣ�ϵͳ��ʼ��ʱ�Զ�װ�룬Torque�ĳ�ʼ����Ӧ��ϵͳ�ĳ�ʼ������  
 */   
public class TorqueInitializer   
    extends HttpServlet {   
  public final void init() throws ServletException {   
    String config = getServletConfig().getInitParameter("torqueConfig");   
    ServletContext servletContext = getServletContext();   
    String configFile = servletContext.getRealPath(config);   
    System.out.println("TorqueConfig: " + config);   
    System.out.println("TorqueConfigFile: " + configFile);   
    if(configFile == null) {   
      throw new ServletException("Could not find torque configuration file: " +   
          config);   
    }   
    try {   
      Torque.init(configFile);   
      System.out.println("==========================");   
      System.out.println("Torque has inited!");   
      System.out.println("==========================");   
    } catch(TorqueException e) {   
      throw new ServletException("Failed to configure torque with file: " +   
          configFile, e);   
    }   
  }   
   
  public final void destroy() {   
    Torque.shutdown();   
  }   
}  



