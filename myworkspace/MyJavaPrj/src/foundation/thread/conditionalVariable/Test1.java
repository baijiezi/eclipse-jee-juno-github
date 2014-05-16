package foundation.thread.conditionalVariable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
* Java�̣߳���������
*
* @author leizhimin 2009-11-5 10:57:29
*/

//������һ�����д�ȡ���ģ�����Ϊ�����Ҹ�Java���߳�����������������ɴ��
//��һ���˻�������û����̣߳���ͬʱ��������˻����еĴ���е�ȡ�������棬ȡ�������ƣ�����͸֧��
//�κ���ͼ͸֧�Ĳ��������ȴ��������㹻����ִ�в�����

public class Test1 {
        public static void main(String[] args) {
                //�����������ʵ��˻�
                MyCount1 myCount = new MyCount1("95599200901215522", 10000);
                //����һ���̳߳�
                ExecutorService pool = Executors.newFixedThreadPool(2);
                Thread t1 = new SaveThread1("����", myCount, 2000);
                Thread t2 = new SaveThread1("����", myCount, 3600);
                Thread t3 = new SaveThread1("����", myCount, 2700);
                Thread t4 = new SaveThread1("����", myCount, 600);
                Thread t5 = new SaveThread1("��ţ", myCount, 1300);
                Thread t6 = new SaveThread1("����", myCount, 800);
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
class SaveThread1 extends Thread {
        private String name;                //������
        private MyCount1 myCount;        //�˻�
        private int x;                            //�����

        SaveThread1(String name, MyCount1 myCount, int x) {
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
class DrawThread1 extends Thread {
        private String name;                //������
        private MyCount1 myCount;        //�˻�
        private int x;                            //�����

        DrawThread1(String name, MyCount1 myCount, int x) {
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
class MyCount1 {
        private String oid;                         //�˺�
        private int cash;                             //�˻����
        private Lock lock = new ReentrantLock();                //�˻���
        private Condition _save = lock.newCondition();    //�������
        private Condition _draw = lock.newCondition();    //ȡ������

        MyCount1(String oid, int cash) {
                this.oid = oid;
                this.cash = cash;
        }

        /**
         * ���
         *
         * @param x        �������
         * @param name ������
         */
        public void saving(int x, String name) {
                lock.lock();                        //��ȡ��
                if (x > 0) {
                        cash += x;                    //���
                        System.out.println(name + "���" + x + "����ǰ���Ϊ" + cash);
                }
                _draw.signalAll();            //�������еȴ��̡߳�
                lock.unlock();                    //�ͷ���
        }

        /**
         * ȡ��
         *
         * @param x        �������
         * @param name ������
         */
        public void drawing(int x, String name) {
                lock.lock();                                 //��ȡ��
                try {
                        if (cash - x < 0) {
                                _draw.await();             //����ȡ�����
                        } else {
                                cash -= x;                     //ȡ��
                                System.out.println(name + "ȡ��" + x + "����ǰ���Ϊ" + cash);
                        }
                        _save.signalAll();             //�������д�����
                } catch (InterruptedException e) {
                        e.printStackTrace();
                } finally {
                        lock.unlock();                     //�ͷ���
                }
        }
}
