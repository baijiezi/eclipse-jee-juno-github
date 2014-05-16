package foundation.refect;

/**
 * �ڳ��������ڼ䣬Java����ʱϵͳʼ��Ϊ���еĶ���ά��һ������Ϊ����ʱ�����ͱ�ʶ�������Ϣ������
 * ÿ���������������㼣���������������ʱ��Ϣѡ����Ӧ�ķ���ִ�С�
 * Ȼ�󣬿���ͨ��ר�ŵ�Java�������Щ��Ϣ��������Щ��Ϣ���౻��Ϊ Class �ࡣ
 */
public class RefectTest
{
	public static void main(String[] args)
	{
//		Object ��� getClass() �����᷵��һ�� Class ���͵�ʵ����
		Student student = new Student();
		Class c1 = student.getClass();
		
		
//		���������þ�̬���� forName() ���������Ӧ�� Class ����
		try 
		{
			String className = "foundation.refect.Student";
			Class c2 = Class.forName(className);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		
//		��ȡClass �����ĵ����ַ����ǳ��򵥣����T �������Java���ͣ���ô T.class ������ƥ��������
//		Class ����ʵ���ϴ������һ�����ͣ����������δ��һ����һ���ࡣ
		Class c31 = Student.class;
		Class c32 = int.class;
		Class c33 = Double[].class;
		
		
//		��õ�Class ������ getName ���������������������֡�
		System.out.println( student.getClass().getName() );
		
		
//		�����Ϊÿ�����͹���һ��Class ������ˣ��������� == �����ʵ������ Class ����ȽϵĲ���
		if(student.getClass() == Student.class)
			System.out.println("student�������� Student��");
		
		
//		��forName �� newInstance �������ʹ�ã����Ը��ݴ洢���ַ����е���������һ������
		try 
		{
			String str = "foundation.refect.Student";
			Object stud = Class.forName(str).newInstance();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
