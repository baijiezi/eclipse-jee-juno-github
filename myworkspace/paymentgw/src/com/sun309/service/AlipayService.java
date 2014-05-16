package com.sun309.service;

import java.util.Map;

import com.sun309.config.AlipayConfig;
import com.sun309.util.AlipaySubmit;

/**
 * 支付宝接口构造类
 * @author Administrator
 */
public class AlipayService {
	
	/**
	 * 支付宝提供给商户的服务接入网关URL
	 */
	private static final String ALIPAY_GATEWAY_NEW= "https://mapi.alipay.com/gateway.do?";
	
	/**
	 * 构造即时到账批量退款有密接口
	 * @param sParaTemp 请求参数集合
	 * @return 支付宝返回表单提交HTMl信息
	 * @throws Exception
	 */
	public static String refund_fastpay_by_platform_pwd(Map<String,String> sParaTemp) throws Exception {
		//增加基本信息
		sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		
		String resultType = sParaTemp.get("resultType");
		String resultText = "";
		if("HTML".equals(resultType)){
			resultText = AlipaySubmit.buildForm(sParaTemp, ALIPAY_GATEWAY_NEW, "get");
		}else if("XML".equals(resultType)){
			resultText = AlipaySubmit.buildXML(sParaTemp);
		}
		return resultText;
	}

}
