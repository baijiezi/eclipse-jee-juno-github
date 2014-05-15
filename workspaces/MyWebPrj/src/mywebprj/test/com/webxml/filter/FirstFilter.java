package mywebprj.test.com.webxml.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FirstFilter implements Filter{

	private FilterConfig filterConfig;
	
	@Override
	public void destroy() {
		System.out.println("FirstFilter销毁"); 
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
        System.out.println("FirstFilter过滤前"); 
        System.out.println(filterConfig.getInitParameter("param1")); 
        chain.doFilter(request, response);//放行。让其走到下个链或目标资源中 
        System.out.println("FirstFilter过滤后"); 
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("FirstFilter初始化"); 
        this.filterConfig = filterConfig; 
	}


}
