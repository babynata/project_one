package datastructure.queue;

public class ArrayQueue {

    public String[] array;

    public int count;

    public int head;

    public int n;

    public ArrayQueue(int n) {
        this.n = n;
        array = new String[n];
        count = 0;
        head = 0;
    }

    public boolean enQueue(String s) {
        if(count==n) return false;
        array[count] = s;
        count++;
        return true;
    }

    public void printArray() {
        int i = head;
        while (i < count) {
            System.out.println(array[i]);
            i++;
        }
    }

    public String deQueue() {
        if(count==0) return null;
        String t = array[head];
        head++;
        return t;
    }

    public static void main(String[] args){
        ArrayQueue arrayQueue = new ArrayQueue(10);
        arrayQueue.enQueue("a");
        arrayQueue.enQueue("b");
        arrayQueue.enQueue("c");
        arrayQueue.enQueue("d");
        System.out.println(arrayQueue.deQueue());
        System.out.println(arrayQueue.deQueue());
        arrayQueue.enQueue("a");
        arrayQueue.enQueue("b");
        System.out.println("------");
        arrayQueue.printArray();
    }
}
