package foundation.innerClass.example4;

public class MyIncrement 
{
    public void increment()
    {
    	System.out.println("这里是父类中的increment方法");
    }

    static void f(MyIncrement f)
    {
    	f.increment();
    }
}
