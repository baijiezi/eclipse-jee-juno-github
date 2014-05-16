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
	 * 如果list里头存放的是字符串或数字，则可以直接调用Collections.sort()方法
	 */
	public void stringSort()
	{
		List list = new ArrayList();
	    list.add("a");//add（）方法里传进的参数类型是object类型的
	    list.add("b");
	    list.add("m");
	    list.add("c");
	    list.add("d");
	    list.add(1,"k");//注意这里多了个参数，意思是在第一个位置插入数据k
	    Collections.sort(list);//做排序操作
	    System.out.println(list);
	    
	    List _list = new ArrayList();
	    _list.add(1);//add（）方法里传进的参数类型是object类型的
	    _list.add(2);
	    _list.add(9);
	    _list.add(3);
	    _list.add(4);
	    _list.add(1,7);//注意这里多了个参数，意思是在第一个位置插入数据k
	    Collections.sort(_list);//做排序操作
	    System.out.println(_list);
	}
	
	/**
	 * 如果要在list里头存放自定义的数据类型，则自定义的数据类型必须实现lang包里头的comparable接口并且
	 * 实现里头的 compareTo()方法，才可以调用Collections.sort()方法
	 * 要修改实体类Student 的源码，是这种方法的缺点，所以推荐使用 sortByComparator 中的方法
	 */
	public void objectSort()
	{
		List list=new ArrayList();//创建一个ArrayList对象
	    list.add(new Student("张三",23));        
	    list.add(new Student("李四",22));                 
	    list.add(new Student("王五",25)); 

	    Collections.sort(list);                        
	    System.out.println(list);
	}
	
}
