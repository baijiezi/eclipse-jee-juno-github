
package jms.activemq.myexample.spring;
 
import javax.jms.*;
 
/**
 * Text消息监听
 * 
 * @author xiaochuanyu
 * 
 */
public class TextListener implements MessageListener {
 
    /**
     * Casts the message to a TextMessage and displays its text.
     * 
     * @param message
     *            the incoming message
     */
    public void onMessage(Message message) {
        TextMessage msg = null;
 
        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                System.out.println("读到的消息内容： " + msg.getText());
            } else {
                System.out.println("Message of wrong type: "
                        + message.getClass().getName());
            }
        } catch (JMSException e) {
            System.out.println("JMSException in onMessage(): " + e.toString());
        } catch (Throwable t) {
            System.out.println("Exception in onMessage():" + t.getMessage());
        }
    }
}