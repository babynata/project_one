package datastructure;

public class SinglelyNode<T> {

    private T data;

    private SinglelyNode next;

    public SinglelyNode(T data, SinglelyNode next) {
        this.data = data;
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public SinglelyNode getNext() {
        return next;
    }

    public void setNext(SinglelyNode next) {
        this.next = next;
    }
}
