package foundation.xml.hhfe.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import foundation.xml.hhfe.dto.Bind;
import foundation.xml.hhfe.dto.Item;
import foundation.xml.hhfe.dto.Mapping;
import foundation.xml.hhfe.dto.Request;
import foundation.xml.hhfe.dto.Response;
import foundation.xml.hhfe.dto.Tran;

public class ConfigProperties
{
	private String className;
	
	// �������ļ��л�� mapping.xml �ļ���·��
	public String get(String key) throws Exception
	{
		InputStream is = getClass().getResourceAsStream("src.foundation.xml.hhfe.config.properties");
		Properties dbProps = new Properties();
		try { dbProps.load(is); } catch (IOException e) { throw new Exception("���������ļ���config.properties��ʧ��" + e); }
		String value = dbProps.getProperty(key);
		return value; 
	}
	
	public static void main(String[] args)
	{
		ConfigProperties c = new ConfigProperties();
		try
		{
			String a[] = c.getBindTrans("GET_USER_INFO");
			System.out.println(a); 
			
			Tran t = c.getTran("sun0003");
			System.out.println(t); 
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	//�������ȡ Tran ����
	public Tran getTran(String transCode) throws Exception
	{
		Mapping mapping = parseMapping();
		return mapping.getTran().get(transCode); 
	}
	
	
	//�������ȡ��������
	public String[] getBindTrans(String buzCode) throws Exception
	{
		Mapping mapping = parseMapping();
		ArrayList<Bind> bindList = mapping.getBindTrans();
		for(Bind bind : bindList)
		{
			if(bind.getOuterCode().equals(buzCode))
			{
				String[] transCode = bind.getInnerCode().toString().split(",");
				this.setClassName(bind.getClassName()); 
				for(int i = 0; i < transCode.length; i++)
				{
					transCode[i] = transCode[i].trim();
				}
				return transCode;
			}
		}
		return null;
	}
	
	
	//�� mapping.xml �ļ�����רΪ Mapping ���󣬲�����ö���
	private Mapping parseMapping() throws Exception
	{
		//��ȥ�Ĵ��룺������������� mapping ������ֱ�ӷ���
		SAXReader reader = new SAXReader();
		try
		{
			Mapping mapping = new Mapping();
			
			Document doc = reader.read(new File("src/foundation/xml/hhfe/mapping.xml"));
			Element rootEle = doc.getRootElement();
			List<?> rootNode = rootEle.elements(); 
			
			ArrayList<Bind> bindTransList = new ArrayList<Bind>();
			for(int i = 0; i < rootNode.size(); i++)
			{
				Element mappingEle = (Element)rootNode.get(i);
				if("BindTrans".toLowerCase().equals(mappingEle.getName().toLowerCase()))
				{
					List<?> bindTransNode = mappingEle.elements();
					for(int ii = 0; ii < bindTransNode.size(); ii++)
					{
						Element bindTransEle = (Element)bindTransNode.get(ii);
						//��ȡ�ڵ�������б�
						List<?> bindAttribute = bindTransEle.attributes();
						Bind b = new Bind();
						for(int iii = 0; iii < bindAttribute.size(); iii++)
						{
							Attribute attribute = (Attribute)bindAttribute.get(iii); 
							if( "OuterCode".toLowerCase().equals(attribute.getName().toLowerCase()) ) b.setOuterCode(attribute.getValue()); 
							if( "InnerCode".toLowerCase().equals(attribute.getName().toLowerCase()) ) b.setInnerCode(attribute.getValue()); 
							if( "Desc".toLowerCase().equals(attribute.getName().toLowerCase()) ) 	  b.setDesc(attribute.getValue()); 
							if( "ClassName".toLowerCase().equals(attribute.getName().toLowerCase()) ) 	  b.setClassName(attribute.getValue()); 
						}
						bindTransList.add(b); 
					}
					mapping.setBindTrans(bindTransList);
				}
				if("Trans".toLowerCase().equals(mappingEle.getName().toLowerCase()))
				{
					List<?> transNode = mappingEle.elements();
					HashMap<String, Tran> tranHm = new HashMap<String, Tran>();
					
					for(int ii = 0; ii < transNode.size(); ii++)
					{
						Element transEle = (Element)transNode.get(ii);
						List<?> tranAttribute = transEle.attributes();
						String code = "";
						for(int iii = 0; iii < tranAttribute.size(); iii++)
						{
							Attribute attribute = (Attribute)tranAttribute.get(iii); 
							if( "Code".toLowerCase().equals(attribute.getName().toLowerCase()) )  code = attribute.getValue(); 
						}
						Tran tran = new Tran();
						tran.setCode(code); 
						Request request = new Request();
						Response response = new Response();
						List<?> tranNode = transEle.elements();
						for(int iii = 0; iii < tranNode.size(); iii++)
						{
							Element tranEle = (Element)tranNode.get(iii);
							request.setCode(code); 
							if("Request".toLowerCase().equals(tranEle.getName().toLowerCase()))
							{
								List<?> requestNode = tranEle.elements();
								ArrayList<Item> itemList = new ArrayList<Item>();
								for(int iiii = 0; iiii < requestNode.size(); iiii ++)
								{
									Element itemEle = (Element)requestNode.get(iiii);
									List<?> itemAttribute = itemEle.attributes();
									Item item = new Item();
									for(int j = 0; j < itemAttribute.size(); j++)
									{
										Attribute attribute = (Attribute)itemAttribute.get(j); 
										if( "Key".toLowerCase().equals((attribute).getName().toLowerCase()) )     item.setKey(attribute.getValue());
										if( "Mapping".toLowerCase().equals((attribute).getName().toLowerCase()) ) item.setMapping(attribute.getValue());
										if( "Desc".toLowerCase().equals((attribute).getName().toLowerCase()) )    item.setDesc(attribute.getValue());						
									}
									itemList.add( item );
								}
								request.setItemList(itemList); 
							}
							tran.setRequest(request);
							
							
							response.setCode(code);
							if("Response".toLowerCase().equals(tranEle.getName().toLowerCase()))
							{
								List<?> responseNode = tranEle.elements();
								ArrayList< Item > itemList = new ArrayList< Item >();
								for(int iiii = 0; iiii < responseNode.size(); iiii ++)
								{
									Element itemEle = (Element)responseNode.get(iiii);
									List<?> itemAttribute = itemEle.attributes();
									Item item = new Item();
									for(int j = 0; j < itemAttribute.size(); j++)
									{
										Attribute attribute = (Attribute)itemAttribute.get(j); 
										if( "Key".toLowerCase().equals((attribute).getName().toLowerCase()) )     item.setKey(attribute.getValue());
										if( "Mapping".toLowerCase().equals((attribute).getName().toLowerCase()) ) item.setMapping(attribute.getValue());
										if( "Desc".toLowerCase().equals((attribute).getName().toLowerCase()) )    item.setDesc(attribute.getValue());						
									}
									itemList.add( item );
								}
								response.setItemList(itemList); 
							}
							tran.setResponse(response);
						}
						tranHm.put(code, tran);
					}
					mapping.setTran(tranHm);
				}
			}
			//.....��ȥ���� mapping ����Ĵ���
			return mapping;
		} 
		catch (DocumentException e)
		{
			throw new Exception("���������ļ���mapping.xml��ʧ��" + e);
		}
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}
}
