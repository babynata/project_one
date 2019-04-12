package project_one.thread;

public class MainThreadCount {

    public static void main(String[] args){
        System.out.println("Hello World");
        ThreadGroup group=Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup=group;
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        int nowThreads=topGroup.activeCount();
        Thread[] threads = new Thread[nowThreads];
        topGroup.enumerate(threads);
        for (int i=0;i<nowThreads;i++) {
            System.out.println(threads[i].getName());
        }
    }
}
