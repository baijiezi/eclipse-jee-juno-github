package foundation.string;

/**
 * 请求远程端口时，其返回的数据很可能是这样一种格式
 * 请求成功时返回的信息：    0~<root><items><item></item></items></root>
 * 请求失败时返回的信息：    1~错误信息
 * 
 *  可用如下的方法解析这样的返回结果
 */
public class StringToArray 
{
	public static void main(String[] args) throws Exception
	{
		String result1 = "0~<root><items><item></item></items></root>";  //请求成功时返回的信息
		String result2 = "1~错误信息";	//请求失败时返回的信息
		
		int resultCode = -1;
		String[] result = null;
		try { result = result2.split("~"); resultCode = Integer.parseInt(result[0]); } catch(Exception e) { throw new Exception("与HIS进行交易出现异常，请重试！"); }
		if(resultCode != 0)
		{
			throw new Exception(result[1]);
		}
 	}
}
