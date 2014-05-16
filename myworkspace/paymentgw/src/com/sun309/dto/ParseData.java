package com.sun309.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/** 
 * 解析批量退款xml @author Administrator
 */
public class ParseData {
	//请求参数的数组
	private Map<String,String> sParaTemp = new HashMap<String, String>();
	
	public ParseData(){}
	public ParseData(String input){
		try {
			Document doc = DocumentHelper.parseText(input);
			Element root = doc.getRootElement();
			if(root != null){
				List node = root.elements();
				for(int i=0;i<node.size();i++){
					Element element = (Element)node.get(i);
					if("batch-no".equals(element.getName())){
						sParaTemp.put("batch-no", element.getText());
					}
					if("refund-date".equals(element.getName())){
						sParaTemp.put("refund-date", element.getText());
					}
					if("batch-num".equals(element.getName())){
						sParaTemp.put("batch-num", element.getText());
					}
					if("detail-data".equals(element.getName())){
						sParaTemp.put("detail-data", element.getText());
					}
					if("resultType".equals(element.getName())){
						sParaTemp.put("resultType", element.getText());
					}
					element = null;					
				}
				node = null;
			}
		} catch (DocumentException e) {
			System.out.println("解析xml时出错");
		}
	}
	
	public Map<String, String> getSParaTemp() {
		return sParaTemp;
	}
	public void setSParaTemp(Map<String, String> paraTemp) {
		sParaTemp = paraTemp;
	}

}
