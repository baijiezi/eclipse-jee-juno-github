package foundation.exception.selfException;

 /**
 *�Զ����쳣�����֣��ֱ��Ǽ̳�Exception��Throwable ��,��ΪThrowable��exception�ĸ��࣬���ԣ�
 *�̳�Exception�ͼ̳�ThrowableЧ����࣬�����Ǽ̳�Exception���Զ�������ʵ�ֺ��÷���
 *
 */
public class Application 
{
	private static CaculateFunction CaculateFunction = null;
   	public static void main(String[] args) 
   	{
   		CaculateFunction = new CaculateFunction();
   		try 
   		{
   			CaculateFunction.add(12, 12);
   		} 
   		catch (ObjAlreadyExistException e) 
   		{
   			e.printStackTrace();
   		}
   	}
}