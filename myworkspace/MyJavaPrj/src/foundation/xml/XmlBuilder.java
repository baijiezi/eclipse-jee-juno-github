package foundation.xml;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

public class XmlBuilder 
{
	private int error = 0;	//出错代码，0:无错； 1:错误
	private String message = "";	//当 error为1 时，对错误的说明信息
	private String contents = "";	//返回的正文内容
	private Hashtable<String, String> table = new Hashtable<String, String>();
	
	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}

	public int getError() 
	{
		return error;
	}

	public void setError(int error) 
	{
		this.error = error;
	}

	public String getContents() 
	{
		return contents;
	}

	public void setContents(String contents) 
	{
		this.contents = contents;
	}

	public Hashtable<String, String> getTable() 
	{
		return table;
	}

	public void setTable(Hashtable<String, String> table)
	{
		this.table = table;
	}

	
	/**
	 *  产生具有error、message、content三个节点的 XML
	 * @return 
	 */
	public String process()
	{
		StringBuffer xml = new StringBuffer();
		xml.append("<root>");
		xml.append("<error>");
		xml.append(this.getError());
		xml.append("</error>");
		xml.append("<message>");
		xml.append(this.getMessage());
		xml.append("</message>");
		if(this.getContents()!=null && !this.getContents().equals(""))
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

	
	/**
	 *  将一个Hashtable 的属性和值转化为XML
	 * @return 
	 */
	public String multiProcess()
	{
		StringBuffer xml = new StringBuffer();
		xml.append("<root>");
		for(String node : table.keySet())
		{
			String nodeValue = table.get(node);
			xml.append("<").append(node).append(">");
			xml.append(nodeValue);
			xml.append("</").append(node).append(">");
		}
		xml.append("</root>");
		return xml.toString();
	}
	
	
	/**
	 * 产生一个父节点下的多个子节点，有XML头部
	 * @param nodeName 节点名称
	 * @return 
	 */
	public String multiProcess(String nodeName)
	{
		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version='1.0' encoding='utf-8'?>");
		xml.append("<root>");
		if(!StringUtils.isEmpty(nodeName))
			xml.append("<").append(nodeName).append(">");
		for(String node : table.keySet())
		{
			String nodeValue = table.get(node);
			xml.append("<").append(node).append(">");
			xml.append(nodeValue);
			xml.append("</").append(node).append(">");
		}
		if(!StringUtils.isEmpty(nodeName))
			xml.append("</").append(nodeName).append(">");
		xml.append("</root>");
		return xml.toString();
	}
	
	
	/**
	 * 将一个Hashtable 的属性和值转化为XML
	 * @param nodeName 节点名称
	 * @return 
	 */
	public String p(String nodeName)
	{
		StringBuffer xml = new StringBuffer();
		if(!StringUtils.isEmpty(nodeName))
			xml.append("<").append(nodeName).append(">");
		for(String node : table.keySet())
		{
			String nodeValue = table.get(node);
			xml.append("<").append(node).append(">");
			xml.append(nodeValue);
			xml.append("</").append(node).append(">");
		}
		if(!StringUtils.isEmpty(nodeName))
			xml.append("</").append(nodeName).append(">");
		return xml.toString();
	}
	
	
	/**
	 * 将一个节点及内容添加到XML
	 * @param node 节点名称
	 * @param content 节点内容
	 * @return 
	 */
	public String p(String node, String content)
	{
		StringBuffer xml = new StringBuffer();
		if(!StringUtils.isEmpty(node))
			xml.append("<").append(node).append(">");
		xml.append(content);
		if(!StringUtils.isEmpty(node))
			xml.append("</").append(node).append(">");
		return xml.toString();
	}
	
	
	/**
	 * 产生带属性的XML
	 * @param node 父节点名称
	 * @param item 节点名称
	 * @param list 节点具有的属性和值
	 * @return 
	 */
	public String setXmlAttr(String item, String node, ArrayList<Hashtable<String, String>> list)
	{
		StringBuffer __result = new StringBuffer();
		for(Hashtable<String, String> ht : list)
		{
			StringBuffer _result = new StringBuffer();
			for(String _node : ht.keySet())
			{
				String nodeValue = ht.get(_node);
				_result.append(_node).append("=\"").append(nodeValue).append("\"");
			}
			__result.append("<").append(item).append(" ").append(_result.toString()).append(" />");
		}
		if(node == null)
			return this.p("", __result.toString());
		else
			return this.p(node, __result.toString());
	}
	
	
	/**
	 * 产生带属性的XML，与上一个方法的区别是，Hashtable 改为 WeakHashMap
	 * @param node 父节点名称
	 * @param item 节点名称
	 * @param list 节点具有的属性和值
	 * @return 
	 */
	public String setXmlAttr1(String item, String node, ArrayList<WeakHashMap<String, String>> list)
	{
		StringBuffer __result = new StringBuffer();
		for(WeakHashMap<String, String> ht : list)
		{
			StringBuffer _result = new StringBuffer();
			for(String _node : ht.keySet())
			{
				String nodeValue = ht.get(_node);
				_result.append(_node).append("=\"").append(nodeValue).append("\"");
			}
			__result.append("<").append(item).append(" ").append(_result.toString()).append(" />");
		}
		if(node == null)
			return this.p("", __result.toString());
		else
			return this.p(node, __result.toString());
	}
	
	
	/**
	 * 产生简单的XML
	 * @param error 出错代码，0：正确，1：错误
	 * @param message 错误的解释信息，当 error 为 1 时有用
	 * @return 
	 */
	public String createSimpleSml(String error, String message)
	{
		return new StringBuilder("<?xml version='1.0' encoding='utf-8'?>").append("<root>")
			.append("<error>").append(error).append("</error>").append("</message>").append(message)
			.append("</root>").toString();
	}
	
	
	/**
	 * 将一个Map 的属性和值组装为一个XML
	 * @param param 包含要组装的属性和值
	 * @return 
	 */
	public String buildXmlBySun309ConcurrentHashMap(ConcurrentHashMap<String, String> param)
	{
		StringBuffer xml = new StringBuffer();
		for(Entry<String, String> entry : param.entrySet())
		{
			xml.append("<").append(entry.getKey()).append(">");
			xml.append(entry.getValue());
			xml.append("</").append(entry.getKey()).append(">");
		}
		return xml.toString();
	}
	
	
	/**
	 * 产生XML的通用方法，使用了递归
	 * @param node 节点名称
	 * @param o 节点的内容，可能为 Hashtable 或 List 或 数组 或 字符串 等
	 * @return 
	 */
	public String createXml(String node, Object o)
	{
		if(o instanceof Hashtable)
		{
			Hashtable<String, Object> ht = (Hashtable)o;
			StringBuilder sb = new StringBuilder();
			sb.append("<").append(node).append(">");
			for(String k : ht.keySet())
			{
				sb.append(createXml(k, ht.get(k)));
			}
			sb.append("</").append(node).append(">");
			return sb.toString();
		}
		else if(o instanceof List)
		{
			List l = (List)o;
			StringBuilder sb = new StringBuilder();
			for(Object so : l)
			{
				sb.append(createXml(node, so));
			}
			return sb.toString();
		}
		else if(o.getClass().isArray())
		{
			Object[] l = (Object[])o;
			StringBuilder sb = new StringBuilder();
			for(Object so : l)
			{
				sb.append(createXml(node, so));
			}
			return sb.toString();
		}
		else
		{
			StringBuilder sb = new StringBuilder();
			sb.append("<").append(node).append(">");
			sb.append(o);
			sb.append("</").append(node).append(">");
			return sb.toString();
		}
	}
	
	public static void main(String[] args)
	{
		XmlBuilder xmlUtils = new XmlBuilder();
		xmlUtils.multiProcess();
	}
}
