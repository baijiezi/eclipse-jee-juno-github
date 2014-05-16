package foundation.innerClass.example1;

/**
 * 1.内部类可以很好的实现隐藏
 * 这是客户端调用的代码，从这段代码里面我只知道Example的getIn()方法能返回一个
 * InterfaceTest 实例但我并不知道这个实例是这么实现的。而且由于InsideClass 是private的，
 * 所以我们如果不看代码的话根本看不到这个具体类的名字，所以说它可以很好的实现隐藏。
 * 
 */
public class TestExample 
{
	 public static void main(String args[])
	 {
	    Example a=new Example();
	    InterfaceTest a1=a.getIn();
	    a1.test();
	 }
}
