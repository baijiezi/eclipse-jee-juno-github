package com.sun309.gateway.api;

import java.util.Hashtable;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class SpRpt
{
	private Hashtable<String, String> ht = new Hashtable<String, String>();

	public static void main(String[] args)
	{
		String a = "<MessagesReport count=\"3\"><message><FSMID>674256362</FSMID><STATE>0</STATE></message><message><FSMID>674256374</FSMID><STATE>0</STATE></message><message><FSMID>674251252</FSMID><STATE>0</STATE></message></MessagesReport>";
		SpRpt sp = new SpRpt(a);
		
	}
	
	public SpRpt(String xml)
	{
		Hashtable<String, String> _ht = new Hashtable<String, String>();
		try
		{
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			List node = root.elements();
			for(int i = 0; i < node.size(); i++)
			{
				Element element = (Element) node.get(i);
				List n = element.elements();
				String FSMID = new String();
				String STATE = new String();
				for(int j = 0; j < n.size(); j ++)
				{
					Element _e = (Element) n.get(j);
					if("FSMID".equals(_e.getName()))
					{
						FSMID = _e.getText();
					}
					if("STATE".equals(_e.getName()))
					{
						STATE = _e.getText();
					}
				}
				_ht.put(FSMID, STATE);
				element = null;
			}
			this.setHt(_ht);
			doc = null;
			root = null;
			node = null;
		}
		catch (Exception e)
		{
			System.out.println("--" + e); 
		}
		finally
		{
			xml = null;
		}
	}

	public Hashtable<String, String> getHt()
	{
		return ht;
	}

	public void setHt(Hashtable<String, String> ht)
	{
		this.ht = ht;
	}
}
