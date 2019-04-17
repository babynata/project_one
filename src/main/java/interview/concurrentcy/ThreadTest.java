package interview.concurrentcy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest implements Runnable{

    private static int a = 0;

    private int threadNo=0;

    private int threadCount = 0;

    private int max = 0;

    private static final Object LOCK = new Object();

    public ThreadTest(int threadNo, int threadCount, int max) {
        this.threadNo = threadNo;
        this.threadCount = threadCount;
        this.max = max;
    }

    public static void main(String[] args){
        for(int i=0;i<3;i++) {
            new Thread(new ThreadTest(i,3,100),"Thread-"+i).start();
        }
    }

    @Override
    public void run() {

        while (true) {
            synchronized (LOCK) {
                while (a % threadCount != threadNo) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (a < max) {
                    System.out.println(Thread.currentThread().getName() + ":" + a);
                    ++a;
                }

                LOCK.notifyAll();
            }

        }

    }
}
