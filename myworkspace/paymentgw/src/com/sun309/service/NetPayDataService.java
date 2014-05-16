package com.sun309.service;

import java.util.ArrayList;

import com.sun309.dto.NetPayData;
import com.sun309.dto.NetPayDataParams;

public interface NetPayDataService 
{
	public String insert(NetPayData netpay);									/** 数据添加方法 */
	public NetPayData findByTradeID(String tradeID);							/** 数据按ID查找 */
	public ArrayList<NetPayData> findByCondition(String condition);				/** 条件查询方法 */
	public boolean moveToLog(ArrayList<NetPayData>list ,String tableName);		/** 有条件的数据转移方法		*/
	public boolean updateStatus(String id, String statusName, Object status);	/** 按ID状态更新方法[单字段] */
	public boolean updateParams(String id, String statusName, String status);	/** 按ID状态更新方法[单字段] */
	public boolean updateByID(String outOrderNo, String stringSet);				/**	按ID多字段更新方法 		*/
	public boolean insertFeeException(NetPayData netpay);						/** 金额异常数据保存方法		*/
	public boolean insertParams(NetPayDataParams params);						/** 保存出入参数				*/
	public NetPayDataParams findParamsByTradeID(String tradeID);				/** 查找参数对象	现用于异步交互	*/
}
