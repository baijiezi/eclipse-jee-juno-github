package utils.sun;

import java.util.HashMap;

public class ValidateData
{
	public static boolean check(HashMap<String, Object> hm, String keys) throws Exception
	{
		String[] keyList = keys.split(","); 
		for(String k : keyList)
		{
			if(hm.get(k) == null || hm.get(k).toString().trim().equals("")) throw new Exception("对不起，数据【" + k + "】不能为空！"); 
		}
		return true; 
	}
}
