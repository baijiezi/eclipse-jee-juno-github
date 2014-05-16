package foundation.collection.sortByComparator;

import java.util.Comparator;
 /**
  * 把比较的责任单独写在一个类里面，这样就不用修改实体对象的源码了
  * @author Asus
  *
  */
public class StudentCompare implements Comparator
{
	public int compare(Object obj1,Object obj2)
	{
		if(obj1 instanceof Student && obj2 instanceof Student)
		{
			Student stu1=(Student) obj1;
			Student stu2=(Student) obj2;
			return stu1.getAge()-stu2.getAge();
		}
		return 0;
	}
}
