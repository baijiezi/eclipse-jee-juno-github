package foundation.thread.yeald;

/**
* Java�̣߳��̵߳ĵ���-�ò�
*
* @author leizhimin 2009-11-4 9:02:40
*/
//�̵߳��ò��������ʹ��ǰ�������߳��ó�CPU��Դ������Ȼ��˭��֪�����������ó����߳�״̬�ص�������״̬��
//�̵߳��ò�ʹ��Thread.yield()������yield() Ϊ��̬��������������ͣ��ǰ����ִ�е��̶߳��󣬲�ִ�������̡߳�
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
                        System.out.println("�߳�1��" + i + "��ִ�У�");
                }
        }
}

class MyRunnable implements Runnable {
        public void run() {
                for (int i = 0; i < 100; i++) {
                        System.out.println("�߳�2��" + i + "��ִ�У�");
                        Thread.yield();
                }
        }
}
