package foundation.thread.yeald;

/**
* Java线程：线程的调度-让步
*
* @author leizhimin 2009-11-4 9:02:40
*/
//线程的让步含义就是使当前运行着线程让出CPU资源，但是然给谁不知道，仅仅是让出，线程状态回到可运行状态。
//线程的让步使用Thread.yield()方法，yield() 为静态方法，功能是暂停当前正在执行的线程对象，并执行其他线程。
public class Test {
        public static void main(String[] args) {
                Thread t1 = new MyThread1();
                Thread t2 = new Thread(new MyRunnable());

                t2.start();
                t1.start();
        }
}

class MyThread1 extends Thread {
        public void run() {
                for (int i = 0; i < 100; i++) {
                        System.out.println("线程1第" + i + "次执行！");
                }
        }
}

class MyRunnable implements Runnable {
        public void run() {
                for (int i = 0; i < 100; i++) {
                        System.out.println("线程2第" + i + "次执行！");
                        Thread.yield();
                }
        }
}
