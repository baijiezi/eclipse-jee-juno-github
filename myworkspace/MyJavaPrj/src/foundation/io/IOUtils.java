package foundation.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Scanner;

public class IOUtils 
{
	public static void main(String[] args)
	{
		IOUtils ioUtils = new IOUtils();
//		String line = ioUtils.readOneLineFromScanner();
//		System.out.println(line);
//		
//		String line2 = ioUtils.readOneLineFromFile();
//		System.out.println(line2);
		
		ioUtils.readFileAsByteArray();
	}
	
	
//	�ӿ���̨��ȡһ������
	public String readOneLineFromScanner()
	{
		Scanner scannner = new Scanner(System.in);
		System.out.println("������һ�����ݣ�");
		String line = scannner.nextLine();
		return line;
	}
	
	
//	���ļ��ж�ȡһ������
	public String readOneLineFromFile()
	{
		String line = "";
		try{
			File file = new File("src/foundation/io/a.txt");
			BufferedReader reder = new BufferedReader(new FileReader(file));
			line = reder.readLine();
		}catch(Exception e){
			e.printStackTrace();
		}
		return line;
	}
	
	
	public String readFileAsByteArray()
	{
		String line = "";
		try
		{
			File file = new File("F:\\ҵ��\\���Ӵʵ�\\��ɽ�ʰ�\\PowerWordDict\\dicts\\ciba_segment.index");
			InputStream inputStream = new FileInputStream(file);
			long length = file.length();
			byte[] bytes = new byte[(int)length];
			inputStream.read(bytes, 0, 1000);
			System.out.println(new String(bytes, "UTF-8"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return line;
	}
	
}
