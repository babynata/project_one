package interview.collections;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest implements Runnable{

    private static final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    private String[] names;

    public CopyOnWriteArrayListTest(String[] names) {
        this.names = names;
    }

    public void add(String s) {
        list.add(s);
    }

    public boolean contains(String s) {
        return list.contains(s);
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new CopyOnWriteArrayListTest(new String[]{"Jenny"}),"Thread-0").start();
        Thread.sleep(2000);
        new Thread(new CopyOnWriteArrayListTest(new String[]{"Natalie"}),"Thread-1").start();
    }

    @Override
    public void run() {
        if (names.length > 0) {
            for (String name : names) {
                add(name);
            }
        }
        while (true) {
            if (contains("Natalie")) {
                System.out.println(Thread.currentThread().getName() + " has Natalie");
            }
        }
    }
}
