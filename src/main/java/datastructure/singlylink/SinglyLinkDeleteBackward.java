package datastructure.singlylink;

public class SinglyLinkDeleteBackward {

    public void delete(int backward, SinglelyLinked<String> linked) {
        SinglelyNode<String> fast = linked.getHead();
        SinglelyNode<String> slow = linked.getHead();

        int count = 0;
        while (fast.getNext() != null) {
            fast = fast.getNext();
            if (count >= backward) {
                slow = slow.getNext();
            }
            count++;
        }

        linked.delete((String) slow.getNext().getData());
    }

    public static void main(String[] args){
        SinglelyLinked<String> link = new SinglelyLinked<>();
        link.init("abcdefg",link);
        SinglyLinkDeleteBackward singlyLinkDeleteBackward = new SinglyLinkDeleteBackward();
        singlyLinkDeleteBackward.delete(2,link);
        link.printLink();
    }
}
