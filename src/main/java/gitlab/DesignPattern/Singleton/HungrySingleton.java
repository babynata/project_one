package gitlab.DesignPattern.Singleton;

import java.util.concurrent.CountDownLatch;

public class HungrySingleton {

    private static final HungrySingleton hungry = new HungrySingleton();

    public static HungrySingleton getInstance() {
        System.out.println(System.currentTimeMillis()+" : "+hungry);
        return hungry;
    }

    public static void main(String[] args){

        int count=100;

        CountDownLatch cdl = new CountDownLatch(count);

        for (int i=0;i<count;i++) {
            new Thread(()->{
                HungrySingleton.getInstance();
                cdl.countDown();
            }).start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}