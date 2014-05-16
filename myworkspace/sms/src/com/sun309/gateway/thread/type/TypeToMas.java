package com.sun309.gateway.thread.type;

import com.sun309.gateway.dao.MasMtDao;
import com.sun309.gateway.dao.MessagesDao;
import com.sun309.gateway.dto.Messages;
import com.sun309.gateway.dto.MessagesContent;
import com.sun309.gateway.dto.mas.MasMt;
import com.sun309.gateway.factory.MasMtDaoFactory;
import com.sun309.gateway.factory.MessagesDaoFactory;

public class TypeToMas implements TypeService
{
	public void doSend(Messages message, MessagesContent messageContent)
	{
		MasMt masMt = new MasMt();
		// insert mas mt
		MasMtDao masMtDao = MasMtDaoFactory.create();
		masMt = masMtDao.insert(message.getMobile(), messageContent.getMessageContent(), message.getRptStatus().getRptId().longValue(), message.getRptStatus().getMoId().longValue(), message.getInterfaceName(), message.getNeedCallBack());
		// update sms mt
		MessagesDao messagesDao = MessagesDaoFactory.create();
		messagesDao.updateRptStatusMtId(masMt, message.getMessageId().intValue());
	}

	public void doRpt()
	{
	}
}