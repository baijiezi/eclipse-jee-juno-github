package foundation.encoding;

import java.io.File;
import java.io.FileInputStream;

//以"中文"两个字为例，经查表可以知道其GB2312编码是"d6d0 cec4"，Unicode编码为"4e2d 6587"，
//UTF编码就是"e4b8ad e69687"。注意，这两个字没有iso8859-1编码，但可以用iso8859-1编码来"表示"。

public class Encoding {
	public static void main(String[] args){
		try {
			
			//从文件读取GB2312编码的中文，并输出其十六进制编码
			File file = new File("src/foundation/encoding/GB2312.txt");
			FileInputStream fileInputString = new FileInputStream(file);
			long length = file.length();
			System.out.println(length);
			byte[] bytes = new byte[(int)length];
			fileInputString.read(bytes, 0, (int)length);
			printHexString(bytes);
			System.out.println(new String(bytes, "GBK"));
			System.out.println();
			
			
			////从文件读取Unicode编码的中文，并输出其十六进制编码
			file = new File("src/foundation/encoding/Unicode.txt");
			fileInputString = new FileInputStream(file);
			length = file.length();
			System.out.println(length);
			bytes = new byte[(int)length];
			fileInputString.read(bytes, 0, (int)length);
			printHexString(bytes);
			System.out.println(new String(bytes, "Unicode"));
			System.out.println();
			
			
			//从文件读取UTF-8编码的中文，并输出其十六进制编码
			file = new File("src/foundation/encoding/utf-8.txt");
			fileInputString = new FileInputStream(file);
			length = file.length();
			System.out.println(length);
			bytes = new byte[(int)length];
			fileInputString.read(bytes, 0, (int)length);
			printHexString(bytes);
			System.out.println(new String(bytes, "utf-8"));
			System.out.println();
			
			
			//使用 new String() 方法操作bytes,这个过程执行了以下步骤：
			//1、按UTF-8方式解析bytes，然后将它转成java的Unicode码
			//2、将得到的Unicode码转成这个类文件采用的编码。可以试着将这个类文件设置成不同的编码来运行，看运行结果的差异
			String str = new String(bytes, "UTF-8");
			System.out.println(str);
			bytes = str.getBytes();
			printHexString(bytes);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	//将指定byte数组以16进制的形式打印到控制台
	public static void printHexString( byte[] b) {  
	   for (int i = 0; i < b.length; i++) { 
	     String hex = Integer.toHexString(b[i] & 0xFF); 
	     if (hex.length() == 1) { 
	       hex = '0' + hex; 
	     } 
	     System.out.print(hex.toUpperCase() ); 
	   } 
	   System.out.println();
	}

}
