package foundation.collection.sortByComparator;

import java.util.Comparator;
 /**
  * �ѱȽϵ����ε���д��һ�������棬�����Ͳ����޸�ʵ������Դ����
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
