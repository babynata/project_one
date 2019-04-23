package datastructure.singlylink;

public class SinglyLinkReverse {

    private SinglelyLinked<String> link;

    public void init(SinglelyLinked<String> link) {
        this.link = link;
    }

    public void reverse() {
        SinglelyNode<String> head = link.getHead();
        SinglelyNode<String> temp = link.getHead().getNext();
        SinglelyNode<String> tail = link.getHead().getNext();
        SinglelyNode<String> cur = link.getHead().getNext();
        SinglelyNode<String> next = link.getHead().getNext().getNext();
        while (next != null) {
            temp = next.getNext();
            next.setNext(cur);
            cur = next;
            next = temp;
        }
        tail.setNext(null);
        head.setNext(cur);
    }

    public static void main(String[] args){
        SinglyLinkReverse singlyLinkReverse = new SinglyLinkReverse();
        SinglelyLinked<String> link = new SinglelyLinked<>();
        link.init("abcde",link);
        singlyLinkReverse.init(link);
        singlyLinkReverse.reverse();
        link.printLink();
    }
}
