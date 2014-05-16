package foundation.refect;

/**
 * Student ÀàµÄ¸¸Àà
 *
 */
public class Person 
{
	private String tall;
	public String weight = "56";
	
	
	public Person() {}
	
	public Person(String tall, String weight)
	{
		this.tall = tall;
		this.weight = weight;
	}
	
	
	public String getTall()
	{
		return tall;
	}
	public void setTall(String tall)
	{
		this.tall = tall;
	}
	public String getWeight() 
	{
		return weight;
	}
	public void setWeight(String weight)
	{
		this.weight = weight;
	}
	
	
}
