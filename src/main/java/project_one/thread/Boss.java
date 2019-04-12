package project_one.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Boss implements Runnable {

    private CountDownLatch countDownLatch;

    public Boss(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void run() {

        System.out.println("Boss is waiting...");

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Boss is checking...");
    }

    public static void main(String[] args){
        ExecutorService executorService= Executors.newCachedThreadPool();
        CountDownLatch countDownLatch=new CountDownLatch(3);
        executorService.execute(new Boss(countDownLatch));
        executorService.execute(new Worker("1",countDownLatch));
        executorService.execute(new Worker("2",countDownLatch));
        executorService.execute(new Worker("3",countDownLatch));
    }
}
