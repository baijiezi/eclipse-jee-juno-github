package foundation.xml.hhfe.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Response
{
	private String code;
	private ArrayList<Item> itemList;
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public ArrayList<Item> getItemList()
	{
		return itemList;
	}
	public void setItemList(ArrayList<Item> itemList)
	{
		this.itemList = itemList;
	}
 
	public static ArrayList<HashMap<String, Object>> parse(String resultXml, Response res, HashMap<String, Object> params) throws Exception
	{
		ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		try
		{
			Document doc = DocumentHelper.parseText(resultXml);
			Element response = doc.getRootElement();
			List<?> responseNode = response.elements();
			for(int i = 0; i < responseNode.size(); i++)
			{
				Element rootEle = (Element)responseNode.get(i);
				if("details".toLowerCase().equals( rootEle.getName().toLowerCase() ))
				{
					List<?> rowNode = rootEle.elements();
					for(int ii = 0; ii < rowNode.size(); ii++)
					{
						Element rowEle = (Element)rowNode.get(ii);
						List<?> rowList = rowEle.elements();
						Item tempItem = new Item();
						HashMap<String, Object> hm = new HashMap<String, Object>(4);
						for(Item _item : res.getItemList())
						{
							tempItem = _item;
							boolean flag = true;
							boolean flag1 = true;
							for(int iii = 0; iii < rowList.size(); iii ++)
							{
								Element node = (Element)rowList.get(iii);
								if( _item.getMapping().toLowerCase().equals( node.getName().toLowerCase() )) 
								{ 
									Item item = new Item();
									item.setDesc(_item.getDesc());
									item.setKey(_item.getKey());
									item.setMapping(_item.getMapping()); 
									item.setValue(node.getText());
									hm.put(item.getKey(), item); 
									flag = false;
									break;
								}
							}
							if(flag)
							{
								for(String key : params.keySet())
								{
									if(key.toLowerCase().equals(_item.getKey().toLowerCase()) && (hm.get(key) == null || StringUtils.isEmpty(((Item)hm.get(key)).getValue())))
									{
										Item item = new Item();
										item.setDesc(_item.getDesc()); 
										item.setKey(_item.getKey());
										item.setMapping(_item.getMapping()); 
										item.setValue(params.get(key).toString());
										String value = params.get(key).toString();
										item.setValue(value);
										hm.put(item.getKey(), item); 
										flag1 = false;
										break;
									}
								}
								if(flag1)
								{
									tempItem.setValue("");
									hm.put(tempItem.getKey(), tempItem);  
								}
							}
						}
						resultList.add(hm); 
					}
				}
				if("master".toLowerCase().equals( rootEle.getName().toLowerCase() ))
				{
					List<?> rowNode = rootEle.elements();
					HashMap<String, Object> hm = new HashMap<String, Object>(4);
					Item tempItem = new Item();
					for(Item _item : res.getItemList())
					{
						tempItem = _item;
						boolean flag = true;
						boolean flag1 = true;
						for(int ii = 0; ii < rowNode.size(); ii++)
						{
							Element node = (Element)rowNode.get(ii);
							if( _item.getMapping().toLowerCase().equals( node.getName().toLowerCase() )) 
							{ 
								Item item = new Item();
								item.setDesc(_item.getDesc());
								item.setKey(_item.getKey());
								item.setMapping(_item.getMapping()); 
								item.setValue(node.getText());
								hm.put(item.getKey(), item); 
								flag = false;
								break;
							}
						}
						if(flag)
						{
							for(String key : params.keySet())
							{
								if(key.toLowerCase().equals(_item.getKey().toLowerCase()) && (hm.get(key) == null || StringUtils.isEmpty(((Item)hm.get(key)).getValue())))
								{
									Item item = new Item();
									item.setDesc(_item.getDesc()); 
									item.setKey(_item.getKey());
									item.setMapping(_item.getMapping()); 
									item.setValue(params.get(key).toString());
									String value = params.get(key).toString();
									item.setValue(value);
									hm.put(item.getKey(), item); 
									flag1 = false;
									break;
								}
							}
							if(flag1)
							{
								tempItem.setValue("");
								hm.put(tempItem.getKey(), tempItem);  
							}
						}
					}
					resultList.add(hm); 
				}
			}
			
//			for(HashMap<String, Item> hm : resultList)
//			{
//				for(String key : hm.keySet()) 
//				{
//					System.out.println(key + " = " + hm.get(key).getValue());  
//				}
//				System.out.println(" --------------------------------- ");
//			}
			
			return resultList;
		}
		catch (Exception e)
		{
			throw new Exception("解析返回值XML，异常【" + e.getMessage() + "】");
		}
	}
}
