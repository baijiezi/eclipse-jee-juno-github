package com.sun309.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.sun309.factory.Factory;

/**
 * @author Wormwood OPC_GROUP
 * 网络多支付数据格式化工具类
 */
public class NetPayFormatTools 
{
	/** 异常通知分发地址列表 */
	private static Map<String, Object> ExceptionDistributeUrl;
	/** 数据通知分发地址列表 */
	private static Map<String, Object> DataDistributeUrl;
	/** 数据通知分发地址列表 */
	private static Map<String, Object> WaresCodeName;
	
	/** 把银联支付总金额格式化为16位字符串 */
	public static String ChinaPayTotalFee(Long totalFee)
	{
		StringBuilder Fee= new StringBuilder();
		for(int i = 0;i<12-totalFee.toString().length();i++)
		{
			Fee.append("0");
		}
		Fee.append(totalFee.toString());
		return Fee.toString();
	}
	
	/** 处理支付总额的单位转换 */
	public static Float ToAliPayTotalFee(Long totalFee)
	{
		return Float.valueOf(totalFee.toString())/100;
	}
	
	/** 处理支付总额的单位转换 */
	public static Long FromAliPayTotalFee(String totalFee)
	{
		Double fee = Double.valueOf(totalFee)*100;
		return new Long(fee.longValue());
	}
	
	/** 生成银联用订单号16位数字字符串:第5-9位由银联商户号最后5位组成,其它位数由多支付流水ID组成[去除头字符和流水前超过长度的0] */
	public static String ChinaPayOrderID(String mpID,String merId)
	{
		/**银联商户号最后5位 */
		String merIdLast	=	merId.substring(merId.length()-5);
		/** 找到流水号中的所有数字 */
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(mpID);
		matcher.find();
		String number		= mpID.substring(matcher.start(), matcher.end());
		/** 按位置在流水号数字中 取年2位 月2位 日2位 最后的流水号5位 */
		StringBuilder orderID = new StringBuilder(number.substring(2, 6))
			.append(merIdLast).append(number.substring(6, 8)).append(number.substring(number.length()-5, number.length()));
		return orderID.toString();
	}
	
	/** 从银联用订单号返回多支付流水ID */
	public static String ChinaPayBackID(String chinaPayOrderId)
	{
		/** 这个默认设置的有效期是从 00年 开始 到 100年 超过这个时间会产生语义性错误  改进方法是把20按时间弄成变量 */
		String idStart 		= 	"NPGW20";
		String subStart 	= 	chinaPayOrderId.substring(0, 4);
		String subMiddel 	=	chinaPayOrderId.substring(chinaPayOrderId.length()-7,chinaPayOrderId.length()-5);
		String subLast		= 	chinaPayOrderId.substring(chinaPayOrderId.length()-5);
		StringBuilder NPID	=	new StringBuilder(idStart).append(subStart).append(subMiddel)
			.append("0000").append(subLast);
		idStart = null; subStart= null; subMiddel = null; subLast = null;
		return NPID.toString();
	}
	
	/** 28位订单号 SPID[10]+date[8]+id[10] 最后10位流水号多支付产生 */
	public static String TenPayOrderID(String mpID,String SPID)
	{
		String date = mpID.substring(4,12);
		String id	= mpID.substring(mpID.length()-9);
		StringBuilder orderId = new StringBuilder(SPID)
			.append(date).append("0").append(id);
		date = null; id = null;
		return orderId.toString();
	}
	
	public static String getTenPayDateFromTradeNo(String tradeNo)
	{
		return tradeNo.substring(10, 18);
	}
	
	/** 按商品代码 取得商品名称 */
	public static String getNetPaySubject(String waresCode)
	{
		if(WaresCodeName == null)
		{
			NetPayXmlTools xml = (NetPayXmlTools)Factory.create(NetPayXmlTools.class);
			WaresCodeName = xml.getWareCodeList();
		}
		Set<Entry<String,Object>> 		es = WaresCodeName.entrySet();
		Iterator<Entry<String,Object>> 	it = es.iterator();
		while(it.hasNext()) {
			Map.Entry<String,Object> entry = (Map.Entry<String,Object>)it.next();
			if(waresCode.startsWith(entry.getKey().toString()))
				return entry.getValue().toString();
		}
		es = null; 
		it = null;
		return "阳光医疗网[康众服务]";
	}
	
	/** 按商品代码取得 商品交易结果通知地址 */
	public static String getNetPayDistribute(String waresCode)
	{
		if(DataDistributeUrl == null)
		{
			NetPayXmlTools xml = (NetPayXmlTools)Factory.create(NetPayXmlTools.class);
			DataDistributeUrl = xml.getDataDistributeList();
		}
		Set<Entry<String,Object>> 		es = DataDistributeUrl.entrySet();
		Iterator<Entry<String,Object>> 	it = es.iterator();
		while(it.hasNext()) {
			Map.Entry<String,Object> entry = (Map.Entry<String,Object>)it.next();
			if(waresCode.startsWith(entry.getKey().toString()))
				return entry.getValue().toString();
		}
		es = null; 
		it = null;
		return "http://www.sun309.com";
	}
	
	/** 按商品代码取得 商品交易异常通知地址 */
	public static String getNetPayExceptionDistribute(String waresCode)
	{
		if(ExceptionDistributeUrl == null)
		{
			NetPayXmlTools xml = (NetPayXmlTools)Factory.create(NetPayXmlTools.class);
			ExceptionDistributeUrl = xml.getDataDistributeList();
		}
		Set<Entry<String,Object>> 		es = ExceptionDistributeUrl.entrySet();
		Iterator<Entry<String,Object>> 	it = es.iterator();
		while(it.hasNext()) {
			Map.Entry<String,Object> entry = (Map.Entry<String,Object>)it.next();
			if(waresCode.startsWith(entry.getKey().toString()))
				return entry.getValue().toString();
		}
		es = null; 
		it = null;
		return "http://www.sun309.com";
	}
	
	public static void main(String[] args)
	{
		System.out.println(NetPayFormatTools.getNetPayExceptionDistribute("DS2011031800000968"));
	}
}
