package foundation.others.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Serialization�����л�����һ�ֽ�������һ�������ֽ������Ĺ��̣������л�deserialization��һ�ֽ�
 * ��Щ�ֽ��ؽ���һ������Ĺ��̡�Java���л�API�ṩһ�ִ���������л��ı�׼���ơ�����������ѧ��
 * ������л�һ������ʲôʱ����Ҫ���л��Լ�Java���л����㷨��������һ��ʵ����ʾ�����л��Ժ���ֽ�
 * ���������һ���������Ϣ�ġ�
 * Java�У�һ�ж��Ƕ����ڷֲ�ʽ�����о�����Ҫ��Object����һ��������豸���ݵ���һ�ˡ�
 * �����Ҫ��һ�ֿ��������˴������ݵ�Э�顣Java���л����ƾ���Ϊ�˽����������������
 * һ�������ܹ����л���ǰ����ʵ��Serializable�ӿڣ�Serializable�ӿ�û�з����������Ǹ���ǡ�
 * ���������ǵ�Class���ܱ����л����ƴ���
 *
 */
public class SerialTest extends Parent implements Serializable 
{
	int version = 66;
	Contain con = new Contain();

	public static void main(String args[]) throws IOException 
	{
		//��SerialTest�������л������浽�ļ�
		FileOutputStream fos = new FileOutputStream("src/foundation/others/serialize/temp.out");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		SerialTest st = new SerialTest();
		oos.writeObject(st);
		oos.flush();
		oos.close();
		
		
		//���ļ����ѱ����л��Ķ�����з����л����õ�����ʵ������������ĳ������ֵ
		try 
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/foundation/others/serialize/temp.out"));
			SerialTest st2 = (SerialTest)ois.readObject();
			System.out.println(st2.con.containVersion);
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public int getVersion() 
	{
		return version;
	}

}



/**
����������൱��ֱ������SerialTest��ʵ����Parent���࣬�ڲ�������һ��Container����
���л���ĸ�ʽ���£�
����AC ED 00 05 73 72 00 0A 53 65 72 69 61 6C 54 65
����73 74 05 52 81 5A AC 66 02 F6 02 00 02 49 00 07
����76 65 72 73 69 6F 6E 4C 00 03 63 6F 6E 74 00 09
����4C 63 6F 6E 74 61 69 6E 3B 78 72 00 06 70 61 72
����65 6E 74 0E DB D2 BD 85 EE 63 7A 02 00 01 49 00
����0D 70 61 72 65 6E 74 56 65 72 73 69 6F 6E 78 70
����00 00 00 0A 00 00 00 42 73 72 00 07 63 6F 6E 74
����61 69 6E FC BB E6 0E FB CB 60 C7 02 00 01 49 00
����0E 63 6F 6E 74 61 69 6E 56 65 72 73 69 6F 6E 78
����70 00 00 00 0B
��������ϸ������Щ�ֽڶ�������ɶ����ͷ���֣�����ɫ��
AC ED: STREAM_MAGIC. ����ʹ�������л�Э��.
00 05: STREAM_VERSION. ���л�Э��汾.
0x73: TC_OBJECT. ��������һ���µĶ���.  
   ���л��㷨�ĵ�һ�������������������������������ʾ����ΪSerialTest��ʵ������˽��������SerialTest�������������ɫ��
0x72: TC_CLASSDESC. �������￪ʼһ����Class��
00 0A: Class���ֵĳ���.
53 65 72 69 61 6c 54 65 73 74: SerialTest,Class����.
05 52 81 5A AC 66 02 F6: SerialVersionUID, ���л�ID�����û��ָ����������㷨��
������һ��8byte��ID
0x02: ��Ǻ�. ��ֵ�����ö���֧�����л���
00 02: �������������������
���������㷨������е�һ����int version=66������ɫ��
0x49: ������. 49 ����"I", Ҳ����Int.
00 07: �����ֵĳ���.
76 65 72 73 69 6F 6E: version,����������.
Ȼ���㷨�����һ����contain con = new contain();����е����⣬�Ǹ���������
������������ʱ��Ҫʹ��JVM�ı�׼����ǩ����ʾ��������ɫ��
0x4C: �������.
00 03: �����ֳ���.
63 6F 6E: ������������con
0x74: TC_STRING. ����һ��new String.��String�����ö���
00 09: ��String����.
4C 63 6F 6E 74 61 69 6E 3B: Lcontain;, JVM�ı�׼����ǩ����ʾ��.
0x78: TC_ENDBLOCKDATA,�������ݿ�����ı�־
�������㷨�ͻ��������Ҳ����Parent�������ˣ�����ɫ��
0x72: TC_CLASSDESC. ��������Ǹ�����.
00 06: ��������.
70 61 72 65 6E 74: parent,����������
0E DB D2 BD 85 EE 63 7A: SerialVersionUID, ���л�ID.
0x02: ��Ǻ�. ��ֵ�����ö���֧�����л�.
00 01: ������ĸ���.
   ��һ�������parent�����������int parentVersion=100;ͬ����ɫ��
0x49: ������. 49 ����"I", Ҳ����Int.
00 0D: �����ֳ���.
70 61 72 65 6E 74 56 65 72 73 69 6F 6E: parentVersion��������������
0x78: TC_ENDBLOCKDATA,���������ı�־��
0x70: TC_NULL, ˵��û����������ı�־��.

   ����Ϊֹ���㷨�Ѿ������е���������������������һ�����ǰ�ʵ�������ʵ��ֵ����ˡ���ʱ���Ǵ�parent Class����ʼ�ģ�����ɫ��
00 00 00 0A: 10, parentVersion���ֵ.   
����SerialTest�����
00 00 00 42: 66, version���ֵ.
�������bytes�Ƚ�����˼���㷨��Ҫ����contain�����Ϣ��Ҫ��ס�����ڻ�û�ж�contain����й�����������ɫ��
0x73: TC_OBJECT, ��������һ���µĶ���.
0x72: TC_CLASSDESC�������￪ʼһ����Class.
00 07: �����ĳ���.
63 6F 6E 74 61 69 6E: contain,��������.
FC BB E6 0E FB CB 60 C7: SerialVersionUID, ���л�ID.
0x02: Various flags. ��Ǻ�. ��ֵ�����ö���֧�����л�
00 01: ���ڵ��������
    .���contain��Ψһ����������int containVersion=11��
0x49: ������. 49 ����"I", Ҳ����Int..
00 0E: �����ֳ���.63 6F 6E 74 61 69 6E 56 65 72 73 69 6F 6E: containVersion, ����������.
0x78: TC_ENDBLOCKDATA���������ı�־.
��ʱ�����л��㷨����contain�Ƿ��г��࣬����еĻ�����������
0x70:TC_NULL��û�г����ˡ�
��󣬽�contain��ʵ����ֵ�����
00 00 00 0B: 11, containVersion��ֵ.
**/
