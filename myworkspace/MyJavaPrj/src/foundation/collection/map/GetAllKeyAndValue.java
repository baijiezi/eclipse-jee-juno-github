package foundation.collection.map;

import java.util.HashMap;

/**
 * ��ȡһ��Map ��������е�Key �� Value
 *
 */
public class GetAllKeyAndValue 
{
	public static void main(String[] args)
	{
		HashMap<String, Object> hashMap = new HashMap<String, Object> ();
		hashMap.put("DoctorId", "171515");
		hashMap.put("RegTypeCode", "0");
		hashMap.put("DeptId", "108011");
		hashMap.put("StartTime", "2011-06-03 00:00:00");
		hashMap.put("EndTime", "2011-06-03 17:00:00");
		
		
		//�õ�һ��Map ���󣬼��ɻ�ȡ��Map �������е�Key ��ֵ��
		for(String key : hashMap.keySet()) 
		{
			System.out.println( key + " = " +  hashMap.get(key));
		}
	}
}
