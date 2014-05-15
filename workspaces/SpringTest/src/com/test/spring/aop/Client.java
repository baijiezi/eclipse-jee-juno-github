package com.test.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");
        IBaseBusiness business = (IBaseBusiness ) context.getBean("businessProxy");
        business.delete("猫");
    }

}
