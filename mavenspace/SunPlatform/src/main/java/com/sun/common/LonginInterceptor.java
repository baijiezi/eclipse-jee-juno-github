package com.sun.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sun.entity.User;

/** 
 * 处理session超时的拦截器 
 */  
public class LonginInterceptor  implements HandlerInterceptor{  
      
    public String[] allowUrls;//还没发现可以直接配置不拦截的资源，所以在代码里面来排除  
      
    public void setAllowUrls(String[] allowUrls) {  
        this.allowUrls = allowUrls;  
    }  
  
 
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  
            Object arg2) throws Exception {
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
        if(null != allowUrls && allowUrls.length>=1)
            for(String url : allowUrls) {
                if(requestUrl.contains(url)) {
                    return true;
                }
            }
        User user = (User) request.getSession().getAttribute("user1");
        if(user != null) {
            return true;  //返回true，则这个方面调用后会接着调用postHandle(),afterCompletion()
        }else{
            // 未登录  跳转到登录页面
            throw new SessionTimeoutException();//返回到配置文件中定义的路径
        }
    }
    public void afterCompletion(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2, ModelAndView arg3) throws Exception {
    }
}
