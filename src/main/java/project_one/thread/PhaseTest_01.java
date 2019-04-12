package project_one.thread;

import java.util.concurrent.Phaser;

public class PhaseTest_01 {

    static class Task implements Runnable {

        private Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" 's task was completed.Waiting for others...");

            phaser.arriveAndAwaitAdvance();

            System.out.println(Thread.currentThread().getName()+" get to work continuely...");
        }
    }

    public static void main(String[] args){
        Phaser phaser = new Phaser(5);

        for (int i=0;i<5;i++) {
            Task task = new Task(phaser);
            new Thread(task).start();
        }
    }
}
