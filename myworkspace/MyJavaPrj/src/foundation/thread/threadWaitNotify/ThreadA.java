package foundation.thread.threadWaitNotify;

/**
* 计算输出其他线程锁计算的数据
*
* @author leizhimin 2008-9-15 13:20:38
*/

//void notify()
//唤醒在此对象监视器上等待的单个线程。
//void notifyAll()
//唤醒在此对象监视器上等待的所有线程。
//void wait()
//导致当前的线程等待，直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法。

//关于等待/通知，要记住的关键点是：
//必须从同步环境内调用wait()、notify()、notifyAll()方法。线程不能调用对象上等待或通知的方法，
//除非它拥有那个对象的锁。
//wait()、notify()、notifyAll()都是Object的实例方法。与每个对象具有锁一样，每个对象可以有一个线程列表，
//他们等待来自该信号（通知）。线程通过执行对象上的wait()方法获得这个等待列表。从那时候起，它不再执行任何其
//他指令，直到调用对象的notify()方法为止。如果多个线程在同一个对象上等待，则将只选择一个线程（不保证以何种
//顺序）继续执行。如果没有线程等待，则不采取任何特殊操作。

public class ThreadA {
    public static void main(String[] args) {
        ThreadB b = new ThreadB();
        //启动计算线程
        b.start();
        //线程A拥有b对象上的锁。线程为了调用wait()或notify()方法，该线程必须是那个对象锁的拥有者
        synchronized (b) {
            try {
                System.out.println("等待对象b完成计算。。。");
                //当前线程A等待
                b.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("b对象计算的总和是：" + b.total);
        }
    }
}
