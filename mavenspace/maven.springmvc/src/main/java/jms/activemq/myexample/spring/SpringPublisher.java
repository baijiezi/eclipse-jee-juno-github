
package jms.activemq.myexample.spring;
 
import javax.jms.Destination;
 
import org.springframework.jms.core.JmsTemplate;
 
public class SpringPublisher {
    /**
     * Jms模板
     */
    private JmsTemplate template;
 
    /**
     * Topic
     */
    private Destination topic;
 
    public JmsTemplate getTemplate() {
        return template;
    }
 
    public void setTemplate(JmsTemplate template) {
        this.template = template;
    }
 
    public Destination getTopic() {
        return topic;
    }
 
    public void setTopic(Destination topic) {
        this.topic = topic;
    }
 
    /**
     * Start
     * 
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
 
        int messageCount = 10;
 
        while ((--messageCount) > 0) {
            sendMessage(messageCount);
            Thread.sleep(1000);
        }
    }
 
    /**
     * 消息发送
     */
    protected void sendMessage(int msgNO) {
 
    	System.out.println("发送消息topic：" + topic);
        this.template.send(this.topic, new MyMessageCreator(msgNO));
    }
}