package com.sun309.config;

/**
 * 
 * 基础配置 @author Administrator
 *
 */
public class AlipayConfig {
	//合作者账号
	public static String seller_email = "13538788208";
	
	//合作者身份ID,以2088开头由16位纯数字组成的字符串
	public static String partner = "2088102681649780";
	
	//交易安全检验码，由数字和字母组成的32位字符串
	public static String key = "30ruggr3udodi58jn27kucd5fka7ixky";
	
	//支付宝服务器通知的页面，要用http://格式的完整路径，不允许加?id=123这类自定义参数
	//必须保证其他能够在互联网中访问得到
	public static String notify_url = "";
	
	//字符编码格式
	public static String input_charset = "utf-8";
	
	//签名方式
	public static String sign_type = "MD5";
	
	

}
