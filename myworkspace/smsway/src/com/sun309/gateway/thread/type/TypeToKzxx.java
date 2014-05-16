package com.sun309.gateway.thread.type;

import java.util.List;
import java.util.Map;

import com.sun309.gateway.api.SendMsgKzxx;
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

public class TypeToKzxx implements TypeService
{
	private LogService log = LogFactory.create(LogFactory.SEND_DATA, TypeToKzxx.class);
	public void doSend(Messages message, MessagesContent messageContent)
	{
		DateUtils du = new DateUtils();
		log.debug("短信发送【康众信息】接口[mobile]"+message.getMobile()+" [sendContext]"+messageContent.getMessageContent(), "TypeToKzxx");
		try{
			List<Map> resList = SendMsgKzxx.send(message.getMobile(), messageContent.getMessageContent());
			log.debug("短信发送【康众信息】接口获得发送短信成功条数"+resList.size(), "TypeToKzxx");
			if(resList.size() >0){
				for (Map map : resList) {
					SpMessageMapping mapping = new SpMessageMapping();
					mapping.setAccess("Kzxx");
					mapping.setMessageId(message.getMessageId());
					mapping.setSpMessageId(map.get("smsID").toString());
					SpMessageMappingDao service = SpMessageMappingDaoFactory.create();
					boolean insertResult = service.insert(mapping);
					log.debug(new StringBuffer("康众信息短信发送结果").append(insertResult).toString(), message.getMobile());
					
				}
			}else{
				log.debug(new StringBuffer("康众信息短信发送失败").toString(), message.getMobile());
				Map resmap = SendMsgKzxx.getBalance();
				if(resmap != null && resmap.get("Error")!= null){
					log.debug("【康众信息】短信发送失败"+resmap.get("Message"), "TypeToKzxx");
				}
			}
			
		}finally{
			log.write();
		}
		
	}
	
	
	public void doRpt()
	{
	}
	
}
