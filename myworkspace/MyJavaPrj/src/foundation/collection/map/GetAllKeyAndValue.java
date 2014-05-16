package foundation.collection.map;

import java.util.HashMap;

/**
 * 获取一个Map 对象的所有的Key 和 Value
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
		
		
		//得到一个Map 对象，即可获取该Map 对象所有的Key 和值。
		for(String key : hashMap.keySet()) 
		{
			System.out.println( key + " = " +  hashMap.get(key));
		}
	}
}
