package myprj.test.com.annotation.myannotation;

public class AnnotationTest2 {

    @MyAnnotation(value1={"a","b"})
    @Deprecated
    public void execute(){
        System.out.println("method");
    }
}