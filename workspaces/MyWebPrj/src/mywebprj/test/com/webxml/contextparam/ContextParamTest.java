package mywebprj.test.com.webxml.contextparam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContextParamTest extends HttpServlet{
	
	//访问地址 http://localhost:8080/MyWebPrj/contextparam
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
        response.setContentType("text/html;UTF-8");  
        response.setCharacterEncoding("UTF-8");//处理乱码，使发送和接收都在UTF-8中进行  
                                               //因为默认是ISO  
        PrintWriter out = response.getWriter();  
          
        String name = this.getServletContext().getInitParameter("name");  
          
        out.println(name);  
    }  
}
