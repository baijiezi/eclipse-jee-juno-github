package foundation.io.byteStream;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

//字节流的类层次，继承于InputStream 和 OutputStream
public class Test 
{
	public static void main(String[] args)
	{
		
//		字节层次上的读写
		try{
			File f = new File("a.txt");
			FileInputStream fin = new FileInputStream(f);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
//		读取数据类型
		try{
			File f = new File("a.txt");
			FileInputStream fin = new FileInputStream(f);
			DataInputStream din = new DataInputStream(fin);
		}catch(Exception e){
			e.printStackTrace();
		}

	
//		使用缓存读写数据
		try{
			File file = new File("a.txt");
			DataInputStream din = new DataInputStream(
					new BufferedInputStream(
							new FileInputStream(file)));
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
		
		
	}
}
