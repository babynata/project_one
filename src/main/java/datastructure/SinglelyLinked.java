package datastructure;

public class SinglelyLinked<T> {

    private SinglelyNode<T> head;

    private int size;

    public SinglelyLinked() {
        head = new SinglelyNode(null, null);
        size = 0;
    }

    public SinglelyLinked(T object) {
        head = new SinglelyNode(null, null);
        SinglelyNode<T> newNode = new SinglelyNode<>(object, null);
        head.setNext(newNode);
        size = 1;
    }

    /*add new node to the last of the link*/
    public void add(T object) {
        SinglelyNode<T> newNode = new SinglelyNode<>(object,null);

        SinglelyNode<T> last = head;
        int count = 0;
        do {
            last = last.getNext();
            count++;
        } while (count < size);
        last.setNext(newNode);
        size++;
    }

    /*add new node to the specifided place*/
    public void add(T object, int index) {
        SinglelyNode<T> newNode = new SinglelyNode<>(object, null);

        int count = 0;
        SinglelyNode<T> prev = head;
        SinglelyNode<T> curr = head.getNext();
        do {
            if (count == index) {
                newNode.setNext(curr);
                prev.setNext(newNode);
                break;
            } else {
                prev = curr;
                curr = curr.getNext();
            }
            count++;
        } while (count < size);
        size++;
    }

    /*delete the last node of the link*/
    public void delete() {
        int count = 0;
        SinglelyNode<T> prev = head;
        SinglelyNode<T> curr = head.getNext();
        do {
            prev = curr;
            curr = curr.getNext();
            count++;
        } while (count < size);
        prev.setNext(null);
        size--;
    }

    /*delete the specified node*/
    public void delete(int index) {
        int count = 0;
        SinglelyNode<T> prev = head;
        SinglelyNode<T> curr = head.getNext();
        while (count != index && count < size) {
            prev = curr;
            curr = curr.getNext();
            count++;
        }
        prev.setNext(curr.getNext());
        size--;
    }

    public void delete(T object) {
        int count = 0;
        SinglelyNode<T> prev = head;
        SinglelyNode<T> curr = head.getNext();
        do {
            if (curr.getData().equals(object)) {
                prev.setNext(curr.getNext());
                break;
            } else {
                prev = curr;
                curr = curr.getNext();
            }
            count++;
        } while (count < size);
        size--;
    }


    public void printLink() {
        int count = 0;
        SinglelyNode<T> next = head;
        System.out.println("the content of link:");
        do {
            next = next.getNext();
            System.out.println(next.getData());
            count++;
        } while (count < size);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static void main(String[] args){
//        SinglelyLinked<String> strLink = new SinglelyLinked<>();
        SinglelyLinked<String> strLink = new SinglelyLinked<>("Jenny");
        strLink.add("Natalie");
        strLink.add("Janice",0);
        strLink.add("Atom",1);
        strLink.printLink();
        strLink.delete("Atom");
        strLink.printLink();
        strLink.delete(1);
        strLink.printLink();
        strLink.delete();
        strLink.printLink();
    }
}
