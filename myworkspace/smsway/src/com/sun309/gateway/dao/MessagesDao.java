package com.sun309.gateway.dao;

import java.util.ArrayList;

import com.sun309.gateway.dto.Messages;
import com.sun309.gateway.dto.MessagesContent;
import com.sun309.gateway.dto.MessagesLog;
import com.sun309.gateway.dto.RptStatus;
import com.sun309.gateway.dto.mas.MasMt;

public interface MessagesDao
{
	/**
	 * 插入短信
	 * @param message
	 * @return
	 */
	public int insert(Messages message);
	/**
	 * 查询要发送的短信
	 * @param condition
	 * @return
	 */
	public ArrayList<Messages> findSendMessages(String condition);
	/**
	 * 通过ID查询备份短信
	 * @param mid
	 * @return
	 */
	public MessagesLog findMessagesLogByMessageId(int mid);
	/**
	 * 通过条件查询备份短信
	 * @param condition
	 * @return
	 */
	public ArrayList<MessagesLog> findMessagesLogByCondition(String condition);
	/**
	 * 更新rpt_status表中的mt_id字段
	 * @param masMt
	 * @param messageId
	 */
	public void updateRptStatusMtId(MasMt masMt, int messageId);
	/**
	 * 通过ID查询短信内容
	 * @param messageId
	 * @return
	 */
	public MessagesContent findMessagesContentByMessageId(int messageId);
	/**
	 * 回执时, 1、将messsages插入messages_log 2、删除messsages 3、删除rpt_status
	 * @param rptId
	 * @param rptCode
	 * @param rptTime
	 * @param _message
	 * @return
	 */
	public boolean doRpt(String rptId, String rptCode, String rptTime, Messages _message);
	/**
	 * 重发8\102回执状态的短信
	 * @param rptId
	 * @return
	 */
	public boolean resend8And102Status(long rptId);
	/**
	 * Constants.RE_SEND_MESSAGES_TIME小时后，未收到回执，重发
	 * @return
	 */
	public ArrayList<RptStatus> findResendMessages();
	
	/**
	 * 超过Constants.RE_SEND_MESSAGES_TIME小时后，未收到回执，重发
	 * @param messageId
	 */
	public boolean timeOutResend(RptStatus rpt);
	
	/**
	 * delete messages_status
	 * @param messageId
	 * @return
	 */
	public boolean deleteRptStatus(int messageId);
	
	/**
	 * delete messages
	 * 
	 * @param messageId
	 * @return
	 */
	public boolean deleteMessages(int messageId);
	
	/**
	 * 通过条件查询短信
	 * 
	 * @param condition
	 * @return
	 */
	public ArrayList<Messages> findMessagesByCondition(String condition);
	
	/**
	 * 通过ID删除短信
	 * @param messageId
	 * @return
	 */
	public boolean delete(int messageId);
	
	/**
	 * Sp1接收回执
	 * @param success
	 * @param failure
	 * @return
	 */
	public void sp1DoRpt(Object[] success, Object[] failure);
}
