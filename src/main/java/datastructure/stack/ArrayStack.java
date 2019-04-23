package datastructure.stack;

public class ArrayStack {

    public String[] array;

    public int count;

    public int n;

    public ArrayStack(int n){
        array = new String[n];
        this.n = n;
        count = 0;
    }

    public boolean push(String a) {
        if (count == n) {
            return false;
        }
        array[count] = a;
        count++;
        return true;
    }

    public String pop() {
        if (count == 0) {
            return null;
        }
        String t = array[count];
        count--;
        return t;
    }

    public void printArray() {
        int i = 0;
        while (i < count) {
            System.out.println(array[i]);
            i++;
        }
    }

    public static void main(String[] args){
        ArrayStack arrayStack = new ArrayStack(10);
        arrayStack.push("a");
        arrayStack.push("b");
        arrayStack.push("c");
        arrayStack.push("d");
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.printArray();
    }
}
