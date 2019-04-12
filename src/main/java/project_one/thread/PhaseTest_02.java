package project_one.thread;

import java.util.concurrent.Phaser;

public class PhaseTest_02 {

    public static void main(String[] args) throws InterruptedException {

        Phaser phaser = new Phaser(1);

        for(int i=0;i<3;i++) {
            Task task = new Task(phaser);
            new Thread(task).start();
        }

        Thread.sleep(3000);

        System.out.println("main threads completed.Others start!");

        phaser.arrive();
    }

    static class Task implements Runnable {

        private Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {

            phaser.awaitAdvance(phaser.getPhase());

            System.out.println(Thread.currentThread().getName()+" starts to work...");
        }
    }
}
