package foundation.thread.notifyAll;

/**
* 获取计算结果并输出
*
* @author leizhimin 2008-9-20 11:15:22
*/

//运行结果表明，程序中有异常，并且多次运行结果可能有多种输出结果。这就是说明，这个多线程的交互程序还
//存在问题。究竟是出了什么问题，需要深入的分析和思考

public class ReaderResult extends Thread {
        Calculator c;

        public ReaderResult(Calculator c) {
                this.c = c;
        }

        public void run() {
                synchronized (c) {
                        try {
                                System.out.println(Thread.currentThread() + "等待计算结果。。。");
                                c.wait();
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread() + "计算结果为：" + c.total);
                }
        }

        public static void main(String[] args) {
                Calculator calculator = new Calculator();

                //启动三个线程，分别获取计算结果
                new ReaderResult(calculator).start();
                new ReaderResult(calculator).start();
                new ReaderResult(calculator).start();
                //启动计算线程
                calculator.start();
        }
}
