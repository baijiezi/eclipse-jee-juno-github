package foundation.collection.sortByComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

 /**
  * ����һ�����򷽷��ǵ���дһ���Ƚ����࣬ʵ�ֵ���comparator�ӿڣ�ע���comparable�ӿڲ���һ���ӿ�
  * �Ƽ�ʹ�����ַ�������Ϊ���ַ����ѱȽϵ����ε���д��һ�������棬�����Ͳ����޸�ʵ������Դ����
  * @author Asus
  *
  */
public class TestArrayList 
{
    public static void main(String[] args)
    {
        List list=new ArrayList();//����һ��ArrayList����
        list.add(new Student("����",23));        
        list.add(new Student("����",22));                 
        list.add(new Student("����",25)); 

        StudentCompare com=new StudentCompare();
        Collections.sort(list,com);   //�Ѳ����ͱȽϹ���com����ȥsort����������                     

     	System.out.println(list);
    }
}
