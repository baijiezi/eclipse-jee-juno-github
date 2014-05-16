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
	private int error = 0;	//������룬0:�޴� 1:����
	private String message = "";	//�� errorΪ1 ʱ���Դ����˵����Ϣ
	private String contents = "";	//���ص���������
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
	 *  ��������error��message��content�����ڵ�� XML
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
	 *  ��һ��Hashtable �����Ժ�ֵת��ΪXML
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
	 * ����һ�����ڵ��µĶ���ӽڵ㣬��XMLͷ��
	 * @param nodeName �ڵ�����
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
	 * ��һ��Hashtable �����Ժ�ֵת��ΪXML
	 * @param nodeName �ڵ�����
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
	 * ��һ���ڵ㼰������ӵ�XML
	 * @param node �ڵ�����
	 * @param content �ڵ�����
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
	 * ���������Ե�XML
	 * @param node ���ڵ�����
	 * @param item �ڵ�����
	 * @param list �ڵ���е����Ժ�ֵ
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
	 * ���������Ե�XML������һ�������������ǣ�Hashtable ��Ϊ WeakHashMap
	 * @param node ���ڵ�����
	 * @param item �ڵ�����
	 * @param list �ڵ���е����Ժ�ֵ
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
	 * �����򵥵�XML
	 * @param error ������룬0����ȷ��1������
	 * @param message ����Ľ�����Ϣ���� error Ϊ 1 ʱ����
	 * @return 
	 */
	public String createSimpleSml(String error, String message)
	{
		return new StringBuilder("<?xml version='1.0' encoding='utf-8'?>").append("<root>")
			.append("<error>").append(error).append("</error>").append("</message>").append(message)
			.append("</root>").toString();
	}
	
	
	/**
	 * ��һ��Map �����Ժ�ֵ��װΪһ��XML
	 * @param param ����Ҫ��װ�����Ժ�ֵ
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
	 * ����XML��ͨ�÷�����ʹ���˵ݹ�
	 * @param node �ڵ�����
	 * @param o �ڵ�����ݣ�����Ϊ Hashtable �� List �� ���� �� �ַ��� ��
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
