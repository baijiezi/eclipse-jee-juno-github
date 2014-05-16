/**
 * 网络多支付XML工具类
 */
package com.sun309.util;

import java.io.StringReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sun309.dto.NetPayData;
import com.sun309.dto.NetPayRequest;
import com.sun309.factory.Factory;
import com.sun309.service.BaseRequest;
import com.sun309.service.Request;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * @author Wormwood OPC_GROUP
 *
 */
public class NetPayXmlTools 
{
	/** 基础XML解释方法 */
	public Map<String, Object> parseXml(String param)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		SAXBuilder sb = new SAXBuilder();
		try 
		{
			// 通过输入源构造一个Document
			if(param.length() == 0 && param.trim() == "")
				return null;
			Document doc = sb.build(new StringReader(param));
			Element root = doc.getRootElement();
			List<Element> list = root.getChildren();
			Iterator<Element> iter = list.iterator();
			while (iter.hasNext()) {
				Element el = iter.next();
				result.put(el.getName(), el.getValue());
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	/** 直接从XML 字符串中取得NPGWID 值 */
	public String parseAsyncID(String param)
	{
		Map<String, Object> result = parseXml(param);
		return result.get("NPGWID").toString();
	}
	
	/** 处理XML数据返回Request对象 */
	public Request parseRequest(String param)
	{
		/** 同步生成参数列表  */
		Map<String, Object> result = parseXml(param);
		NetPayRequest req = new NetPayRequest();
		if(result.containsKey("expireTime"))		
			req.setExpireTime(Long.parseLong(result.get("expireTime").toString()));
		if(result.containsKey("outTtradeNo"))
			req.setOutTtradeNo(result.get("outTtradeNo").toString());
		if(result.containsKey("waresCode"))		
			req.setWaresCode(result.get("waresCode").toString());
		if(result.containsKey("payType"))
			req.setPayType(Integer.parseInt(result.get("payType").toString()));
		if(result.containsKey("resultType"))
			req.setResultType(result.get("resultType").toString());
		if(result.containsKey("clientIP"))
			req.setClientIP(result.get("clientIP").toString());
		
		/** 异步生成数据时下面两个参数没有值 */
		if(result.containsKey("returnUrl"))
			req.setReturnUrl(result.get("returnUrl").toString());
		else
			req.setInitTag(Boolean.FALSE);
		
		if(result.containsKey("totalFee"))
			req.setTotalFee(Long.parseLong(result.get("totalFee").toString()));
		else
			req.setInitTag(Boolean.FALSE);
		/** 异步独有参数列表  */
		if(result.containsKey("NPGWID"))
		{
			req.setNPGWID(result.get("NPGWID").toString());
			req.setInitTag(Boolean.FALSE);
		}
		return req;
	}
	
	/** 处理数据生成XML结果 简单通知格式返回*/
	public StringBuilder simpleMeesage(int i,String mesage)
	{
		StringBuilder builder = new StringBuilder("<?xml version=").append("\"").append("1.0")
		.append("\"").append(" encoding=").append("\"").append("utf-8").append("\"").append("?><netpayresponse>")
		.append("<simplemessage><error>").append(i).append("</error>")
		.append("<message>").append(mesage).append("</message>")
		.append("</simplemessage></netpayresponse>");
		return builder;
	}
	
	/** 最终通知给展示方的XML返回结果 */
	public StringBuilder setNetPayResponse(BaseRequest base)
	{
		StringBuilder builder = new StringBuilder("<?xml version=").append("\"").append("1.0")
		.append("\"").append(" encoding=").append("\"").append("utf-8").append("\"").append("?><netpayresponse>")
		.append("<simplemessage><error>").append(0).append("</error>")
		.append("<message>").append(base.getValidateInfo()).append("</message>")
		.append("</simplemessage><NPGWID>").append(base.getID())
		.append("</NPGWID><payType>").append(base.getHTMLPayType())
		.append("</payType></netpayresponse>");
		return builder;
	}
	
	/** 异步生成ActionUrl 或者 ActionForm的时候 用来在第一次返回NPGWID 的XML */
	public StringBuilder setNetPayResponseOnlyOrderNo(BaseRequest base)
	{
		StringBuilder builder = new StringBuilder("<?xml version=").append("\"").append("1.0")
		.append("\"").append(" encoding=").append("\"").append("utf-8").append("\"").append("?><netpayresponse>")
		.append("<simplemessage><error>").append(0).append("</error>")
		.append("<message>").append(base.getValidateInfo()).append("</message>")
		.append("</simplemessage><NPGWID>").append(base.getID())
		.append("</NPGWID></netpayresponse>");
		return builder;
	}
	
	/** 数据分发方法 发出的通知XML */
	public StringBuilder createXml(NetPayData pay)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=").append("\"").append("1.0")
			.append("\"").append(" encoding=").append("\"").append("utf-8").append("\"").append("?>")
			.append("<netpayresult>")
			.append("<outTtradeNo>").append(pay.getOutOrderNo()).append("</outTtradeNo>")
			.append("<waresCode>").append(pay.getWaresCode()).append("</waresCode>")
			.append("<totalFee>").append(pay.getTotalFee()).append("</totalFee>")
			.append("<payType>").append(pay.getPayType()).append("</payType>")
			.append("<payStatus>").append(getPayStatus(pay.getPayStatus())).append("</payStatus>")
			.append("<tranLineDetail>").append(pay.getTranLineDetail()==pay.getOutOrderNo()?"":pay.getTranLineDetail()).append("</tranLineDetail>")
			.append("</netpayresult>");
		return xml;
	}
	
