package wsimport;

/**
 * �Լ���ӵĹ�����
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
