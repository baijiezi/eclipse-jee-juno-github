package com.sun309.gateway.thread;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.sun309.gateway.dao.MessagesDao;
import com.sun309.gateway.dto.Messages;
import com.sun309.gateway.dto.MessagesContent;
import com.sun309.gateway.factory.MessagesDaoFactory;
import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;
import com.sun309.gateway.thread.type.TypeFactory;
import com.sun309.gateway.thread.type.TypeService;
import com.sun309.gateway.util.Common;
import com.sun309.gateway.util.Constants;

public class SendDataThread extends Thread
{
	private LogService log = LogFactory.create(LogFactory.SEND_DATA, SendDataThread.class);

	public SendDataThread()
	{
		super("SendDataThread");
	}

	public void run()
	{
		while (true)
		{
			try
			{
				MessagesDao messagesDao = MessagesDaoFactory.create();
				StringBuffer condition = new StringBuffer();
				condition.append("m.send_time <= ");
				condition.append(Common.getNowTime()); // 发送的时间小于等于现在时间
				condition.append(" AND m.send_counter < '" ).append( Constants.RE_SEND_COUNTER ).append( "' AND m.enable='0' AND m.status IN (" ); // 发送的次数小于RE_SEND_COUNTER,
																																	// 并且为enable有效
				condition.append(Constants.MESSAGE_INIT_STATUS ).append( ",");
				condition.append(Constants.MESSAGE_RE_SEND ).append( ",");
				condition.append(8);
				condition.append(")");
				condition.append(" ORDER BY m.send_level DESC, m.send_time LIMIT " ).append( Constants.BUFFER_NUMBER);
				// 获取出要发送的短信
				ArrayList<Messages> list = messagesDao.findSendMessages(condition.toString());
				
				if (list != null && list.size() > 0)
				{
					for (Messages message : list)
					{
						log.debug(new StringBuffer("开始查询要发送的短信,条件：" ).append( condition.toString() ).toString(), message.getMobile());
						log.debug(new StringBuffer("开始查询结果：list" ).append( list ).toString(), message.getMobile());
						log.debug(new StringBuffer("发送的端口为").append(message.getOperName()).toString(), message.getMobile()); 
						
						//if(message.getAccess()!=null && "Sp1".equals(message.getAccess().trim()))  continue;
						
						// 查询ID的短信内容
						MessagesContent messageContent = messagesDao.findMessagesContentByMessageId(message.getMessageId().intValue());
						log.debug(new StringBuffer("短信内容messageContent=" ).append( messageContent ).append( "; messageContent.getContent = " ).append( messageContent.getMessageContent()).toString(), message.getMobile());
						if (messageContent != null && messageContent.getMessageContent() != null && !StringUtils.isEmpty(messageContent.getMessageContent()))
						{
							log.debug(new StringBuffer("access=").append(message.getAccess()).toString(), message.getMobile());
							TypeService service = TypeFactory.create(message.getAccess());  
							service.doSend(message, messageContent);
						}
						messageContent = null;
						
						if(message.getAccess()!=null && "Sp1".equals(message.getAccess().trim())) 
						{
							System.out.println("====Sp1====");
							
							Thread.sleep(Constants.SEND_SP1_DATA_THREAD_SLEEP_TIME);
						}
						else 
						{ 
							System.out.println("====MAS====");
							Thread.sleep(Constants.SEND_DATA_THREAD_SLEEP_TIME);
						}
						System.out.println("==SendDataThread=="); 
					}
					log.write();
				}
				list=null;
			}
			catch (Exception e)
			{

			}
		}
	}
}
