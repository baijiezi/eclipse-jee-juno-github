package foundation.thread;
/**
* һ����������������100����ÿ������֮����ͣ1�룬ÿ��10���������һ���ַ���
*
* @author leizhimin 2008-9-14 9:53:49
*/ 

//ע�⣺
//1���߳�˯���ǰ��������̻߳�����л������÷�����
//2���߳�˯�ߵ����Զ����ѣ������ص�������״̬����������״̬��sleep()��ָ����ʱ�����̲߳������е����ʱ�䡣��ˣ�sleep()�������ܱ�֤���߳�˯�ߵ��ں�Ϳ�ʼִ�С�
//3��sleep()�Ǿ�̬������ֻ�ܿ��Ƶ�ǰ�������е��̡߳�


public class ThreadSleep extends Thread {

    public void run() {
        for (int i = 0; i < 100; i++) {
            if ((i) % 10 == 0) {
                System.out.println("-------" + i);
            }
            System.out.print(i);
            try {
                Thread.sleep(1);
                System.out.print("    �߳�˯��1���룡\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ThreadSleep().start();
    } 
}
