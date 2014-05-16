package foundation.json.jsonobject;

import net.sf.json.JSON;
import net.sf.json.JSONObject;



public class JsonTest 
{
	public static void main(String[] args)
	{
		String json = "{name:\"������\",sex:\"male\",age:18,job:\"student\"}";
		   JSONObject jsonObject = JSONObject.fromObject(json);
		   //����ʹ�� JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(json);
//		   JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(json);
		   System.out.println(jsonObject.get("name"));// ������
		   System.out.println(jsonObject.get("job"));// student
		   System.out.println(jsonObject.getString("sex"));// male
		   System.out.println(jsonObject.getInt("age"));// 18
		
	}
}







