package foundation.thread.threadWaitNotify;

/**
* ������������߳������������
*
* @author leizhimin 2008-9-15 13:20:38
*/

//void notify()
//�����ڴ˶���������ϵȴ��ĵ����̡߳�
//void notifyAll()
//�����ڴ˶���������ϵȴ��������̡߳�
//void wait()
//���µ�ǰ���̵߳ȴ���ֱ�������̵߳��ô˶���� notify() ������ notifyAll() ������

//���ڵȴ�/֪ͨ��Ҫ��ס�Ĺؼ����ǣ�
//�����ͬ�������ڵ���wait()��notify()��notifyAll()�������̲߳��ܵ��ö����ϵȴ���֪ͨ�ķ�����
//������ӵ���Ǹ����������
//wait()��notify()��notifyAll()����Object��ʵ����������ÿ�����������һ����ÿ�����������һ���߳��б�
//���ǵȴ����Ը��źţ�֪ͨ�����߳�ͨ��ִ�ж����ϵ�wait()�����������ȴ��б�����ʱ����������ִ���κ���
//��ָ�ֱ�����ö����notify()����Ϊֹ���������߳���ͬһ�������ϵȴ�����ֻѡ��һ���̣߳�����֤�Ժ���
//˳�򣩼���ִ�С����û���̵߳ȴ����򲻲�ȡ�κ����������

public class ThreadA {
    public static void main(String[] args) {
        ThreadB b = new ThreadB();
        //���������߳�
        b.start();
        //�߳�Aӵ��b�����ϵ������߳�Ϊ�˵���wait()��notify()���������̱߳������Ǹ���������ӵ����
        synchronized (b) {
            try {
                System.out.println("�ȴ�����b��ɼ��㡣����");
                //��ǰ�߳�A�ȴ�
                b.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("b���������ܺ��ǣ�" + b.total);
        }
    }
}
