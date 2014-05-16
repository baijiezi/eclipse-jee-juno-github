package com.sun309.test;

import java.util.HashMap;
import java.util.Map;

import com.sun309.dto.ParseData;
import com.sun309.util.AlipaySubmit;
import com.sun309.ws.Ws;

public class RefundTest {
	public static void main(String[] args) {
//		AlipaySubmit as = new AlipaySubmit();
//		Map<String,String> map = new HashMap<String, String>();
//		map.put("resultType", "XML");
//		map.put("325266@fag", "333");
//		System.out.println(as.buildXML(map).toString());
		
		Ws ws = new Ws();
		String input = "<?xml version=\"1.0\" encoding=\"utf-8\"?><netrefundrequest><batch-no>201112200001</batch-no>" +
				"<refund-date>2011-12-21 12:00:00</refund-date><batch-num>2</batch-num>" +
				"<detail-data>20111101123456^200^测试#20111101123458^233^退款</detail-data><resultType>HTML</resultType></netrefundrequest>";
		String result = ws.getRefundInfo(input);
		System.out.println(result);
		
//		Map<String,String> map = new HashMap<String, String>();
//		String input = "<?xml version=\"1.0\" encoding=\"utf-8\"?><netrefundrequest><batch-no>201112200001</batch-no>" +
//		"<refund-date>2011-12-21 12:00:00</refund-date><batch-num>2</batch-num>" +
//		"<detail-data>20111101123456^200^测试#20111101123458^233^退款</detail-data><resultType>HTML</resultType></netrefundrequest>";
//		ParseData pd = new ParseData(input);
//		System.out.println(pd.getSParaTemp().get("resultType"));
		
//		Map<String,String> map = new HashMap<String, String>();
//		map.put("resultType", "XML");
//		map.put("325266@fag", "333");
//		map.remove("resultType");
//		System.out.println(map.get("325266@fag"));
		
	}

}
