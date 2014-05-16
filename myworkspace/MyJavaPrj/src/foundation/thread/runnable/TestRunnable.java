package foundation.thread.runnable;

/**
* 测试Runnable类实现的多线程程序
*
* @author leizhimin 2008-9-13 18:15:02
*/

//Java中，每个线程都有一个调用栈，即使不在程序中创建任何新的线程，线程也在后台运行着。
//一个Java应用总是从main()方法开始运行，mian()方法运行在一个线程内，它被称为主线程。
//一旦创建一个新的线程，就产生一个新的调用栈。
//
//启动线程的方法是在线程的Thread对象上调用start()方法，而不是run()或者别的方法。
//在调用start()方法之前：线程处于新状态中，新状态指有一个Thread对象，但还没有一个真正的线程。
// 
//在调用start()方法之后：发生了一系列复杂的事情
//启动新的执行线程（具有新的调用栈）；
//该线程从新状态转移到可运行状态；
//当该线程获得机会执行时，其目标run()方法将运行。
// 
//注意：对Java来说，run()方法没有任何特别之处。像main()方法一样，它只是新线程知道调用的方法名称(和签名)。
//因此，在Runnable上或者Thread上调用run方法是合法的。但并不启动新的线程。


public class TestRunnable {
    public static void main(String[] args) {
        DoSomething ds1 = new DoSomething("阿三");
        DoSomething ds2 = new DoSomething("李四");

        
        //正确的调用方法
        Thread t1 = new Thread(ds1);
        Thread t2 = new Thread(ds2);
        t1.start();
        t2.start();
        
        
        //错误的调用方法，这样调用并没有启动线程，而只是调用一般类的成员方法。
        ds1.run();
        ds2.run();
    }
}







