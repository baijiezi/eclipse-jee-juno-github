package foundation.xml.hhfe.dto;

import java.util.ArrayList;
import java.util.HashMap;

import foundation.xml.hhfe.util.ConfigProperties;
import foundation.xml.hhfe.util.Xml;

public class Request
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


	public static String builder(HashMap<String, Object> params, String transCode, ConfigProperties config) throws Exception
	{
		Tran tran = config.getTran(transCode);
		if(tran == null) throw new Exception("未定义的交易【" + transCode + "】"); 
		Request request = tran.getRequest();
		
		ArrayList<Item> itemList = request.getItemList();
		for(Item item : itemList)
		{
			if(params.get(item.getKey()) == null) throw new Exception(new StringBuilder(item.getDesc()).append("不能为空，请核对数据！").toString());
			String value = params.get(item.getKey()).toString();
			item.setValue(value);
			if("''".equals(value))
			{
				params.put(item.getKey(), "");
			}
		}
		return Xml.builder(itemList);
	}
}
