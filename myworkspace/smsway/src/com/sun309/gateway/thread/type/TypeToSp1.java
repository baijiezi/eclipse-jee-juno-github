package com.sun309.gateway.thread.type;

import com.sun309.gateway.api.SpMessage;
import com.sun309.gateway.api.SpSmsGateway;
import com.sun309.gateway.dao.MasMtDao;
import com.sun309.gateway.dao.SpMessageMappingDao;
import com.sun309.gateway.dto.Messages;
import com.sun309.gateway.dto.MessagesContent;
import com.sun309.gateway.dto.SpMessageMapping;
import com.sun309.gateway.factory.MasMtDaoFactory;
import com.sun309.gateway.factory.SpMessageMappingDaoFactory;
import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;
import com.sun309.gateway.util.DateUtils;

public class TypeToSp1 implements TypeService
{
	private LogService log = LogFactory.create(LogFactory.SEND_DATA, TypeToSp1.class);
	public void doSend(Messages message, MessagesContent messageContent)
	{
		DateUtils du = new DateUtils();
		log.debug("开始调用短信发送接口", message.getMobile());
		SpSmsGateway.send(message.getMobile(), messageContent.getMessageContent(), du.getDate());
		SpMessage spMessage = SpSmsGateway.getM();
		SpMessageMapping mapping = new SpMessageMapping();
		mapping.setAccess("Sp1");
		mapping.setMessageId(message.getMessageId());
		mapping.setSpMessageId(spMessage.getID());
		SpMessageMappingDao service = SpMessageMappingDaoFactory.create();
		boolean insertResult = service.insert(mapping);
		log.debug(new StringBuffer("SP插入记录的结果").append(insertResult).toString(), message.getMobile());
		log.write();
	}
	public void doRpt()
	{
		
	}
	
}
