package foundation.thread.priority;

/**
* Java�̣߳��̵߳ĵ���-���ȼ�
*
* @author leizhimin 2009-11-4 9:02:40
*/

//�����߳���ͬʱ���еģ���ʱ��Ƭ�ķ����ϸ������ȼ���������

public class Test {
        public static void main(String[] args) {
                Thread t1 = new MyThread1();
                Thread t2 = new Thread(new MyRunnable());
                t1.setPriority(10);
                t2.setPriority(1);

                t2.start();
                t1.start();
        }
}

class MyThread1 extends Thread {
        public void run() {
                for (int i = 0; i < 1000; i++) {
                        System.out.println("�߳�1��" + i + "��ִ�У�");
//                        try {
//                                Thread.sleep(100);
//                        } catch (InterruptedException e) {
//                                e.printStackTrace();
//                        }
                }
        }
}

class MyRunnable implements Runnable {
        public void run() {
                for (int i = 0; i < 1000; i++) {
                        System.out.println("�߳�2��" + i + "��ִ�У�");
//                        try {
//                                Thread.sleep(100);
//                        } catch (InterruptedException e) {
//                                e.printStackTrace();
//                        }
                }
        }
}
