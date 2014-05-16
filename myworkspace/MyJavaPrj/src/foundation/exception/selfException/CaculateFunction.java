package foundation.exception.selfException;
 /**
 * @功能；定义一个实现类，它应用了自定义的异常
  *
 */
public class CaculateFunction 
{
	public void add(float a, float b) throws ObjAlreadyExistException 
	{	
		if (a == b)
			throw new ObjAlreadyExistException("输入的数相等！");
	}
}
