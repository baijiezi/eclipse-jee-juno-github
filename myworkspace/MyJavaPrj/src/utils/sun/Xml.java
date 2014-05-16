package utils.sun;

import java.util.ArrayList;
import java.util.HashMap;

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

class Item
{
	private String Key;
	private String Value;
	private String Mapping;
	private String Desc;
	
	public String getKey()
	{
		return Key;
	}
	public void setKey(String key)
	{
		Key = key;
	}
	public String getValue()
	{
		return Value;
	}
	public void setValue(String value)
	{
		Value = value;
	}
	public String getMapping()
	{
		return Mapping;
	}
	public void setMapping(String mapping)
	{
		Mapping = mapping;
	}
	public String getDesc()
	{
		return Desc;
	}
	public void setDesc(String desc)
	{
		Desc = desc;
	}
}
