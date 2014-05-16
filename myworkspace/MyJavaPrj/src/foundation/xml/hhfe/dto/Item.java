package foundation.xml.hhfe.dto;


public class Item
{
	private String Key;
	private String Value;
	private String Mapping;
	private String Desc;
	private Orders orders;
	
	public Orders getOrders()
	{
		return orders;
	}
	public void setOrders(Orders orders)
	{
		this.orders = orders;
	}
	public String getKey()
	{
		return Key;
	}
	public void setKey(String key)
	{
		Key = key;
	}
	public String getValue()
	{
		return Value;
	}
	public void setValue(String value)
	{
		Value = value;
	}
	public String getMapping()
	{
		return Mapping;
	}
	public void setMapping(String mapping)
	{
		Mapping = mapping;
	}
	public String getDesc()
	{
		return Desc;
	}
	public void setDesc(String desc)
	{
		Desc = desc;
	}
}
