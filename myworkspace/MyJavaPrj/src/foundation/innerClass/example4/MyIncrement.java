package foundation.innerClass.example4;

public class MyIncrement 
{
    public void increment()
    {
    	System.out.println("�����Ǹ����е�increment����");
    }

    static void f(MyIncrement f)
    {
    	f.increment();
    }
}
