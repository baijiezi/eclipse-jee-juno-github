package com.test.struts.action;

/**
 * 访问地址 http://localhost:8080/StrutsTest/first/firstaction?msg=aaa
 * @author Asus
 *
 */
public class First {  
    private String  msg;  
      
      
    public String getMsg() {  
        return msg;  
    }  
  
  
    public void setMsg(String msg) {  
        this.msg = msg;  
    }  
  
  
    public String execute()  
     {  
    	msg = "这是第一个struts程序" + msg;  
    	return "success";  
     }  
    
    //一个Controller类里面可以有多个Action方法
    public String execute2()  
    {  
    	msg = "这里是execute2";  
    	return "success";  
    }
    
}  