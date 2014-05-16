package com.sun309.gateway.thread;

import java.util.ArrayList;

import com.sun309.gateway.dao.InvalidDao;
import com.sun309.gateway.dao.MessagesDao;
import com.sun309.gateway.dto.RptStatus;
import com.sun309.gateway.factory.InvalidDaoFactory;
import com.sun309.gateway.factory.MessagesDaoFactory;
import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;
import com.sun309.gateway.util.Constants;

/**
 * 4小时后，如果未能收到回执，重发一次 重发功能 + 备份过期无效的短信
 * 
 * @author caiyuerui
 * 
 */
public class ResendMessagesThread extends Thread
{
	private LogService log = LogFactory.create(LogFactory.SEND_DATA, ResendMessagesThread.class);

	public ResendMessagesThread()
	{
		super("ResendMessagesThread");
		log.debug("start ResendMessagesThread");
	}

	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(Constants.RESEND_MESSAGES_THREAD_SLEEP_TIME);
				MessagesDao messagesDao = MessagesDaoFactory.create();
				// 获取要重新发送的短信
				ArrayList<RptStatus> list = messagesDao.findResendMessages();
				if (list != null && list.size() > 0)
				{
					for (RptStatus rpt : list)
					{
						log.debug(new StringBuffer("rpt id = ").append( rpt.getRptId() ).append(  "重发" ).toString(), "ResendMessagesThread");
						messagesDao.timeOutResend(rpt);
					}
					// 备份过期无效的短信
					InvalidDao invalidDao = InvalidDaoFactory.create();
					invalidDao.move();
				}
				list=null;
			}
			catch (Exception e)
			{
				log.debug(e);
			}
		}
	}
}
