package foundation.xml.hhfe.util;

import java.util.ArrayList;
import java.util.HashMap;

import foundation.xml.hhfe.dto.Item;

public class Xml
{
	public static String builder(HashMap<String, Item> param) 
	{
		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version='1.0' encoding='utf-8'?><request>");
		for (String key : param.keySet())
		{
			xml.append("<");
			xml.append(param.get(key).getMapping());
			xml.append(">");
			xml.append(param.get(key).getValue());  
			xml.append("</");
			xml.append(param.get(key).getMapping());
			xml.append(">");
		}
		xml.append("</request>"); 
		return xml.toString();
	}
	
	public static String builder(ArrayList<Item> itemList) 
	{
		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version='1.0' encoding='GBK'?><request>");
		for (Item item : itemList)
		{
			xml.append("<");
			xml.append(item.getMapping());
			xml.append(">");
			xml.append(item.getValue());  
			xml.append("</");
			xml.append(item.getMapping());
			xml.append(">");
		}
		xml.append("</request>"); 
		return xml.toString();
	}
}
