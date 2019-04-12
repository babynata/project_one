package project_one.thread;

import java.util.ArrayList;
import java.util.List;

public class TestThreadLocal {

    public volatile List<String> list=new ArrayList<String>();

    public static ThreadLocal<List<String>> threadLocal = new ThreadLocal<List<String>>();

    public List<String> getList() {
        return list;
    }

    public void setThreadLocal(List<String> list) {
        threadLocal.set(list);
    }

    public void getValue() {
        System.out.println(Thread.currentThread().getName());
        threadLocal.get().forEach(name -> System.out.println(name));
    }

    public static void main(String[] args){

        final TestThreadLocal testThreadLocal=new TestThreadLocal();

        new Thread(() -> {
            List<String> list=testThreadLocal.getList();
            list.add("Janice");
            testThreadLocal.setThreadLocal(list);
            testThreadLocal.getValue();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            testThreadLocal.getValue();;
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<String> list=testThreadLocal.getList();
            list.add("Natalie");
            list.add("Atom");
            testThreadLocal.setThreadLocal(list);
            testThreadLocal.getValue();
        }).start();

    }


}
