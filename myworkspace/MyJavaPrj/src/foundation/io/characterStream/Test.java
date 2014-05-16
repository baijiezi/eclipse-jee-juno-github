package foundation.io.characterStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

//�������ֽ�Ϊ��λ��������洢ΪUnicode�����Ϣ�ܲ�����(Unicodeÿ�����뵥Ԫʹ�������ֽ�)��������һ��
//ר�ŵ�����������Unicode�ַ�����Щ��̳�Reader �� Writer �����ǵĶ�д�������ǻ���˫�ֽڵ�Unicode
//���뵥Ԫ�������ǻ��ڵ��ֽڡ���Щ����ΪUnicode�ַ���ͱ��ز���ϵͳʹ�õ��ַ���֮���������

public class Test {
	public static void main(String[] args)
	{
//		InputStreamReader��ɽ���Щ�ֽڲ��õ������ַ����뷽����������ת���ɷ���Unicode�ַ���reader
		try{
			InputStreamReader in = new InputStreamReader(System.in);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
//		��һ����ȡ����д����ͬһ���ļ���������
		try{
			File file = new File("a.txt");
			FileWriter writer = new FileWriter(file);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
//		ʹ�û����ȡ�ļ���һ������
		try{
			File file = new File("a.txt");
			BufferedReader reder = new BufferedReader(new FileReader(file));
			String line = reder.readLine();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	
	}
}
