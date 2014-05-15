package myprj.test.com.annotation.myannotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Client {
	public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
	    AnnotationTest2 annotationTest2 = new AnnotationTest2();
	    //获取AnnotationTest2的Class实例
	    Class<AnnotationTest2> c = AnnotationTest2.class;
	    //获取需要处理的方法Method实例
	    Method method = c.getMethod("execute", new Class[]{});
	    //判断该方法是否包含MyAnnotation注解
	    if(method.isAnnotationPresent(MyAnnotation.class)){
	        //获取该方法的MyAnnotation注解实例
	        MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
	        //执行该方法
	        method.invoke(annotationTest2, new Object[]{});
	        //获取myAnnotation
	        String[] value1 = myAnnotation.value1();
	        System.out.println(value1[0]);
	    }
	    //获取方法上的所有注解
	    Annotation[] annotations = method.getAnnotations();
	    for(Annotation annotation : annotations){
	        System.out.println(annotation);
	    }
	}
}
