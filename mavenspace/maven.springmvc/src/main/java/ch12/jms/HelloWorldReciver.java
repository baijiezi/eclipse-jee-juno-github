package ch12.jms;

import javax.jms.Destination;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class HelloWorldReciver
{

 public static void main(String args[]) throws Exception
 {
  ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "/ch12/JMS/applicationContext.xml" });
  JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
  Destination destination = (Destination) context.getBean("destination");
  System.out.println("will wait:" + jmsTemplate.getReceiveTimeout()+ " seconds for message");
  TextMessage msg = (TextMessage) jmsTemplate.receive(destination);
  System.out.println("reviced msg is:" + msg.getText());
 }

}