package foundation.json.jsonobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import utils.sun.DateUtils;

public class JsonBuilderTest 
{
	public static void main(String[] args)
	{
		ArrayList<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<Map<String, Object>> dataList2 = new ArrayList<Map<String, Object>>();
		map.put("key1", "value");
		map.put("key2", "value");
		map.put("key3", dataList2);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("childKey1", "value");
		map2.put("childKey2", "value");
		dataList2.add(map2);
		
		dataList.add(map);
		System.out.println(dataList.toString());
	}
}
