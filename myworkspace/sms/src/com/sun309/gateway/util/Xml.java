package com.sun309.gateway.util;

import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.commons.lang.StringUtils;

public class Xml
{
	private String messages = "";

	private int error = 0;

	private String contents = "";

	private Hashtable<String, String> table = new Hashtable<String, String>();

	public static void main(String[] args)
	{
		Xml xml = new Xml();
		xml.multiProcess();
	}

	public String process()
	{
		StringBuffer xml = new StringBuffer();
		xml.append("<root>");
		xml.append("<error>");
		xml.append(this.getError());
		xml.append("</error>");
		xml.append("<message>");
		xml.append(this.getMessages());
		xml.append("</message>");
		if (this.getContents() != null && !"".equals(this.getContents()))
		{
			xml.append("<content>");
			xml.append("<![CDATA[");
			xml.append(this.getContents());
			xml.append("]]>");
			xml.append("</content>");
		}
		xml.append("</root>");
		return xml.toString();
	}

	public String multiProcess()
	{
		StringBuffer xml = new StringBuffer();
		xml.append("<root>");
		for (String node : table.keySet())
		{
			String nodeValue = table.get(node);
			xml.append("<");
			xml.append(node);
			xml.append(">");
			xml.append(nodeValue);
			xml.append("</");
			xml.append(node);
			xml.append(">");
		}
		xml.append("</root>");
		return xml.toString();
	}

	public String multiProcess(String nodeName)
	{
		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version='1.0' encoding='utf-8'?><root>");
		if (!StringUtils.isEmpty(nodeName))
			xml.append("<" + nodeName + ">");
		for (String node : table.keySet())
		{
			String nodeValue = table.get(node);
			xml.append("<");
			xml.append(node);
			xml.append(">");
			xml.append(nodeValue);
			xml.append("</");
			xml.append(node);
			xml.append(">");
		}
		if (!StringUtils.isEmpty(nodeName))
			xml.append("</" + nodeName + ">");
		xml.append("</root>");
		return xml.toString();
	}

	public String p(String nodeName)
	{
		StringBuffer xml = new StringBuffer();
		if (!StringUtils.isEmpty(nodeName))
			xml.append("<" + nodeName + ">");
		for (String node : table.keySet())
		{
			String nodeValue = table.get(node);
			xml.append("<");
			xml.append(node);
			xml.append(">");
			xml.append(nodeValue);
			xml.append("</");
			xml.append(node);
			xml.append(">");
		}
		if (!StringUtils.isEmpty(nodeName))
			xml.append("</" + nodeName + ">");
		return xml.toString();
	}

	public String p(String node, String content)
	{
		StringBuffer xml = new StringBuffer();
		if (!StringUtils.isEmpty(node))
		{
			xml.append("<");
			xml.append(node);
			xml.append(">");
		}
		xml.append(content);
		if (!StringUtils.isEmpty(node))
		{
			xml.append("</");
			xml.append(node);
			xml.append(">");
		}
		return xml.toString();
	}

	public String setXmlAttr(String item, String _node, ArrayList<Hashtable<String, String>> list)
	{
		StringBuffer __result = new StringBuffer();
		for (Hashtable<String, String> ht : list) 
		{
			StringBuffer _result = new StringBuffer();
			for (String node : ht.keySet())
			{
				String nodeValue = ht.get(node);
				_result.append(node + "=\"" + nodeValue + "\" ");
			}
			__result.append("<" + item + " " + _result.toString() + " />");
		}
		if(_node == null)
			return this.p("", __result.toString());
		else
			return this.p(_node, __result.toString());
	}
	
	public String createSimpleXml(String error, String message)
	{
		return "<?xml version='1.0' encoding='utf-8'?><root><error>" + error + "</error><message>" + message + "</message></root>";
	}

	public String getContents()
	{
		return contents;
	}

	public void setContents(String contents)
	{
		this.contents = contents;
	}

	public int getError()
	{
		return error;
	}

	public void setError(int error)
	{
		this.error = error;
	}

	public String getMessages()
	{
		return messages;
	}

	public void setMessages(String messages)
	{
		this.messages = messages;
	}

	public Hashtable<String, String> getTable()
	{
		return table;
	}

	public void setTable(Hashtable<String, String> table)
	{
		this.table = table;
	}

}
