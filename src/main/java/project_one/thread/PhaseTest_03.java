package project_one.thread;

import java.util.concurrent.Phaser;

public class PhaseTest_03 {

    public static void main(String[] args){
        Phaser phaser = new Phaser(3){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println(Thread.currentThread().getName()+" executing onAdvance method...phase:[" + phase + "],registeredParties:[" + registeredParties + "]");
                return phase==3;
            };
        };

        for (int i = 0; i < 3; i++) {
            Task task = new Task(phaser);
            new Thread(task).start();
        }
    }

    static class Task implements Runnable {

        private Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {

            do {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()+" starts to work...");

                phaser.arriveAndAwaitAdvance();

            } while (!phaser.isTerminated());
        }
    }

}