	/** 异步生成ActionUrl 或者 ActionForm的时候 用来更新参数表原始生成参数 */
	public StringBuilder createInputParam(Request req)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=").append("\"").append("1.0")
		.append("\"").append(" encoding=").append("\"").append("utf-8").append("\"").append("?>")
			.append("<netpayrequest>")
			.append("<outTtradeNo>").append(req.getOutTtradeNo()).append("</outTtradeNo>")
			.append("<waresCode>").append(req.getWaresCode()).append("</waresCode>")
			.append("<expireTime>").append(req.getExpireTime()).append("</expireTime>")
			.append("<totalFee>").append(req.getTotalFee()).append("</totalFee>")
			.append("<payType>").append(req.getPayType()).append("</payType>")
			.append("<clientIP>").append(req.getClientIP()).append("</clientIP>")
			.append("<resultType>").append(req.getResultType()).append("</resultType>")
			.append("<NPGWID>").append(req.getNPGWID()).append("</NPGWID>")
			.append("<returnUrl>").append(req).append("</returnUrl>")
			.append("</netpayrequest>");
		return xml;
	}
	
	/** 读取商品编号到商品名称配置文件返回一个Map */
	public Map<String, Object> getWareCodeList()
	{
		Map<String,Object> codeList = new HashMap<String, Object>();
		codeList.put("PZ", "阳光医疗网[院内陪诊服务]");
		codeList.put("SC", "阳光医疗网[康众服务套餐]");
		codeList.put("MALLSC", "阳光医疗网[康众商城|康众服务套餐]");
		codeList.put("YM", "阳光医疗网[预约疫苗注射服务]");
		codeList.put("PE", "阳光医疗网[预约体检服务]");
		codeList.put("DS", "阳光医疗网[电话咨询服务]");
		codeList.put("OPCN", "阳光医疗网[提前挂号服务]");
		return codeList;
	}
	
	/** 读取数据分发地址配置文件返回一个Map */
	public Map<String, Object> getDataDistributeList()
	{
		return getList(Constants.NPGW_DATA_DISTRIBUTE_PATH);
	}
	
	/** 读取异常分发地址配置文件返回一个Map */
	public Map<String, Object> getExceptionDistributeList()
	{
		return getList(Constants.NPGW_EXCEPTION_DISTRIBUTE_PATH);
	}
	
	/** 映射文件    公用取得方法  */
	private Map<String, Object> getList(String path)
	{
		try
		{
			
			String filePath = this.getClass().getClassLoader().getResource(path).getPath();
			FileUtils fileUtil = (FileUtils)Factory.create(FileUtils.class);
			if(filePath.contains("%"))
				filePath = URLDecoder.decode(filePath, "utf-8");
			String xml = fileUtil.readFileContent(filePath);
			filePath = null;
			return parseXml(xml);
		}
		catch(Exception e)
		{
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("NULL", "NULL EXCEPTION");
			return result;
		}
	}
	
	/** 生成同步测试用的XML请求 */
	public StringBuilder createTestXmlString()
	{
		StringBuilder inputParam = new StringBuilder();
		inputParam.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><netpayrequest><outTtradeNo>")
			.append(getOrderType()).append("20100814000000250</outTtradeNo><totalFee>210</totalFee><clientIP>192.168.1.56</clientIP><returnUrl>http://opc.sun309.com/opc.php?c=booking/Index-opc</returnUrl><payType>")
			.append(getPayType()).append("</payType><resultType>")
			.append(getResultType()).append("</resultType><waresCode>")
			.append(getOrderType()).append("2010081400000000210201201</waresCode><expireTime>")
			.append(Constants.MIN_EXPIRE_TIME).append("</expireTime></netpayrequest>");
		return inputParam;
	}
	
	
	/** 生成异步测试用的XML请求 */
	public StringBuilder createAsyncTestXmlString()
	{
		StringBuilder inputParam = new StringBuilder();
		inputParam.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><netpayrequest>    <outTtradeNo>")
			.append(getOrderType()).append("20100920000000831</outTtradeNo>    <waresCode>")
			.append(getOrderType()).append("201009200000008310020101040</waresCode>    <payType>")
			.append(getPayType()).append("</payType>    <clientIP>124.172.245.98</clientIP>    <expireTime>")
			.append(Constants.MIN_EXPIRE_TIME).append("</expireTime>    <resultType>")
			.append(getResultType()).append("</resultType></netpayrequest>");
		return inputParam;
	}
	
	/** 生成异步结果测试用的XML请求 有生成相关参数的结点*/
	public StringBuilder createAsyncResultTestXmlString(String NPGWID)
	{
		StringBuilder inputParam = new StringBuilder();
		inputParam.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><netpayrequest><NPGWID>")
			.append(NPGWID).append("</NPGWID><totalFee>210</totalFee><returnUrl>http://opc.sun309.com/opc.php?c=booking/Index-opc</returnUrl><resultType>")
			.append(getResultType()).append("</resultType><clientIP>192.168.1.56</clientIP></netpayrequest>");
		return inputParam;
	}
	
	/** 生成异步结果测试测试用的XML请求 只有一个NPGW节点 */
	public StringBuilder createAsyncResultTestXmlOnlyNPGWIDString(String NPGWID)
	{
		StringBuilder inputParam = new StringBuilder();
		inputParam.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><netpayrequest><NPGWID>")
			.append(NPGWID).append("</NPGWID></netpayrequest>");
		return inputParam;
	}
	
	/** 设置测试参数中的PayType **/
	private String getPayType()
	{
		return "5";
	}
	
	/** 设置测试参数中的ResultType **/
	private String getResultType()
	{
		return "HTML";
	}
	
	/** 设置测试参数中的订单类型  */
	private String getOrderType()
	{
		return "OPCN";
	}
	
	/** 在数据通知方法中把支付状态转换为大写英文标识 */
	public String getPayStatus(Integer status)
	{
		switch(status.intValue())
		{
			case Constants.PAY_STATUS__EXPIRE:
				return "EXPIRE";
			case Constants.PAY_STATUS_INIT:
				return "EXPIRE";
			case Constants.PAY_STATUS_SUCCEED:
				return "SUCCEED";
			case Constants.PAY_STATUS_FAIL:
				return "FAIL";
			default :
				return "UNKOWN";
		}
	}
}
