package foundation.thread.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
* Java�̣߳���
*
* @author leizhimin 2009-11-5 10:57:29
*/

//��������������������ӵĻ����ϣ�����ͨ����Ϊ��д����������˻�����ѯ�Ĺ���
//��ʵ�ʿ����У���������ö�д���������ʹ�ö�д��������Ҫ����ͨ����������õ����ܡ�

public class Test1 {
        public static void main(String[] args) {
                //�����������ʵ��˻�
                MyCount1 myCount = new MyCount1("95599200901215522", 10000);
                //����һ��������
                Lock lock = new ReentrantLock();
                //����һ���̳߳�
                ExecutorService pool = Executors.newCachedThreadPool();
                //����һЩ���������û���һ�����ÿ�����Ĵ棬ȡ��ȡ�������ְ�
                User1 u1 = new User1("����", myCount, -4000, lock);
                User1 u2 = new User1("��������", myCount, 6000, lock);
                User1 u3 = new User1("��������", myCount, -8000, lock);
                User1 u4 = new User1("����", myCount, 800, lock);
                //���̳߳���ִ�и����û��Ĳ���
                pool.execute(u1);
                pool.execute(u2);
                pool.execute(u3);
                pool.execute(u4);
                //�ر��̳߳�
                pool.shutdown();
        }
}

/**
* ���ÿ����û�
*/
class User1 implements Runnable {
        private String name;                //�û���
        private MyCount1 myCount;        //��Ҫ�������˻�
        private int iocash;                 //�����Ľ���Ȼ������֮����
        private Lock myLock;                //ִ�в��������������

        User1(String name, MyCount1 myCount, int iocash, Lock myLock) {
                this.name = name;
                this.myCount = myCount;
                this.iocash = iocash;
                this.myLock = myLock;
        }

        public void run() {
                //��ȡ��
                myLock.lock();
                //ִ���ֽ�ҵ��
                System.out.println(name + "���ڲ���" + myCount + "�˻������Ϊ" + iocash + "����ǰ���Ϊ" + myCount.getCash());
                myCount.setCash(myCount.getCash() + iocash);
                System.out.println(name + "����" + myCount + "�˻��ɹ������Ϊ" + iocash + "����ǰ���Ϊ" + myCount.getCash());
                //�ͷ������������߳�û�л���ִ����
                myLock.unlock();
        }
}

/**
* ���ÿ��˻���������͸֧
*/
class MyCount1 {
        private String oid;         //�˺�
        private int cash;             //�˻����

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
