package project_one.thread;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable{

    private String name;

    private CountDownLatch countDownLatch;

    public Worker(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    public void run() {

        System.out.println(name+" is working!");

        countDownLatch.countDown();

        System.out.println(name+" is done!");
    }
}
