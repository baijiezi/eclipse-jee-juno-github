package foundation.thread.threadSynchro;

//�̵߳�ͬ����Ϊ�˷�ֹ����̷߳���һ�����ݶ���ʱ����������ɵ��ƻ���
//���磺�����߳�ThreadA��ThreadB������ͬһ������Foo���󣬲��޸�Foo�����ϵ����ݡ�
//�ӽ�����֣����������ֵ�����ǲ�����ġ�ԭ���������̲߳��ӿ��Ƶķ���Foo�����޸����������¡�

//���Ҫ���ֽ���ĺ����ԣ�ֻ��Ҫ�ﵽһ��Ŀ�ģ����ǽ���Foo�ķ��ʼ������ƣ�ÿ��ֻ����һ���߳��ڷ��ʡ��������ܱ�֤Foo���������ݵĺ������ˡ�
//�ھ����Java��������Ҫ���һ������������
//�Ѿ������ʵ���Դ��Foo����x��ʶΪprivate��
//ͬ����Щ�޸ı����Ĵ��룬ʹ��synchronized�ؼ���ͬ����������롣

//����ԭ��:
//Java��ÿ��������һ��������
//���������е��Ǿ�̬��synchronizedͬ��������ʱ���Զ����������ִ�д�����ĵ�ǰʵ����thisʵ�����йص�����
//���һ���������Ҳ��Ϊ��ȡ�������������ڶ������������ڶ�����ͬ����
//����߳���ͼ����ͬ���������������Ѿ���ռ�ã����߳��ڸö����ϱ�������ʵ���ϣ��߳̽���ö���ĵ�һ�ֳ��У�
//����������ȴ���ֱ���������ͷţ����߳��ٴα�Ϊ�����л�����Ϊֹ��

public class MyRunnable implements Runnable{
    private Foo foo = new Foo();

    public static void main(String[] args) {
        MyRunnable r = new MyRunnable();
        Thread ta = new Thread(r, "Thread-A");
        Thread tb = new Thread(r, "Thread-B");
        ta.start();
        tb.start();
    }

    //ûͬ���ķ������������������
    public  void  run() {
        for (int i = 0; i < 3; i++) {
        	this.fix(30);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : ��ǰfoo�����xֵ= " + foo.getX());

        }
    }

    //ͬ���ķ���������ȷ������
//    public  void  run() {
//        for (int i = 0; i < 3; i++) {
//        	synchronized(this) {
//        		this.fix(30);
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + " : ��ǰfoo�����xֵ= " + foo.getX());
//
//        	}
//        }
//    }
    
    
    public int fix(int y) {
        return foo.fix(y);
    } 
}
