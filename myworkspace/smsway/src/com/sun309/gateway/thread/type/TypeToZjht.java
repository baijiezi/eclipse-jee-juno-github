package com.sun309.gateway.thread.type;

import com.sun309.gateway.api.SendMsgZjht;
import com.sun309.gateway.dao.SpMessageMappingDao;
import com.sun309.gateway.dto.Messages;
import com.sun309.gateway.dto.MessagesContent;
import com.sun309.gateway.dto.SpMessageMapping;
import com.sun309.gateway.factory.SpMessageMappingDaoFactory;
import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;

public class TypeToZjht implements TypeService
{
	private LogService log = LogFactory.create(LogFactory.SEND_DATA, TypeToZjht.class);
	public void doSend(Messages message, MessagesContent messageContent)
	{
		try
		{
			log.debug("开始调用Zjht短信发送接口", message.getMobile());
			int res = SendMsgZjht.send(message.getMobile(), messageContent.getMessageContent(),message.getMessageId());
			log.debug("【中经汇通返回的结果为】" + res);
			if(0 == res){
				SpMessageMapping mapping = new SpMessageMapping();
				mapping.setAccess(message.getAccess());
				mapping.setMessageId(message.getMessageId());
				mapping.setSpMessageId(message.getMessageId().toString());
				SpMessageMappingDao service = SpMessageMappingDaoFactory.create();
				boolean insertResult = service.insert(mapping);
				log.debug(new StringBuffer("【中经汇通】短信发送插入记录的结果").append(insertResult).toString(), "TypeToZjht");
			}else{
				log.debug(new StringBuffer("【中经汇通】短信发失败").append(-1).toString(), "TypeToZjht");
			}
		}
		finally
		{
			log.write();
		}
	}
	
	
	public void doRpt()
	{
		
	}
	
}
