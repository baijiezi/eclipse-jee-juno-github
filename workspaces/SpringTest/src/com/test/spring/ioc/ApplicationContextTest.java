package com.test.spring.ioc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.test.spring.ioc.bean.Hello;


/**
 * 在非WEB模式下，Spring配置文件的加载方式
 * @author Asus
 *
 */
public class ApplicationContextTest {
	public static void main(String[] args){
		//通过文件路径加载 Spring配置文件，并执行其中的一个Bean
		ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		Hello hello = (Hello)context.getBean("sayHello");
		hello.say("Word");
		Log logger = LogFactory.getLog(ApplicationContextTest.class);
		logger.info("aaaaaaaa");
		System.out.println("bbbbbbbbbbb");
	}
}
