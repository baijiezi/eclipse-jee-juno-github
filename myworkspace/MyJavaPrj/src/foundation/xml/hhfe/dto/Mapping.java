package foundation.xml.hhfe.dto;

import java.util.ArrayList;
import java.util.HashMap;

public class Mapping
{
	private ArrayList<Bind> bindTrans;
	private HashMap<String, Tran> tran;
	public ArrayList<Bind> getBindTrans()
	{
		return bindTrans;
	}
	public void setBindTrans(ArrayList<Bind> bindTrans)
	{
		this.bindTrans = bindTrans;
	}
	public HashMap<String, Tran> getTran()
	{
		return tran;
	}
	public void setTran(HashMap<String, Tran> tran)
	{
		this.tran = tran;
	}
}
