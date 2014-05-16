package foundation.string;

//数据类型转换

public class DataTypeChange {
	public static void main(String[] args){
		
		//Double 转字符串
		Double db = 10.011;
		String dbToStr = String.valueOf(db);
		System.out.println(dbToStr);
		
		//boolean 转字符串
		boolean bool = false;
		String boolToStr = String.valueOf(bool);
		System.out.println(boolToStr);
		
		//char 转字符串
		char c = 65;
		String cToStr = String.valueOf(c);
		System.out.println(cToStr);
		
	}
}
