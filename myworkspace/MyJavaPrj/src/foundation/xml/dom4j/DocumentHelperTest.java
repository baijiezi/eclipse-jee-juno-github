package foundation.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

//SAXReader ���Խ�һ��XML �ļ�ת��Ϊ Document ����
//DocumentHelper ����Խ� XML �ַ��� ת��Ϊ Document ����
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
