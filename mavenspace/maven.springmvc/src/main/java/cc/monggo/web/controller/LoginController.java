package cc.monggo.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import cc.monggo.domain.LoginForm;
import cc.monggo.service.TestService;

@Controller
public class LoginController {
    @RequestMapping(value="login")
   public ModelAndView login(HttpServletRequest request,HttpServletResponse response,LoginForm command ){
    	System.out.println("开始LoginController");
    	
    	ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext() );
    	TestService testService = (TestService)ctx.getBean("testService");
    	testService.sayHello();
    	
        String username = command.getUsername();
        ModelAndView mv = new ModelAndView("/index/index","command","LOGIN SUCCESS, " + username);
        return mv;
    }
}
