package foundation.collection.list;

import java.util.ArrayList;

public class ArrayListTest
{
	public static void main(String[] args)
	{
		ArrayList<String> list = new ArrayList<String>();
		
		String name = "bluedavy"; 
		list.add("bluedavy"); 
		System.out.println(list.contains(name));       //  打印什么？ 
		System.out.println(list.contains("bluedavy"));  //  打印什么？ 
	}
}
