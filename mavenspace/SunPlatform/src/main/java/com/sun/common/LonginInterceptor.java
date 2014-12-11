package com.sun.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sun.entity.User;

/** 
 * ����session��ʱ�������� 
 */  
public class LonginInterceptor  implements HandlerInterceptor{  
      
    public String[] allowUrls;//��û���ֿ���ֱ�����ò����ص���Դ�������ڴ����������ų�  
      
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
            return true;  //����true�������������ú����ŵ���postHandle(),  afterCompletion()  
        }else{
            // δ��¼  ��ת����¼ҳ��
            throw new SessionTimeoutException();//���ص������ļ��ж����·��  
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
