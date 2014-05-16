package spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.bean.HelloWorld;

//spring 可以用在web项目，也可以用在非 web项目
public class TestHelloWorld {
	public static void main(String[] args) {
		
		//使用字符串或字符串数组作为资源(比如组成context定义 的XML文件)的定位路径
		//配置文件一般是 applicationContext.xml，也可以自定义
		ApplicationContext actx = new ClassPathXmlApplicationContext("config.xml");
		// 使用getBean(String)方法就取得bean的实例
		HelloWorld helloWorld = (HelloWorld) actx.getBean("helloWorld");
		helloWorld.say();
	}
}