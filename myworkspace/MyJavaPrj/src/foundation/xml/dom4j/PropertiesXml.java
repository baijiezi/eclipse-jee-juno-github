package foundation.xml.dom4j;

import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//dom4j 解析带属性的XML
public class PropertiesXml {

	public static void main(String[] args)
	{
		PropertiesXml propertiesXml = new PropertiesXml();
		String idName = propertiesXml.findName("Sun01");
		System.out.println(idName);
	}
		
//	获取xml文件并转化为 Document 对象
	public Document loadDocument()
	{   
		Document doc = null;
		try 
		{
			doc = new SAXReader().read(new File("src/foundation/xml/propertiesXml.xml"));
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
			if(item.attribute("id").getValue().equals(id))
			{
				idName = item.attribute("name").getValue();
			}
		}
		return idName;
	}
}
