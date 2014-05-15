package myprj.test.com.annotation.myannotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Client {
	public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
	    AnnotationTest2 annotationTest2 = new AnnotationTest2();
	    //��ȡAnnotationTest2��Classʵ��
	    Class<AnnotationTest2> c = AnnotationTest2.class;
	    //��ȡ��Ҫ����ķ���Methodʵ��
	    Method method = c.getMethod("execute", new Class[]{});
	    //�жϸ÷����Ƿ����MyAnnotationע��
	    if(method.isAnnotationPresent(MyAnnotation.class)){
	        //��ȡ�÷�����MyAnnotationע��ʵ��
	        MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
	        //ִ�и÷���
	        method.invoke(annotationTest2, new Object[]{});
	        //��ȡmyAnnotation
	        String[] value1 = myAnnotation.value1();
	        System.out.println(value1[0]);
	    }
	    //��ȡ�����ϵ�����ע��
	    Annotation[] annotations = method.getAnnotations();
	    for(Annotation annotation : annotations){
	        System.out.println(annotation);
	    }
	}
}
