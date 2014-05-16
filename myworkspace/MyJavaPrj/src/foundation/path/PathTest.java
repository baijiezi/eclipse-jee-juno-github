package foundation.path;

import java.io.File;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

//java������,Ŀ¼���Դ� src ��ʼ��
//����  File file = new File("src/foundation/xml/cfg.xml")
/**
 * "�ļ���" ��ʾ��Ĭ��ֱ�ӴӸ�Ŀ¼��ʼ�������� MyJavaPrj/�ļ���
 * "." ��ʾ��
 * "/" ��ʾ���� getClass().getResourceAsStream("/") �У���ʾ src Ŀ¼�£����忴�����load����
 * "./" ��ʾ���Ӹ�Ŀ¼��ʼ�������� MyJavaPrj/�ļ���
 * "../" ��ʾ����Web ��Ŀ�б�ʾ��һ��Ŀ¼����һ��java��������Ч����linux�У���ʾ��һ��Ŀ¼
 * ֱ��src��ʼ���磺src/foundation/xml/cfg.xml
 * ����·�����磺E\:\\programs\\HospitalFrontEnd\\FEFE\\src\\mapping.xml 
 */
public class PathTest 
{
	public static void main(String[] args)
	{
		Document doc = null;
		try 
		{
			//java �е����·�������֣�һ��������� classpath ��·����
			//�� "E:\MyEclipse 7.1\myworkspace\MyJavaPrj\bin" ������ bin �Ǳ���Ŀ�� classpath
			
			//��һ��������ڵ�ǰ�û�Ŀ¼����������� System.getProperty("user.dir") ���ص�Ŀ¼
			//����һ����Ŀ��������Ŀ�ĸ�Ŀ¼������J2EE��������������ĳ��·������û�й淶������Ҫ����ʹ��
			System.out.println(System.getProperty("user.dir"));
			
			
//			System.out.println("����Ŀ¼��src/foundation/xml/cfg.xml");
//			doc = new SAXReader().read(new File("src/foundation/xml/cfg.xml"));
//			System.out.println("�������");
			
			
//			System.out.println("����Ŀ¼��pathTest1.xml");
//			doc = new SAXReader().read(new File("pathTest1.xml"));
//			System.out.println("�������");
			
			
			System.out.println("����Ŀ¼��pathTest2.xml");
			doc = new SAXReader().read(new File("./pathTest2.xml"));
			System.out.println("�������");
			
			
//			System.out.println("����Ŀ¼��pathTest2.xml");
//			doc = new SAXReader().read(new File("../pathTest2.xml"));
//			System.out.println("������ɣ�'../' ��Ч");
			
			
			PathTest pathTest = new PathTest();
			pathTest.load();
			
			
			
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public void load()
	{
		SAXReader reader = new SAXReader();
		
		//�����  "/" ��ʾ�� src ��ʼ
		//getClass().getResourceAsStream()  ��ʶ��  ��  �е�·��
		InputStream is = getClass().getResourceAsStream("/pathTest2.xml");
		try 
		{
			Document doc = reader.read(is);
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
	}
}
