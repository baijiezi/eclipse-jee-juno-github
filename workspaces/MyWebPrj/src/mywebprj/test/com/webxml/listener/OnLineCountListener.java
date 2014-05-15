package mywebprj.test.com.webxml.listener;

// ==================== Program Discription ===================== 
// 程序名称：示例14-9 : EncodingFilter .java 
// 程序目的：学习使用监听器 
// ============================================================== 
import javax.servlet.http.*; 
import javax.servlet.*; 

public class OnLineCountListener implements HttpSessionListener, ServletContextListener,ServletContextAttributeListener 
{ 
	private int count; 
	private ServletContext context = null; 
	
	public OnLineCountListener() 
	{ 
		System.out.println("初始化OnLineCountListener");
		count=0; 
		//setContext(); 
	} 
	//创建一个session时激发 
	public void sessionCreated(HttpSessionEvent se) 
	{ 
		System.out.println("调用sessionCreated");
		count++; 
		setContext(se); 
	
	} 
	//当一个session失效时激发 
	public void sessionDestroyed(HttpSessionEvent se) 
	{ 
		System.out.println("调用sessionDestroyed");
		count--; 
		setContext(se); 
	} 
	//设置context的属性，它将激发attributeReplaced或attributeAdded方法 
	public void setContext(HttpSessionEvent se) 
	{ 
		System.out.println("调用setContext");
		se.getSession().getServletContext(). 
		setAttribute("onLine",new Integer(count)); 
	} 
	//增加一个新的属性时激发 
	public void attributeAdded(ServletContextAttributeEvent event) { 
		System.out.println("调用attributeAdded");
		log("attributeAdded('" + event.getName() + "', '" + 
		event.getValue() + "')"); 
	
	} 
	//删除一个新的属性时激发 
	public void attributeRemoved(ServletContextAttributeEvent event) { 
		System.out.println("调用attributeRemoved");
		log("attributeRemoved('" + event.getName() + "', '" + 
		event.getValue() + "')"); 
	
	} 
	
	//属性被替代时激发 
	public void attributeReplaced(ServletContextAttributeEvent event) { 
		System.out.println("调用attributeReplaced");
		log("attributeReplaced('" + event.getName() + "', '" + 
		event.getValue() + "')"); 
	} 
	//context删除时激发 
	public void contextDestroyed(ServletContextEvent event) { 
		System.out.println("调用contextDestroyed");
		log("contextDestroyed()"); 
		this.context = null; 
	
	} 
	//context初始化时激发 
	public void contextInitialized(ServletContextEvent event) { 
		System.out.println("调用contextInitialized");
		this.context = event.getServletContext(); 
		log("contextInitialized()"); 
	
	} 
	private void log(String message) { 
		System.out.println("ContextListener: " + message); 
	} 
}