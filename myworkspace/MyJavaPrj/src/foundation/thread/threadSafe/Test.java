package foundation.thread.threadSafe;

public class Test {
    public static void main(String[] args) {
        final NameList nl = new NameList();
        nl.add("aaa");
        class NameDropper extends Thread{			//main()�����а����ڲ���
            public void run(){
                String name = nl.removeFirst();
                System.out.println(name);
            }
        }

        Thread t1 = new NameDropper();
        Thread t2 = new NameDropper();
        t1.start();
        t2.start();
    }
}
