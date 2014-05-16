package foundation.others.clone;

public class Score implements Cloneable
{
	private String Chinese;
	private String English;
	
	public Score clone()
	{
		Score cloned = null;
		try 
		{
			cloned = (Score)super.clone();
		} 
		catch (CloneNotSupportedException e) 
		{
			e.printStackTrace();
		}
		return cloned;
	}
	
	public String getChinese() 
	{
		return Chinese;
	}
	public void setChinese(String chinese) 
	{
		Chinese = chinese;
	}
	public String getEnglish() 
	{
		return English;
	}
	public void setEnglish(String english)
	{
		English = english;
	}
	
	
}
