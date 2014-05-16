package foundation.thread.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
* Java线程：锁
*
* @author leizhimin 2009-11-5 10:57:29
*/

//下面这个例子是在文例子的基础上，将普通锁改为读写锁，并添加账户余额查询的功能
//在实际开发中，最好在能用读写锁的情况下使用读写锁，而不要用普通锁，以求更好的性能。

public class Test1 {
        public static void main(String[] args) {
                //创建并发访问的账户
                MyCount1 myCount = new MyCount1("95599200901215522", 10000);
                //创建一个锁对象
                Lock lock = new ReentrantLock();
                //创建一个线程池
                ExecutorService pool = Executors.newCachedThreadPool();
                //创建一些并发访问用户，一个信用卡，存的存，取的取，好热闹啊
                User1 u1 = new User1("张三", myCount, -4000, lock);
                User1 u2 = new User1("张三他爹", myCount, 6000, lock);
                User1 u3 = new User1("张三他弟", myCount, -8000, lock);
                User1 u4 = new User1("张三", myCount, 800, lock);
                //在线程池中执行各个用户的操作
                pool.execute(u1);
                pool.execute(u2);
                pool.execute(u3);
                pool.execute(u4);
                //关闭线程池
                pool.shutdown();
        }
}

/**
* 信用卡的用户
*/
class User1 implements Runnable {
        private String name;                //用户名
        private MyCount1 myCount;        //所要操作的账户
        private int iocash;                 //操作的金额，当然有正负之分了
        private Lock myLock;                //执行操作所需的锁对象

        User1(String name, MyCount1 myCount, int iocash, Lock myLock) {
                this.name = name;
                this.myCount = myCount;
                this.iocash = iocash;
                this.myLock = myLock;
        }

        public void run() {
                //获取锁
                myLock.lock();
                //执行现金业务
                System.out.println(name + "正在操作" + myCount + "账户，金额为" + iocash + "，当前金额为" + myCount.getCash());
                myCount.setCash(myCount.getCash() + iocash);
                System.out.println(name + "操作" + myCount + "账户成功，金额为" + iocash + "，当前金额为" + myCount.getCash());
                //释放锁，否则别的线程没有机会执行了
                myLock.unlock();
        }
}

/**
* 信用卡账户，可随意透支
*/
class MyCount1 {
        private String oid;         //账号
        private int cash;             //账户余额

        MyCount1(String oid, int cash) {
                this.oid = oid;
                this.cash = cash;
        }

        public String getOid() {
                return oid;
        }

        public void setOid(String oid) {
                this.oid = oid;
        }

        public int getCash() {
                return cash;
        }

        public void setCash(int cash) {
                this.cash = cash;
        }

        @Override
        public String toString() {
                return "MyCount{" +
                                "oid='" + oid + '\'' +
                                ", cash=" + cash +
                                '}';
        }
}
