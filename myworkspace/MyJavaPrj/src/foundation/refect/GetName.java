package foundation.refect;

public class GetName 
{
	public static void main(String[] args)
	{
		GetName getName = new GetName();
		getName.aaa();
	}
	
	public void aaa()
	{
		//��ȡ��ǰ����������
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		//��ȡ�����ߵķ�����
		System.out.println(new Exception().getStackTrace()[1].getMethodName());
	}
}
