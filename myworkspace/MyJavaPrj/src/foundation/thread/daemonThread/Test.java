package foundation.thread.daemonThread;

/**
* Java线程：线程的调度-守护线程
*
* @author leizhimin 2009-11-4 9:02:40
*/
//守护线程使用的情况较少，但并非无用，举例来说，JVM的垃圾回收、内存管理等线程都是守护线程。还有就是在做
//数据库应用时候，使用的数据库连接池，连接池本身也包含着很多后台线程，监控连接个数、超时时间、状态等等
//public final void setDaemon(boolean on)将该线程标记为守护线程或用户线程。当正在运行的线程都是守护
//线程时，Java 虚拟机退出。    
//该方法必须在启动线程前调用。   

//从执行结果可以看出：
//前台线程是保证执行完毕的，后台线程还没有执行完毕就退出了。
//实际上：JRE判断程序是否执行结束的标准是所有的前台执线程行完毕了，而不管后台线程的状态

public class Test {
        public static void main(String[] args) {
                Thread t1 = new MyCommon();
                Thread t2 = new Thread(new MyDaemon());
                t2.setDaemon(true);        //设置为守护线程

                t2.start();
                t1.start();
        }
}

class MyCommon extends Thread {
        public void run() {
                for (int i = 0; i < 5; i++) {
                        System.out.println("线程1第" + i + "次执行！");
                        try {
                                Thread.sleep(7);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
        }
}

class MyDaemon implements Runnable {
        public void run() {
                for (long i = 0; i < 9999999L; i++) {
                        System.out.println("后台线程第" + i + "次执行！");
                        try {
                                Thread.sleep(7);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
        }
}
