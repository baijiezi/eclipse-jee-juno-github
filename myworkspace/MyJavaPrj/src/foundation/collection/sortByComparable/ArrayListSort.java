package foundation.collection.sortByComparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListSort
{
	public static void main(String[] args)
	{
		ArrayListSort listSort = new ArrayListSort();
		listSort.stringSort();
		listSort.objectSort();
	}
	
	/**
	 * ���list��ͷ��ŵ����ַ��������֣������ֱ�ӵ���Collections.sort()����
	 */
	public void stringSort()
	{
		List list = new ArrayList();
	    list.add("a");//add���������ﴫ���Ĳ���������object���͵�
	    list.add("b");
	    list.add("m");
	    list.add("c");
	    list.add("d");
	    list.add(1,"k");//ע��������˸���������˼���ڵ�һ��λ�ò�������k
	    Collections.sort(list);//���������
	    System.out.println(list);
	    
	    List _list = new ArrayList();
	    _list.add(1);//add���������ﴫ���Ĳ���������object���͵�
	    _list.add(2);
	    _list.add(9);
	    _list.add(3);
	    _list.add(4);
	    _list.add(1,7);//ע��������˸���������˼���ڵ�һ��λ�ò�������k
	    Collections.sort(_list);//���������
	    System.out.println(_list);
	}
	
	/**
	 * ���Ҫ��list��ͷ����Զ�����������ͣ����Զ�����������ͱ���ʵ��lang����ͷ��comparable�ӿڲ���
	 * ʵ����ͷ�� compareTo()�������ſ��Ե���Collections.sort()����
	 * Ҫ�޸�ʵ����Student ��Դ�룬�����ַ�����ȱ�㣬�����Ƽ�ʹ�� sortByComparator �еķ���
	 */
	public void objectSort()
	{
		List list=new ArrayList();//����һ��ArrayList����
	    list.add(new Student("����",23));        
	    list.add(new Student("����",22));                 
	    list.add(new Student("����",25)); 

	    Collections.sort(list);                        
	    System.out.println(list);
	}
	
}
