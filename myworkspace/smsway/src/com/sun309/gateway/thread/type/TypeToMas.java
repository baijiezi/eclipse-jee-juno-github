package com.sun309.gateway.thread.type;

import com.sun309.gateway.dao.MasMtDao;
import com.sun309.gateway.dao.MessagesDao;
import com.sun309.gateway.dto.Messages;
import com.sun309.gateway.dto.MessagesContent;
import com.sun309.gateway.dto.mas.MasMt;
import com.sun309.gateway.factory.MasMtDaoFactory;
import com.sun309.gateway.factory.MessagesDaoFactory;
import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;
import com.sun309.gateway.thread.GetRptDataThread;

public class TypeToMas implements TypeService
{
	private LogService log = LogFactory.create(LogFactory.SEND_DATA, TypeToMas.class);
	public void doSend(Messages message, MessagesContent messageContent)
	{
		MasMt masMt = new MasMt();
		try{
			// insert mas mt
			MasMtDao masMtDao = MasMtDaoFactory.create();
			log.debug("【Mas机】现需插入数据到Mas机数据库", "TypeToMas");
			masMt = masMtDao.insert(message.getMobile(), messageContent.getMessageContent(), message.getRptStatus().getRptId().longValue(), message.getRptStatus().getMoId().longValue(), message.getInterfaceName(), message.getNeedCallBack());

			log.debug("【Mas机】数据库操作结束"+masMt.getAutoSn(), "TypeToMas");
			MessagesDao messagesDao = MessagesDaoFactory.create();
			messagesDao.updateRptStatusMtId(masMt, message.getMessageId().intValue());
		}catch(Exception ex){
			ex.printStackTrace();
			log.debug("【Mas机】"+ex.getMessage(),"TypeToMas");
		}
		finally{
			log.write();
		}
	}

	public void doRpt()
	{
	}
}