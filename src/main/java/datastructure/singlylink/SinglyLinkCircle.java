package datastructure.singlylink;

public class SinglyLinkCircle {

    private SinglelyLinked<String> link;

    public boolean ifContainsCircle() {
        if(link==null) return false;
        if (link.getHead().getNext() == null || link.getHead().getNext().getNext() == null) {
            return false;
        }

        SinglelyNode<String> slow = link.getHead();
        SinglelyNode<String> fast = link.getHead().getNext();

        while (fast != null && fast.getNext() != null) {
            if (fast.getData().equals(slow.getData())) {
                return true;
            }
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return false;
    }

    public void init(SinglelyLinked<String> link) {
        this.link = link;
    }

    public static void main(String[] args){
        SinglyLinkCircle singlyLinkCircle = new SinglyLinkCircle();
        SinglelyLinked<String> link = new SinglelyLinked<>();
        link.add("A");
        link.add("B");
        link.add("C");
        link.add("D");
        link.add("B");
        link.add("C");
        link.add("D");
        singlyLinkCircle.init(link);
        System.out.println(singlyLinkCircle.ifContainsCircle());
    }
}
