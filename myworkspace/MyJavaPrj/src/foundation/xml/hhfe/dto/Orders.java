package foundation.xml.hhfe.dto;

import java.util.ArrayList;
import java.util.HashMap;

public class Orders
{
	private HashMap<String, Object> hm;
	private ArrayList<MedicalItem> list;
	public HashMap<String, Object> getHm()
	{
		return hm;
	}
	public void setHm(HashMap<String, Object> hm)
	{
		this.hm = hm;
	}
	public ArrayList<MedicalItem> getList()
	{
		return list;
	}
	public void setList(ArrayList<MedicalItem> list)
	{
		this.list = list;
	}
	 
}
