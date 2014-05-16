package foundation.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

//SAXReader 可以将一个XML 文件转化为 Document 对象
//DocumentHelper 则可以将 XML 字符串 转化为 Document 对象
public class DocumentHelperTest 
{
	public static void main(String[] args)
	{
		String xml = "";
		Document doc = null;
		try 
		{
			doc = DocumentHelper.parseText(xml);
		}
		catch (DocumentException e) 
		{
			e.printStackTrace();
		}
		
	}
}
