package com.sun309.service;

import java.util.ArrayList;

import com.sun309.dto.NetPayData;
import com.sun309.dto.NetPayDataException;

public interface NetPayDetailDataService 
{
	public boolean insert(BaseReceive result ,int type);	/** 保存POST接收到的详细数据 		*/
	public boolean insertException(BaseReceive result);		/** 保存详细数据产生的异常数据 	*/
	public NetPayData findExpireByID(String ID);			/** 按ID条件查询 异常表				*/
	public NetPayData findWarningByID(String ID);			/** 按ID条件查询 警告表				*/
	public ArrayList<NetPayData> findWarningByCondition(String condition);				/** 按条件查询 警告表 */
	public ArrayList<NetPayDataException> findExceptionByCondition(String condition);	/** 按条件查询 异常表 */
	public boolean updateExpireStatus(String ID,String statusName,Object status);		/** 按ID更新超时记录表 [单条]	*/
	public boolean updateExceptionStatus(String ID,String statusName,Object status);	/** 按ID更新异常记录表 [单条]	*/
	public boolean updateWarningStatus(String ID,String statusName,Object status);		/** 按ID更新警告记录表 [单条]	*/
}
