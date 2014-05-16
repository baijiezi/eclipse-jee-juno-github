package com.sun309.util;

public final class Constants 
{
	/**xml配置路径**/
	public static final String TRAN_DATA_FILE_PATH						= "/distribute.xml"; //预约数据与HIS交互的XML配置
	/** ChinaPay 私有密匙路径 */
	public static final String CHINAPAY_PRIVATE_KEY_PATH				="MerPrK_808080450202901_20100527111119.key";
	/** ChinaPay 公有密匙路径 */
	public static final String CHINAPAY_PUBLIC_KEY_PATH					="PgPubk.key";
	/** NPGW 商品编号到商品名称映射关系配置  因读文件乱码未启用 */
	//public static final String NPGW_WARECODE_LIST_PATH				="WareCodeToName.xml";
	/** NPGW 数据分发地址配置  */
	public static final String NPGW_DATA_DISTRIBUTE_PATH				="DataDistributeUrl.xml";
	/** NPGW 异常分发地址配置  */
	public static final String NPGW_EXCEPTION_DISTRIBUTE_PATH			="ExceptionDistributeUrl.xml";
	
	/** 测试用加密KEY [测试已过｜不再使用的参数]*/
	public static final String PACKAGE_TEST_KEY							= "31888085512677328386518140866310";
	
	/** 小钱包加密KEY */
	public static final String PACKAGE_KEY								= "bd15ee9d3d741643aca88fa1f4183db6";
	/** 小钱包APP_ID */
	public static final String PACKAGE_APP_ID							= "0000000594";
	
	/** 小钱包APP_ID [测试已过｜不再使用的参数]*/
	public static final String PACKAGE_TEST_APP_ID						= "0000000594";
	
	
	/****/
	public static final int INITDBCONNECTSIZE							= 65;
	public static final String DB_PROPERTIES							= "/db.properties";
	public static final long RE_CONNECTION_DB 							= 1000 *  60 * 60 * 1;			//重新连接数据库的线程睡觉的时间

	/****/
	public static final int PAYMENT_STATUS_0								= 0;  //初始化
	public static final int PAYMENT_STATUS_1								= 1;  //请求中
	public static final int PAYMENT_STATUS_2								= 2;  //成功
	public static final int PAYMENT_STATUS_3								= 3;  //失败
	
	/**日志是否记录执行的SQL**/
	public static final boolean SHOW_SQL = Boolean.FALSE;
	
	public static final long DISTRIBUTE_THREAD_SLEEP_TIME = 1*1000;    //
	
	/** 网络多支付交易状态	*/
	public static final int PAY_STATUS_INIT			= 11;	//支付请求解释成功初始化
	public static final int PAY_STATUS_SUCCEED		= 12;	//交易成功
	public static final int PAY_STATUS_FAIL			= 13;   //交易失败
	public static final int PAY_STATUS__EXPIRE		= 14;   //交易超时
	
	/** 网络多支付分发结果状态	*/
	public static final int DISTRIBUTE_INIT			= 21;	//支付请求解释成功初始化
	public static final int DISTRIBUTE_SUCCEED		= 22;	//分发成功
	public static final int DISTRIBUTE_FAIL			= 23;	//分发失败
	public static final int DISTRIBUTE_TRY_TIME		= 3;	//分发次数
	public static final int DISTRIBUTE_TRY_MAX		= 192;	//最大分发次数
	
	/** 网络多支付结果接收状态	*/
	public static final int RECEIVE_INIT		= 31;	//支付请求解释成功初始化
	public static final int RECEIVE_WAIT		= 32;	//等待
	public static final int RECEIVE_SUCCEED		= 33;	//成功
	
	/** 网络多支付加密验证状态	*/
	public static final int VALIDATE_INIT		= 41;	//支付请求解释成功初始化
	public static final int VALIDATE_SUCCEED	= 42;	//成功
	public static final int VALIDATE_FAIL		= 43;	//失败
	
	/** 网络多支付日志状态状态	*/
	public static final boolean LOG_STATUS		= Boolean.FALSE;	//是否使用系统LOG
	public static final boolean DBLOG_STATUS 	= Boolean.TRUE;		//是否使用DB  LOG
	public static final boolean LOG_METHOD 		= Boolean.FALSE;	/** 是否使用WRITELOG方法开关 目前只用在数据库操作类中 */
	
	/** 网络多支付类型	*/
	public static final int ALI_PAY				= 5; 	//支付宝
	public static final int CHINA_PAY			= 6; 	//网银
	public static final int TEN_PAY				= 8; 	//财付通
	public static final int PAYPAL_PAY			= 9; 	//PAY_PAL[未启用的参数]
	public static final int TENPAYPACKAGE_PAY	= 11; 	//财付通小钱包
	
	/**  网络多支付类型 数据类型 [未启用的参数]*/
	public static final int TEN_PAY_QUERY = 98;
	
	/** 时间初始化值 	*/
	public static final long TIME_INIT	= 1000;
	/** 最小超时时间值 最小要一个小时才超时[900000]	*/
	public static final long MIN_EXPIRE_TIME	= 900000;
	/** TenPay数据查询调用最短时间[45分钟]:初始化后多长时间没有接收到数据,一定要小于超时时间 [未启用的参数]*/
	public static final long MAX_QUERY_TIME	= 2700000;
	
	/** 网络多支付线程分发睡觉时间 */
	public static final long SLEEP_TIME	= 2*1000;
	
	/** 网络多支异常分发线程 只处理两天之内的数据 */
	public static final long PROCESS_MAX_TIME	= 2*86400*1000;
	
	/** 网络多支付线程分发睡觉时间 15分钟*/
	public static final long EXCEPTION_SLEEP_TIME	= 15*60*1000;
	
	/** 
	 * BaseRquest 参数配置 
	 */
	public static final String TEN_PAY_MERD_ID = "1207498801";
}