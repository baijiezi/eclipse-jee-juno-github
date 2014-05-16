package wsimport;

/**
 * 自己添加的工厂类
 *
 */
public class RemoteServiceFactory
{
	public static MywsServicePortType createRemoteService()
	{
		MywsService wsService = new MywsService();
		MywsServicePortType remoteService = wsService.getMywsServiceHttpPort();
		return remoteService;
	}
}
