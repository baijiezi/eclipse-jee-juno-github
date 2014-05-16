package com.sun309.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun309.config.AlipayConfig;
import com.alipayRefund.util.AlipayCore;

public class AlipaySubmit {
	/**
	 * 构造提交表单HTML数据
	 * @param sParaTemp 请求参数数组
	 * @param gateway 网关地址
	 * @param strMethod 提交方式
	 * @return 提交表单HTML文本
	 */
	public static String buildForm(Map<String,String> sParaTemp,String gateway,String strMethod){
		//待请求参数数组
		Map<String,String> sPara = buildRequestPara(sParaTemp);
		List<String> keys = new ArrayList<String>(sPara.keySet());
		
		StringBuffer sbHtml = new StringBuffer();
		sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + gateway
				+ "_input_charset=" + AlipayConfig.input_charset + "\" method=\"" + strMethod
				+ "\">");
		
		for(int i=0;i<keys.size();i++){
			String name = (String)keys.get(i);
			String value = (String)sPara.get(name);
			if(!"resultType".equals(name) && !"HTML".equals(value)){
				sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value
					+ "\"/>");
			}
		}
		
		sbHtml.append("<input type=\"submit\" value=\"refund\" style=\"display:none;\"></form>");
		sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");
		
		return sbHtml.toString();
	}
	
	
	/**
	 * 构造提交表单的XML数据
	 * @param sParaTemp 请求参数数组
	 * @param gateway 网关地址
	 * @param strMethod 提交方式
	 * @return 提交表单XML文本
	 */
	public static String buildXML(Map<String,String> sParaTemp){
		//待请求参数数组
		Map<String,String> sPara = buildRequestPara(sParaTemp);
		List<String> keys = new ArrayList<String>(sPara.keySet());
		
		StringBuilder strurl = new StringBuilder("https://mapi.alipay.com/gateway.do?");
		for(int i=0;i<keys.size();i++){
			String name = (String)keys.get(i);
			String value = (String)sPara.get(name);
			if(i == keys.size() - 1){//拼接时不包括最后一个&字符
				strurl.append(name).append("=").append(value);
			}else{
				strurl.append(name).append("=").append(value).append("&");
			}
			
		}
		
		//构造xml数据
		StringBuilder builder = new StringBuilder("<?xml version=")
		.append("\"").append("1.0")
		.append("\"").append(" encoding=")
		.append("\"").append("utf-8")
		.append("\"").append("?>")
		.append("<netrefundresponse>")
		.append("<actionUrl>")
		.append("<![CDATA[ ")
		.append(strurl)
		.append(" ]]>")
		.append("</actionUrl>")
		.append("</netrefundresponse>");
		
		return builder.toString();
	}
	
	/**
	 * 生成要请求给支付宝的参数数组
	 * @param sParaTemp 请求前的参数数组
	 * @return 要请求的参数数组
	 */
	private static Map<String,String> buildRequestPara(Map<String,String> sParaTemp)
	{
		//除去数组中的空值和签名函数
		Map<String,String> sPara = AlipayCore.paraFilter(sParaTemp);
		//除去数组中的返回类型
		sPara.remove("resultType");
		//生成签名结果
		String mysign = AlipayCore.buildMysign(sPara,AlipayConfig.key,AlipayConfig.input_charset);
		//签名结果与签名方式加入请求提交参数组中
		sPara.put("sign", mysign);
		sPara.put("sign_type", AlipayConfig.sign_type);
		return sPara;
	}

}
