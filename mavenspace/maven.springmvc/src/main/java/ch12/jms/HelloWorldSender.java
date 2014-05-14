package ch12.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;


/**
 * ch12.jms这个包下是一个单独的测试项目，不需要启动tomcat
1.启动activemq中bin 下的activemq.bat
2.先运行 HelloWorldSender
3.再运行 HelloWorldReciver
4.结果:
will wait:-1 seconds for message
reviced msg is:大家好 这个是测试！
 */
public class HelloWorldSender
{
 public static void main(String args[]) throws Exception
 {
  ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "/ch12/jms/applicationContext.xml" });
  JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
  Destination destination = (Destination) context.getBean("destination");
  jmsTemplate.send
  (
   destination, new MessageCreator()
   {
    public Message createMessage(Session session) throws JMSException
    {
     return session.createTextMessage("大家好这个是测试！");
    }
   }
  );
 }

}