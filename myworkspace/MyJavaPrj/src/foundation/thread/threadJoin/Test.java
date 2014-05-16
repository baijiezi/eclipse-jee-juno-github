package foundation.thread.threadJoin;

/**
* Java线程：线程的调度-合并
*
* @author leizhimin 2009-11-4 9:02:40
*/

//线程的合并的含义就是将几个并行线程的线程合并为一个单线程执行，应用场景是当一个线程必须等待另一个线程执行
//完毕才能执行时可以使用join方法。

public class Test {
        public static void main(String[] args) {
                Thread t1 = new MyThread1();
                t1.start();

                for (int i = 0; i < 20; i++) {
                        System.out.println("主线程第" + i + "次执行！");
                        if (i > 2) try {
                                //t1线程合并到主线程中，主线程停止执行过程，转而执行t1线程，直到t1执行完毕后继续。
                                t1.join();
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
        }
}

class MyThread1 extends Thread {
        public void run() {
                for (int i = 0; i < 10; i++) {
                        System.out.println("线程1第" + i + "次执行！");
                }
        }
}
