package foundation.string;

/**
 * ����Զ�̶˿�ʱ���䷵�ص����ݺܿ���������һ�ָ�ʽ
 * ����ɹ�ʱ���ص���Ϣ��    0~<root><items><item></item></items></root>
 * ����ʧ��ʱ���ص���Ϣ��    1~������Ϣ
 * 
 *  �������µķ������������ķ��ؽ��
 */
public class StringToArray 
{
	public static void main(String[] args) throws Exception
	{
		String result1 = "0~<root><items><item></item></items></root>";  //����ɹ�ʱ���ص���Ϣ
		String result2 = "1~������Ϣ";	//����ʧ��ʱ���ص���Ϣ
		
		int resultCode = -1;
		String[] result = null;
		try { result = result2.split("~"); resultCode = Integer.parseInt(result[0]); } catch(Exception e) { throw new Exception("��HIS���н��׳����쳣�������ԣ�"); }
		if(resultCode != 0)
		{
			throw new Exception(result[1]);
		}
 	}
}
