package foundation.exception.selfException;
 /**
 * @���ܣ�����һ��ʵ���࣬��Ӧ�����Զ�����쳣
  *
 */
public class CaculateFunction 
{
	public void add(float a, float b) throws ObjAlreadyExistException 
	{	
		if (a == b)
			throw new ObjAlreadyExistException("���������ȣ�");
	}
}
