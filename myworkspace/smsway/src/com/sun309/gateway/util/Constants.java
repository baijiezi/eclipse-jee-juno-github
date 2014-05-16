package com.sun309.gateway.util;

public final class Constants
{
	public static final int INITDBCONNECTSIZE								= 30;
	public static final int MASINITDBCONNECTSIZE							= 30;
	
	public static final String DB_PROPERTIES								= "/smswaydb.properties";
	public static final String MAS_DB_PROPERTIES							= "/masdb.properties";
	
	public static final Short MESSAGE_INIT_STATUS							= 1001;						//初始状态
	public static final Short MESSAGE_BUSSINESS_PROCESS_SUCCESS 		 	= 1004;						//成功放入MAS
	public static final Short MESSAGE_RE_SEND							 	= 1007;						//重新发送数据到业务服务器
	public static final Short MESSAGE_CHECK_SEND_TO_BUSSINESS_SUCCESS  		= 1008;						//检验数据已成功发送到业务服务器,但之前没有返回状态,现在重新返回状态
	public static final Short MESSAGE_TEL_NOTICE						 	= 9999;						//通过电话通知
	public static final Short MESSAGE_RPT_OVER_TIME					 		= 4444;						//短信的回执超时
	
	public static final long RE_CONNECTION_DB 						 		= 1000 *  60 * 60 * 1;			//重新连接数据库的线程睡觉的时间
	public static final int SEND_DATA_THREAD_SLEEP_TIME				 		= 300 *  1 * 1;				   //发送数数的线程睡觉的时间
	public static final int SEND_KZXX_DATA_THREAD_SLEEP_TIME				= 400 *  1 * 1;				   //发送数数的线程睡觉的时间
	public static final int SEND_ZJHT_DATA_THREAD_SLEEP_TIME                = 1000 *  2 * 1;               //发送数数的线程睡觉的时间
	public static final int SEND_SP1_DATA_THREAD_SLEEP_TIME				 	= 1000 *  6 * 1;			   //发送数数的线程睡觉的时间
	public static final int RE_SEND_DATA_THREAD_SLEEP_TIME			 		= 1000 *  60 * 2;			   //重新发送数据的时间
	public static final int GET_RPT_DATA_THREAD_SLEEP_TIME  			 	= 500 *  1 * 1;				   //获取回执线程的睡觉时间
	public static final int GET_SP1_RPT_DATA_THREAD_SLEEP_TIME  			= 1000 *  30 * 1;				//获取回执线程的睡觉时间
	public static final int GET_KZXX_RPT_DATA_THREAD_SLEEP_TIME  			= 1000 *  10 * 1;				//获取回执线程的睡觉时间
	public static final int GET_KZXXF_RPT_DATA_THREAD_SLEEP_TIME  			= 600 *  1 * 1;				//获取回执线程的睡觉时间
	public static final int GET_ZJHT_RPT_DATA_THREAD_SLEEP_TIME  			= 1000 *  30 * 1;				//获取回执线程的睡觉时间
	
	public static final int GET_MO_DATA_THREA_SLEEP_TIME				 	= 1000 *  1 * 1;				//获取回复线程的睡觉时间
	public static final int GET_UNICOM_MO_DATA_THREA_SLEEP_TIME				= 1000 *  1 * 1;				//获取回复线程的睡觉时间(联通)
	public static final int RESEND_MESSAGES_THREAD_SLEEP_TIME 		 		= 1000 *  3 * 1;				//当短信的状态为1004 1008时,重新发送线程睡觉的时间
	public static final int GET_OVER_TIME_RPT_THREAD					 	= 1000 *  5 * 1;				//获取过时的回执线程睡觉时间

	public static final int RE_SEND_TIME								 	= 1000 * 60 * 30;				//重新发送的时间(10分钟)
	public static final int RE_SEND_MESSAGES_TIME						 	= 1000 * 60 * 60 * 60 * 1;			//当messages_stutas中的read_time超过3小时，重新发送该短信
	public static final int GET_RPT_TRY_TIME								= RE_SEND_TIME;				//代理服务器在短信发送出后,10分钟不断获取回执,如果超过10分钟,视重新发送
	
	public static final byte RPT_READED_TIMES				 		 		= 50;							//请求回执状态50次后,还是错误,那么跳过.
	public static final int GET_MO_TRY_TIMES					   		 	= 1000;						//获取回执,偿试的次数
	public static final int RE_SEND_COUNTER					  		 		= 100;							//发送15次失败,跳过
	
	
	public static final int BUFFER_NUMBER								 	= 1;							//线程,数据缓冲
	public static final int SEND_RPT_NUMBER							 		= 1;							//发送回执短信,一次最多发多少条
	
	
	public static final String BUSINESS_SERVER_IP						 	= "localhost";
	public static final String BUSINESS_URL							 		= "http://" + BUSINESS_SERVER_IP + ":8080/business/sendDataAction.do";			//业务服务器接收数据的URL
	public static final String GET_RPT_URL					 		 		= "http://" + BUSINESS_SERVER_IP + ":8080/business/rptAction.do";				//获取回执状态
 	public static final String GET_MO_URL								 	= "http://" + BUSINESS_SERVER_IP + ":8080/business/moAction.do";				//获取回执状态
 	public static final String GET_UNICOM_MO_URL						 	= "http://" + BUSINESS_SERVER_IP + ":8080/business/unicomMoAction.do";			//获取联通回执状态
 	public static final String GET_OVER_TIME_RPT						 	= "http://" + BUSINESS_SERVER_IP + ":8080/business/overTimeRptAction.do";		//获取超时回执
 
}