package jms.activemq.myexample.spring;
 
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
public class SpringJmsTestMain {
 
    public static void main(String[] args) throws InterruptedException {
 
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "file:D:/eclipse-jee-juno/mavenspace/maven.springmvc/src/main/webapp/WEB-INF/applicationContext.xml" });
 
        SpringPublisher publisher = (SpringPublisher) context
                .getBean("springPublisher");
        publisher.start();
    }
}