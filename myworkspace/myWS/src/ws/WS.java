package ws;

//本地访问地址:http://127.0.0.1:8080/myWS/services/mywsService.asmx
public class WS implements WebService
{
	public String getName(String name)
	{
		String result = "<?xml version='1.0' encoding='utf-8'?><root><items><name></name></items></root>";
		return "Hello";
		
	}
	
	public String getAge(int age)
	{
		String result = "<?xml version='1.0' encoding='utf-8'?><root><items><age>" +age +"</age></items></root>";
		return result;
	}
}
