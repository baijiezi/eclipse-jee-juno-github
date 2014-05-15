package com.test.spring.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.test.spring.ioc.bean.Hello;

/**
 * 访问地址  http://localhost:8080/SpringTest/hello
 * @author Asus
 *
 */
public class HelloWorldController implements Controller {  
    @Override  
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {  
       //1、收集参数、验证参数  
       //2、绑定参数到命令对象  
       //3、将命令对象传入业务对象进行业务处理  
       //4、选择下一个页面  
       ModelAndView mv = new ModelAndView();  
       //添加模型数据 可以是任意的POJO对象  
       mv.addObject("message", "Hello HelloWorldController!");  
       //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面  
       mv.setViewName("hello");  
       
       System.out.println("===========");
       ApplicationContext ctx = 
    		   WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());
       Hello helloBean = (Hello)ctx.getBean("sayHello");
       helloBean.say(" bean");
       
       return mv;  
    }  
}