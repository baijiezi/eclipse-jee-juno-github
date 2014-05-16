package foundation.refect.methodPointer;

/**
 * Method的使用方法
 *
 */
public class Names {
	public String firstName ;
	public String lastName ;
 
	public Names(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFullName()
	{
		return firstName + " " + lastName;
	}
 
	public String getFullName(String firstName,String lastName)
	{
		return firstName + " " + lastName;
	}
}