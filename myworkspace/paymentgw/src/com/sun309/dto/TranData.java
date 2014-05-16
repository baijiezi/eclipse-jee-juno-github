package com.sun309.dto;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class TranData
{
	private Integer TransType;
	private String PayOrderID;
	private String ExtOrderID;
	private String CardNO;
	private Integer Flag;
	private String Message;
	private Integer Status;
	private String MidID;
	private Integer error;
	
	private ArrayList<TranData> list = new ArrayList<TranData>();
	
	public TranData(){}
	public TranData(String input)
	{
		try
		{
			Document doc = DocumentHelper.parseText(input);
			Element root = doc.getRootElement();
			if (root != null)
			{
				List node = root.elements();
				for(int i = 0; i < node.size(); i++)
				{
					Element foo = (Element) node.get(i);
					List cNode = foo.elements();
					TranData bd = new TranData();
					for (int ii = 0; ii < cNode.size(); ii++)
					{
						Element element = (Element) cNode.get(ii);

						if ("TransType".equals(element.getName())) 	 try { bd.setTransType( Integer.parseInt(element.getText())); } catch(Exception e) {}
						if ("PayOrderID".equals(element.getName())) 	 try { bd.setPayOrderID( element.getText()); } catch(Exception e) {}
						if ("ExtOrderID".equals(element.getName())) 	 try { bd.setExtOrderID( element.getText()); } catch(Exception e) {}
						if ("CardNO".equals(element.getName())) 	 try { bd.setCardNO( element.getText()); } catch(Exception e) {}
						if ("Flag".equals(element.getName())) 	 try { bd.setFlag( Integer.parseInt(element.getText())); } catch(Exception e) {}
						if ("Message".equals(element.getName())) 	 try { bd.setMessage( element.getText()); } catch(Exception e) {}
						if ("MidID".equals(element.getName())) 	 try { bd.setMidID( element.getText()); } catch(Exception e) {}
						if ("Status".equals(element.getName())) 	 try { bd.setStatus( Integer.parseInt(element.getText())); } catch(Exception e) {}
						
						if ("error".equals(element.getName())) 	 try { bd.setError( Integer.parseInt(element.getText())); } catch(Exception e) {}

						element=null;
					}
					list.add(bd);
					foo=null;
					cNode=null;
				}
				node=null;
			}
			
			doc = null;
			root=null;
		}
		catch (Exception e)
		{
			System.out.println("解释TranData出错");
 		}
	}
	
	
	public Integer getTransType()
	{
		return TransType;
	}
	public void setTransType(Integer transType)
	{
		TransType = transType;
	}
	public String getPayOrderID()
	{
		return PayOrderID;
	}
	public void setPayOrderID(String payOrderID)
	{
		PayOrderID = payOrderID;
	}
	public String getExtOrderID()
	{
		return ExtOrderID;
	}
	public void setExtOrderID(String extOrderID)
	{
		ExtOrderID = extOrderID;
	}
	public String getCardNO()
	{
		return CardNO;
	}
	public void setCardNO(String cardNO)
	{
		CardNO = cardNO;
	}
	public Integer getFlag()
	{
		return Flag;
	}
	public void setFlag(Integer flag)
	{
		Flag = flag;
	}
	public String getMessage()
	{
		return Message;
	}
	public void setMessage(String message)
	{
		Message = message;
	}
	public Integer getStatus()
	{
		return Status;
	}
	public void setStatus(Integer status)
	{
		Status = status;
	}
	public String getMidID()
	{
		return MidID;
	}
	public void setMidID(String midID)
	{
		MidID = midID;
	}
	public ArrayList<TranData> getList()
	{
		return list;
	}
	public void setList(ArrayList<TranData> list)
	{
		this.list = list;
	}
	public Integer getError()
	{
		return error;
	}
	public void setError(Integer error)
	{
		this.error = error;
	}
}
