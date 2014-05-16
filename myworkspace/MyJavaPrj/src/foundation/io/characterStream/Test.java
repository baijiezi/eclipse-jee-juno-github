package foundation.io.characterStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

//由于与字节为单位的流处理存储为Unicode码的信息很不方便(Unicode每个代码单元使用两个字节)，所以有一个
//专门的类层次来处理Unicode字符。这些类继承Reader 和 Writer 。他们的读写操作都是基于双字节的Unicode
//代码单元，而不是基于单字节。这些类作为Unicode字符码和本地操作系统使用的字符码之间的桥梁。

public class Test {
	public static void main(String[] args)
	{
//		InputStreamReader类可将那些字节采用的特殊字符编码方案的输入流转换成发布Unicode字符的reader
		try{
			InputStreamReader in = new InputStreamReader(System.in);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
//		将一个读取器或写入器同一个文件关联起来
		try{
			File file = new File("a.txt");
			FileWriter writer = new FileWriter(file);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
//		使用缓存读取文件的一行数据
		try{
			File file = new File("a.txt");
			BufferedReader reder = new BufferedReader(new FileReader(file));
			String line = reder.readLine();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	
	}
}
