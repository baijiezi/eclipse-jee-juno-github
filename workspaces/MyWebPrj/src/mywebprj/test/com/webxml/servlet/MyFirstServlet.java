package mywebprj.test.com.webxml.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyFirstServlet extends javax.servlet.http.HttpServlet{
	
	//访问地址 http://localhost:8080/MyWebPrj/myservlet
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServletException{
		
		System.out.println("MyFirstServlet");
		
		HttpSession session = request.getSession();
	}
	
}
