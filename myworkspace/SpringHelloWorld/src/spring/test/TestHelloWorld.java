package spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.bean.HelloWorld;

//spring ��������web��Ŀ��Ҳ�������ڷ� web��Ŀ
public class TestHelloWorld {
	public static void main(String[] args) {
		
		//ʹ���ַ������ַ���������Ϊ��Դ(�������context���� ��XML�ļ�)�Ķ�λ·��
		//�����ļ�һ���� applicationContext.xml��Ҳ�����Զ���
		ApplicationContext actx = new ClassPathXmlApplicationContext("config.xml");
		// ʹ��getBean(String)������ȡ��bean��ʵ��
		HelloWorld helloWorld = (HelloWorld) actx.getBean("helloWorld");
		helloWorld.say();
	}
}