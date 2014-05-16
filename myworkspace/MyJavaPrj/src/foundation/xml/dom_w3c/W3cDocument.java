package foundation.xml.dom_w3c;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

//Element �̳��� Node��Element ������� Node
public class W3cDocument 
{
	public static void main(String[] args)
	{
		W3cDocument w3cDoc = new W3cDocument();
		String name = w3cDoc.findName("Sun01");
		System.out.println(name);
	}
	
	
//	��ȡxml�ļ���ת��Ϊ Document ����
	public Document loadDocument()
	{
		DocumentBuilder documentBuilder = null;
		Document doc = null;
		try 
		{
			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = documentBuilder.parse(new File("src/foundation/xml/cfg.xml"));
        }catch (Exception e) 
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return doc;
	}
	
	
//	����ĳ��id ������
	public String findName(String id)
	{
		String idName = "";
		Document doc = this.loadDocument();
		Element docElement = doc.getDocumentElement();
		NodeList itemNodeList = docElement.getElementsByTagName("item");
		for(int i=0; i<itemNodeList.getLength(); i++)
		{
			Element itemElement = (Element)itemNodeList.item(i);
			String idValue = itemElement.getElementsByTagName("id").item(0).getTextContent();
			if(idValue.equals(id))
			{
				idName = itemElement.getElementsByTagName("name").item(0).getTextContent();
			}
		}
		return idName;
	}
}
