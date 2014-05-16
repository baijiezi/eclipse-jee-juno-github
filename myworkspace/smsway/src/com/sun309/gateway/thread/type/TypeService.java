package com.sun309.gateway.thread.type;

import com.sun309.gateway.dto.Messages;
import com.sun309.gateway.dto.MessagesContent;

public interface TypeService
{
	public void doSend(Messages message, MessagesContent messageContent);
}
