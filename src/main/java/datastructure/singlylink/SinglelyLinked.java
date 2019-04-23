package datastructure.singlylink;

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

    public void init(String string, SinglelyLinked<String> link) {
        for (String s : string.split("")) {
            link.add(s);
        }
    }

    /*add new node to the last of the link*/
    public void add(T object) {
        SinglelyNode<T> newNode = new SinglelyNode<>(object,null);

        SinglelyNode<T> last = head;
        int count = 0;
        while (count < size && last.getNext() != null) {
            last = last.getNext();
            count++;
        }
        last.setNext(newNode);
        size++;
    }

    /*add new node to the specifided place*/
    public void add(T object, int index) {
        SinglelyNode<T> newNode = new SinglelyNode<>(object, null);

        int count = 0;
        SinglelyNode<T> prev = head;
        SinglelyNode<T> curr = head.getNext();
        while (count < size && curr != null) {
            if (count == index) {
                newNode.setNext(curr);
                prev.setNext(newNode);
                break;
            } else {
                prev = curr;
                curr = curr.getNext();
            }
            count++;
        }
        size++;
    }

    public void add(SinglelyNode<T> newNode) {
        SinglelyNode<T> last = head;
        int count = 0;
        while (count < size && last.getNext() != null) {
            last = last.getNext();
            count++;
        }
        last.setNext(newNode);
        size++;
    }

    /*delete the last node of the link*/
    public void delete() {
        SinglelyNode<T> cur = head;
        while (cur.getNext().getNext() != null && cur.getNext() != null) {
            cur = cur.getNext();
        }
        cur.setNext(null);
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
        while (count < size && curr != null) {
            if (curr.getData().equals(object)) {
                prev.setNext(curr.getNext());
                break;
            } else {
                prev = curr;
                curr = curr.getNext();
            }
            count++;
        }
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

    public SinglelyNode<T> getHead() {
        return head;
    }

    public void setHead(SinglelyNode<T> head) {
        this.head = head;
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
