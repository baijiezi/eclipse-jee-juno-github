package processor;

import wsimport.MywsServicePortType;
import wsimport.RemoteServiceFactory;

public class GetNameProcessor 
{
	public static void main(String[] args)
	{
		MywsServicePortType remoteService = RemoteServiceFactory.createRemoteService();
		String result = remoteService.getName("sss");
		System.out.println(result);
	}
}
