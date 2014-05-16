package foundation.thread.conditionalVariable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* Java�̣߳�������������
*
* @author leizhimin 2009-11-5 10:57:29
*/

//�������ǲ��������������������ʵ�ִ˹����أ�������ʵ�ִ��룺

public class Test2 {
        public static void main(String[] args) {
                //�����������ʵ��˻�
                MyCount2 myCount = new MyCount2("95599200901215522", 10000);
                //����һ���̳߳�
                ExecutorService pool = Executors.newFixedThreadPool(2);
                Thread t1 = new SaveThread2("����", myCount, 2000);
                Thread t2 = new SaveThread2("����", myCount, 3600);
                Thread t3 = new SaveThread2("����", myCount, 2700);
                Thread t4 = new SaveThread2("����", myCount, 600);
                Thread t5 = new SaveThread2("��ţ", myCount, 1300);
                Thread t6 = new SaveThread2("����", myCount, 800);
                //ִ�и����߳�
                pool.execute(t1);
                pool.execute(t2);
                pool.execute(t3);
                pool.execute(t4);
                pool.execute(t5);
                pool.execute(t6);
                //�ر��̳߳�
                pool.shutdown();
        }
}

/**
* ����߳���
*/
class SaveThread2 extends Thread {
        private String name;                //������
        private MyCount2 myCount;        //�˻�
        private int x;                            //�����

        SaveThread2(String name, MyCount2 myCount, int x) {
                this.name = name;
                this.myCount = myCount;
                this.x = x;
        }

        public void run() {
                myCount.saving(x, name);
        }
}

/**
* ȡ���߳���
*/
class DrawThread2 extends Thread {
        private String name;                //������
        private MyCount2 myCount;        //�˻�
        private int x;                            //�����

        DrawThread2(String name, MyCount2 myCount, int x) {
                this.name = name;
                this.myCount = myCount;
                this.x = x;
        }

        public void run() {
                myCount.drawing(x, name);
        }
}


/**
* ��ͨ�����˻�������͸֧
*/
class MyCount2 {
        private String oid;                         //�˺�
        private int cash;                             //�˻����

        MyCount2(String oid, int cash) {
                this.oid = oid;
                this.cash = cash;
        }

        /**
         * ���
         *
         * @param x        �������
         * @param name ������
         */
        public synchronized void saving(int x, String name) {
                if (x > 0) {
                        cash += x;                    //���
                        System.out.println(name + "���" + x + "����ǰ���Ϊ" + cash);
                }
                notifyAll();            //�������еȴ��̡߳�
        }

        /**
         * ȡ��
         *
         * @param x        �������
         * @param name ������
         */
        public synchronized void drawing(int x, String name) {
                if (cash - x < 0) {
                        try {
                                wait();
                        } catch (InterruptedException e1) {
                                e1.printStackTrace();
                        }
                } else {
                        cash -= x;                     //ȡ��
                        System.out.println(name + "ȡ��" + x + "����ǰ���Ϊ" + cash);
                }
                notifyAll();             //�������д�����
        }
}
