package com.sun309.oldNetPay;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

abstract class NetPayService {

	/**
	 * 进行数字签名
	 * 
	 * @return String
	 */
	abstract public String sign(String param);

	/**
	 * 验证返回数据的签名
	 * 
	 * @param param
	 * @return
	 */
	abstract public String verify(String param);

	@SuppressWarnings("unchecked")
	protected Map<String, Object> parseXml(String param) {

		Map<String, Object> result = new HashMap<String, Object>();
		SAXBuilder sb = new SAXBuilder();
		try {
			// 通过输入源构造一个Document
			Document doc = sb.build(new StringReader(param));
			Element root = doc.getRootElement();
			List<Element> list = root.getChildren();
			Iterator<Element> iter = list.iterator();
			while (iter.hasNext()) {
				Element el = iter.next();
				result.put(el.getName(), el.getValue());
			}
		} catch (Exception e) {
		}

		return result;
	}

}
