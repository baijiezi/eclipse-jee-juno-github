package foundation.xml.hhfe.dto;

public class Tran
{
	private String code;
	private Request request;
	private Response response;
	public Request getRequest()
	{
		return request;
	}
	public void setRequest(Request request)
	{
		this.request = request;
	}
	public Response getResponse()
	{
		return response;
	}
	public void setResponse(Response response)
	{
		this.response = response;
	}
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
}
