package foundation.io.byteStream;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

//�ֽ��������Σ��̳���InputStream �� OutputStream
public class Test 
{
	public static void main(String[] args)
	{
		
//		�ֽڲ���ϵĶ�д
		try{
			File f = new File("a.txt");
			FileInputStream fin = new FileInputStream(f);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
//		��ȡ��������
		try{
			File f = new File("a.txt");
			FileInputStream fin = new FileInputStream(f);
			DataInputStream din = new DataInputStream(fin);
		}catch(Exception e){
			e.printStackTrace();
		}

	
//		ʹ�û����д����
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
