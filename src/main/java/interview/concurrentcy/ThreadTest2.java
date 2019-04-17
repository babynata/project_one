package interview.concurrentcy;

public class ThreadTest2 implements Runnable {

    private int threadNo = 0;

    private int a = 0;

    private static final Object LOCK = new Object();

    public static void main(String[] args){
        for (int i = 0; i < 2; i++) {
            new Thread(new ThreadTest2()).start();
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized (LOCK) {
                if (a < 100) {
                    if (a % 2 == 0) {
                        System.out.print("偶线程：");
                    } else {
                        System.out.print("奇线程：");
                    }
                    System.out.println(a);
                    ++a;
                }
            }
        }
    }
}
