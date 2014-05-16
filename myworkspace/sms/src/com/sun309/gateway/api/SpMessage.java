package com.sun309.gateway.api;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class SpMessage
{
	private String ID;
	private String FS;
	
	
	public SpMessage(String xml)
	{
		try
		{
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			List node = root.elements();
			for(int i = 0; i < node.size(); i++)
			{
				Element element = (Element) node.get(i);
				List n = element.elements();
				for(int j = 0; j < n.size(); j ++)
				{
					Element _e = (Element) n.get(j);
					if("ID".equals(_e.getName())) this.setID(_e.getText());
					if("FS".equals(_e.getName())) this.setFS(_e.getText());
				}
				element = null;
			}
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
	
	public String getID()
	{
		return ID;
	}
	public void setID(String id)
	{
		ID = id;
	}
	public String getFS()
	{
		return FS;
	}
	public void setFS(String fs)
	{
		FS = fs;
	}
}
