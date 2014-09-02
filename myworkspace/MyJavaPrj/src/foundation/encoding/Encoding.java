package foundation.encoding;

import java.io.File;
import java.io.FileInputStream;

//��"����"������Ϊ������������֪����GB2312������"d6d0 cec4"��Unicode����Ϊ"4e2d 6587"��
//UTF�������"e4b8ad e69687"��ע�⣬��������û��iso8859-1���룬��������iso8859-1������"��ʾ"��

public class Encoding {
	public static void main(String[] args){
		try {
			
			//���ļ���ȡGB2312��������ģ��������ʮ�����Ʊ���
			File file = new File("src/foundation/encoding/GB2312.txt");
			FileInputStream fileInputString = new FileInputStream(file);
			long length = file.length();
			System.out.println(length);
			byte[] bytes = new byte[(int)length];
			fileInputString.read(bytes, 0, (int)length);
			printHexString(bytes);
			System.out.println(new String(bytes, "GBK"));
			System.out.println();
			
			
			////���ļ���ȡUnicode��������ģ��������ʮ�����Ʊ���
			file = new File("src/foundation/encoding/Unicode.txt");
			fileInputString = new FileInputStream(file);
			length = file.length();
			System.out.println(length);
			bytes = new byte[(int)length];
			fileInputString.read(bytes, 0, (int)length);
			printHexString(bytes);
			System.out.println(new String(bytes, "Unicode"));
			System.out.println();
			
			
			//���ļ���ȡUTF-8��������ģ��������ʮ�����Ʊ���
			file = new File("src/foundation/encoding/utf-8.txt");
			fileInputString = new FileInputStream(file);
			length = file.length();
			System.out.println(length);
			bytes = new byte[(int)length];
			fileInputString.read(bytes, 0, (int)length);
			printHexString(bytes);
			System.out.println(new String(bytes, "utf-8"));
			System.out.println();
			
			
			//ʹ�� new String() ��������bytes,�������ִ�������²��裺
			//1����UTF-8��ʽ����bytes��Ȼ����ת��java��Unicode��
			//2�����õ���Unicode��ת��������ļ����õı��롣�������Ž�������ļ����óɲ�ͬ�ı��������У������н���Ĳ���
			String str = new String(bytes, "UTF-8");
			System.out.println(str);
			bytes = str.getBytes();
			printHexString(bytes);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	//��ָ��byte������16���Ƶ���ʽ��ӡ������̨
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
