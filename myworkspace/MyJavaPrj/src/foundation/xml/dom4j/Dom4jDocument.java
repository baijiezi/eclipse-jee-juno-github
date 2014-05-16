package foundation.xml.dom4j;

import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Dom4jDocument 
{
	public static void main(String[] args)
	{
		Dom4jDocument dom4jDocument = new Dom4jDocument();
		String idName = dom4jDocument.findName("Sun01");
		System.out.println(idName);
	}
	
//	获取xml文件并转化为 Document 对象
	public Document loadDocument()
	{   
		Document doc = null;
		try 
		{
			doc = new SAXReader().read(new File("src/foundation/xml/cfg.xml"));
		} 
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
		return doc;
	}   
	
	
//	查找某个id 的名称
	public String findName(String id)
	{
		String idName = "";
		Document doc = this.loadDocument();
		Element rootElement = doc.getRootElement();
		List<Element> itemList = rootElement.elements("item");
		for(Element item : itemList)
		{
			List<Element> itemElements = item.elements();
			for(Element element : itemElements)
			{
				if(element.getName().equals("id") && element.getText().equals(id))
				{
					Element nameElement = item.element("name");
					idName = nameElement.getText();
				}
			}
		}
		return idName;
	}
	
}
