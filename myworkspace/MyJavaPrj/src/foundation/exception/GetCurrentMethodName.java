package foundation.exception;

//ͨ���쳣��Ķ�ջ��Ϣ��ȡ��ǰ����������
public class GetCurrentMethodName 
{
	public static void main(String[] args)
	{
		GetCurrentMethodName getMethodName = new GetCurrentMethodName();
		getMethodName.aaa();
	}
	
	public void aaa()
	{
		//��ȡ��ǰ����������
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		//��ȡ�����ߵķ�����
		System.out.println(new Exception().getStackTrace()[1].getMethodName());
	}
}
