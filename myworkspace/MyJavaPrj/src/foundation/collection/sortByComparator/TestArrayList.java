package foundation.collection.sortByComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

 /**
  * 还有一种排序方法是单独写一个比较器类，实现的是comparator接口，注意和comparable接口不是一个接口
  * 推荐使用这种方法，因为这种方法把比较的责任单独写在一个类里面，这样就不用修改实体对象的源码了
  * @author Asus
  *
  */
public class TestArrayList 
{
    public static void main(String[] args)
    {
        List list=new ArrayList();//创建一个ArrayList对象
        list.add(new Student("张三",23));        
        list.add(new Student("李四",22));                 
        list.add(new Student("王五",25)); 

        StudentCompare com=new StudentCompare();
        Collections.sort(list,com);   //把参数和比较规则com传进去sort（）方法里                     

     	System.out.println(list);
    }
}
