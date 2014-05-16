package com.sun309.util;

import java.util.Hashtable;

public class PeInstituteId
{
	private static final String PAY_INSTITUTEID_PREFIX = "TJ";
	private static final StringBuffer sb = new StringBuffer();
	
	public static final String get(String hospitalNo) throws Exception
	{
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put("0020101010", "0020101010"); //广东省第二人民医院体检 
		ht.put("0020101012", "0020101012"); //珠江医院体检 
		ht.put("0020101017", "0020101017"); //武警广东省总队医院体检 
		ht.put("0020101024", "0020101024"); //广东省荣誉军人康复医院体检 
		ht.put("0020101032", "0020101032"); //广州市越秀区中医医院体检 
		ht.put("0020101033", "0020101033"); //广州市海珠区第二人民医院体检
		ht.put("0020102003", "0020102003"); //中山大学附属第二医院体检
		ht.put("0020102008", "0020102008"); //广州市第一人民医院体检 
		ht.put("0020102011", "0020102011"); //广州医学院第一附属医院体检 
		ht.put("0020102012", "0020102012"); //广州医学院第二附属医院体检 
		ht.put("0020102020", "0020102020"); //广州医学院第三附属医院体检 
		ht.put("0020102035", "0020102035"); //暨南大学附属第一医院——广州华侨医院体检
		ht.put("0020102036", "0020102036"); //广东药学院附属第一医院体检 
		ht.put("0020102037", "0020102037"); //广州空军医院体检 
		ht.put("0020102045", "0020102045"); //中国人民解放军第421医院体检
		ht.put("0020102046", "0020102046"); //广州市海珠区第一人民医体检
		ht.put("0020103012", "0020103012"); //广州市黄埔区红十字会医院体检
		ht.put("0020103014", "0020103014"); //广州市荔湾区第二人民医院体检
		ht.put("0020103026", "0020103026"); //广州市天河区妇幼保健院体检
		ht.put("0020201004", "0020201004"); //广州市番禺区中医院体检
		ht.put("0020303005", "0020303005"); //黄埔区中医院体检
		ht.put("0020403003", "0020403003"); //广州市荔湾区妇幼保健院体检
		
		String instituteId = ht.get(hospitalNo);
		
		ht = null;
		return (instituteId == null) ? instituteId : instituteId + PAY_INSTITUTEID_PREFIX;
	}
}
